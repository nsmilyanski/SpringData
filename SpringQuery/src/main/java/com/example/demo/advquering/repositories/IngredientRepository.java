package com.example.demo.advquering.repositories;

import com.example.demo.advquering.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartingWith(String start);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> ingredients);

    void deleteByName(String name);

    @Modifying
    @Query("update Ingredient " +
            "set price = price * :multiplayer")
    void updateIngredientByPrice(@Param("multiplayer") BigDecimal price);

    @Modifying
    @Query("update Ingredient " +
            "set price = price * 1.1 " +
            "where name in (:list)")
    void updateIngredientsByNames(@Param("list") List<String> names);
}
