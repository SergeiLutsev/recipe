package com.sergei.recipe.servises;

import com.sergei.recipe.converters.RecipeCommand_To_Recipe;
import com.sergei.recipe.converters.Recipe_To_RecipeCommand;
import com.sergei.recipe.domain.Recipe;
import com.sergei.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ResipeService_implTest {
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommand_To_Recipe recipeCommand_to_recipe;
    @Mock
    Recipe_To_RecipeCommand recipe_to_recipeCommand;

    ResipeService_impl service;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service=new ResipeService_impl(recipeRepository,recipeCommand_to_recipe,recipe_to_recipeCommand);
    }

    @Test
    public void getRecipies() {
        Recipe rc=new Recipe();
        rc.setId(1L);

        Set<Recipe> set=new HashSet<>();
        set.add(rc);
        when(recipeRepository.findAll()).thenReturn(set);

        Set<Recipe> rsps=service.getRecipies();
        assertEquals(1,rsps.size());
    }

    @Test
    public void findById() {
        Long recId=1L;
        Recipe rcData=new Recipe();
        rcData.setId(recId);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(rcData));

        Recipe rc=service.findById(recId);

        assertNotNull("Null recipe returned",rc);

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();

    }
}