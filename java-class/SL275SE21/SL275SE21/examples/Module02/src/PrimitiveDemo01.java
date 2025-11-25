public class PrimitiveDemo01 {
    public static void main(String[] args) {
        System.out.printf("byte:  %d ~ %d%n", Byte.MIN_VALUE, Byte.MAX_VALUE);
        System.out.printf("short: %d ~ %d%n", Short.MIN_VALUE, Short.MAX_VALUE);
        System.out.printf("int:   %d ~ %d%n", Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("long:  %d ~ %d%n", Long.MIN_VALUE, Long.MAX_VALUE);

        System.out.println("float:  " + Float.MIN_VALUE + " ~ " + Float.MAX_VALUE);
        System.out.println("double: " + Double.MIN_VALUE + " ~ " + Double.MAX_VALUE);

        System.out.println("char:  " + (int)Character.MIN_VALUE + " ~ " + (int)Character.MAX_VALUE);
    }
}
