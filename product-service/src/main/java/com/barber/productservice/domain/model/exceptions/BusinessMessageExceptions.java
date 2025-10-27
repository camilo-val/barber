package com.barber.productservice.domain.model.exceptions;

import lombok.Getter;

@Getter
public enum BusinessMessageExceptions {

    INVALID_PRODUCT("MSP0001","invalid product"),
    PRODUCT_NOT_EXIST("MSP0002","the product not exist"),
    PRODUCT_EXIST("MSP0003","the product exist");

    private final String code;
    private final String message;

    BusinessMessageExceptions(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
