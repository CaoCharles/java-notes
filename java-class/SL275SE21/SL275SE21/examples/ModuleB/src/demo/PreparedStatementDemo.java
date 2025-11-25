package demo;

import static demo.ConnectionInfo.PASSWORD;
import static demo.ConnectionInfo.URL;
import static demo.ConnectionInfo.USERNAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PreparedStatementDemo {
    public static void main(String[] args) {
        String query = "SELECT * FROM Emp WHERE Salary > ?";

        double value = 100_000.00;

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setDouble(1, value);
            ResultSet rs = pstmt.executeQuery();

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
            System.out.println(e.getMessage());
        }

    }

}
