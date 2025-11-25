package practices;

public class Test {

    public static void main(String[] args) {
        Employee emp = new Employee("101", "simon", 12500, "simon", "123");
        
        try {
            if (DataUtil.isDataSerializable(emp)) {
                DataUtil.initDataObject(emp);
                System.out.println(DataUtil.objectToData(emp));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
