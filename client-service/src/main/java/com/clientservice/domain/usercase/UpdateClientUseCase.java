package com.clientservice.domain.usercase;

import com.clientservice.domain.model.Client;
import com.clientservice.domain.model.exceptions.BusinessException;
import com.clientservice.domain.model.exceptions.message.BusinessMessageException;
import com.clientservice.domain.model.gateway.ClientGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateClientUseCase {
    private final ClientGateway clientGateway;

    public Mono<Client> update (String id, Client client, String userId){
        return Mono.zip(this.clientGateway.clientExist(client.getDocument()),
                this.clientGateway.findById(id))
                .flatMap(tuple -> {
                    if (tuple.getT1()){
                        if (!tuple.getT2().getDocument().equals(client.getDocument())){
                            return Mono.error(new BusinessException(BusinessMessageException.CLIENT_EXIST));
                        }
                        if (!tuple.getT2().getUserId().equals(userId)){
                            return Mono.error(new BusinessException(BusinessMessageException.CLIENT_INVALID));
                        }
                    }
                    Client newClient = Client.newClient(client.getId(), client.getName(), client.getEmail(), client.getDocument(), client.getCellphone(), userId, client.getBirthdate());
                    return this.clientGateway.save(newClient);
                });
    }
}
