package pl.creative.rental_server.entities;

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
