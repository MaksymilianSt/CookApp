CREATE TABLE recipe_ingredient (
    ingredient_id INT,
    recipe_id INT,
    quantity DOUBLE,
    FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id),
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id),
    PRIMARY KEY (ingredient_id, recipe_id)
);
