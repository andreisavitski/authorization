package eu.senla.authorization.controller;

import eu.senla.authorization.dto.security.JwtAuthenticationResponseDto;
import eu.senla.authorization.dto.security.SignInRequestDto;
import eu.senla.authorization.dto.security.SignUpRequestDto;
import eu.senla.authorization.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authorize")
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientService clientService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponseDto signUp(@RequestBody SignUpRequestDto request) {
        return clientService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponseDto signIn(@RequestBody SignInRequestDto request) {
        return clientService.signIn(request);
    }
}
