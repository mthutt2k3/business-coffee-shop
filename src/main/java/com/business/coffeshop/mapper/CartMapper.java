package com.business.coffeshop.mapper;

import com.business.coffeshop.dto.CartDTO;
import com.business.coffeshop.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class CartMapper {

    @Mappings({
            @Mapping(source = "accountId", target = "account.id"),  // ✅ Đúng tên biến
            @Mapping(source = "productId", target = "product.id"),
            @Mapping(source = "productName", target = "product.productName") // ✅ Thêm ánh xạ productName

    })
    public abstract Cart toCartEntity(CartDTO cartDto);

    @Mappings({
            @Mapping(source = "account.id", target = "accountId"),
            @Mapping(source = "product.id", target = "productId"),
            @Mapping(source = "product.productName", target = "productName"), // ✅ Thêm ánh xạ productName
            @Mapping(source = "product.imageUrl", target = "productImage"), // ✅ Thêm ảnh
            @Mapping(source = "product.sellingPrice", target = "price")
    })
    public abstract CartDTO toCartDto(Cart cart);
}
