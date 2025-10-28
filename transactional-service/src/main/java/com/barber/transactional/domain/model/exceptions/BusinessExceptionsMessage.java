package com.barber.transactional.domain.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessExceptionsMessage {

    TRANSACTION_NOT_SUPPORTED("MST0001","Transaction isn't supported"),
    INVALID_EMPLOYED("MSE_0002","the employed is not valid valid"),
    INVALID_CLIENT("MSC_0003","the client is not valid valid");


    private final String code;
    private final String message;
}
