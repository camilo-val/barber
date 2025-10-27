package com.barber.productservice.infrastructure.entrypoint.reactiveweb.exception;

import lombok.Getter;

@Getter
public class TechnicalExceptions extends RuntimeException{
    private final TechnicalExceptionsMessage technicalExceptionsMessage;

    public TechnicalExceptions(TechnicalExceptionsMessage technicalExceptionsMessage) {
        super(technicalExceptionsMessage.getMessage());
        this.technicalExceptionsMessage = technicalExceptionsMessage;
    }

    public TechnicalExceptions(TechnicalExceptionsMessage technicalExceptionsMessage, Throwable cause) {
        super(technicalExceptionsMessage.getMessage(), cause);
        this.technicalExceptionsMessage = technicalExceptionsMessage;
    }
}
