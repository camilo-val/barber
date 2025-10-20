package com.clientservice.infrastructure.entrypoint.reactiveweb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@ToString
public class ClientRequestDto {
    private String id;
    private String name;
    private String email;
    private String document;
    private String cellphone;
    private UserRequestDto user;
    private Date birthdate;
}
