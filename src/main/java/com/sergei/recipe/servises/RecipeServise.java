package com.sergei.recipe.servises;

import com.sergei.recipe.commands.RecipeCommand;
import com.sergei.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeServise {
    Set<Recipe> getRecipies();
    Recipe findById(Long id);
    RecipeCommand saveRrecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(Long id);
    void deleteById(Long id);
}
