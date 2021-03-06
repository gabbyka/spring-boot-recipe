package guru.springframework.spring5recipeapp.services;

import java.util.List;
import java.util.Optional;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.model.Recipe;

public interface RecipeService {
    List<Recipe> listRecipes();

    Recipe getRecipeById(Long recipeId);
    Optional<Recipe> getOptionalRecipeById(Long recipeId);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    
    void deleteById(Long id);

}
