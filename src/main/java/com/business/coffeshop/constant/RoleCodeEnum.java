package com.business.coffeshop.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleCodeEnum {
    AD("Admin"),
    MAR("Marketer"),
    SAL("Sale"),
    CUST("Customer");

    private final String roleName;
}
