package com.clientservice.infrastructure.drivenadapter.restconsumer.mapper;


import com.clientservice.domain.model.User;
import com.clientservice.infrastructure.drivenadapter.restconsumer.dto.UserRequestDto;
import com.clientservice.infrastructure.drivenadapter.restconsumer.dto.UserResponseDto;

public class UserMapper {
    private UserMapper(){}
    public static UserResponseDto toUserResponse(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    public static UserRequestDto toUserRequestDto(User user){
        return UserRequestDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    public static User toUser(UserResponseDto user){
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), user.getRole(),user.getCreateAt(), user.getUpdateAt());
    }
}
