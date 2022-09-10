package pl.creative.rental_server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
//    @Email
    private String email;
    @NotNull
    @NotEmpty
    @Length(min = 2 , max = 60)
    private String name;
    @NotNull
    @NotEmpty
    @Length(min = 2 , max = 60)
    private String surname;
    @OneToMany
    List<Item> borrowedItems=new ArrayList<>();
    @NotEmpty
    @NotNull
//    @Length(min = 6 , max = 40)
    private String password;

    private boolean isVerified =false;
    Role role;

    public Account(Long id, String email, String name, String surname, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }


}
