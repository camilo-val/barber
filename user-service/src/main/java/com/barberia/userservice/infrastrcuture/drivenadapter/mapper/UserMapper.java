package com.barberia.userservice.infrastrcuture.drivenadapter.mapper;

import com.barberia.userservice.domain.model.User;
import com.barberia.userservice.infrastrcuture.drivenadapter.document.UserEntity;

public class UserMapper {
    private UserMapper(){}
    public static User toUser(UserEntity user){
        return User.createUser(user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), user.getRole(),user.getCreateAt());
    }
    public static UserEntity toEntity(User user){
        return new UserEntity(user.getId(), user.getUsername(), user.getPassword(),user.getStatus(),user.getRole(),user.getCreateAt(),user.getUpdateAt());
    }
}
