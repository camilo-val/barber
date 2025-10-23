package com.barberia.userservice.domain.model.exceptions;

import com.barberia.userservice.domain.model.exceptions.message.BusinessMessageException;
import lombok.Getter;

@Getter
public class BusinessExceptions extends RuntimeException{
    private final BusinessMessageException businessMessageException;

    public BusinessExceptions(BusinessMessageException message) {
        super(message.getMessage());
        this.businessMessageException = message;
    }
}
