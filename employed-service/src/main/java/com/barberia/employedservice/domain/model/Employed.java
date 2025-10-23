package com.barberia.employedservice.domain.model;

import com.barberia.employedservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.employedservice.domain.model.exceptions.BusinessExceptionsMessages;
import lombok.Getter;

import java.util.Date;

@Getter
public class Employed {
    private String id;
    private String name;
    private String document;
    private String email;
    private String cellphone;
    private String userId;
    private Date birthdate;

    private Employed(String id, String name, String document, String email, String cellphone, String userId, Date birthdate) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.cellphone = cellphone;
        this.userId = userId;
        this.birthdate = birthdate;
    }

    public static Employed createEmployed(String id, String name, String document, String email, String cellphone, String userId, Date birthdate){
        if (document == null || document.isBlank() || userId == null){
            throw new BusinessExceptions(BusinessExceptionsMessages.INVALID_CLIENT);
        }
        return new Employed(id,name,document,email,cellphone,userId,birthdate);
    }
}
