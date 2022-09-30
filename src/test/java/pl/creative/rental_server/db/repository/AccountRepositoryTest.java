package pl.creative.rental_server.db.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.creative.rental_server.db.entities.Account;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void existsAccountByEmail() {
        // given
        String email = "jamila@gmail.com";
        Account account = new Account(
                5L, email, "name", "surname", "password"
        );
        underTest.save(account);

        // when
        Boolean expected = underTest.existsAccountByEmail(email);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void findByEmail() {
        // given
        String email = "jamila@gmail.com";
        Account account = new Account(
                5L, email, "name", "surname", "password"
        );
        underTest.save(account);

        // when
        Optional<Account> expected = underTest.findByEmail(email);

        // then
        assertThat(expected).isPresent();
    }
}