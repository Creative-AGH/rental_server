package pl.creative.rental_server.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.JwtToken;
import pl.creative.rental_server.repository.JwtTokenRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ExpiryJwtTokenHandler {

    private final JwtTokenRepository jwtTokenRepository;
    @Value("${jwt.deleteTimeAfterExpiry}")
    private long timeToDeleteExpiredJwtToken;

    public boolean checkIfGivenJwtTokenIsNotExpired(String token) {
        if (jwtTokenRepository.findById(token).isEmpty())
            return true;
        else
            return jwtTokenRepository.findById(token)
                    .map(JwtToken::getDateOfExpire)
                    .filter(x -> x.isBefore(LocalDateTime.now()))
                    .isEmpty();
    }

    public void removeExpiredTokensAfterSomeTime() {
        List<JwtToken> tokensForRemoval = StreamSupport.stream(jwtTokenRepository.findAll().spliterator(), false)
                .filter(x -> x.getDateOfExpire().plusSeconds(timeToDeleteExpiredJwtToken).isBefore(LocalDateTime.now()))
                .toList();
        jwtTokenRepository.deleteAll(tokensForRemoval);
    }

}
