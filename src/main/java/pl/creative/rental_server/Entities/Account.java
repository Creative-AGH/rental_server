package pl.creative.rental_server.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UniqueElements
    private Long id;
    @NotNull
    @NotEmpty
    @Email
    @UniqueElements
    private String email;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String surname;
    @OneToMany
    List<Item> borrowedItems=new ArrayList<>();
    @NotEmpty
    @NotNull
    private String password;

    private boolean isVerified =false;
    @ManyToMany
    List<Role> roles;



}
