package com.business.coffeshop.controller;

import com.business.coffeshop.dto.RegisterAccountDto;
import com.business.coffeshop.service.AccountService;
import com.business.coffeshop.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("business-coffeeshop/account")
@RequiredArgsConstructor
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("customer-login")
    public String showLoginForm() {
        return "account/customer/customer-login";
    }

    @PostMapping("customer-login")
    public String login(@RequestParam String msisdn, @RequestParam String password, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(msisdn, password)
            );
            String token = jwtUtil.generateToken(authentication);
            model.addAttribute("jwtToken", token);

            // ✅ Điều hướng dựa vào role
            return "redirect:business-coffeeshop/product/customer-product-list";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid credentials!");
            return "account/customer/customer-login";
        }
    }

    @GetMapping("/customer-register")
    public String showCustomerRegisterForm(Model model) {
        model.addAttribute("registerAccount", new RegisterAccountDto());
        return "account/customer/customer-register";
    }
    @PostMapping("/customer-register")
    public String registerCustomerAccount(@Valid @ModelAttribute("registerAccount") RegisterAccountDto registerAccount, Model model) {
        model.addAttribute("registerAccount", registerAccount);
        try {
            accountService.registerCustomerAccount(registerAccount);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
        }
        return "account/customer/customer-register";
    }
}
