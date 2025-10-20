package com.clientservice.infrastructure.drivenadapter.mongo.mapper;

import com.clientservice.domain.model.Client;
import com.clientservice.infrastructure.drivenadapter.mongo.document.ClientDocument;

public class ClientMapper {
    public static Client toClient(ClientDocument document){
        return Client.newClient(document.getId(), document.getName(), document.getEmail(), document.getDocument(),document.getCellphone(), document.getUserId(), document.getBirthdate());
    }

    public static ClientDocument toDocument(Client client){
        return new ClientDocument(client.getId(), client.getName(), client.getEmail(), client.getDocument(), client.getUserId(), client.getCellphone(), client.getBirthdate());

    }
}
