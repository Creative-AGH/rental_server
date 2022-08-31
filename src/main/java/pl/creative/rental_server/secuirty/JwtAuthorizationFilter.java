package pl.creative.rental_server.secuirty;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.creative.rental_server.handlers.ExpiryJwtTokenHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final UserDetailsService userDetailsService;
    private final String secret;
    private final ExpiryJwtTokenHandler expiryJwtTokenHandler;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDetailsService userDetailsService,
                                  String secret, ExpiryJwtTokenHandler expiryJwtTokenHandler) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.secret = secret;
        this.expiryJwtTokenHandler = expiryJwtTokenHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token == null)
            return null;
        String tokenWithOutPrefix = token.replace(TOKEN_PREFIX, "");
        expiryJwtTokenHandler.removeExpiredTokensAfterSomeTime();

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(tokenWithOutPrefix);
        String id = decodedJWT.getId();
        log.info(id);
        if (expiryJwtTokenHandler.checkIfGivenJwtTokenIsNotExpired(id)) {
            String userName = decodedJWT.getSubject();
            if (userName != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
            }
        }
        return null;
    }
}
