package com.clientservice.infrastructure.entrypoint.reactiveweb.exceptions;

import lombok.Getter;

@Getter
public class TechnicalExceptions extends RuntimeException{
    private final TechnicalExceptionsMessage technicalExceptionsMessage;

    public TechnicalExceptions(TechnicalExceptionsMessage technicalExceptionsMessage) {
        super(technicalExceptionsMessage.getMessage());
        this.technicalExceptionsMessage = technicalExceptionsMessage;
    }
}
