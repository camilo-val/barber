package com.clientservice.infrastructure.entrypoint.reactiveweb.mapper;

import com.clientservice.domain.model.Client;
import com.clientservice.infrastructure.entrypoint.reactiveweb.dto.ClientRequestDto;

public class ClientMapper {
    public static Client toClient(ClientRequestDto document){
        return Client.newClient(document.getId(), document.getName(), document.getEmail(), document.getDocument(),document.getCellphone(), document.getUser().getId(), document.getBirthdate());
    }
}
