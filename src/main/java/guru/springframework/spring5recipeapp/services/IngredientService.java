package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.model.Ingredient;

public interface IngredientService {
    Ingredient getIngredientById(Long id);

    IngredientCommand getIngredientCommandById(Long id);

    IngredientCommand getIngredientCommandByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
