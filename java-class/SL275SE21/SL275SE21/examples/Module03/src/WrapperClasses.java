public class WrapperClasses {
    public static void main(String[] args) {
        int a = 42;
        Integer b = Integer.valueOf(a);
        // Integer b = new Integer(a);
        int c = b.intValue();

        b = a;  // auto boxing
        c = b;  // auto unboxing

        String d = "12.25";
        Float e = Float.valueOf(d);     // String to Float
        float f = Float.parseFloat(d);  // String to float
        String g = String.valueOf(a);   // int to String
    }
}
