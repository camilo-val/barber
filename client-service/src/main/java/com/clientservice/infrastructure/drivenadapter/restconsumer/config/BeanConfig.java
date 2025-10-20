package com.clientservice.infrastructure.drivenadapter.restconsumer.config;

import com.clientservice.domain.model.gateway.ClientGateway;
import com.clientservice.domain.model.gateway.UserGateway;
import com.clientservice.domain.usercase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CreateClientUseCase createClientUseCase(ClientGateway clientGateway){
        return new CreateClientUseCase(clientGateway);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientGateway clientGateway){
        return new UpdateClientUseCase(clientGateway);
    }

    @Bean
    public FindClient findClient(ClientGateway clientGateway){
        return new FindClient(clientGateway);
    }

    @Bean
    public UserUseCase userUseCase(UserGateway userGateway){
        return new UserUseCase(userGateway);
    }

    @Bean
    public ClientUseCase clientUseCase(CreateClientUseCase createClientUseCase, UserUseCase userUseCase, UpdateClientUseCase updateClientUseCase){
        return new ClientUseCase(createClientUseCase,userUseCase,updateClientUseCase);
    }
}
