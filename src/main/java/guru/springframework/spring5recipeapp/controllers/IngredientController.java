package guru.springframework.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.RecipeService;

@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable Long recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredient/list";
    }

}
