package com.barberia.userservice.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role{
    ADMIN("Admin"),
    BARBER("Barber"),
    CLIENT("Client");
    private String role;
}
