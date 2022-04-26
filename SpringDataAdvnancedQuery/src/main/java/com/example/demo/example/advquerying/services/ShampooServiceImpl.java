package com.example.demo.example.advquerying.services;

import com.example.demo.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShampooServiceImpl implements ShampooService {

  private final  ShampooRepository shampooRepository;

@Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


}
