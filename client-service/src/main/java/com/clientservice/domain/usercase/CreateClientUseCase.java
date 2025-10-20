package com.clientservice.domain.usercase;

import com.clientservice.domain.model.Client;
import com.clientservice.domain.model.exceptions.BusinessException;
import com.clientservice.domain.model.exceptions.message.BusinessMessageException;
import com.clientservice.domain.model.gateway.ClientGateway;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateClientUseCase {
    private final ClientGateway clientGateway;

    public Mono<Client> createClient(Client client, String userId) {

        return exist(client.getDocument()).flatMap(exist -> {
            if (exist){
                return Mono.error(new BusinessException(BusinessMessageException.CLIENT_EXIST));
            }
            Client newClient = Client.newClient(client.getId(), client.getName(), client.getEmail(), client.getDocument(), client.getCellphone(), userId, client.getBirthdate());
            return this.clientGateway.save(newClient);
        });
    }

    private Mono<Boolean> exist(String document){
        return this.clientGateway.findByDocument(document).hasElement();

    }

}
