package com.business.coffeshop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private String productCount;

}
