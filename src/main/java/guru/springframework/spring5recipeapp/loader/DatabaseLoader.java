package guru.springframework.spring5recipeapp.loader;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.spring5recipeapp.model.Category;
import guru.springframework.spring5recipeapp.model.Difficulity;
import guru.springframework.spring5recipeapp.model.Ingredient;
import guru.springframework.spring5recipeapp.model.Notes;
import guru.springframework.spring5recipeapp.model.Recipe;
import guru.springframework.spring5recipeapp.model.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DatabaseLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        Recipe hungarianSoup = new Recipe();

        HashSet<Ingredient> hunSoupIngredients = new HashSet<>();

        Ingredient soupSaltIngerdient = new Ingredient();
        soupSaltIngerdient.setAmount(BigDecimal.valueOf(20L));
        soupSaltIngerdient.setDescription("salt");
        soupSaltIngerdient.setRecipe(hungarianSoup);
        UnitOfMeasure soupSaltKg = unitOfMeasureRepository.findByDescription("Kg");
        soupSaltIngerdient.setUnitOfMeasure(soupSaltKg);
        
        hunSoupIngredients.add(soupSaltIngerdient);

        Ingredient soupMilkIngerdient = new Ingredient();
        soupMilkIngerdient.setAmount(BigDecimal.valueOf(5L));
        soupMilkIngerdient.setDescription("milk");
        soupMilkIngerdient.setRecipe(hungarianSoup);
        UnitOfMeasure soupMilkLiter = unitOfMeasureRepository.findByDescription("Liter");
        soupMilkIngerdient.setUnitOfMeasure(soupMilkLiter);
        
        hunSoupIngredients.add(soupMilkIngerdient);

        hungarianSoup.setDifficulity(Difficulity.EASY);
        hungarianSoup.setCookTime(60);
        hungarianSoup.setDescription("Good hungarian soup!");
        hungarianSoup.setIngredients(hunSoupIngredients);

        Notes hunSoupNote = new Notes();
        hunSoupNote.setRecipeNotes("Very famous and very tasty soup from Hungary.");
        hunSoupNote.setRecipe(hungarianSoup);

        hungarianSoup.setNotes(hunSoupNote);

        hungarianSoup.setCookTime(45);
        hungarianSoup.setDescription("really handy recipe");
        hungarianSoup.setPrepTime(5);
        hungarianSoup.setServings(4);
        hungarianSoup.setSource("from an old hungarian lady");
        hungarianSoup.setUrl("http://nagyi.hu");

        Set<Category> categories = new HashSet<>();
        Category actCategory = categoryRepository.findByDescription("Hungarian");
        categories.add(actCategory);

        hungarianSoup.setCategories(categories);
        recipeRepository.save(hungarianSoup);

    }

}
