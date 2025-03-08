package com.business.coffeshop.dto;

import com.business.coffeshop.entity.Account;
import com.business.coffeshop.entity.Category;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketerProductDto {
    private Long id;

    private Account account;

    private Category category;

    private String productName;

    private String description;

    private String imageUrl;

    private String status;

    private String originalPrice;  // Giá gốc

    private String sellingPrice;  // Giá bán

    private Date createdAt;  // Giá bán

    private String createBy;  // Giá bán

    private Date updatedAt;  // Giá bán

    private String updatedBy;  // Giá bán
}
