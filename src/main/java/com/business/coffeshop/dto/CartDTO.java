package com.business.coffeshop.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO {
    private Long id;
    private Long accountId;  // ✅ Sửa lại từ acocuntId -> accountId
    private Long productId;
    private String productName; // ✅ Thêm productName để tránh lỗi
    private int quantity;
    private String productImage; // Thêm ảnh sản phẩm
    private double price;
}
