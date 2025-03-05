package com.business.coffeshop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {
    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
}