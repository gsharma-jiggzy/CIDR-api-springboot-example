package com.agl.ipapi.controller;

import com.agl.ipapi.model.IpAddress;
import com.agl.ipapi.repository.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class IpAddressController {

  @Autowired
  private IpAddressRepository ipAddressRepository;

  @GetMapping("/list")
  public List<IpAddress> getAllIps() {
    return ipAddressRepository.findAll();
  }


  @PostMapping("/acquire")
  public ResponseEntity<IpAddress> acquire(@RequestBody String address) {
    try {
        IpAddress ipAdd =
            ipAddressRepository
                .findByAddressAndStatus(address, "available");
            ipAdd.setStatus("acquired");
            ipAddressRepository.save(ipAdd);
        return ResponseEntity.ok().body(ipAdd);
    }
    catch(Exception e) {
        return new ResponseEntity<IpAddress>( HttpStatus.BAD_REQUEST );
    }
  }

  @PostMapping("/release")
  public ResponseEntity<IpAddress> release(@RequestBody String address) {
    try {
        IpAddress ipAdd =
        ipAddressRepository
            .findByAddressAndStatus(address, "acquired");
        ipAdd.setStatus("available");
        ipAddressRepository.save(ipAdd);
        return ResponseEntity.ok().body(ipAdd);
    }
    catch (Exception e) {
        return new ResponseEntity<IpAddress>( HttpStatus.BAD_REQUEST );
    }
  }

  @PostMapping("/create")
  public ResponseEntity<IpAddress> createIps(@RequestBody String subnet) {
      try {
          SubnetUtils utils = new SubnetUtils(subnet);
            String[] addresses = utils.getInfo().getAllAddresses();
            for (String address : addresses) {
                IpAddress ipAddress = ipAddressRepository.findByAddress(address);
                if (ipAddress == null) {
                    ipAddress = new IpAddress();
                    ipAddress.setAddress(address);
                    ipAddress.setStatus("available");
                    ipAddress.setCreatedAt(new Date());
                    ipAddress.setUpdatedAt(new Date());
                    ipAddressRepository.save(ipAddress);
                }
            }
            return new ResponseEntity<IpAddress>( HttpStatus.ACCEPTED );
      }
      catch (Exception e) {
        return new ResponseEntity<IpAddress>( HttpStatus.BAD_REQUEST );
      }
  }

}