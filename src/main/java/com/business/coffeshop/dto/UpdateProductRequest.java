package com.business.coffeshop.dto;

import com.business.coffeshop.entity.Account;
import com.business.coffeshop.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {
    private Long id;

    private Account account;

    private Category category;

    private String productName;

    private String description;

    private String imageUrl;

    private String status;

    private String originalPrice;  // Giá gốc

    private String sellingPrice;  // Giá bán

}
