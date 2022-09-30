package pl.creative.rental_server.core.global.secuirty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginCredentials {
    private String username;
    private String password;
}
