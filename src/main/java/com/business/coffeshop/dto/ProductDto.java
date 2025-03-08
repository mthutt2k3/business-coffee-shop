package com.business.coffeshop.dto;

import com.business.coffeshop.entity.Account;
import com.business.coffeshop.entity.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private Long accountId;

    private String categoryName;

    private String productName;

    private String description;

    private List<String> imageUrls;

    private String status;

    private String originalPrice;  // Giá gốc

    private String sellingPrice;  // Giá bán

    private int discountPercentage;

}
