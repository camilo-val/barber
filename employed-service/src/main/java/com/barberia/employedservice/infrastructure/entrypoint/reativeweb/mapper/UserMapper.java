package com.barberia.employedservice.infrastructure.entrypoint.reativeweb.mapper;


import com.barberia.employedservice.domain.model.User;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.dto.UserRequestDto;

public class UserMapper {

    public static User toUser(UserRequestDto user){
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), user.getRole(),user.getCreateAt(), user.getUpdateAt());
    }

    public static UserRequestDto toUserRequestDto(User user){
        return UserRequestDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .status(user.getStatus())
                .role(user.getRole())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();

    }
}
