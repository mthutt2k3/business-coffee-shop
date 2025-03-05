package com.business.coffeshop.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer-product-list.html")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;
    
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
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

    @Column(name = "size", columnDefinition = "TEXT")
    private String size;  // Kích thước
}
