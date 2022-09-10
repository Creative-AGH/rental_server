package pl.creative.rental_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**",
                "/swagger-ui.html",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .httpBasic();
    }
}


