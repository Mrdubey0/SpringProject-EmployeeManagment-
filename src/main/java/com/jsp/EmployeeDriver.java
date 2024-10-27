package com.jsp;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jsp.config.EmployeeConfig;
import com.jsp.entity.Employee;
import com.jsp.serivice.EmployeeService;


public class EmployeeDriver {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(EmployeeConfig.class);
        EmployeeService service = ac.getBean("employeeService",EmployeeService.class);
        Scanner sc = new Scanner(System.in);
        
        int op = 0;
        while (op != 7) {
            
            System.out.println("1. Save Employee");
            System.out.println("2. Find Employee By ID");
            System.out.println("3. Delete Employee By ID");
            System.out.println("4. Update Employee By ID");
            System.out.println("5. All Details of Employees");
            System.out.println("6. Find Employee by Email");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            op = sc.nextInt();
            
            switch (op) {
                case 1:
                    System.out.println("Enter Employee Details");
                    Employee emp = new Employee();
                    System.out.print("Enter Employee's Phone: ");
                    emp.setPhone(sc.nextLong());
                    System.out.print("Enter Employee's Email: ");
                    emp.setEmail(sc.next());
                    System.out.print("Enter Employee's Position: ");
                    emp.setPosition(sc.next());
                    System.out.print("Enter Employee's Department: ");
                    emp.setDepartment(sc.next());
                    service.saveEmployee(emp);
                    System.out.println("Employee saved successfully.");
                    break;
                
                case 2:
                    System.out.print("Enter Employee ID to find: ");
                    Employee e = service.findById(sc.nextInt());
                    if (e != null) {
                        System.out.println(e);
                    } else {
                        System.err.println("No data found with this ID.");
                    }
                    break;
                
                case 3:
                    System.out.print("Enter the Employee ID to delete: ");
                    boolean isDeleted = service.deleteById(sc.nextInt());
                    if (isDeleted) {
                        System.out.println("Employee deleted successfully.");
                    } else {
                        System.err.println("Failed to delete employee.");
                    }
                    break;
                
                case 4:
                    System.out.println("Enter Updated Employee Details");
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    
                    Employee emp1 = new Employee();
                    System.out.print("Enter Updated Employee's Phone: ");
                    emp1.setPhone(sc.nextLong());
                    System.out.print("Enter Updated Employee's Email: ");
                    emp1.setEmail(sc.next());
                    System.out.print("Enter Updated Employee's Position: ");
                    emp1.setPosition(sc.next());
                    System.out.print("Enter Updated Employee's Department: ");
                    emp1.setDepartment(sc.next());
                    service.updateEmployee(emp1, id);
                    System.out.println("Employee updated successfully.");
                    break;
                    
                case 5:
                    System.out.println("All Employee Details:");
                    List<Employee> employees = service.getAllEmployees();
                    for (Employee employee : employees) {
                        System.out.println(employee);
                    }
                    break;
                    
                case 6:
                    System.out.print("Enter Employee Email to find: ");
                    Employee emp2 = service.getEmployeeByEmail(sc.next());
                    if (emp2 != null) {
                        System.out.println(emp2);
                    } else {
                        System.err.println("No employee found with this email.");
                    }
                    break;
                    
                case 7:
                    System.out.println("Exiting program...");
                    break;
                    
                default:
                    System.err.println("Invalid choice! Please select a valid option.");
                    break;
            }
        }
        
        sc.close();
    }
}
