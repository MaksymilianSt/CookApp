package com.CookApp.CookApp.controller;

import com.CookApp.CookApp.model.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeRepository repository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    public RecipeController(RecipeRepository repository, IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.repository = repository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<Recipe>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(e -> ResponseEntity.ok(e))
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping("")
//    public ResponseEntity<Recipe> post(@Valid @RequestBody Recipe toSave) {
//        Recipe saved = repository.save(toSave);
//        return ResponseEntity.created(URI.create("/" + saved.getId())).build();
//    }

    @PostMapping("")
    public ResponseEntity<Recipe> post(@Valid @RequestBody RecipeDto source) {


        Recipe saved = repository.save(source.getRecipe());

        var ingredients = source.getIngredientQuantityDTOS();
        List<Integer> repositoryIngredeintsIds = recipeIngredientRepository.findAll()
                .stream().map(e -> e.getIngredient().getId())
                .collect(Collectors.toList());

        List<Integer> sourceIds = ingredients.stream().map(o -> o.getIngredientId()).collect(Collectors.toList());


        if (sourceIds.stream()
                .filter(e -> !repositoryIngredeintsIds.contains(e))
                .count() != 0
        ) {
            return ResponseEntity.badRequest().build();
        }

        ingredients.stream()
                .forEach(ingredient ->
                        {
                            try {
                                recipeIngredientRepository.
                                        save(new RecipeIngredient(ingredientRepository.findById(ingredient.getIngredientId()).orElseThrow(Exception::new)
                                                , saved
                                                , ingredient.getQuantity()));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                );
        return ResponseEntity.created(URI.create("/" + saved.getId())).build();
    }

    @GetMapping("/test")
    public void test() {
        logger.info("Jestem w metodzie :    JD kurwe");
        Recipe recipe = new Recipe();
        recipe.setName("pomidorowa");
        recipe.setDescription("po rosole z wczoraj");
        recipe.setCategory(RecipeCategory.SOUP);
        recipe.setDifficultyLevel(DifficultyLevel.EASY);
        Ingredient ingredient = new Ingredient();
        ingredient.setName("pomidor");
        ingredient.setCarbQuantityPer100Gram(10.0);
        ingredient.setDescription("malinowy ozarowski");

        Recipe savedRec = repository.save(recipe);
        Ingredient savedIngr = ingredientRepository.save(ingredient);

        RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, recipe, 22);
//        recipeIngredient.setId(new RecipeIngredientKey(savedRec.getId(),savedIngr.getId()));
//        recipeIngredient.setRecipe(recipe);
//        recipeIngredient.setIngredient(ingredient);
//        recipeIngredient.setQuantity(69);
        Recipe recipe2 = new Recipe();
        recipe2.setName("pgryzbowa");
        recipe2.setDescription("po pomidorowe z wczoraj");
        recipe2.setCategory(RecipeCategory.DESSERT);
        recipe2.setDifficultyLevel(DifficultyLevel.HARD);
        //recipe2.addIngredient(ingredient);
        repository.save(recipe2);

        recipeIngredientRepository.save(new RecipeIngredient(ingredient, recipe2, 55));
        recipeIngredientRepository.save(recipeIngredient);

    }

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
