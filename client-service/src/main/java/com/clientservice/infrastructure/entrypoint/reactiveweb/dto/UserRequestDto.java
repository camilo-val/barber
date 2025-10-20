package com.clientservice.infrastructure.entrypoint.reactiveweb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@ToString
public class UserRequestDto {
    private String id;
    private String username;
    private String password;
    private String status;
    private String role;
    private Date createAt;
    private Date updateAt;
}
