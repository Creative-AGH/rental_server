package pl.creative.rental_server.Entities.urls;

import lombok.Data;
import pl.creative.rental_server.Entities.Item;
import pl.creative.rental_server.Entities.urls.UrlToFile;

import javax.persistence.*;

@Entity
@Data
public class UrlToItem extends UrlToFile<Item> {
}
