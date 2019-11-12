package com.sergei.recipe.servises;

import com.sergei.recipe.commands.IngredientCommand;
import com.sergei.recipe.converters.IngredientCommand_To_Ingredient;
import com.sergei.recipe.converters.Ingredient_To_IngredientCommand;
import com.sergei.recipe.domain.Ingredient;
import com.sergei.recipe.domain.Recipe;
import com.sergei.recipe.repositories.RecipeRepository;
import com.sergei.recipe.repositories.UnitOfMesureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Service
public class IngridientServise_impl implements IngridientServise {
    final private RecipeServise recipeServise;
    final private Ingredient_To_IngredientCommand ingredient_to_ingredientCommand;
    final private RecipeRepository recipeRepository;
    final private UnitOfMesureRepository unitOfMesureRepository;
    final private IngredientCommand_To_Ingredient ingredientCommand_to_ingredient;

    public IngridientServise_impl(RecipeServise recipeServise,
                                  Ingredient_To_IngredientCommand ingredient_to_ingredientCommand, RecipeRepository recipeRepository, UnitOfMesureRepository unitOfMesureRepository, IngredientCommand_To_Ingredient ingredientCommand_to_ingredient) {
        this.recipeServise = recipeServise;
        this.ingredient_to_ingredientCommand = ingredient_to_ingredientCommand;
        this.recipeRepository = recipeRepository;
        this.unitOfMesureRepository = unitOfMesureRepository;
        this.ingredientCommand_to_ingredient = ingredientCommand_to_ingredient;
    }

    @Override
    public IngredientCommand getByRecipeIdAndIngridientID(Long recipId, Long ingridientId) {
        Recipe recipe=recipeServise.findById(recipId);
        if(recipe==null){
            log.debug("Recipe id = "+recipId+" not found");
        }

        Optional<IngredientCommand> ingCom=recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingridientId))
                .map(ingredient -> ingredient_to_ingredientCommand.convert(ingredient))
                .findFirst();
        return ingCom.get();
    }

    @Transactional
    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipe = recipeRepository.findById(command.getRecipeId());
        if(!recipe.isPresent()){
            log.debug("Can not find Recipe with id = "+command.getId());
            return new IngredientCommand();
        }else {
            Ingredient ingFound=recipe.get().getIngredients()
                                        .stream()
                                        .filter(ingredient -> ingredient.getId().equals(command.getId()))
                                        .findFirst()
                                        .orElse(null);
            if(ingFound!=null){
                ingFound.setAmount(command.getAmount());
                ingFound.setDescription(command.getDescription());
                ingFound.setUom(unitOfMesureRepository.findById(command.getUom().getId()).get());
            }else{
                recipe.get().addIngridients(ingredientCommand_to_ingredient.convert(command));
            }

            Recipe savedRecipe=recipeRepository.save(recipe.get());
            Optional<Ingredient> saveIngridiantOptional=savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();
            if(!saveIngridiantOptional.isPresent()){
                saveIngridiantOptional=savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                        .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                        .filter(ingredient -> ingredient.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            return ingredient_to_ingredientCommand.convert(saveIngridiantOptional.get());
        }

    }

    @Override
    @Transactional
    public void deleteByIdRecipAndIdIngredient(Long recId, Long ingId) {


        Recipe recipe=recipeServise.findById(recId);

        Ingredient ingredient = recipe.getIngredients().stream()
                .filter(ingr -> ingr.getId().equals(ingId))
                .findFirst()
                .get();

        ingredient.setRecipe(null);
        boolean removed = recipe.getIngredients().remove(ingredient);
        log.debug("Ingridient was removed : "+removed);
        Recipe save = recipeRepository.save(recipe);
        log.debug("Recipe was update : "+save.getId());
    }
}
