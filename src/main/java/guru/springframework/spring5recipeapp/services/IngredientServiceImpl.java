package guru.springframework.spring5recipeapp.services;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.model.Ingredient;
import guru.springframework.spring5recipeapp.repositories.IngredientRepository;

public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(IngredientRepository ingredientRepository,
            IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
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

}
