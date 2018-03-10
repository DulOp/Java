
package employeedb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Db {
    
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    
    Employee employee;
    
    public List getEmployees(Employee employee) {
        
        session.beginTransaction();
        Query query = session.createQuery("from Employee");
        List employees = query.list();
        
        employees.forEach((_item) -> {
            System.out.println("id: " + employee.getEmployeeId() + " Name: " + employee.getName() + " Age: " + employee.getAge() + " Adress: " + employee.getAdress() + " Salary: " + employee.getSalary());
        });
        session.getTransaction().commit();
        
        return employees;
    }
    
    public Employee getEmployee(int id) {
        
        session.beginTransaction();
        Query query = session.createQuery("from Employee");
        employee = (Employee)session.load(Employee.class, id);
        session.get(Employee.class, id);
        session.getTransaction().commit();
        
        return employee;
    }
    
    public void deleteEmployee(int id) {
        
        session.beginTransaction();
        employee = (Employee)session.load(Employee.class, id);
        session.delete(employee);
        session.getTransaction().commit();
        session.close();
    }
    
    public void changeEmployee(Employee employee, int id) throws SQLException {
        
        session.beginTransaction();
        
        employee = (Employee)session.get(Employee.class, id);
        employee.setName(employee.getName());
        employee.setAge(employee.getAge());
        employee.setAdress(employee.getAdress());
        employee.setSalary(employee.getSalary());
        session.getTransaction().commit();
    }
    
    public void addEmployee(Employee employee) {
        
        employee = new Employee();
        
        session.beginTransaction();
        employee.setName(employee.getName());
        employee.setAge(employee.getAge());
        employee.setAdress(employee.getAdress());
        employee.setSalary(employee.getSalary());
        
        session.save(employee);
        session.getTransaction().commit();
    }
}
