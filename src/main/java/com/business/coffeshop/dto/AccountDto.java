package com.business.coffeshop.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long id;

    private String roleCode;

    private String msisdn;

    private String password;

    private String accountName;

    private String address;

    private String imageUrl;

    private String status;
}
