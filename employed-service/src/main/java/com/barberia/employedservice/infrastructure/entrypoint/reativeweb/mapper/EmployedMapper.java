package com.barberia.employedservice.infrastructure.entrypoint.reativeweb.mapper;

import com.barberia.employedservice.domain.model.Employed;
import com.barberia.employedservice.infrastructure.drivenadapter.restconsumer.dto.UserResponseDto;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.dto.EmployedRequestDto;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.dto.EmployedResponseDto;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.dto.UserRequestDto;

public class EmployedMapper {
    public static Employed toEmployed(EmployedRequestDto requestDto){
        return Employed.createEmployed(requestDto.getId(),
                requestDto.getName(),
                requestDto.getDocument(),
                requestDto.getEmail(),
                requestDto.getCellphone(),
                requestDto.getUser().getId(),
                requestDto.getBirthdate());
    }

    public static EmployedResponseDto toResponse(Employed employed, UserRequestDto responseDto){
        return EmployedResponseDto.builder()
                .id(employed.getId())
                .name(employed.getName())
                .document(employed.getDocument())
                .email(employed.getEmail())
                .cellphone(employed.getCellphone())
                .user(responseDto)
                .birthdate(employed.getBirthdate())
                .build();

    }
}
