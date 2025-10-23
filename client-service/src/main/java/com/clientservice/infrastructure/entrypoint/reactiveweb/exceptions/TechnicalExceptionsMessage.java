package com.clientservice.infrastructure.entrypoint.reactiveweb.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum TechnicalExceptionsMessage {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "999", "Invalid request"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "998", "internal server error"),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, "997", "unknown error"),
    EXTERNAL_ERROR_USER(HttpStatus.CONFLICT, "996","error consulting the user"),
    EXTERNAL_ERROR(HttpStatus.BAD_REQUEST, "995","invalid user");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
