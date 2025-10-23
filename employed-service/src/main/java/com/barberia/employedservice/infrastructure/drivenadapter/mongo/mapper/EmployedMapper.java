package com.barberia.employedservice.infrastructure.drivenadapter.mongo.mapper;

import com.barberia.employedservice.domain.model.Employed;
import com.barberia.employedservice.infrastructure.drivenadapter.mongo.document.EmployedDocument;

public class EmployedMapper {

    public static EmployedDocument toEmployedDocument(Employed employed){
        return new EmployedDocument(employed.getId(), employed.getName(), employed.getDocument(),
                employed.getEmail(), employed.getCellphone(), employed.getUserId(), employed.getBirthdate());
    }

    public static Employed toEmployed(EmployedDocument employedDocument){
        return Employed.createEmployed(employedDocument.getId(), employedDocument.getName(), employedDocument.getDocument(),
                employedDocument.getEmail(), employedDocument.getCellphone(), employedDocument.getUserId(), employedDocument.getBirthdate());

    }
}
