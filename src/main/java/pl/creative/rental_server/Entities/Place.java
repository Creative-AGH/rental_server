package pl.creative.rental_server.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
public class Place {
    @Id
    @UniqueElements
    private String id;
    @NotNull
    @NotEmpty
    @UniqueElements
    private String name;
    @NotNull
    @NotEmpty
    private String description;

    @ManyToMany
    private Set<Item> items;


}
