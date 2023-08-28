package com.CookApp.CookApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "Ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "ingredient's name cannot be empty !")
    private String name;
    private String description;
    @Column(name = "protein_quantity_per100gram")
    private double proteinQuantityPer100Gram;
    @Column(name = "carb_quantity_per100gram")
    private double carbQuantityPer100Gram;
    @Column(name = "fat_quantity_per100gram")
    private double fatQuantityPer100Gram;
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    @JsonIgnore
    List<RecipeIngredient> recipes;

    public Ingredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProteinQuantityPer100Gram() {
        return proteinQuantityPer100Gram;
    }

    public void setProteinQuantityPer100Gram(double proteinQuantityPer100Gram) {
        this.proteinQuantityPer100Gram = proteinQuantityPer100Gram;
    }

    public double getCarbQuantityPer100Gram() {
        return carbQuantityPer100Gram;
    }

    public void setCarbQuantityPer100Gram(double carbQuantityPer100Gram) {
        this.carbQuantityPer100Gram = carbQuantityPer100Gram;
    }

    public double getFatQuantityPer100Gram() {
        return fatQuantityPer100Gram;
    }

    public void setFatQuantityPer100Gram(double fatQuantityPer100Gram) {
        this.fatQuantityPer100Gram = fatQuantityPer100Gram;
    }

    public List<RecipeIngredient> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeIngredient> recipes) {
        this.recipes = recipes;
    }
}
