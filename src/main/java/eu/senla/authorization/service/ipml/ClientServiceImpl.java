package eu.senla.authorization.service.ipml;

import eu.senla.authorization.dto.security.JwtAuthenticationResponseDto;
import eu.senla.authorization.dto.security.SignInRequestDto;
import eu.senla.authorization.dto.security.SignUpRequestDto;
import eu.senla.authorization.entity.Client;
import eu.senla.authorization.exception.ApplicationException;
import eu.senla.authorization.mapper.ClientMapper;
import eu.senla.authorization.repository.ClientRepository;
import eu.senla.authorization.repository.RoleRepository;
import eu.senla.authorization.security.ClientDetails;
import eu.senla.authorization.service.ClientService;
import eu.senla.authorization.service.security.ClientDetailService;
import eu.senla.authorization.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static eu.senla.authorization.constant.AppConstants.SIMPLE_CLIENT;
import static eu.senla.authorization.exception.ApplicationError.ROLE_NOT_FOUND;
import static eu.senla.authorization.exception.ApplicationError.THE_CLIENT_EXISTS;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final RoleRepository roleRepository;

    private final ClientMapper clientMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final ClientDetailService clientDetailService;

    @Override
    public JwtAuthenticationResponseDto signUp(SignUpRequestDto request) {
        Client client = clientMapper.toClient(checkingUniqueLogin(request));
        client.setRole(roleRepository.findByName(SIMPLE_CLIENT).orElseThrow(
                () -> new ApplicationException(ROLE_NOT_FOUND)
        ));
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        clientRepository.save(client);
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

    private SignUpRequestDto checkingUniqueLogin(SignUpRequestDto signUpRequestDto) {
        if (clientRepository.findByLogin(signUpRequestDto.getLogin()).isEmpty()) {
            return signUpRequestDto;
        } else {
            throw new ApplicationException(THE_CLIENT_EXISTS);
        }
    }
}
