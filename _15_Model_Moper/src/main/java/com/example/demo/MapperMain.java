package com.example.demo;

import com.example.demo.demo.DTO.ManagerDto;
import com.example.demo.demo.entities.Address;
import com.example.demo.demo.entities.Employee;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();

        Address address = new Address(1, "Pirin", 5, "Sandanski", "Bulgaria");

        Employee manager = new Employee("Nikola", "Kirilov", BigDecimal.TEN,
                LocalDate.of(1993, 4, 25), address, true);

        Employee first = new Employee("Kiril", "Kirilov", BigDecimal.TEN,
                LocalDate.of(1988, 4, 25), address, true);

        Employee second = new Employee("Ivan", "Ivanov", BigDecimal.TEN,
                LocalDate.of(1968, 4, 25), address, true);

        manager.addEmployee(first);
        manager.addEmployee(second);


        ManagerDto dto = modelMapper.map(manager, ManagerDto.class);

        System.out.println(dto);
    }
}
