package pl.creative.rental_server.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TokenToVerifyEmail {
    @Id
    private String id;
    @OneToOne
    private Account account;
}
