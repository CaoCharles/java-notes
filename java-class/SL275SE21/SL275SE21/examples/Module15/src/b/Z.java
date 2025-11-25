package b;
import a.X;
//import a.Y;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Z {
    void introspectY() throws ClassNotFoundException {
        Class c = Class.forName("a.Y");
        Field[] fields = c.getFields();
        Method[] methods = c.getMethods();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new Z().introspectY();
    }
}