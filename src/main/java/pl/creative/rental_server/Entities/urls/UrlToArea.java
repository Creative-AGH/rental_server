package pl.creative.rental_server.Entities.urls;

import lombok.Data;
import pl.creative.rental_server.Entities.Area;
import pl.creative.rental_server.Entities.urls.UrlToFile;

import javax.persistence.Entity;

@Entity
@Data
public class UrlToArea extends UrlToFile<Area> {
}
