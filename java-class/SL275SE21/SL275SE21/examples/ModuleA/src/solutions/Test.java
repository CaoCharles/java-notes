package solutions;

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
/*
1. 宣告 DataSerializable Annotation, 
   Retention 為 RUNTIME, Target 為 TYPE
2. 宣告 DataElement Annotation, 
   Retention 為 RUNTIME, Target 為 FIELD,
   可以設定資料名稱為 key, 型態為 String, 預設值為 ""
3. 宣告 DataInit Annotation, 
   Retention 為 RUNTIME, Target 為 METHOD
4. 開啟 DataUtil.java, 依照註解修改程式碼
5. 開啟 Employee.java, 移除使用 annotation 的註解
6. 開啟 Text.java, 了解內容後執行
*/
