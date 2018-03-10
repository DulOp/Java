
package employeedb;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class View {
    
    Db db;
    Employee employee;
    
    public int menu() {
        System.out.println("Welcome to the employee database info and edit system\n Choose an option:\n 1.Add New 2.Change Existing 3.Delete 4.Show Individual 5.Show All 6.Exit");
        
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }
    
    public int takeNumber() {
        System.out.println("Type id");
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }
    
    public void changeEmployee(Employee employee) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type Employee Name");
        employee.setName(sc.nextLine());
        System.out.println("Type Employee Age");
        employee.setAge(Integer.valueOf(sc.nextLine()));
        System.out.println("Type Employee Adress");
        employee.setAdress(sc.nextLine());
        System.out.println("Type Employee Salary");
        employee.setSalary(Integer.valueOf(sc.nextLine()));
    }
    
    public Employee takeEmployee() {
        Scanner sc = new Scanner(System.in);
        Employee employee = new Employee();
        System.out.println("Type Employee Name");
        employee.setName(sc.nextLine());
        System.out.println("Type Employee Age");
        employee.setAge(Integer.valueOf(sc.nextLine()));
        System.out.println("Type Employee Adress");
        employee.setAdress(sc.nextLine());
        System.out.println("Type Employee Salary");
        employee.setSalary(Integer.valueOf(sc.nextLine()));
        
        
        return employee;
    }
}
