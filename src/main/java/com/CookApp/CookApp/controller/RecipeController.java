package com.CookApp.CookApp.controller;

import com.CookApp.CookApp.model.*;
import com.CookApp.CookApp.service.RecipeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("")
    public ResponseEntity<List<Recipe>> getAll() {
        return ResponseEntity.ok(recipeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable Integer id) {
        return recipeService.getById(id)
                .map(e -> ResponseEntity.ok(e))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("")
    public ResponseEntity<Recipe> post(@Valid @RequestBody RecipeDto source) {
        try {
            Recipe saved = recipeService.save(source);
            return ResponseEntity.created(URI.create("/" + saved.getId())).build();

        } catch (IllegalArgumentException e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
//
//    @GetMapping("/test")
//    public void test() {
//        logger.info("Jestem w metodzie :    JD kurwe");
//        Recipe recipe = new Recipe();
//        recipe.setName("pomidorowa");
//        recipe.setDescription("po rosole z wczoraj");
//        recipe.setCategory(RecipeCategory.SOUP);
//        recipe.setDifficultyLevel(DifficultyLevel.EASY);
//        Ingredient ingredient = new Ingredient();
//        ingredient.setName("pomidor");
//        ingredient.setCarbQuantityPer100Gram(10.0);
//        ingredient.setDescription("malinowy ozarowski");
//
//        Recipe savedRec = repository.save(recipe);
//        Ingredient savedIngr = ingredientRepository.save(ingredient);
//
//        RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, recipe, 22);
////        recipeIngredient.setId(new RecipeIngredientKey(savedRec.getId(),savedIngr.getId()));
////        recipeIngredient.setRecipe(recipe);
////        recipeIngredient.setIngredient(ingredient);
////        recipeIngredient.setQuantity(69);
//        Recipe recipe2 = new Recipe();
//        recipe2.setName("pgryzbowa");
//        recipe2.setDescription("po pomidorowe z wczoraj");
//        recipe2.setCategory(RecipeCategory.DESSERT);
//        recipe2.setDifficultyLevel(DifficultyLevel.HARD);
//        //recipe2.addIngredient(ingredient);
//        repository.save(recipe2);
//
//        recipeIngredientRepository.save(new RecipeIngredient(ingredient, recipe2, 55));
//        recipeIngredientRepository.save(recipeIngredient);

//          }

    @GetMapping("/test2")
    @Transactional
    public void test2() {
//        Recipe recipe = new Recipe();
//        recipe.setName("test2");
//        recipe.setDescription("test 2 desc");
//        logger.info("\t\ttest2");
//        Ingredient ingredient = ingredientRepository.findAll().stream().filter(e-> e.getId() == 13).findAny().get();
//
//        logger.info(ingredient != null?"nie null":"null");
//        logger.info("rozmiar przepisow : " +ingredient.getRecipes().size());
//        ingredient.getRecipes().forEach( e-> e.getRecipe().setName("hihi"));


    }
}
