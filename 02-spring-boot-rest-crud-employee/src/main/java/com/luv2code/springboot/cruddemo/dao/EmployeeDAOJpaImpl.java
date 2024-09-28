package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// it indicates that this class is DAO(Data Access Object)
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // set up constructor injection
    // spring boot automatically creates entityManager
    // we injected entityManager via constructor injection

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> finAll() {

        // create a query

        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee" ,Employee.class);

        // execute query and get result list

        List<Employee> employees = theQuery.getResultList();

        // return the results

        return employees;
    }

    @Override
    public Employee findById(int id) {

        // get employee

        Employee employee = entityManager.find(Employee.class,id);

        // return the employee

        return employee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        // save employee
        // if id == 0 then insert/save or update

        Employee dbEmployee = entityManager.merge(theEmployee);

        // return the dbEmployee
        // it has updated id from the database (in the case of insert)

        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {

        // find employee by id

        Employee employee = entityManager.find(Employee.class,id);

        // remove employee
        entityManager.remove(employee);
    }
}
