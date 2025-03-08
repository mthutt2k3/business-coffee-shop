package com.business.coffeshop.controller;

import com.business.coffeshop.dto.CartDTO;
import com.business.coffeshop.entity.Account;
import com.business.coffeshop.entity.OrderDetail;
import com.business.coffeshop.entity.Orders;
import com.business.coffeshop.service.AccountService;
import com.business.coffeshop.service.CartService;
import com.business.coffeshop.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CartService cartService;
    private final AccountService accountService;
    private final OrderServiceImpl orderServiceImpl;

    @GetMapping
    public String viewCheckout(Model model, Principal principal,
                               @RequestParam(value = "selectedItems", required = false) List<Long> selectedItems) {
        Long accountId = 4L; // Giả lập user, thay bằng user thực khi có auth

        // Lấy toàn bộ sản phẩm trong giỏ hàng
        List<CartDTO> cartItems = cartService.getCartByAccountId(accountId);

        // Nếu không có sản phẩm nào được chọn, trả về trang giỏ hàng
        if (selectedItems == null || selectedItems.isEmpty()) {
            return "redirect:/cart";
        }

        // Lọc danh sách sản phẩm được chọn
        List<CartDTO> selectedCartItems = cartItems.stream()
                .filter(item -> selectedItems.contains(item.getId()))
                .collect(Collectors.toList());

        // Tính tổng tiền
        double totalPrice = selectedCartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        model.addAttribute("cartItems", selectedCartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "cart/checkout"; // Hiển thị trang checkout
    }

    @PostMapping("confirm")
    public String confirmOrder(@RequestParam Long userId,
                               @RequestParam List<Long> selectedItems) {
        // Tạo đơn hàng mới
        Orders order = new Orders();
        order.setCustomer(new Account());
        order.setOrderTime(Date.from(Instant.now()));
        order.setStatus("Pending");

        // Lưu đơn hàng
//        Orders savedOrder = orderService.saveOrder(order);
//
//        // Lưu danh sách sản phẩm vào đơn hàng
//        List<OrderDetail> orderDetails = cartService.getCartByAccountId(userId)
//                .stream()
//                .filter(item -> selectedItems.contains(item.getId()))
//                .map(item -> new OrderDetail(null, savedOrder.getId(), item.getProductId(), item.getQuantity(), item.getPrice(), item.getPrice() * item.getQuantity()))
//                .collect(Collectors.toList());
//
//        orderService.saveOrderDetails(orderDetails);

        return "redirect:/order-success";
    }
}
