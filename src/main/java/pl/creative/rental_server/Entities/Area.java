package pl.creative.rental_server.Entities;

import pl.creative.rental_server.Entities.urls.UrlToArea;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Area {
    @Id
    private String id;

    @OneToMany
    List<Place> list= new ArrayList<>();
    @OneToOne
    UrlToArea urlToArea;
}
