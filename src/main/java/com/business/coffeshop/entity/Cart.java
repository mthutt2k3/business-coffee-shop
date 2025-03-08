package com.business.coffeshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    private int quantity;

}
