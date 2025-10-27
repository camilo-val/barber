package com.barber.productservice.domain.model.exceptions;

import lombok.Getter;

@Getter
public class BusinessExceptions extends RuntimeException{
    private final BusinessMessageExceptions businessMessageExceptions;

    public BusinessExceptions(BusinessMessageExceptions businessMessageExceptions) {
        super(businessMessageExceptions.getMessage());
        this.businessMessageExceptions = businessMessageExceptions;
    }

    public BusinessExceptions(BusinessMessageExceptions businessMessageExceptions, Throwable cause) {
        super(businessMessageExceptions.getMessage(), cause);
        this.businessMessageExceptions = businessMessageExceptions;
    }
}
