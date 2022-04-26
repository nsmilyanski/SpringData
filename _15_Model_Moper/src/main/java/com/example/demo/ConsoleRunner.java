package com.example.demo;

import com.example.demo.dto.EmployeeSpringDTO;
import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.EmployeeServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println();

//       this.persist();

        List<EmployeeSpringDTO> managerOp = this.employeeService.findEmployeeBornBefore(1990);


        managerOp.forEach(System.out::println);

        ModelMapper modelMapper = new ModelMapper();



    }
    private void persist(){

        Employee manager = new Employee(
                "Mrs.",
                "Manager",
                BigDecimal.ONE,
                LocalDate.now(),
                null);

        Employee first = new Employee(
                "first",
                "last",
                BigDecimal.TEN,
                LocalDate.now(),
                manager);

        Employee second = new Employee(
                "second",
                "last",
                BigDecimal.TEN,
                LocalDate.now(),
                manager);

        employeeService.save(first);
    }
}
