package com.barberia.employedservice.domain.model.exceptions;

import lombok.Getter;

@Getter
public enum BusinessExceptionsMessages {

    INVALID_CLIENT("CLE0000","the client have error"),
    CLIENT_NOT_EXIST("CLE001","the client not exist"),
    CLIENT_EXIST("CLE001","the client exist");

    private final String code;
    private final String message;

    BusinessExceptionsMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
