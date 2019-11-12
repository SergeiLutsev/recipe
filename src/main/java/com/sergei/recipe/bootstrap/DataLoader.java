package com.sergei.recipe.bootstrap;

import com.sergei.recipe.domain.*;
import com.sergei.recipe.repositories.CategoryRepository;
import com.sergei.recipe.repositories.RecipeRepository;
import com.sergei.recipe.repositories.UnitOfMesureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMesureRepository unitOfMesureRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMesureRepository unitOfMesureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;

        this.unitOfMesureRepository = unitOfMesureRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        recipeRepository.saveAll(getRecipies());

        log.debug("Data loaded....");
    }

    private List<Recipe> getRecipies() {
        List<Recipe> resipies = new ArrayList<>();

        Map<String, Category> catMap = getCategories();
        Map<String, UnitOfMeasure> unitMap = getUnites();

        //How to Make Perfect Guacamole
        Recipe rec1 = new Recipe();
        rec1.setDescription("Perfect Guacamole");

        rec1.setPrepTime(10);
        rec1.setCookTime(5);
        rec1.setServing(4);
        rec1.setSource("ELISE BAUER");
        rec1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        rec1.setDifficulty(Difficulty.EASY);

        rec1.setDirection("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");


        Notes gucNot = new Notes();
        gucNot.setNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
        gucNot.setRecipe(rec1);
        rec1.setNotes(gucNot);


        rec1.addIngridients(new Ingredient("ripe avocados", new BigDecimal(2), unitMap.get("each")));
        rec1.addIngridients(new Ingredient("Kosher salt", new BigDecimal(".5"), unitMap.get("Teaspoon")));
        rec1.addIngridients(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), unitMap.get("Tablespoon")));
        rec1.addIngridients(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), unitMap.get("Tablespoon")));
        rec1.addIngridients(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), unitMap.get("each")));
        rec1.addIngridients(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), unitMap.get("Tablespoon")));
        rec1.addIngridients(new Ingredient("freshly grated black pepper", new BigDecimal(1), unitMap.get("dash")));
        rec1.addIngridients(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), unitMap.get("each")));

        rec1.getCategories().add(catMap.get("Mexican"));
        rec1.getCategories().add(catMap.get("American"));

        resipies.add(rec1);

        // Spicy Grilled Chicken Tacos
        Recipe rec2 = new Recipe();
        rec2.setDescription("Spicy Grilled Chicken Tacos");

        rec2.setPrepTime(20);
        rec2.setCookTime(15);
        rec2.setServing(6);
        rec2.setSource("Sally Vargas");
        rec2.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        rec2.setDifficulty(Difficulty.MODERATE);

        rec2.setDirection("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!" +
                "The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");


        Notes gucNot2 = new Notes();
        gucNot2.setNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        gucNot2.setRecipe(rec2);
        rec2.setNotes(gucNot2);

        rec2.addIngridients(new Ingredient(" ancho chili powder", new BigDecimal(2), unitMap.get("Tablespoon")));
        rec2.addIngridients(new Ingredient("dried oregano", new BigDecimal(1), unitMap.get("Teaspoon")));
        rec2.addIngridients(new Ingredient("dried cumin", new BigDecimal(1), unitMap.get("Teaspoon")));
        rec2.addIngridients(new Ingredient("sugar", new BigDecimal(1), unitMap.get("Teaspoon")));
        rec2.addIngridients(new Ingredient("salt", new BigDecimal(".5"), unitMap.get("Teaspoon")));
        rec2.addIngridients(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), unitMap.get("each")));
        rec2.addIngridients(new Ingredient("finely grated orange zest", new BigDecimal(1), unitMap.get("Tablespoon")));
        rec2.addIngridients(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), unitMap.get("Tablespoon")));
        rec2.addIngridients(new Ingredient("olive oil", new BigDecimal(2), unitMap.get("Tablespoon")));
        rec2.addIngridients(new Ingredient(" skinless, boneless chicken thighs", new BigDecimal("1.25"), unitMap.get("pounds")));

        rec2.getCategories().add(catMap.get("Mexican"));
        rec2.getCategories().add(catMap.get("American"));

        resipies.add(rec2);

        return resipies;
    }

    private Map<String, UnitOfMeasure> getUnites() {
        Map<String, UnitOfMeasure> unitMap = new HashMap<>();
        Iterable<UnitOfMeasure> unitSet = unitOfMesureRepository.findAll();
        for (UnitOfMeasure unit : unitSet) {
            unitMap.put(unit.getDescription(), unit);
        }
        return unitMap;
    }

    private Map<String, Category> getCategories() {
        Map<String, Category> map = new HashMap<>();

        Iterable<Category> catSet = categoryRepository.findAll();

        for (Category cat : catSet) {
            map.put(cat.getDescription(), cat);
        }

        return map;
    }


}
