package com.CookApp.CookApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient {
    @EmbeddedId
    RecipeIngredientKey id;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")//
    private Ingredient ingredient;

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipeId")
    @JsonBackReference
    private Recipe recipe;

    private double Quantity;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredient, Recipe recipe, double quantity) {
        this.id = new RecipeIngredientKey(ingredient.getId(), recipe.getId());
        this.ingredient = ingredient;
        this.recipe = recipe;
        Quantity = quantity;
    }

    public RecipeIngredientKey getId() {
        return id;
    }

    public void setId(RecipeIngredientKey id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }
}
