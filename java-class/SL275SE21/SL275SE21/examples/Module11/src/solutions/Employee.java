package solutions;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private double salary;
    private String dept;

    public Employee(int id, String firstName, String lastName,
                    LocalDate birthDate, double salary, String dept) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
    
    public void print() {
        System.out.printf(
                "ID: " + id
                + ", Name: " + firstName + " " + lastName
                + ", Birthdate: " + birthDate
                + ", Salary: " + salary
                + ", Dept: " + dept + "\n"
        );
    }
    
    public void printSummary() {
        System.out.printf(
                "Name: " + firstName + " " + lastName
                + ", Salary: " + salary + "\n"
        );
    }

    public static List<Employee> getEmployees() {
        List<Employee> data = new ArrayList<>();
        data.add(new Employee(101, "Abhijit", "Gopali", LocalDate.of(1956, 6, 1), 70000, "ADMIN"));
        data.add(new Employee(110, "Troy", "Hammer", LocalDate.of(1965, 3, 31), 102100, "HR"));
        data.add(new Employee(123, "Michael", "Walton", LocalDate.of(1986, 8, 25), 93400, "ENG"));
        data.add(new Employee(201, "Thomas", "Fitzpatrick", LocalDate.of(1961, 9, 22), 75000, "ENG"));
        data.add(new Employee(388, "Monica", "Rose", LocalDate.of(1976, 8, 13), 170000, "HR"));
        data.add(new Employee(500, "Jill", "Murray", LocalDate.of(1950, 9, 21), 95000, "SALES"));
        data.add(new Employee(512, "Simon", "Johnson", LocalDate.of(1968, 10, 20), 67000, "SALES"));
        data.add(new Employee(999, "Java", "SE21", LocalDate.of(2023, 9, 19), 21000, "SALES"));
        return data;
    }
    
}
