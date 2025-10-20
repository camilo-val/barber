package com.barberia.userservice.domain.model.exceptions.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessMessageException {
    INVALID_REQUEST("SFB000","Invalid request"),
    USER_NOT_EXIST("SFB001","The user not exist"),
    USER_EXIST("SFB002","The user exist");
    private String code;
    private String Message;
}
