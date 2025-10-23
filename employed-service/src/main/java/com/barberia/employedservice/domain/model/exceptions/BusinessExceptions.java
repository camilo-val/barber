package com.barberia.employedservice.domain.model.exceptions;

import lombok.Getter;

@Getter
public class BusinessExceptions extends RuntimeException{
    private final BusinessExceptionsMessages businessExceptionsMessages;

    public BusinessExceptions(BusinessExceptionsMessages businessExceptionsMessages){
        super(businessExceptionsMessages.getMessage());
        this.businessExceptionsMessages = businessExceptionsMessages;
    }

}
