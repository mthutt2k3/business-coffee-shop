package com.business.coffeshop.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;
}