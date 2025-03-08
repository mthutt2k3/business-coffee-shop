package com.business.coffeshop.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartRequestDTO {
    private Long accountId;
    private Long productId;
    private int quantity;
}
