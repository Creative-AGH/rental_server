package pl.creative.rental_server.Entities;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Place {
    @Id
    private String id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String description;

    @OneToMany
    @ToString.Exclude
    private Set<Item> items = new HashSet<>();


    public Place(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
