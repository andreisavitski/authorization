package eu.senla.security.service.ipml;

import eu.senla.security.dto.security.JwtAuthenticationResponseDto;
import eu.senla.security.dto.security.SignInRequestDto;
import eu.senla.security.dto.security.SignUpRequestDto;
import eu.senla.security.entity.Client;
import eu.senla.security.enums.Role;
import eu.senla.security.exception.ApplicationException;
import eu.senla.security.mapper.ClientMapper;
import eu.senla.security.repository.ClientRepository;
import eu.senla.security.security.ClientDetails;
import eu.senla.security.service.ClientService;
import eu.senla.security.service.security.ClientDetailService;
import eu.senla.security.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static eu.senla.security.exception.ApplicationError.THE_CLIENT_EXISTS;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ClientDetailService clientDetailService;

    @Override
    public JwtAuthenticationResponseDto signUp(SignUpRequestDto request) {
        Client client = clientMapper.toClient(request);
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        client.setRole(Role.SIMPLE_CLIENT);
        clientRepository.save(checkingUniqueLogin(client));
        ClientDetails clientDetails = new ClientDetails(client);
        String jwt = jwtService.generateToken((clientDetails));
        return new JwtAuthenticationResponseDto(jwt);
    }

    @Override
    public JwtAuthenticationResponseDto signIn(SignInRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
        UserDetails client = clientDetailService.loadUserByUsername(request.getLogin());
        String jwt = jwtService.generateToken(client);
        return new JwtAuthenticationResponseDto(jwt);
    }

    private Client checkingUniqueLogin(Client client) {
        if (clientRepository.findByLogin(client.getLogin()).isEmpty()) {
            return client;
        } else {
            throw new ApplicationException(THE_CLIENT_EXISTS);
        }
    }
}
