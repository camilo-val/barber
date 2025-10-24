package com.clientservice.domain.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class User {
    private String id;
    private String username;
    private String password;
    private String status;
    private String role;
    private Date createAt;
    private Date updateAt;

    public User(String id, String username, String password, String status, String role, Date createAt, Date updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public User(String id, String username, String password, String role, Date createAt, Date updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}