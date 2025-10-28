package com.barber.transactional.domain.model.employed;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Employed {
    private final String id;
    private final String name;
    private final String document;
    private final String email;
    private final String userId;
}
