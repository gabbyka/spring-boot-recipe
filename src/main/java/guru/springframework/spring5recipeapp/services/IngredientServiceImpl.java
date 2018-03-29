package guru.springframework.spring5recipeapp.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.model.Ingredient;
import guru.springframework.spring5recipeapp.model.Recipe;
import guru.springframework.spring5recipeapp.repositories.IngredientRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository,
            IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
        return ingredientOptional.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public IngredientCommand getIngredientCommandById(Long id) {
        Ingredient ingredient = getIngredientById(id);
        return ingredientToIngredientCommand.convert(ingredient);
    }

    @Override
    public IngredientCommand getIngredientCommandByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        Recipe recipe = recipeOptional.orElseThrow(IllegalArgumentException::new);

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        return ingredientCommandOptional.orElseThrow(IllegalArgumentException::new);
    }

}
