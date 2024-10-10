package eu.senla.security.service.security;

import eu.senla.security.exception.ApplicationException;
import eu.senla.security.repository.ClientRepository;
import eu.senla.security.security.ClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static eu.senla.security.exception.ApplicationError.CLIENT_NOT_FOUND;

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
