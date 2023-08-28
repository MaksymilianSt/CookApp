package com.CookApp.CookApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Recipe's name cannot be empty !")
    private String name;
    private String description;
    @JsonIgnore
    private int timeNeededInMin;
    @Transient
    private String timeNeeded;

    public String getTimeNeeded() {
        return  Integer.divideUnsigned(timeNeededInMin,60)+ "h:" +timeNeededInMin % 60 + "min";
    }

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;
    @Column(name = "recipe_category")
    @Enumerated(EnumType.STRING)
    private RecipeCategory category;
    @OneToMany(mappedBy = "recipe")
    @JsonManagedReference
    List<RecipeIngredient> ingredients = new ArrayList<>();

    public Recipe() {
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

    public int getTimeNeededInMin() {
        return timeNeededInMin;
    }

    public void setTimeNeededInMin(int timeNeededInMin) {
        this.timeNeededInMin = timeNeededInMin;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
