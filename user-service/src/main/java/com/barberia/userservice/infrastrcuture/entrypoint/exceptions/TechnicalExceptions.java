package com.barberia.userservice.infrastrcuture.entrypoint.exceptions;

import lombok.Getter;

@Getter
public class TechnicalExceptions extends RuntimeException{
    private final TechnicalExceptionsMessage technicalExceptionsMessage;

    public TechnicalExceptions(TechnicalExceptionsMessage technicalExceptionsMessage) {
        super(technicalExceptionsMessage.getMessage());
        this.technicalExceptionsMessage = technicalExceptionsMessage;
    }
}
