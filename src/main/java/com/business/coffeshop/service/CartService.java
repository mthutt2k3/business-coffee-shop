package com.business.coffeshop.service;

import com.business.coffeshop.dto.CartDTO;

import java.util.List;

public interface CartService {
    CartDTO addToCart(CartDTO cartDto);        // Thêm sản phẩm vào giỏ hàng
    void removeFromCart(Long cartId);          // Xóa sản phẩm khỏi giỏ hàng
    List<CartDTO> getCartByAccountId(Long accountId);  // Lấy danh sách giỏ hàng theo người dùng
    void updateCartQuantity(Long cartId, int quantity); // Cập nhật số lượng sản phẩm
}
