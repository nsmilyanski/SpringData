package com.example.demo.advquering.services;

import com.example.demo.advquering.entities.Shampoo;
import com.example.demo.advquering.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<Shampoo> selectBySize(Size size);
    List<Shampoo> selectBySizeOrLabelId(Size medium, long labelId);

    List<Shampoo> selectAllShampoosWithPriceHigherThan(BigDecimal price);

    int getCountOfShampoosLowerThan(BigDecimal price);

    List<Shampoo> getAllShampoosWhereIngridentsAre(Set<String> ingredients);

    List<Shampoo> getAllShampoosWhereIngredientsLessThen(int number);
}
