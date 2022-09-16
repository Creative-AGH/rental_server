package pl.creative.rental_server.core.features.all.register;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.db.entities.Account;
import pl.creative.rental_server.db.entities.Role;
import pl.creative.rental_server.db.entities.TokenToRegister;
import pl.creative.rental_server.core.global.exception.notFound.AccountException;
import pl.creative.rental_server.core.global.handlersAndUtils.RandomIdHandler;
import pl.creative.rental_server.core.features.all.register.dto.RegisterMapper;
import pl.creative.rental_server.core.features.all.register.dto.RegisterNewAccountDto;
import pl.creative.rental_server.core.global.handlersAndUtils.EmailService;
import pl.creative.rental_server.db.repository.AccountRepository;
import pl.creative.rental_server.db.repository.TokenToRegisterRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {
    private final AccountRepository accountRepository;
    private final RegisterMapper registerMapper;
    private final TokenToRegisterRepository tokenToRegisterRepository;
    private final RandomIdHandler randomIdHandler;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Transactional
    public void registerNewAccount(RegisterNewAccountDto registerNewAccountDto) {
        String newAccountEmail = registerNewAccountDto.getEmail();
        boolean doesSuchAccountAlreadyExist = accountRepository.existsAccountByEmail(newAccountEmail);
        if (!doesSuchAccountAlreadyExist) {
            Account account = registerMapper.mapRegisterNewAccountDtoToAccount(registerNewAccountDto);
            account.setPassword(passwordEncoder.encode(registerNewAccountDto.getPassword()));
            account.setRole(Role.USER);
            String generatedToken = randomIdHandler.generateUniqueIdFromTable(tokenToRegisterRepository);
            TokenToRegister tokenToRegister = new TokenToRegister(generatedToken, account);

            try {
                emailService.sendMail(registerNewAccountDto.getEmail(), tokenToRegister.getToken());
                accountRepository.save(account);
                tokenToRegisterRepository.save(tokenToRegister);
            } catch (Exception e) {
                accountRepository.delete(account);

                log.error("Unable to create account due to reason {} ", e.getMessage());
            }

        } else {
            log.error("Account with such email {} already exist", newAccountEmail);
            throw new AccountException(String.format("Account with such email %s already exist", newAccountEmail));
        }
    }

    public boolean verifyEmail(String token) {
        Optional<TokenToRegister> optionalToken = tokenToRegisterRepository.findById(token);
        if (optionalToken.isEmpty())
            return false;
        else {
            TokenToRegister tokenToRegister = optionalToken.get();
            Account account = tokenToRegister.getAccount();
            account.setVerified(true);
            accountRepository.save(account);
            tokenToRegisterRepository.delete(tokenToRegister);
            log.info("Account {} now has verified email", account.getEmail());
            return true;
        }
    }
}
