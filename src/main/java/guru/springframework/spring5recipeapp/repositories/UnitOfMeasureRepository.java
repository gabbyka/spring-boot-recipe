package guru.springframework.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import guru.springframework.spring5recipeapp.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    UnitOfMeasure findByDescription(String description);
}
