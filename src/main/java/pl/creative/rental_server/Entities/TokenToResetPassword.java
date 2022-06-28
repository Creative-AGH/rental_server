package pl.creative.rental_server.Entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class TokenToResetPassword {
    @Id
    private String id;
    @ManyToOne
    Account account;
}
