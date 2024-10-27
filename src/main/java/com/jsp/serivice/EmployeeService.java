package com.jsp.serivice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.dao.EmployeeDao;
import com.jsp.entity.Employee;
import com.jsp.exceptionClass.InvalidPhone;

@Component(value = "employeeService")
public class EmployeeService {
    @Autowired
    private EmployeeDao dao;

    // Method to Save Employee with Phone Validation
    public Employee saveEmployee(Employee employee) {
        long phone = employee.getPhone();
        if (phone > 999999999 && phone <= 9999999999L) {
            dao.saveEmployee(employee);
            return employee;
        } else {
            throw new InvalidPhone("Employee Phone is Invalid");
        }
    }

    // Method to Find Employee by ID
    public Employee findById(int id) {
        return dao.findEmployeeById(id);
    }

    // Method to Delete Employee by ID
    public boolean deleteById(int id) {
        return dao.deleteEmployeeById(id);
    }

    // Method to Update Employee with Phone Validation
    public Employee updateEmployee(Employee employee, int id) {
        long phone = employee.getPhone();
        if (phone > 999999999 && phone <= 9999999999L) {
            dao.updateEmployee(id, employee);
            return employee;
        } else {
            throw new InvalidPhone("Employee Phone is Invalid");
        }
    }

    // Method to Get All Employees
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    // Method to Get Employee by Email
    public Employee getEmployeeByEmail(String email) {
        return dao.getEmployeeByEmail(email);
    }
}
