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

    public static String getRoleNameByCode(String roleCode) {
        for (RoleCodeEnum role : RoleCodeEnum.values()) {
            if (role.name().equalsIgnoreCase(roleCode)) {
                return role.roleName;
            }
        }
        return null; // or throw an exception if needed
    }
}
