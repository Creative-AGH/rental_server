package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
}
