package com.barberia.userservice.infrastrcuture.entrypoint.dto;

import com.barberia.userservice.domain.model.enums.Role;
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
    private Role role;
    private Date createAt;
    private Date updateAt;
}
