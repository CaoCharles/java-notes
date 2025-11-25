import java.util.Optional;

public class ErroneousValue {
    public static void main(String[] args) {
        final int MAX = Integer.MAX_VALUE;
        int value = Math.addExact(MAX, 1);
        double badValue = 1/Double.MIN_VALUE; // or 1/-Double.MIN_VALUE
        boolean infinite = Double.isInfinite(badValue);
        double untrusted_double = 0.2;
        boolean NaN = Double.isNaN(untrusted_double);
        SomeDatabase someDatabase = new SomeDatabase();
        Optional<Product> o = someDatabase.findProduct(101);
        if (o.isPresent()) {
            Product p = o.get();
        }
    }

    static class Product { }

    static class SomeDatabase {
        public Optional<Product> findProduct(int id) {
            return Optional.of(new Product());
        }
    }
}
