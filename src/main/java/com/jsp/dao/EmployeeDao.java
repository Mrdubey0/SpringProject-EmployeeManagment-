package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.entity.Employee;

@Component
public class EmployeeDao {
    @Autowired
    private EntityManager em; 
    
    // Method to Create the Employee Object in Database
    public void saveEmployee(Employee employee) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(employee);
        et.commit();
    }
    
    // Method to Find the Employee Object from Database by ID
    public Employee findEmployeeById(int id) {
        return em.find(Employee.class, id);
    }
    
    // Method to Delete the Employee Object from Database
    public boolean deleteEmployeeById(int id) {
        Employee dbEmployee = findEmployeeById(id);
        
        if (dbEmployee != null) {
            EntityTransaction et = em.getTransaction();
            
            try {
                et.begin();
                em.remove(dbEmployee);
                et.commit();
                System.out.println("Successfully Deleted Employee for ID: " + id);
                return true;
            } 
            catch (Exception e) {
                System.err.println("Error while deleting employee: " + e.getMessage());
                return false;
            }
        } 
        else {    
            System.err.println("Cannot Find Employee for ID: " + id);
            return false;
        }
    }

    // Method to Update the Employee Object in Database
    public Employee updateEmployee(int id, Employee updateEmployee) {
        Employee dbEmployee = findEmployeeById(id);
        if (dbEmployee != null) {
            EntityTransaction et = em.getTransaction();
            et.begin();
            updateEmployee.setId(id);
            em.merge(updateEmployee);
            et.commit();
            return updateEmployee;
        } else {
            return null;
        }
    }
    
    // Method to Get All Employees
    public List<Employee> getAllEmployees() {
        String query = "select e from Employee e";
        Query q = em.createQuery(query);
        List<Employee> employees = q.getResultList();
        return employees;
    }
    
    // Method to Get Employee by Email
    public Employee getEmployeeByEmail(String email) {
        String query = "select e from Employee e where e.email=?1";
        TypedQuery<Employee> qt = em.createQuery(query, Employee.class);
        qt.setParameter(1, email);
        Employee employee = qt.getSingleResult();
        return employee;
    }
}
