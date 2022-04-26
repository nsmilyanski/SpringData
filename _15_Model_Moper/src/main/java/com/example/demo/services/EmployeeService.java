package com.example.demo.services;

import com.example.demo.dto.EmployeeSpringDTO;
import com.example.demo.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findOneById(int id);

    void save(Employee employee);


    List<EmployeeSpringDTO> findEmployeeBornBefore(int year);
}
