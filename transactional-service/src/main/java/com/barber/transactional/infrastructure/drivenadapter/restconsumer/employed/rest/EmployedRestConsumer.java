package com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.rest;

import com.barber.transactional.domain.model.employed.Employed;
import com.barber.transactional.domain.model.employed.EmployedGateway;
import com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.dto.EmployedResponseDto;
import com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.mapper.EmployedRestConsumerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployedRestConsumer implements EmployedGateway {
    private final WebClient.Builder employedWebClient;
    private final EmployedRestConsumerMapper mapper;


    @Override
    public Mono<Employed> findById(String id) {
        return this.employedWebClient.build()
                .get()
                .uri("/api/employed-service/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EmployedResponseDto.class)
                .map(mapper::toDomain);
    }
}
