package com.clientservice.domain.model;

import com.clientservice.domain.model.exceptions.BusinessException;
import com.clientservice.domain.model.exceptions.message.BusinessMessageException;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class Client {
    private String id;
    private String name;
    private String email;
    private String document;
    private String cellphone;
    private String userId;
    private Date birthdate;

    public Client(String id, String name, String email, String document, String cellphone, String userId, Date birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.cellphone = cellphone;
        this.userId = userId;
        this.birthdate = birthdate;
    }

    public static Client newClient(String id, String name, String email, String document, String cellphone, String userId, Date birthdate){
        if ((document == null || document.isBlank())){
            throw new BusinessException(BusinessMessageException.CLIENT_INVALID);
        }
        return new Client(id, name, email, document, cellphone, userId, birthdate);
    }
}
