package pl.creative.rental_server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private String id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String description;

//    @ToString.Exclude
    @OneToMany(mappedBy = "place") //one place can have many items
    private List<Item> items = new ArrayList<>();

    public Place(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


}
