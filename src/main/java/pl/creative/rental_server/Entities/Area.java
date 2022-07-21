package pl.creative.rental_server.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Area {
    @Id
    private String id;

    @OneToMany
    List<Place> places= new ArrayList<>();
    public void addPlace(Place place)
    {
        places.add(place);
    }
    public Area(String id) {
        this.id = id;
    }
}
