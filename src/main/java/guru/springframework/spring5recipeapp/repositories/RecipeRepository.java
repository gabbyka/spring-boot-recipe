package guru.springframework.spring5recipeapp.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import guru.springframework.spring5recipeapp.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findById(Long id);
    
    void deleteById(Long id);
}
