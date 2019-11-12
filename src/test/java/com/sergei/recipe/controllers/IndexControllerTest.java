package com.sergei.recipe.controllers;

import com.sergei.recipe.servises.RecipeServise;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


public class IndexControllerTest {
    IndexController indexController;
    @Mock
    RecipeServise recipeServise;
    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController=new IndexController(recipeServise);

    }

    @Test
    public void getIndex() {
        String test=indexController.getIndex(model);
        assertEquals("index",test);
        verify(recipeServise, times(1)).getRecipies();
        verify(model, times(1)).addAttribute(eq("recipies"), anySet());

    }
}
