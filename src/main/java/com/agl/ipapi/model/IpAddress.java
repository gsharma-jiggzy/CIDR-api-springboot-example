package com.agl.ipapi.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ipaddress")
@EntityListeners(AuditingEntityListener.class)
public class IpAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "address", nullable = false, unique=true)
    private String address;

    @Column(name = "status", nullable = false)
    private String status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

  public long getId() {
        return id;
    }

  public void setId(long id) {
        this.id = id;
    }

  public String getAddress() {
        return address;
    }

  public void setAddress(String address) {
        this.address = address;
    }

  public String getStatus() {
        return status;
    }

  public void setStatus(String status) {
        this.status = status;
    }

  public Date getCreatedAt() {
        return createdAt;
    }

  public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

  public Date getUpdatedAt() {
        return updatedAt;
    }

  public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


}