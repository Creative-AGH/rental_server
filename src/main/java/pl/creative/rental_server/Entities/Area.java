package pl.creative.rental_server.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Area {
    @Id
    private String id;

    @OneToMany
    List<Place> list= new ArrayList<>();
}
