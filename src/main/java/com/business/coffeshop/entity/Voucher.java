package com.business.coffeshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "voucher")
public class Voucher extends BaseEntity {
    @Column(name = "voucher_code", nullable = false, unique = true, length = 50)
    private String voucherCode;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private double coin;
    
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    
    private Date startedDate;
    private Date expirationDate;
}