package com.CookApp.CookApp.model;

import java.util.List;

public class RecipeDto {
    private Recipe recipe;
    private List<IngredientQuantityDTO> ingredientQuantityDTOS;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<IngredientQuantityDTO> getIngredientQuantityDTOS() {
        return ingredientQuantityDTOS;
    }

    public void setIngredientQuantityDTOS(List<IngredientQuantityDTO> ingredientQuantityDTOS) {
        this.ingredientQuantityDTOS = ingredientQuantityDTOS;
    }
}
