package com.CookApp.CookApp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface RecipeRepository  extends JpaRepository<Recipe,Integer> {
}
