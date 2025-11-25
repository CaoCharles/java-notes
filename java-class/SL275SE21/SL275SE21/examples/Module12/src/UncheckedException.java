public class UncheckedException {
    public static void main(String[] args) {
        int x = 5;
        int y = 0;
        int z = divide(x, y);
        System.out.println(z);
    }

    public static int divide(int x, int y) {
        if (y == 0) {
            throw new ArithmeticException("Error: " + x + "/" + y);
        }
        int z = x / y;
        return z;
    }
}
