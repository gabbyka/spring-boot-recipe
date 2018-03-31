package guru.springframework.spring5recipeapp.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.model.Ingredient;
import guru.springframework.spring5recipeapp.model.Recipe;
import guru.springframework.spring5recipeapp.repositories.IngredientRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository,
            UnitOfMeasureRepository unitOfMeasureRepository,
            IngredientToIngredientCommand ingredientToIngredientCommand,
            IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

        Optional<IngredientCommand> ingredientCommandOptional =
                recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
                        .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        return ingredientCommandOptional.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!recipeOptional.isPresent()) {
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId())).findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository.findById(ingredientCommand.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
            } else {
                // add new Ingredient
                recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            // to do check for fail
            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
                    .findFirst().get());
        }
    }

}
