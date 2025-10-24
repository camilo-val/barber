package com.clientservice.domain.usercase;

import com.clientservice.domain.model.Client;
import com.clientservice.domain.model.User;
import com.clientservice.domain.model.exceptions.BusinessException;
import com.clientservice.domain.model.exceptions.message.BusinessMessageException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@RequiredArgsConstructor
public class ClientUseCase {
    private final CreateClientUseCase createClientUseCase;
    private final UserUseCase userUseCase;
    private final UpdateClientUseCase updateClientUseCase;

    public Mono<Client> create(Client client, User user){
        return userUseCase.create(user).flatMap( newUser -> createClientUseCase.createClient(client,newUser.getId()))
                .onErrorResume(BusinessException.class, ex -> ex.getMessage().equals(BusinessMessageException.CLIENT_EXIST.getMessage())
                            ? userUseCase.deleteByUsername(user.getUsername()).then(Mono.error(ex))
                            : Mono.error(ex));
    }

    public Mono<Client> update (String id, Client client, User user){
        return validateUser(user.getId(), id)
                .flatMap(isValid -> {
                    Client updateClient = Client.newClient(id, client.getName(), client.getEmail()
                            , client.getDocument(), client.getCellphone(),
                            client.getUserId(), client.getBirthdate());
                    return this.createClientUseCase.createClient(updateClient, id);
                })
                .zipWhen(clientUpdate -> {
                    User updateUser = new User(clientUpdate.getUserId(), user.getUsername(),
                            user.getPassword(), user.getStatus(),user.getCreateAt(),
                            user.getUpdateAt());
                    return this.userUseCase.update(updateUser.getId(), updateUser);
                }).map(Tuple2::getT1);
    }

    private Mono<Boolean> validateUser(String userId, String clientUserId){
        return Mono.just(userId.equals(clientUserId));
    }
    
}
