package com.sergei.recipe.bootstrap;

import com.sergei.recipe.domain.Category;
import com.sergei.recipe.domain.UnitOfMeasure;
import com.sergei.recipe.repositories.CategoryRepository;
import com.sergei.recipe.repositories.RecipeRepository;
import com.sergei.recipe.repositories.UnitOfMesureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class DataLoaderMySql implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMesureRepository unitOfMesureRepository;

    public DataLoaderMySql(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMesureRepository unitOfMesureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMesureRepository = unitOfMesureRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Load MySql data");
        if(categoryRepository.count()==0) {
            createCategory();
            log.debug("Load category....");
        }
        if(unitOfMesureRepository.count()==0) {
            createUM();
            log.debug("Load Unit of measure");
        }
    }

    private void createUM() {
        UnitOfMeasure uom1=new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMesureRepository.save(uom1);

        UnitOfMeasure uom2=new UnitOfMeasure();
        uom2.setDescription("Tablespoon");
        unitOfMesureRepository.save(uom2);

        UnitOfMeasure uom3=new UnitOfMeasure();
        uom3.setDescription("Cap");
        unitOfMesureRepository.save(uom3);

        UnitOfMeasure uom4=new UnitOfMeasure();
        uom4.setDescription("Pinch");
        unitOfMesureRepository.save(uom4);

        UnitOfMeasure uom5=new UnitOfMeasure();
        uom5.setDescription("Ounce");
        unitOfMesureRepository.save(uom5);

        UnitOfMeasure uom6=new UnitOfMeasure();
        uom6.setDescription("each");
        unitOfMesureRepository.save(uom6);

        UnitOfMeasure uom7=new UnitOfMeasure();
        uom7.setDescription("pounds");
        unitOfMesureRepository.save(uom7);

        UnitOfMeasure uom8=new UnitOfMeasure();
        uom8.setDescription("dash");
        unitOfMesureRepository.save(uom8);
    }

    private void createCategory() {
        Category catMex=new Category();
        catMex.setDescription("Mexican");
        categoryRepository.save(catMex);

        Category catAm=new Category();
        catMex.setDescription("American");
        categoryRepository.save(catMex);

        Category catFastFood=new Category();
        catFastFood.setDescription("Fast food");
        categoryRepository.save(catFastFood);

        Category catItal=new Category();
        catItal.setDescription("Italian");
        categoryRepository.save(catItal);
    }
}
