package com.CookApp.CookApp.model;

import jakarta.validation.constraints.NotNull;

public class IngredientQuantityDTO {
    @NotNull
    private int ingredientId;
    @NotNull
    private double Quantity;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }
}
