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

public class InsertDemo {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO Emp VALUES (500, 'Jill', 'Murray','1950-09-21', 150000, 'SALES')";
            
            if (stmt.executeUpdate(query) > 0) {
                System.out.println("A new Employee record is added");
            }

            String query1 = "select * from Emp";
            ResultSet rs = stmt.executeQuery(query1);

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
        catch (SQLException ex) {
            System.out.println("SQLState:  " + ex.getSQLState());
            System.out.println("Error Code:" + ex.getErrorCode());
            System.out.println("Message:   " + ex.getMessage());
        }
    }
    
}
