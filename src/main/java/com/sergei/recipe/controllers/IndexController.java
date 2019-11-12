package com.sergei.recipe.controllers;

import com.sergei.recipe.servises.RecipeServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
public class IndexController {
   private final RecipeServise recipeServise;

    public IndexController(RecipeServise recipeServise) {
        this.recipeServise = recipeServise;
    }


    @GetMapping({"","/","index"})
    public String getIndex(Model model){
        log.debug("I'm index controller");
      model.addAttribute("recipies",recipeServise.getRecipies());
        return "index";
    }
}
