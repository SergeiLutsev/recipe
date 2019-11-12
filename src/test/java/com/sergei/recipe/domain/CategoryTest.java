package com.sergei.recipe.domain;


import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class CategoryTest {
    Category category;

    @Before
    public void setup(){
        category=new Category();
    }

    @Test
    public void getId() {
        Long idValue = 5L;
        category.setId(idValue);
        assertEquals(idValue,category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}
