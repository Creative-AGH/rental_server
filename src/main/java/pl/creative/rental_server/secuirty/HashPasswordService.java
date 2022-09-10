package pl.creative.rental_server.secuirty;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Account;

import javax.transaction.Transactional;
@Data
@Service
public class HashPasswordService {
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void setPasswordEncodedPassword(Account account, String password)
    {
        account.setPassword(passwordEncoder.encode(password));
    }
}
