package solutions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtil {

    public static boolean isDataSerializable(Object obj) {
        if (obj == null) 
            return false;
        else {
            Class<?> clazz = obj.getClass();
            // 將下一行敘述修改為判斷類別是否使用@DataSerializable
            return clazz.isAnnotationPresent(DataSerializable.class);
        } 
    }
    
    public static void initDataObject(Object obj) throws Exception {
        if (obj == null) return;
        
        Class<?> clazz = obj.getClass();
        
        for (Method method : clazz.getDeclaredMethods()) {
            // 將下一行敘述修改為判斷方法是否使用@DataInit
            if (method.isAnnotationPresent(DataInit.class)) {
                method.setAccessible(true);
                method.invoke(obj);
            }
        }
    }
    
    public static String getKey(Field field) {
        String result = field.getName();
        
        // 將下一行敘述修改為判斷變數是否使用@DataElement
        if (field.isAnnotationPresent(DataElement.class)) {
            // 將下一行敘述修改為使用 field 變數取得 DataElement 物件
            DataElement de = field.getAnnotation(DataElement.class);
            String key = de.key();
            
            if (!key.equals("")) {
                result = key;
            }
        }
        
        return result;
    }
    
    public static String objectToData(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        List<String> result = new ArrayList<>();
        
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            
            // 將下一行敘述修改為判斷變數是否使用@DataElement
            if (field.isAnnotationPresent(DataElement.class)) {
                result.add(getKey(field) + "=" + field.get(obj));
            }
         }
        
        return result.stream()
                .collect(Collectors.joining(","));
    }
    
}
