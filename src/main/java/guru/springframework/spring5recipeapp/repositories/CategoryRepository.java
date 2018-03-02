package guru.springframework.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import guru.springframework.spring5recipeapp.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByDescription(String description);
}
