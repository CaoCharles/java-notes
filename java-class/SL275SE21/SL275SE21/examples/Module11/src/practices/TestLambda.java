package practices;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestLambda {
    public static void main(String[] args) {
        List<Employee> es = Employee.getEmployees();

        System.out.println("*** 顯示所有員工資料");
        // Stream.forEach


        System.out.println("\n*** 顯示薪資大於100,000的員工資料");
        // Stream.filter, forEach


        System.out.println("\n*** 顯示依照薪資排序(由小到大)的員工資料");
        // Stream.sorted, forEach; Comparator.comparingDouble


        System.out.println("\n*** 顯示依照薪資排序(由大到小)的員工資料");
        // Stream.sorted, forEach; Comparator.comparingDouble, reversed


        System.out.println("\n*** 所有員工調薪5%並儲存到資料庫");
        // Stream.peek, forEach; dao.updateEmployee


        System.out.println("\n*** 顯示依照部門分組的員工資料");
        // Stream.collect; Collectors.groupingBy


        // Map.forEach


        System.out.println("\n*** 顯示部門的平均薪資");
        // Stream.collect; Collectors.groupingBy; Collectors.averagingDouble

        // Map.forEach


        System.out.println("\n*** 把薪資大於100,000的員工資料轉換為 List 物件");
        // Stream.filter, collect; Collectors.toList()
        List<Employee> data = null;
        processEmpList(data);

        System.out.println("\n*** 把員工資料轉換為編號與全名(FirstName LastName)的 Map 物件");
        // Stream.collect; Collectors.toMap()
        Map<Integer, String> data2 = null;
        processEmpMap(data2);
    }
    
    public static void processEmpList(List<Employee> emps) {
        if (emps == null || emps.isEmpty()) return;
        emps.forEach(Employee::print);
    }
    
    public static void processEmpMap(Map<Integer, String> data) {
        if (data == null || data.isEmpty()) return;
        data.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
