package com.business.coffeshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders extends BaseEntity {
    @Column(name = "order_code", nullable = false, unique = true, length = 50)
    private String orderCode;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Account customer;
    
    private Date orderTime;
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
    
    private double totalDiscount;
    private double totalFee;
    private double totalPrice;
    private double grandTotal;
}
