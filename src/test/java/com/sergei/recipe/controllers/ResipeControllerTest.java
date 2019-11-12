package com.sergei.recipe.controllers;

import com.sergei.recipe.domain.Recipe;
import com.sergei.recipe.servises.RecipeServise;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ResipeControllerTest {
    @Mock
    RecipeServise recipeServise;
    @Mock
    Model model;

    RecipeController recipeController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController=new RecipeController(recipeServise);
    }

    @Test
    public void showById() throws Exception {
        MockMvc mvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        Recipe rcp=new Recipe();
        rcp.setId(1L);

        when(recipeServise.findById(anyLong())).thenReturn(rcp);

        mvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"));



    }
}