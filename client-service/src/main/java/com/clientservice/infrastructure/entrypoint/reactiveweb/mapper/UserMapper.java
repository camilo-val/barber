package com.clientservice.infrastructure.entrypoint.reactiveweb.mapper;


import com.clientservice.domain.model.User;
import com.clientservice.infrastructure.entrypoint.reactiveweb.dto.UserRequestDto;

public class UserMapper {

    public static User toUser(UserRequestDto user){
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), user.getRole(),user.getCreateAt(), user.getUpdateAt());
    }
}
