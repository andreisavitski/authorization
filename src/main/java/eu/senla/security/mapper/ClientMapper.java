package eu.senla.security.mapper;

import eu.senla.security.dto.security.SignUpRequestDto;
import eu.senla.security.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toClient(SignUpRequestDto request);
}
