package com.barberia.employedservice.infrastructure.drivenadapter.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployedDocument {
    @Id
    private String id;
    private String name;
    private String document;
    private String email;
    private String cellphone;
    private String userId;
    private Date birthdate;
}
