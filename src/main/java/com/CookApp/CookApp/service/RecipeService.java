package com.CookApp.CookApp.service;

import com.CookApp.CookApp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RecipeService(RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Optional<Recipe> getById(int id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public Recipe save(RecipeDto source) {
        var ingredients = source.getIngredientQuantityDTOS();

        if (!allIngredientsExistsInRepository(ingredients))
            throw new IllegalArgumentException("some ingredients does not exists");

        Recipe saved = recipeRepository.save(source.getRecipe());

        associateRecipeWithIngredients(saved, ingredients);
        return saved;
    }

    private boolean allIngredientsExistsInRepository(List<IngredientQuantityDTO> ingredients) {
        var repositoryIngredientsIds = recipeIngredientRepository.findAll()
                .stream()
                .map(e -> e.getIngredient().getId())
                .collect(Collectors.toList());

        return ingredients.stream()
                .map(e -> e.getIngredientId())
                .filter(e -> !repositoryIngredientsIds.contains(e))
                .count() == 0;
    }

    private void associateRecipeWithIngredients(Recipe recipe, List<IngredientQuantityDTO> ingredients) {
        ingredients.stream()
                .forEach(ingredient ->
                        {
                            try {
                                saveRecipeIngredient(recipe, ingredient);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }

    private void saveRecipeIngredient(Recipe recipe, IngredientQuantityDTO ingredient) throws Exception {
        recipeIngredientRepository.
                save(new RecipeIngredient(
                                ingredientRepository.findById(ingredient.getIngredientId()).orElseThrow(Exception::new)
                                , recipe
                                , ingredient.getQuantity()
                        )
                );
    }


}
