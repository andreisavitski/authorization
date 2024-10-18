package eu.senla.authorization.dto.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class SignInRequestDto {

    @NotBlank
    private final String login;

    @NotBlank
    private final String password;
}
