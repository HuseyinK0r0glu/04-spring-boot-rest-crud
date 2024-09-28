package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee employee = employeeService.findById(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return employee;
    }

    // add mapping for POST /employees - add new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){

        // also just in case they pass an id in JSON ... set id t zero
        // this is to force a save of new item ... instead of update

        employee.setId(0);

        Employee dbEmployee = employeeService.save(employee);

        return dbEmployee;
    }

    // add mapping for PUT /employees - update an existing employee

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        Employee dbEmployee = employeeService.save(employee);

        return dbEmployee;
    }

    // add mapping for /employees/{employeeId} - delete an existing employee

    @DeleteMapping("/employees/{studentId}")
    public String deleteEmployee(@PathVariable int studentId){

        Employee tempEmployee = employeeService.findById(studentId);

        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + studentId);
        }

        employeeService.deleteById(studentId);

        return "Deleted Employee Id -" + studentId;

    }




}



