package guru.springframework.spring5recipeapp.loader;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void run(String... args) throws Exception {
        Recipe hungarianSoup = new Recipe();

        Ingredient soupSaltIngerdient = new Ingredient();
        soupSaltIngerdient.setAmount(BigDecimal.valueOf(20L));
        soupSaltIngerdient.setDescription("salt");
        soupSaltIngerdient.setRecipe(hungarianSoup);
        Optional<UnitOfMeasure> soupSaltKgOptional = unitOfMeasureRepository.findByDescription("Kg");
        UnitOfMeasure soupSaltKg = soupSaltKgOptional.orElseThrow(IllegalArgumentException::new);
        soupSaltIngerdient.setUnitOfMeasure(soupSaltKg);

        hungarianSoup.getIngredients().add(soupSaltIngerdient);

        Ingredient soupMilkIngerdient = new Ingredient();
        soupMilkIngerdient.setAmount(BigDecimal.valueOf(5L));
        soupMilkIngerdient.setDescription("milk");
        soupMilkIngerdient.setRecipe(hungarianSoup);
        Optional<UnitOfMeasure> soupMilkLiterOptional = unitOfMeasureRepository.findByDescription("Liter");
        UnitOfMeasure soupMilkLiter = soupMilkLiterOptional.orElseThrow(IllegalArgumentException::new);
        soupMilkIngerdient.setUnitOfMeasure(soupMilkLiter);

        hungarianSoup.getIngredients().add(soupMilkIngerdient);

        hungarianSoup.setDifficulity(Difficulity.EASY);
        hungarianSoup.setCookTime(60);
        hungarianSoup.setDescription("Good hungarian soup!");

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

        Optional<Category> actCategoryOptional = categoryRepository.findByDescription("Hungarian");
        Category actCategory = actCategoryOptional.orElseThrow(IllegalArgumentException::new);
        hungarianSoup.getCategories().add(actCategory);
        
        Optional<Category> actCategoryOptional2 = categoryRepository.findByDescription("Italian");
        Category actCategory2 = actCategoryOptional2.orElseThrow(IllegalArgumentException::new);
        hungarianSoup.getCategories().add(actCategory2);
        
        hungarianSoup.setDirections("This is a long description about the greates hungarian soup of all time");
        recipeRepository.save(hungarianSoup);

    }

}
