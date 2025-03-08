package com.business.coffeshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account extends BaseEntity {

    private String roleCode;
    
    private String msisdn;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "account_name", nullable = false, length = 100)
    private String accountName;
    
    @Column(columnDefinition = "TEXT")
    private String address;
    
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
}