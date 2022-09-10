package pl.creative.rental_server.secuirty;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Account;
import pl.creative.rental_server.entities.Role;
import pl.creative.rental_server.repository.AccountRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByEmail(username);
        if (accountOptional.isPresent()) {

            Account account = accountOptional.get();
            Role role=account.getRole();
            return User
                    .builder()
                    .username(account.getEmail())
                    .password(account.getPassword())
                    .roles(String.valueOf(role))
                    .build();

        } else {
            return null;
        }

    }
}
