package pl.creative.rental_server.registerManagment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.registerManagment.dto.RegisterNewAccountDto;

@RestController
@RequiredArgsConstructor
public class RegisterController implements RegisterApi {
    private final RegisterService registerService;

    @Override
    public ResponseEntity<?> register(RegisterNewAccountDto registerNewAccountDto) {
        registerService.registerNewAccount(registerNewAccountDto);
        return ResponseEntity.noContent().build(); //TODO change to ok status with a body
    }

    @Override
    public ResponseEntity<Boolean> verifyEmail(String token) {
        boolean response = registerService.verifyEmail(token);
        if (!response)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }
}
