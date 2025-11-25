package solutions;

@DataSerializable
public class Employee {
    
    @DataElement(key="Employee ID")
    private String id;
    
    @DataElement(key="Employee Name")
    private String name;
    
    @DataElement
    private double salary;
    
    private String account;
    private String password;

    public Employee(String id, String name, double salary, String account, String password) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.account = account;
        this.password = password;
    }
    
    @DataInit
    private void dataInit() {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
