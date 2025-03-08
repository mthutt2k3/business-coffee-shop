package com.business.coffeshop.controller;

import com.business.coffeshop.service.AccountService;
import com.business.coffeshop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/${application-context-name}/private/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "account/customer-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String msisdn, @RequestParam String password, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(msisdn, password)
            );
            String token = jwtUtil.generateToken(authentication);
            model.addAttribute("jwtToken", token);

            // ✅ Lấy role của user từ authentication
            String role = authentication.getAuthorities().iterator().next().getAuthority();

            // ✅ Điều hướng dựa vào role
            if ("ROLE_ADMIN".equals(role)) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/customer/products";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Invalid credentials!");
            return "account/login";
        }
    }

}
