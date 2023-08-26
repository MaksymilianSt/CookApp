CREATE TABLE Recipe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    time_needed_in_min INT,
    difficulty_level VARCHAR(255),
    recipe_category VARCHAR(255)
);



