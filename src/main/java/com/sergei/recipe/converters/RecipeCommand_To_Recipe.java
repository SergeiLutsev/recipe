package com.sergei.recipe.converters;

import com.sergei.recipe.commands.RecipeCommand;
import com.sergei.recipe.domain.Category;
import com.sergei.recipe.domain.Ingredient;
import com.sergei.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeCommand_To_Recipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommand_To_Notes notesCommand_to_notes;
    private final IngredientCommand_To_Ingredient ingredientCommand_to_ingredient;
    private final CategoryCommand_To_Category categoryCommand_to_category;

    public RecipeCommand_To_Recipe(NotesCommand_To_Notes notesCommand_to_notes,
                                   IngredientCommand_To_Ingredient ingredientCommand_to_ingredient,
                                   CategoryCommand_To_Category categoryCommand_to_category) {
        this.notesCommand_to_notes = notesCommand_to_notes;
        this.ingredientCommand_to_ingredient = ingredientCommand_to_ingredient;
        this.categoryCommand_to_category = categoryCommand_to_category;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand==null) {
            return null;
        }
        final Recipe rc=new Recipe();
        rc.setId(recipeCommand.getId());
        rc.setCookTime(recipeCommand.getCookTime());
        rc.setDescription(recipeCommand.getDescription());
        rc.setDifficulty(recipeCommand.getDifficulty());
        rc.setDirection(recipeCommand.getDirection());
        rc.setImage(recipeCommand.getImage());
        rc.setPrepTime(recipeCommand.getPrepTime());
        rc.setServing(recipeCommand.getServing());
        rc.setSource(recipeCommand.getSource());
        rc.setUrl(recipeCommand.getUrl());
        rc.setNotes(notesCommand_to_notes.convert(recipeCommand.getNotes()));

        Set<Category> categories=new HashSet<>();
        recipeCommand.getCategories().forEach(categoryCommand -> {
            categories.add(categoryCommand_to_category.convert(categoryCommand));
        });
        rc.setCategories(categories);

        Set<Ingredient> ingredients=new HashSet<>();
        recipeCommand.getIngredients().forEach(ingredientCommand -> {
            ingredients.add(ingredientCommand_to_ingredient.convert(ingredientCommand));
        });
        rc.setIngredients(ingredients);

        return rc;
    }
}
