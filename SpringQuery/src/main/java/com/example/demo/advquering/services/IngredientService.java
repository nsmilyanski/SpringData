package com.example.demo.advquering.services;

import com.example.demo.advquering.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    List<Ingredient> getAllIngredientStartWith(String start);

    List<Ingredient> getAllIngredientsWhereIn(List<String> ingredients);

    void deleteIngredientByGivenName(String name);

    void updateIngredientsByPrice(BigDecimal price);

    void updateIngredientByNames(List<String> names);
}
