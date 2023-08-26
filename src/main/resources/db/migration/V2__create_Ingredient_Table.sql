CREATE TABLE Ingredient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    protein_quantity_per100gram DOUBLE,
    carb_quantity_per100gram DOUBLE,
    fat_quantity_per100gram DOUBLE
);