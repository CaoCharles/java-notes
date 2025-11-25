package solutions;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private double salary;
    private String dept;

    public Employee(int id, String firstName, String lastName, 
            Date birthDate, double salary, String dept) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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
                + ", Birthdate: " + getLocalizedDate(birthDate)
                + ", Salary: " + getLocalizedNumber(salary)
                + ", Dept: " + dept + "\n"
        );
    }
    
    public void printSummary() {
        System.out.printf(
                "Name: " + firstName + " " + lastName
                + ", Salary: " + getLocalizedNumber(salary) + "\n"
        );
    }

    private static String getLocalizedDate(Date date) {
        Locale def = Locale.getDefault();
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM, def);
        String result = format.format(date);
        return result;
    }

    private static String getLocalizedNumber(double num) {
        Locale def = Locale.getDefault();
        NumberFormat format = NumberFormat.getInstance(def);
        String result = format.format(num);
        return result;
    }
    
}
