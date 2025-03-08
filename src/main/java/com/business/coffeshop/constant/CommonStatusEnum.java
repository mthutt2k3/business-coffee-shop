package com.business.coffeshop.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonStatusEnum {
    ACT("Active"),
    IACT("InActive");

    private final String description;

}
