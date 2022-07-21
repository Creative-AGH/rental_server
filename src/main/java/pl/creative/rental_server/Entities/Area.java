package pl.creative.rental_server.Entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Area {
    @Id
    private String id;

    @OneToMany
    List<Place> list= new ArrayList<>();

    public Area(String id) {
        this.id = id;
    }
}
