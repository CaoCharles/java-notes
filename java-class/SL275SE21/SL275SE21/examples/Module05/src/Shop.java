import java.math.BigDecimal;

public class Shop {
    public static void main(String[] args) {
        Product p1 = new Product();
        p1.setPrice(BigDecimal.valueOf(1.99));
        BigDecimal total = p1.getPrice();
        // p1.tax = 3.20;
        // p1.calculateTax();

        String name = p1.getName();
        BigDecimal price = p1.getPrice();
        price = BigDecimal.valueOf(1.99);
        Product p2 = new Product("Tea", price);

        Product tea = new Product("Tea", Condition.HOT);
        Person joe = new Person("Joe");
        joe.consume(tea.serve());
    }
}
