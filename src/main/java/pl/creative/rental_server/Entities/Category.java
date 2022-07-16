package pl.creative.rental_server.Entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Category {
    @Id
    @Column(unique = true)
    private String id;
    @NotBlank(message = "Name may not be empty or null")
    private String name;
    private String description;
    @ManyToMany
    Set<Item> items = new HashSet<>();
}
