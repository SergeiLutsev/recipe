package com.sergei.recipe.repositories;

import com.sergei.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMesureRepository_IntegrationTest {
    @Autowired
    UnitOfMesureRepository unitOfMesureRepository;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> uom=unitOfMesureRepository.findByDescription("Tablespoon");

        assertEquals("Tablespoon",uom.get().getDescription());
    }

    @Test
    public void findByDescriptionCup(){
        Optional<UnitOfMeasure> uom=unitOfMesureRepository.findByDescription("Cap");
        assertEquals("Cap",uom.get().getDescription());

    }}