package guru.springframework.spring5recipeapp.services;

import java.util.List;
import org.springframework.stereotype.Service;
import guru.springframework.spring5recipeapp.model.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;


    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> listRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }
}
