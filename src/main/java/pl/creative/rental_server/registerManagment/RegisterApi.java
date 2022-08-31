package pl.creative.rental_server.registerManagment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.creative.rental_server.registerManagment.dto.RegisterNewAccountDto;

import javax.validation.Valid;

public interface RegisterApi {

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody @Valid RegisterNewAccountDto registerNewAccountDto);

    @GetMapping("/validate")
    ResponseEntity<?> verifyEmail(@RequestParam String token);
}
