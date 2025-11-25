package practices;

import java.util.Optional;

public class TestEmployeeDAO {
    public static void main(String[] args) {
        try (EmployeeDAO dao = new EmployeeDAO()) {
            System.out.println("*** EmployeeDAO.getAllEmployee");
            dao.getAllEmployee().stream()
                    .forEach(Employee::print);
            
            System.out.println("\n*** EmployeeDAO.getEmployee");
            Optional<Employee> e = dao.getEmployee(101);
            e.ifPresent(Employee::print);
            
            System.out.println("\n*** EmployeeDAO.updateEmployee");
            Employee emp101 = dao.getEmployee(101).get();
            emp101.setSalary(emp101.getSalary() * 1.05);
            dao.updateEmployee(emp101);
            dao.getEmployee(101).ifPresent(Employee::print);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
