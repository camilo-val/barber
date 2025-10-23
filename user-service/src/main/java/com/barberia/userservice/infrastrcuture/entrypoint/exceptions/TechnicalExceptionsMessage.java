package com.barberia.userservice.infrastrcuture.entrypoint.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum TechnicalExceptionsMessage {
    INTERNAL_SERVER(HttpStatus.INTERNAL_SERVER_ERROR,"999","Internal server error"),
    NOT_FOUND(HttpStatus.NOT_FOUND,"404","The route does not exist or the query has no data."),
    TIME_OUT(HttpStatus.REQUEST_TIMEOUT,"999","Time out"),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR,"997","Unknown error");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
