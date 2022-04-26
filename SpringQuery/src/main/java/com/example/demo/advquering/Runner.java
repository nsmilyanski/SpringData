package com.example.demo.advquering;

import com.example.demo.advquering.entities.Ingredient;
import com.example.demo.advquering.entities.Shampoo;
import com.example.demo.advquering.repositories.ShampooRepository;
import com.example.demo.advquering.services.IngredientService;
import com.example.demo.advquering.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public Runner(ShampooRepository shampooRepository, ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.updateIngredientsByGivenNames();
    }
    public void getAllShampooWithSize(){
        Set<String> ingredients = Set.of("Berry", "Mineral-Collagen");

        List<Shampoo> shampoos = shampooService.getAllShampoosWhereIngridentsAre(ingredients);

        shampoos.stream()
                .forEach(s -> System.out.println(s.toString()) );
    }
    public void getAllIngredients(){
        List<String> ingredients = List.of("Lavender",
                "Herbs", "Apple");
        List<Ingredient> allIngredientStartWith = ingredientService.getAllIngredientsWhereIn(ingredients);

        allIngredientStartWith.forEach(System.out::println);
    }
    private void getCountOfShampoo(){
        int count = 5;

        List<Shampoo> shampoos = shampooService.getAllShampoosWhereIngredientsLessThen(count);

        shampoos.stream()
                .forEach(s -> System.out.println(s.toString()) );



    }
    private void deleteIngredientsByName(){
        String name = "";
        ingredientService.deleteIngredientByGivenName(name);
    }
    private void updateIngredientsByPrice(){
        BigDecimal price = new BigDecimal("1.1");

        this.ingredientService.updateIngredientsByPrice(price);
    }

    private void updateIngredientsByGivenNames(){
        List<String> names = List.of("Berry", "Mineral-Collagen");

        this.ingredientService.updateIngredientByNames(names);
    }

}
