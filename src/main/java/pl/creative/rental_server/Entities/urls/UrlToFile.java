package pl.creative.rental_server.Entities.urls;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@Data
@MappedSuperclass
public abstract class UrlToFile <T> {
    @Id
    private String id;
    @ManyToOne
    private T value;
    private String link;
    private String description;
    private String name;
}
