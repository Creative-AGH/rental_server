package pl.creative.rental_server.core.global.secuirty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import pl.creative.rental_server.core.global.handlersAndUtils.ExpiryJwtTokenHandler;
import pl.creative.rental_server.db.entities.Role;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final RestAuthenticationFailureHandler authenticationFailureHandler;
    private final RestAuthenticationSuccessHandler authenticationSuccessHandler;

    private final String secret;
    private final UserDetailsService userDetailsService;

    private final ExpiryJwtTokenHandler expiryJwtTokenHandler;

    public SecurityConfig(RestAuthenticationFailureHandler authenticationFailureHandler, RestAuthenticationSuccessHandler authenticationSuccessHandler,
                          @Value("${jwt.secret}") String secret, UserDetailsService userDetailsService, ExpiryJwtTokenHandler expiryJwtTokenHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.secret = secret;
        this.userDetailsService = userDetailsService;
        this.expiryJwtTokenHandler = expiryJwtTokenHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }

    @Bean
    PasswordEncoder getNoOpPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers(
                "/swagger-ui.html",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/v2/api-docs",
                "/h2-console/**",
                "/console/*"
//                ,"/**" // COMENT THIS when you want to use with user restrictions
        );
    }

    @Override
    public void configure(HttpSecurity http) {
        try {
            http
                    .csrf().disable()
                    .cors().disable()
                    .authorizeRequests()
//                    .mvcMatchers("/all/**").permitAll()//UNCOMENT THIS when you want to use with user restrictions
//                    .mvcMatchers("/user/**").hasRole(Role.USER.getCode())
//                    .mvcMatchers("/moderator/**").hasRole(Role.MODERATOR.getCode())
//                    .mvcMatchers("/admin/**").hasRole(Role.ADMIN.getCode())
                    .anyRequest().permitAll()
                    .and().httpBasic()
                    .and()
                    .headers().frameOptions().disable()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilter(authenticationFilter())
                    .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService, secret, expiryJwtTokenHandler)) // 2
                    .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        } catch (Exception e) {
            log.error("Something went wrong with authorization or authentication");
        }
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
}
