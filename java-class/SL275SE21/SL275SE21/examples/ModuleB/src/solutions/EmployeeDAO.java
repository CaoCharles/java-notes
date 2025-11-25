package solutions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static solutions.ConnectionInfo.PASSWORD;
import static solutions.ConnectionInfo.URL;
import static solutions.ConnectionInfo.USERNAME;

public class EmployeeDAO implements AutoCloseable {
    
    private final Connection con;
    
    public EmployeeDAO() throws SQLException {
        // 建立資料庫連線物件 (DriverManager.getConnection)
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    // 傳回所有員工資料
    public List<Employee> getAllEmployee() throws SQLException {
        // 宣告與建立元素為 Employee 的 ArrayList 物件
        List<Employee> result = new ArrayList<>();
        String query = "SELECT * FROM Emp";
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                // 讀取 ID
                int empID = rs.getInt("ID");
                // 讀取 FirstName
                String first = rs.getString("FirstName");
                // 讀取 LastName
                String last = rs.getString("LastName");
                // 讀取 BirthDate
                Date birthDate = rs.getDate("BirthDate");
                // 讀取 Salary
                float salary = rs.getFloat("Salary");
                // 讀取 dept
                String dept = rs.getString("Dept");
                
                result.add(
                    // 使用讀取資料庫的資料建立員工物件
                    new Employee(empID, first, last, birthDate, salary, dept)
                );
            }
        }
        
        return result;
    }
    
    // 傳回指定編號的員工資料
    public Optional<Employee> getEmployee(int id) throws SQLException {
        Optional<Employee> result = Optional.empty();
        
        try (PreparedStatement pstmt = psGetEmployee(id);
             ResultSet rs = pstmt.executeQuery()) {
             
            if (rs.next()) {
                // 讀取 ID
                int empID = rs.getInt("ID");
                // 讀取 FirstName
                String first = rs.getString("FirstName");
                // 讀取 LastName
                String last = rs.getString("LastName");
                // 讀取 BirthDate
                Date birthDate = rs.getDate("BirthDate");
                // 讀取 Salary
                float salary = rs.getFloat("Salary");
                // 讀取 dept
                String dept = rs.getString("Dept");
                
                // 使用讀取資料庫的資料建立員工物件
                Employee e = new Employee(empID, first, last, birthDate, salary, dept);
                // 建立包裝員工物件 e 的 Optional 物件
                result = Optional.of( e );
            }
        }   
        
        return result;
    }
        
    // 建立並傳回查詢員工資料用的 PreparedStatement 物件
    private PreparedStatement psGetEmployee(int id) throws SQLException {
        String query = "SELECT * FROM Emp WHERE ID=?";
        // 宣告與建立 PreparedStatement 物件
        PreparedStatement ps = con.prepareStatement(query);
        // 呼叫 setInt 方法設定 ps 物件編號 1 的資料 id
        ps.setInt(1, id);
        
        return ps;
    }
    
    // 修改員工資料
    public void updateEmployee(Employee e) throws SQLException {
        try (PreparedStatement pstmt = psUpdateEmployee(e)) {
            // 呼叫 pstmt物件的 executeUpdate 方法
            pstmt.executeUpdate();
        }
    }
    
    // 建立並傳回修改員工資料用的 PreparedStatement 物件
    private PreparedStatement psUpdateEmployee(Employee e) throws SQLException {
        String query = "UPDATE Emp set FirstName=?, LastName=?, BirthDate=?, "
                + "Salary=?, Dept=? WHERE ID=?";
        // 宣告與建立 PreparedStatement 物件
        PreparedStatement ps = con.prepareStatement(query);
        
        // 呼叫 setString 方法設定 ps 物件編號 1 的資料 firstName
        ps.setString(1, e.getFirstName());
        // 呼叫 setString 方法設定 ps 物件編號 2 的資料 lastName
        ps.setString(2, e.getLastName());
        // 呼叫 setDate 方法設定 ps 物件編號 3 的資料 birthDate
        ps.setDate(3, new java.sql.Date(e.getBirthDate().getTime()));
        // 呼叫 setDouble 方法設定 ps 物件編號 4 的資料 salary
        ps.setDouble(4, e.getSalary());
        // 呼叫 setString 方法設定 ps 物件編號 5 的資料 dept
        ps.setString(5, e.getDept());
        // 呼叫 setInt 方法設定 ps 物件編號 6 的資料 id
        ps.setInt(6, e.getId());
        
        return ps;
    }

    @Override
    public void close() throws Exception {
        if (con != null) con.close();
    }
    
}
