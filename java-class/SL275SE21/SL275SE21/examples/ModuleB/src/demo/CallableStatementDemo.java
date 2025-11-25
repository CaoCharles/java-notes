package demo;

import static demo.ConnectionInfo.PASSWORD;
import static demo.ConnectionInfo.URL;
import static demo.ConnectionInfo.USERNAME;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementDemo {
    public static void main(String[] args) {
        int min = 90_000, max = 100_000;

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            CallableStatement cstmt = con.prepareCall("{CALL empsalarycount(?,?,?)}");

            cstmt.setInt(1, min);
            cstmt.setInt(2, max);

            ResultSet rs = cstmt.executeQuery();
            cstmt.registerOutParameter(3, Types.INTEGER);
            boolean result = cstmt.execute();
            int amount = cstmt.getInt(3);
            System.out.println("Amount: " + amount);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
