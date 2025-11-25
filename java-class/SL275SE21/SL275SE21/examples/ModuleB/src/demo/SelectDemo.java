package demo;

import static demo.ConnectionInfo.PASSWORD;
import static demo.ConnectionInfo.URL;
import static demo.ConnectionInfo.USERNAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class SelectDemo {
    public static void main(String[] args) {
        String query = "SELECT * FROM Emp";
        
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                int empID = rs.getInt("ID");
                String first = rs.getString("FirstName");
                String last = rs.getString("LastName");
                Date birthDate = rs.getDate("BirthDate");
                float salary = rs.getFloat("Salary");
                String dept = rs.getString("Dept");
                
                System.out.println("Employee ID:   " + empID + "\n" +
                                   "Employee Name: " + first + " " + last + "\n" +
                                   "Birth Date:    " + birthDate + "\n" +
                                   "Salary:        " + salary + "\n" +
                                   "Dept:          " + dept + "\n");
            }
        }
        catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
    }
    
}
