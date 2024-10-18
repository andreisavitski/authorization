package eu.senla.authorization.dto.security;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class SignUpRequestDto {

    private final String login;

    private final String password;
}
