import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalClass {
    public static void main(String[] args) {
        BigDecimal price = java.math.BigDecimal.valueOf(12.99);
        BigDecimal taxRate = BigDecimal.valueOf(0.2);
        BigDecimal tax = price.multiply(taxRate); // tax is 2.598
        price = price.add(tax).setScale(2, RoundingMode.HALF_UP); // price is 15.59
    }
}
