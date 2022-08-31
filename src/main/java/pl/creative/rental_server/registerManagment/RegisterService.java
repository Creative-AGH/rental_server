package pl.creative.rental_server.registerManagment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Account;
import pl.creative.rental_server.entities.Role;
import pl.creative.rental_server.entities.TokenToRegister;
import pl.creative.rental_server.exception.notFound.AccountException;
import pl.creative.rental_server.exception.notFound.RoleNotFound;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.registerManagment.dto.RegisterMapper;
import pl.creative.rental_server.registerManagment.dto.RegisterNewAccountDto;
import pl.creative.rental_server.registerManagment.emailManagement.EmailService;
import pl.creative.rental_server.repository.AccountRepository;
import pl.creative.rental_server.repository.RoleRepository;
import pl.creative.rental_server.repository.TokenToRegisterRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {
    private final AccountRepository accountRepository;
    private final RegisterMapper registerMapper;
    private final TokenToRegisterRepository tokenToRegisterRepository;
    private final RandomIdHandler randomIdHandler;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final RoleRepository roleRepository;
    private final EmailService emailService;

    @Transactional
    public void registerNewAccount(RegisterNewAccountDto registerNewAccountDto) {
        String newAccountEmail = registerNewAccountDto.getEmail();
        boolean doesSuchAccountAlreadyExist = accountRepository.existsAccountByEmail(newAccountEmail);
        if (!doesSuchAccountAlreadyExist) {
            Account account = registerMapper.mapRegisterNewAccountDtoToAccount(registerNewAccountDto);
//            account.setPassword(passwordEncoder.encode(registerNewAccountDto.getPassword()));
            account.setPassword(registerNewAccountDto.getPassword()); //FIXME passwordEncoder does not work
            List<Role> roles = new ArrayList<>();
            Optional<Role> optionalUserRole = roleRepository.findByName("USER");
            if(optionalUserRole.isEmpty()){
                log.error("USER role does not exist");
                throw new RoleNotFound("USER role does not exist");
            } else {
                Role user = optionalUserRole.get();
                roles.add(user);
            }
            account.setRoles(roles);

            String generatedToken = randomIdHandler.generateUniqueIdFromTable(tokenToRegisterRepository);
            TokenToRegister tokenToRegister = new TokenToRegister(generatedToken, account);

            try {
//                emailService.sendMail(registerNewAccountDto, tokenToRegister);
                accountRepository.save(account);
                tokenToRegisterRepository.save(tokenToRegister); //TODO
            } catch (Exception e){
                accountRepository.delete(account);
                log.error("Unable to create account");
            }

        } else {
            log.error("Account with such email {} already exist", newAccountEmail);
            throw new AccountException(String.format("Account with such email %s already exist", newAccountEmail));
        }
    }

    public boolean verifyEmail(String token) {
        return false;
    }
}
