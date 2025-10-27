package com.clientservice.domain.model.exceptions.message;

import lombok.Getter;

@Getter
public enum BusinessMessageException {

    BAD_REQUEST("F0501","Invalid Request"),
    CLIENT_INVALID("F0501","The client isn't valid"),
    CLIENT_EXIST("F0502","The client exist"),
    CLIENT_NOT_EXIST("F0502","The client not exist");

    private final String code;
    private final String message;

    BusinessMessageException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
