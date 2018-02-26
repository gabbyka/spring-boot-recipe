package guru.springframework.spring5recipeapp.loader;

import java.math.BigDecimal;
import java.util.HashSet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.spring5recipeapp.model.Difficulity;
import guru.springframework.spring5recipeapp.model.Ingredient;
import guru.springframework.spring5recipeapp.model.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {
    
    private final RecipeRepository recipeRepository;
    
    
    public DatabaseLoader(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        HashSet<Ingredient> ingerdients = new HashSet<>();
        
        Ingredient soupSaltIngerdient = new Ingredient();
        soupSaltIngerdient.setAmount(BigDecimal.valueOf(20L));
        soupSaltIngerdient.setDescription("salt");
        
        ingerdients.add(soupSaltIngerdient);
        
        Ingredient soupMilkIngerdient = new Ingredient();
        soupMilkIngerdient.setAmount(BigDecimal.valueOf(20L));
        soupMilkIngerdient.setDescription("salt");
        
        ingerdients.add(soupMilkIngerdient);
        
        
        Recipe hungarianSoup = new Recipe();
        hungarianSoup.setDifficulity(Difficulity.EASY);
        hungarianSoup.setCookTime(60);
        hungarianSoup.setDescription("Good hungarian soup!");
        //hungarianSoup.setIngredients(ingredients);
        
        recipeRepository.save(hungarianSoup);
        
    }

}
