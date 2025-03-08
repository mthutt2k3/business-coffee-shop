package com.business.coffeshop.controller;

import com.business.coffeshop.dto.CartDTO;
import com.business.coffeshop.entity.Account;
import com.business.coffeshop.service.AccountService;
import com.business.coffeshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final AccountService accountService;

    @GetMapping
    public String viewCart(Model model, Principal principal) {
//        Account account = accountService.getAccountByUsername(principal.getName());
        List<CartDTO> cartItems = cartService.getCartByAccountId(4L);
//        double totalPrice = cartItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", 100);
        return "cart/cart";
    }

    @PostMapping("add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, Principal principal) {
//        Account account = accountService.getAccountByUsername(principal.getName());

        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(productId);
        cartDTO.setQuantity(quantity);

        cartService.addToCart(cartDTO);
        return "redirect:/cart";
    }

    @PostMapping("update")
    public String updateCart(@RequestParam Long cartId, @RequestParam int quantity) {
        cartService.updateCartQuantity(cartId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("remove")
    public String removeCart(@RequestParam Long cartId) {
        cartService.removeFromCart(cartId);
        return "redirect:/cart";
    }
}
