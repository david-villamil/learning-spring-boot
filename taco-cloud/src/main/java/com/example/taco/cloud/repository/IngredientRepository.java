package com.example.taco.cloud.repository;

import com.example.taco.cloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {}
