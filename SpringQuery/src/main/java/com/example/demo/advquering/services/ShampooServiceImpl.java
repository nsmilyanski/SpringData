package com.example.demo.advquering.services;

import com.example.demo.advquering.entities.Shampoo;
import com.example.demo.advquering.entities.Size;
import com.example.demo.advquering.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

  private final ShampooRepository shampooRepository;

@Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {

    this.shampooRepository = shampooRepository;
    }


    @Override
    public List<Shampoo> selectBySize(Size size) {
        return this.shampooRepository.findBySize(size);
    }

    @Override
    public List<Shampoo> selectBySizeOrLabelId(Size size, long labelId) {
        return this.shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, labelId);
    }

    @Override
    public List<Shampoo> selectAllShampoosWithPriceHigherThan(BigDecimal price) {
        return shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int getCountOfShampoosLowerThan(BigDecimal price) {
        return this.shampooRepository.countByPriceLessThan(price);
    }

    @Override
    @Transactional
    public List<Shampoo> getAllShampoosWhereIngridentsAre(Set<String> ingredients) {
        return this.shampooRepository.findByIngredientsNames(ingredients);
    }

    @Override
    public List<Shampoo> getAllShampoosWhereIngredientsLessThen(int number) {
      return this.shampooRepository.findByIngredientsCountLessThan(number);
    }
}
