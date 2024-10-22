package eu.senla.authorization.security;

import eu.senla.authorization.entity.Client;
import eu.senla.authorization.entity.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClientDetails implements UserDetails {

    private final Long id;

    private final String login;

    private final String password;

    private final Role role;

    private final List<GrantedAuthority> authorities;

    public ClientDetails(Client client) {
        this.id = client.getId();
        this.login = client.getLogin();
        this.password = client.getPassword();
        this.role = client.getRole();
        this.authorities = client.getPermissions()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
