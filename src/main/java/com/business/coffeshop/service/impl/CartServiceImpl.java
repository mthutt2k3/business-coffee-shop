package com.business.coffeshop.service.impl;


import com.business.coffeshop.dto.CartDTO;
import com.business.coffeshop.entity.Cart;
import com.business.coffeshop.entity.Account;
import com.business.coffeshop.entity.Product;
import com.business.coffeshop.mapper.CartMapper;
import com.business.coffeshop.repository.CartRepository;
import com.business.coffeshop.repository.AccountRepository;
import com.business.coffeshop.repository.ProductRepository;
import com.business.coffeshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final AccountServiceImpl accountServiceImpl;

    @Override
    public CartDTO addToCart(CartDTO cartDto) {
        Account account = accountRepository.findById(cartDto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Product product = productRepository.findById(cartDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Cart existingCart = cartRepository.findByAccountAndProduct(account, new Product());
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + cartDto.getQuantity());
            cartRepository.save(existingCart);
            return cartMapper.toCartDto(existingCart);
        }

        // Nếu chưa có, thêm mới vào giỏ hàng
        Cart cart = cartMapper.toCartEntity(cartDto);
        cart.setAccount(account);
        cart.setProduct(product);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toCartDto(savedCart);
    }

    @Override
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public List<CartDTO> getCartByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        List<Cart> cartList = cartRepository.findByAccount(account);
        return cartList.stream().map(cartMapper::toCartDto).collect(Collectors.toList());
    }

    @Override
    public void updateCartQuantity(Long cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }
}

