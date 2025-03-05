package com.business.coffeshop.controller;

import com.business.coffeshop.dto.AuthRequest;
import com.business.coffeshop.dto.AuthResponse;
import com.business.coffeshop.dto.RegisterRequest;
import com.business.coffeshop.entity.Account;
import com.business.coffeshop.service.AccountService;
import com.business.coffeshop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // ✅ API ĐĂNG NHẬP (Lấy JWT Token)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMsisdn(), request.getPassword())
        );

        String token = jwtUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // ✅ API ĐĂNG KÝ (Tạo tài khoản mới)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (accountService.existsByMsisdn(request.getMsisdn())) {
            return ResponseEntity.badRequest().body("Số điện thoại đã được đăng ký!");
        }

        Account newAccount = new Account();
        newAccount.setMsisdn(request.getMsisdn());
        newAccount.setPassword(passwordEncoder.encode(request.getPassword()));
        newAccount.setAccountName(request.getAccountName());
        newAccount.setAddress(request.getAddress());

        accountService.save(newAccount);
        return ResponseEntity.ok("Tài khoản đã được tạo thành công!");
    }
}
