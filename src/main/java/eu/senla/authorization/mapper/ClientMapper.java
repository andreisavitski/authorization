package eu.senla.authorization.mapper;

import eu.senla.authorization.dto.security.SignUpRequestDto;
import eu.senla.authorization.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toClient(SignUpRequestDto request);
}
