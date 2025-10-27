package com.barber.productservice.infrastructure.entrypoint.reactiveweb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TechnicalExceptionsMessage {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "TMP_999", "internal server error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "TMP_998", "invalid request"),
    TIME_OUT(HttpStatus.REQUEST_TIMEOUT, "TMP_997", "timeout"),
    UNKNOWN(HttpStatus.REQUEST_TIMEOUT, "TMP_998", "unknown error");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
