package com.example.demo.advquering.services;

import com.example.demo.advquering.entities.Ingredient;
import com.example.demo.advquering.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getAllIngredientStartWith(String start) {
        return this.ingredientRepository.findByNameStartingWith(start);
    }

    @Override
    public List<Ingredient> getAllIngredientsWhereIn(List<String> ingredients) {
        return this.ingredientRepository.findByNameInOrderByPriceAsc(ingredients);
    }

    @Override
    public void deleteIngredientByGivenName(String name) {
        this.ingredientRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void updateIngredientsByPrice(BigDecimal price) {
        this.ingredientRepository.updateIngredientByPrice(price);
    }

    @Override
    @Transactional
    public void updateIngredientByNames(List<String> names) {
        this.ingredientRepository.updateIngredientsByNames(names);
    }
}
