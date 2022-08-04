package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.ItemTemplate;

public interface ItemTemplateRepository extends PagingAndSortingRepository<ItemTemplate, String> {
}
