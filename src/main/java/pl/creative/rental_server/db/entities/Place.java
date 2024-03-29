package pl.creative.rental_server.db.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "place")
public class Place {
    @Id
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String id;
    @NotNull
    @NotEmpty
    private String name;
    private String description;

    @OneToMany(mappedBy = "place") //one place can have many items
    private List<Item> items = new ArrayList<>();

    @ElementCollection
    private List<Double> placeCoordinates;

    public Place(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }
}
