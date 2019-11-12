package com.sergei.recipe.converters;

import com.sergei.recipe.commands.IngredientCommand;
import com.sergei.recipe.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class Ingredient_To_IngredientCommand implements Converter<Ingredient, IngredientCommand> {
    final UnitOfMeasure_To_UnitOfMeasureCommand unitOfMeasure_to_unitOfMeasureCommand;

    public Ingredient_To_IngredientCommand(UnitOfMeasure_To_UnitOfMeasureCommand unitOfMeasure_to_unitOfMeasureCommand) {
        this.unitOfMeasure_to_unitOfMeasureCommand = unitOfMeasure_to_unitOfMeasureCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient==null) {
            return null;
        }
     final  IngredientCommand ic=new IngredientCommand();
        ic.setId(ingredient.getId());
        ic.setAmount(ingredient.getAmount());
        if(ingredient.getRecipe()!=null) {
            ic.setRecipeId(ingredient.getRecipe().getId());
        }
        ic.setDescription(ingredient.getDescription());
        ic.setUom(unitOfMeasure_to_unitOfMeasureCommand.convert(ingredient.getUom()));

        return ic;

    }
}
