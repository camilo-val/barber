package com.clientservice.domain.model.exceptions;

import com.clientservice.domain.model.exceptions.message.BusinessMessageException;

public class BusinessException extends RuntimeException{

    private final BusinessMessageException businessMessageException;

    public BusinessException(BusinessMessageException businessMessageException) {
        super(businessMessageException.getMessage());
        this.businessMessageException = businessMessageException;
    }
}
