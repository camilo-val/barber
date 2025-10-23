package com.barberia.employedservice.infrastructure.drivenadapter.restconsumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@ToString
public class UserResponseDto {
    private String id;
    private String username;
    @JsonIgnore
    private String password;
    private String status;
    private String role;
    private Date createAt;
    private Date updateAt;
}
