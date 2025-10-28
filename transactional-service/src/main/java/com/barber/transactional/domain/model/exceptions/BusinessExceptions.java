package com.barber.transactional.domain.model.exceptions;

import lombok.Getter;

@Getter
public class BusinessExceptions extends RuntimeException {

    private final BusinessExceptionsMessage businessExceptionsMessage;

    public BusinessExceptions (BusinessExceptionsMessage businessExceptionsMessage){
        super(businessExceptionsMessage.getMessage());
        this.businessExceptionsMessage = businessExceptionsMessage;
    }

    public BusinessExceptions (BusinessExceptionsMessage businessExceptionsMessage, Throwable cause){
        super(businessExceptionsMessage.getMessage(), cause);
        this.businessExceptionsMessage = businessExceptionsMessage;
    }
}
