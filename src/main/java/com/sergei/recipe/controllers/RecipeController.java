package com.sergei.recipe.controllers;

import com.sergei.recipe.commands.RecipeCommand;
import com.sergei.recipe.domain.Ingredient;
import com.sergei.recipe.domain.Notes;
import com.sergei.recipe.domain.Recipe;
import com.sergei.recipe.servises.RecipeServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@Slf4j
@Controller
public class RecipeController {
  private final RecipeServise recipeServise;

    public RecipeController(RecipeServise recipeServise) {
        this.recipeServise = recipeServise;
    }

    @GetMapping({"/recipe/{id}/show"})
    private String showById(@PathVariable String id, Model model){
        Recipe rc=recipeServise.findById(Long.valueOf(id));
        Notes notes=rc.getNotes();

        model.addAttribute("recipe",rc);
        return "/recipe/show";
    }

    @GetMapping({"/recipe/new"})
    public String createRecipe(Model model){

        model.addAttribute("recipe",new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand rc=recipeServise.saveRrecipeCommand(command);
        return "redirect:/recipe/"+rc.getId()+"/show";
    }

    @GetMapping({"/recipe/{id}/update"})
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeServise.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping({"/recipe/{id}/delete"})
    public String deleteById(@PathVariable String id){
        recipeServise.deleteById(Long.valueOf(id));
        log.debug("id "+id+" was deleted");
        return "redirect:/";
    }

}
