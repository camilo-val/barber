package com.barberia.userservice.domain.model;

import com.barberia.userservice.domain.model.enums.Role;
import com.barberia.userservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.userservice.domain.model.exceptions.message.BusinessMessageException;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
public class User {
    private String id;
    private String username;
    private String password;
    private String status;
    private Role role;
    private Date createAt;
    private Date updateAt;

    private User(String id, String username, String password,
                 String status, Role role, Date createAt, Date updateAt){
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
        this.createAt = createAt;
        this.updateAt = updateAt;

    }

    private User(String id, String username, String password,
                 String status, Role role, Date updateAt){
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
        this.updateAt = updateAt;

    }

    public static User createUser(String id, String username, String password,
                                  String status, Role role, Date createAt){
        if(username == null || username.length() < 1){
            throw new BusinessExceptions(BusinessMessageException.INVALID_REQUEST);
        }
        return new User(id,username,password,status,role,createAt, null);

    }
    public static User updateUser(String id, String username, String password,
                                  String status, Role role, Date updateAt){
        if((id == null) || (username == null || username.length()< 1)){
            throw new BusinessExceptions(BusinessMessageException.INVALID_REQUEST);
        }
        return new User(id,username,password,status,role, updateAt);

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", role=" + role +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
