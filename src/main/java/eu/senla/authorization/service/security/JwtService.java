package eu.senla.authorization.service.security;

import eu.senla.authorization.security.ClientDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static eu.senla.authorization.constant.AppConstants.*;
import static io.jsonwebtoken.io.Decoders.BASE64;

@Service
public class JwtService {

    @Autowired
    @Value(JWT_KEY)
    private String jwtKey;

    @Autowired
    @Value(JWT_EXPIRATION)
    private Long jwtExpiration;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof ClientDetails clientDetails) {
            claims.put(ID, clientDetails.getId());
            claims.put(LOGIN, clientDetails.getLogin());
            claims.put(ROLE, clientDetails.getRole().getId());
            claims.put(AUTHORITIES, clientDetails.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(",")));
        }
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> extraClaims) {
        return Jwts.builder()
                .subject(extraClaims.get(LOGIN).toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .claims(extraClaims)
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = BASE64.decode(jwtKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
