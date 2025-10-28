package com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.mapper;

import com.barber.transactional.domain.model.employed.Employed;
import com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.dto.EmployedResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployedRestConsumerMapper {
    Employed toDomain(EmployedResponseDto dto);
}
