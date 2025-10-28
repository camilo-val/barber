package com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
public class EmployedResponseDto {
    private String id;
    private String name;
    private String email;
    private String document;
    private String cellphone;
    private String user;
    private Date birthdate;
}