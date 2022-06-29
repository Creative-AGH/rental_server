package pl.creative.rental_server.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.Entities.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
}
