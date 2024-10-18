package eu.senla.authorization.service;

import eu.senla.authorization.dto.security.JwtAuthenticationResponseDto;
import eu.senla.authorization.dto.security.SignInRequestDto;
import eu.senla.authorization.dto.security.SignUpRequestDto;

public interface ClientService {

    JwtAuthenticationResponseDto signUp(SignUpRequestDto request);

    JwtAuthenticationResponseDto signIn(SignInRequestDto request);
}
