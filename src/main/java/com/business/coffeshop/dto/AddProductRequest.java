package com.business.coffeshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProductRequest {

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product description is required")
    private String description;

    @NotNull(message = "Product image is required")
    private MultipartFile imageFile;

    @Builder.Default
    private Boolean status = true;

    @NotNull(message = "Original Price is required")
    @Min(value = 1, message = "Original Price must be greater than 0")
    private Double originalPrice;  // Giá gốc

    @NotNull(message = "Selling Price is required")
    @Min(value = 1, message = "Selling Price must be greater than 0")
    private Double sellingPrice;  // Giá bán
}
