package records;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductDemo {
    public static void main(String[] args) {
        Product p1 = new Product("Tea", 1.99);
        Product p2 = new Product("Tea", 1.99);
        boolean same = p1.equals(p2);
        int hashCode = p1.hashCode();
        String name = p1.name();
        String text = p1.toString();

        Delivery obj = new Delivery(p1, LocalDateTime.now());

        if (obj instanceof Delivery(Product(String n, double p), var t)) {
            System.out.println(n + " " + p + " " + t);
        }

        String result = switch (obj) {
            case null -> "No data";
            case Delivery d when d.time().isBefore(LocalDateTime.now()) -> "Delivered";
//            case Delivery d2 -> "Due " + d2.time();
            default -> obj.toString();
        };

        System.out.println(result);
    }
}
