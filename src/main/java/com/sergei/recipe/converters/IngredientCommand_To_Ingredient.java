package com.sergei.recipe.converters;

import com.sergei.recipe.commands.IngredientCommand;
import com.sergei.recipe.domain.Ingredient;
import com.sergei.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommand_To_Ingredient implements Converter<IngredientCommand, Ingredient> {
    final UnitOfMeasureCommand_To_UnitOfMeasure unitOfMeasureCommand_to_unitOfMeasure;

    public IngredientCommand_To_Ingredient(UnitOfMeasureCommand_To_UnitOfMeasure unitOfMeasureCommand_to_unitOfMeasure) {
        this.unitOfMeasureCommand_to_unitOfMeasure = unitOfMeasureCommand_to_unitOfMeasure;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand==null) {
            return null;
        }

        final Ingredient ingredient=new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setUom(unitOfMeasureCommand_to_unitOfMeasure.convert(ingredientCommand.getUom()));
        if(ingredientCommand.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setId(ingredientCommand.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngridients(ingredient);
        }

        return ingredient;
    }
}
