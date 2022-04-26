package com.example.demo.example.advquerying.repositories;

import com.example.demo.example.advquerying.entities.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
}
