package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Controller {

    private final EmployeeRepository employeeRepository;

    Controller(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/getEmployes")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @PostMapping("/newEmployee")
    public ResponseEntity<String> addNewEmployee(@RequestBody Employee newEmployee) {
        if(newEmployee.getName() == null){
            return new ResponseEntity<>("Employee lacks name", HttpStatus.BAD_REQUEST);
        }

        if(newEmployee.getLastName() == null){
            return new ResponseEntity<>("Employee lacks last name", HttpStatus.BAD_REQUEST);
        }

        employeeRepository.save(new Employee(newEmployee.getName(), newEmployee.getLastName()));
        return new ResponseEntity<>("Employee added successfully", HttpStatus.OK);
    }
}
