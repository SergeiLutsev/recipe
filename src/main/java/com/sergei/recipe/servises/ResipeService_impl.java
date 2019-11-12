package com.sergei.recipe.servises;

import com.sergei.recipe.commands.RecipeCommand;
import com.sergei.recipe.converters.RecipeCommand_To_Recipe;
import com.sergei.recipe.converters.Recipe_To_RecipeCommand;
import com.sergei.recipe.domain.Recipe;
import com.sergei.recipe.exceptions.NotFoundException;
import com.sergei.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ResipeService_impl implements RecipeServise {
    private final RecipeRepository recipeRepository;
    private final RecipeCommand_To_Recipe recipeCommand_to_recipe;
    private final Recipe_To_RecipeCommand recipe_to_recipeCommand;

    public ResipeService_impl(RecipeRepository recipeRepository,
                              RecipeCommand_To_Recipe recipeCommand_to_recipe,
                              Recipe_To_RecipeCommand recipe_to_recipeCommand) {

        this.recipeRepository = recipeRepository;
        this.recipeCommand_to_recipe=recipeCommand_to_recipe;
        this.recipe_to_recipeCommand=recipe_to_recipeCommand;
    }

    @Override
    public Set<Recipe> getRecipies() {
        log.debug("I'm Recipe service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optRecipe=recipeRepository.findById(id);
        if(!optRecipe.isPresent()){
           // throw new RuntimeException("Recipe not found!!!");
            throw new NotFoundException("Recipe id is not correct");
        }
        return optRecipe.get();
    }


    @Override
    @Transactional
    public RecipeCommand saveRrecipeCommand(RecipeCommand recipeCommand) {
            Recipe recipe=recipeCommand_to_recipe.convert(recipeCommand);
            Recipe saveRecipe=recipeRepository.save(recipe);
            RecipeCommand recCom=recipe_to_recipeCommand.convert(saveRecipe);
            return  recCom;
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        RecipeCommand rc=recipe_to_recipeCommand.convert(findById(id));
        return rc;
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
