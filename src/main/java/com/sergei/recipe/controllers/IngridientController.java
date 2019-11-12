package com.sergei.recipe.controllers;

import com.sergei.recipe.commands.IngredientCommand;
import com.sergei.recipe.commands.RecipeCommand;
import com.sergei.recipe.commands.UnitOfMeasureCommand;
import com.sergei.recipe.domain.Recipe;
import com.sergei.recipe.servises.IngridientServise;
import com.sergei.recipe.servises.RecipeServise;
import com.sergei.recipe.servises.UomServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngridientController {
    final private RecipeServise recipeServise;
    final private IngridientServise ingridientServise;
    final  private UomServise uomServise;

    public IngridientController(RecipeServise recipeServise, IngridientServise ingridientServise, UomServise uomServise) {
        this.recipeServise = recipeServise;
        this.ingridientServise = ingridientServise;
        this.uomServise = uomServise;
    }

       @GetMapping({"/recipe/{id}/ingridient"})
    public String getIngridientsList(@PathVariable String id, Model model){
        log.debug("Ingridient list mapping");

        model.addAttribute("recipe",recipeServise.findCommandById(Long.valueOf(id)));
        return "/recipe/ingridient/list";
    }


    @GetMapping({"/recipe/{recID}/ingridient/{ingID}/show"})
    public String getByRecipeIdAndIngridientID(@PathVariable String recID,
                                               @PathVariable String ingID,
                                               Model model){
        model.addAttribute("ingridient",ingridientServise.getByRecipeIdAndIngridientID(Long.valueOf(recID),
                                                                                        Long.valueOf(ingID)));

        return "/recipe/ingridient/show";
    }


    @GetMapping({"/recipe/{recID}/ingridient/{ingID}/update"})
    public String formToUpdate(@PathVariable String recID,
                               @PathVariable String ingID,
                               Model model) {
        model.addAttribute("ingridient",ingridientServise.getByRecipeIdAndIngridientID(Long.valueOf(recID),
                Long.valueOf(ingID)));
        model.addAttribute("umoList",uomServise.getAllUomComand());
        return "recipe/ingridient/ingridientform";
    }

    @PostMapping("/recipe/{recID}/ingridient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand saveommand = ingridientServise.saveIngredientCommand(command);
        log.debug("Save ingridients");
        return "redirect:/recipe/"+saveommand.getRecipeId()+"/ingridient/"+saveommand.getId()+"/show";
    }

    @GetMapping({"/recipe/{recId}/ingridient/new"})
    public String newRecipe(@PathVariable String recId, Model model){
        RecipeCommand recipeCommand=recipeServise.findCommandById(Long.valueOf(recId));
        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recId));
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingridient",ingredientCommand);
        model.addAttribute("umoList",uomServise.getAllUomComand());
        return "recipe/ingridient/ingridientform";
    }


    @GetMapping({"/recipe/{recID}/ingridient/{ingID}/delete"})
    public String deleteByRecipeIdAndIngridientID(@PathVariable String recID,
                                               @PathVariable String ingID,
                                               Model model){
       ingridientServise.deleteByIdRecipAndIdIngredient(Long.valueOf(recID),Long.valueOf(ingID));

        return "redirect:/recipe/"+recID+"/ingridient";
    }
}
