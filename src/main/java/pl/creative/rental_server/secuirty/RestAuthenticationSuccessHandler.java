package pl.creative.rental_server.secuirty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import pl.creative.rental_server.entities.JwtToken;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.repository.JwtTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final String secret;
    private final JwtTokenRepository jwtTokenRepository;
    private final long jwtExistTime;
    private final RandomIdHandler randomIdHandler;

    public RestAuthenticationSuccessHandler(@Value("${jwt.secret}") String secret, JwtTokenRepository jwtTokenRepository, @Value("${jwt.expirationTime}") long jwtExistTime, RandomIdHandler randomIdHandler) {
        this.secret = secret;
        this.jwtTokenRepository = jwtTokenRepository;
        this.jwtExistTime = jwtExistTime;
        this.randomIdHandler = randomIdHandler;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();


        String jwtId = randomIdHandler.generateUniqueIdFromTable(jwtTokenRepository).replace("-", "");

        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExistTime))
                .withJWTId(jwtId)
                .sign(Algorithm.HMAC256(secret));
        jwtTokenRepository.save(JwtToken.createJwtTokenWithExpiryDate(jwtId, jwtExistTime));


        response.getOutputStream().print("{\"token\": \"" + token + "\"}");
        response.addHeader("Authorization", "Bearer " + token);
    }
}