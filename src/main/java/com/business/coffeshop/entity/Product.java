package com.business.coffeshop.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    @Column(name = "productName", nullable = false, length = 100)
    private String productName;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    
    @Column(nullable = false)
    private String status;

    @Column(name = "original_price", nullable = false)
    private Double originalPrice;  // Giá gốc

    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;  // Giá bán

}
