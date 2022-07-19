package pl.creative.rental_server.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.Entities.ItemTemplate;

public interface ItemTemplateRepository extends PagingAndSortingRepository<ItemTemplate, String> {
}
