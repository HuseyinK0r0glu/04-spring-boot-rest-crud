package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

// Data Access Object(DAO)
public interface EmployeeDAO {

    List<Employee> finAll();

    Employee findById(int id);

    Employee save(Employee theEmployee);

    void deleteById(int id);
}
