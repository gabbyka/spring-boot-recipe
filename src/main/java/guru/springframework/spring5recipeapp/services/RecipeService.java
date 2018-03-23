package guru.springframework.spring5recipeapp.services;

import java.util.List;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.model.Recipe;

public interface RecipeService {
    List<Recipe> listRecipes();

    Recipe getRecipeById(Long recipeId);
    
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    
}
