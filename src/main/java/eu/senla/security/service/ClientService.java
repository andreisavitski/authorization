package eu.senla.security.service;

import eu.senla.security.dto.security.JwtAuthenticationResponseDto;
import eu.senla.security.dto.security.SignInRequestDto;
import eu.senla.security.dto.security.SignUpRequestDto;

public interface ClientService {

    JwtAuthenticationResponseDto signUp(SignUpRequestDto request);

    JwtAuthenticationResponseDto signIn(SignInRequestDto request);
}
