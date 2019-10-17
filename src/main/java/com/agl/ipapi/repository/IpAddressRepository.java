package com.agl.ipapi.repository;

import com.agl.ipapi.model.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, Long> {
    @Query("select u from IpAddress u where u.address = :address and u.status = :status")
    IpAddress findByAddressAndStatus(@Param("address") String address,
                                 @Param("status") String status);
    IpAddress findByAddress(@Param("address") String address);
}