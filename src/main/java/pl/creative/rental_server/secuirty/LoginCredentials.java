package pl.creative.rental_server.secuirty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginCredentials {
    private String username;
    private String password;
}
