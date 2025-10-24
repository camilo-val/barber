package com.barberia.userservice.infrastrcuture.entrypoint.mapper;

import com.barberia.userservice.domain.model.User;
import com.barberia.userservice.infrastrcuture.entrypoint.dto.UserRequestDto;
import com.barberia.userservice.infrastrcuture.entrypoint.dto.UserResponseDto;

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

    public static User toUser(UserRequestDto user){
        return User.createUser(user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), user.getRole(),user.getCreateAt());
    }

}
