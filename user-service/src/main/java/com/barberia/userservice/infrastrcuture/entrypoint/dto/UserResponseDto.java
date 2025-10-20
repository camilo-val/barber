package com.barberia.userservice.infrastrcuture.entrypoint.dto;

import com.barberia.userservice.domain.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.util.Date;

@Builder(toBuilder = true)
public class UserResponseDto {
    private String id;
    private String username;
    @JsonIgnore
    private String password;
    private String status;
    private Role role;
    private Date createAt;
    private Date updateAt;
}
