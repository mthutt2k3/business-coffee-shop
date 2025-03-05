package com.business.coffeshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String msisdn;       // Số điện thoại đăng ký
    private String password;     // Mật khẩu
    private String accountName;  // Tên tài khoản
    private String address;      // Địa chỉ (tùy chọn)
    private String imageUrl;     // Ảnh đại diện (tùy chọn)
}
