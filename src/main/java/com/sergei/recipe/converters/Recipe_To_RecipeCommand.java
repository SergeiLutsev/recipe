package com.sergei.recipe.converters;

import com.sergei.recipe.commands.CategoryCommand;
import com.sergei.recipe.commands.IngredientCommand;
import com.sergei.recipe.commands.RecipeCommand;
import com.sergei.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class Recipe_To_RecipeCommand implements Converter<Recipe, RecipeCommand>  {
    final private Category_To_CategoryCommand category_to_categoryCommand;
    final  private  Ingredient_To_IngredientCommand ingredient_to_ingredientCommand;
    final private  Notes_To_NotesCommand notes_to_notesCommand;

    Recipe_To_RecipeCommand(Category_To_CategoryCommand category_to_categoryCommand,
                            Ingredient_To_IngredientCommand ingredient_to_ingredientCommand,
                            Notes_To_NotesCommand notes_to_notesCommand) {
        this.category_to_categoryCommand = category_to_categoryCommand;
        this.ingredient_to_ingredientCommand = ingredient_to_ingredientCommand;
        this.notes_to_notesCommand = notes_to_notesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe==null) {
            return null;
        }

        final RecipeCommand rc=new RecipeCommand();
        rc.setId(recipe.getId());
        rc.setCookTime(recipe.getCookTime());
        rc.setDescription(recipe.getDescription());
        rc.setDifficulty(recipe.getDifficulty());
        rc.setDirection(recipe.getDirection());
        rc.setImage(recipe.getImage());
        rc.setPrepTime(recipe.getPrepTime());
        rc.setServing(recipe.getServing());
        rc.setSource(recipe.getSource());
        rc.setUrl(recipe.getUrl());

        Set<CategoryCommand> categories=new HashSet<>();
        recipe.getCategories().forEach(category -> {
            categories.add(category_to_categoryCommand.convert(category));
        });
        rc.setCategories(categories);

        Set<IngredientCommand> ingredients=new HashSet<>();
        recipe.getIngredients().forEach(ingredient -> {
            ingredients.add(ingredient_to_ingredientCommand.convert(ingredient));
        });
        rc.setIngredients(ingredients);

        return rc;
    }
}

