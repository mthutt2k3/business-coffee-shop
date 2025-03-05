package com.business.coffeshop.entity;


import jakarta.persistence.*;
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    private int quantity;
    private double unitPrice;
    private double totalPrice;
}
