package guru.springframework.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import guru.springframework.spring5recipeapp.services.RecipeService;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.listRecipes());
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    
    @GetMapping("/menu")
    public String getMenuPage() {
        return "menu";
    }
}
