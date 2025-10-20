package com.clientservice.domain.usercase;

import com.clientservice.domain.model.Client;
import com.clientservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientUseCase {
    private final CreateClientUseCase createClientUseCase;
    private final UserUseCase userUseCase;
    private final UpdateClientUseCase updateClientUseCase;

    public Mono<Client> create(Client client, User user){
        return userUseCase.create(user).flatMap( newUser -> createClientUseCase.createClient(client,newUser.getId()));
    }

    public Mono<Client> update (String id, Client client, User user){
        return userUseCase.update(user.getId(), user).flatMap(updateClient -> updateClientUseCase.update(id,client, updateClient.getId()));

    }
    
}
