
package employeedb;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Logic {
    
    Employee employee;
    Db db;
    View view;
    
    public Logic() {
        employee = new Employee();
        db = new Db();
        view = new View();
    }
    
    public void showEmployee() throws SQLException {
        int id = view.takeNumber();
        employee = db.getEmployee(id);
        
        if (employee == null) {
            System.out.println("Employee does not exist");
        } else {
            System.out.println("id: " + employee.getEmployeeId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Age: " + employee.getAge());
            System.out.println("Adress: " + employee.getAdress());
            System.out.println("Salary: " + employee.getSalary());
        }
    }
    
    public void showEmployees() throws SQLException {
        List all = db.getEmployees(employee);
        
        for (Object o : all) {
            Employee employee = (Employee) o;
            System.out.println("id: " + employee.getEmployeeId() + " Name: " + employee.getName() + " Age: " + employee.getAge() + " Adress: " + employee.getAdress() + " Salary: " + employee.getSalary());
        }
    }
    
    public void deleteEmployee() throws SQLException {
        int id = view.takeNumber();
        db.deleteEmployee(id);
    }
    
    public void addEmployee() throws SQLException {
        Employee employee = view.takeEmployee();
        db.addEmployee(employee);
    }
    
    public void changeEmployee() throws SQLException {
        int id = view.takeNumber();
        Employee employee = db.getEmployee(id);
        view.changeEmployee(employee);
        db.changeEmployee(employee, id);
    }
    
    public static void run() throws SQLException {
        Logic lc = new Logic();
        boolean running = true;
        while (running) {
            int chosen = lc.view.menu();
            switch(chosen) {
                case 1:
                    lc.addEmployee();
                break;
                case 2:
                    lc.changeEmployee();
                break;
                case 3:
                    lc.deleteEmployee();
                break;
                case 4:
                    lc.showEmployee();
                break;
                case 5:
                    lc.showEmployees();
                break;
                case 6:
                    running = false;
                break;
            }
        }
    }
}
