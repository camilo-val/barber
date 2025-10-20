package com.barberia.userservice.infrastrcuture.drivenadapter.document;

import com.barberia.userservice.domain.model.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String password;
    private String status;
    private Role role;
    private Date createAt;
    private Date updateAt;
}
