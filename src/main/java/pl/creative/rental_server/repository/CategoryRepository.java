package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
    Optional<Category> findCategoryByCategoryName(String categoryName);
}
