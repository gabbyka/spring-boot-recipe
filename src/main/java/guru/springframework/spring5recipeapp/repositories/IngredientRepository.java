package guru.springframework.spring5recipeapp.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import guru.springframework.spring5recipeapp.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Optional<Ingredient> findById(Long id);
}
