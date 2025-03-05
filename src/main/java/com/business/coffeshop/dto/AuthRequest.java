package com.business.coffeshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String msisdn;  // Số điện thoại đăng nhập
    private String password; // Mật khẩu đăng nhập
}
