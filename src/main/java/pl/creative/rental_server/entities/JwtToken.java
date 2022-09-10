package pl.creative.rental_server.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {
    @Id
    private String id;
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfExpire;
    public static JwtToken createJwtTokenWithExpiryDate(String id,long secondsToExpiry)
    {
        return new JwtToken(id,LocalDateTime.now(),LocalDateTime.now().plusSeconds(secondsToExpiry));
    }
}
