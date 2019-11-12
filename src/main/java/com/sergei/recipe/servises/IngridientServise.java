package com.sergei.recipe.servises;

import com.sergei.recipe.commands.IngredientCommand;

import java.util.Optional;

public interface IngridientServise {
    IngredientCommand getByRecipeIdAndIngridientID(Long recipId, Long ingridientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteByIdRecipAndIdIngredient(Long recId, Long ingId);
}
