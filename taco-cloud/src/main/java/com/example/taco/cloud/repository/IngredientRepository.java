package com.example.taco.cloud.repository;

import com.example.taco.cloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {}
