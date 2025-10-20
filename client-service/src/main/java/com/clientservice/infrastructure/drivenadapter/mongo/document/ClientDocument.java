package com.clientservice.infrastructure.drivenadapter.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "client")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ClientDocument {
    @Id
    private String id;
    private String name;
    private String email;
    private String document;
    private String userId;
    private String cellphone;
    private Date birthdate;
}
