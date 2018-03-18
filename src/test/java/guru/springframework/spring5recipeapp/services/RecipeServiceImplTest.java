package guru.springframework.spring5recipeapp.services;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import guru.springframework.spring5recipeapp.model.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeServiceImpl;
    
    @Mock
    RecipeRepository recipeRepository;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }
    
    @Test
    public void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptinal = Optional.of(recipe);
        
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptinal);
        
        Recipe returnedRecipe = recipeServiceImpl.getRecipeById(1L);
        
        assertNotNull(returnedRecipe);
        
    }
}
