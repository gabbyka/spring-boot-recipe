package guru.springframework.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import guru.springframework.spring5recipeapp.services.RandomService;

@Controller
public class IndexController {
    
    private final RandomService randomService;
    
    public IndexController(RandomService randomService) {
        super();
        this.randomService = randomService;
    }


    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("randomText", randomService.randomText());
        return "index";
    }
    
}
