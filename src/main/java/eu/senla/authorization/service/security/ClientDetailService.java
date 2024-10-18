package eu.senla.authorization.service.security;

import eu.senla.authorization.exception.ApplicationException;
import eu.senla.authorization.repository.ClientRepository;
import eu.senla.authorization.security.ClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static eu.senla.authorization.exception.ApplicationError.CLIENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ClientDetailService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findByLogin(username).map(ClientDetails::new).orElseThrow(
                () -> new ApplicationException(CLIENT_NOT_FOUND)
        );
    }
}
