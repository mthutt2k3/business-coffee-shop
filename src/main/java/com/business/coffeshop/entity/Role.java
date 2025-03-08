package com.business.coffeshop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;
}