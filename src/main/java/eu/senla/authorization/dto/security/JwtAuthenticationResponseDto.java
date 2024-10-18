package eu.senla.authorization.dto.security;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@ToString
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponseDto {

    private String token;
}
