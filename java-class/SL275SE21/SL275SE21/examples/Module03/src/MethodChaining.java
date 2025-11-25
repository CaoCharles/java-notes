import java.math.BigDecimal;
import java.math.RoundingMode;

public class MethodChaining {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = s1.concat("World").substring(3, 6); // s2 is ”loW”
        BigDecimal price = BigDecimal.valueOf(12.99);
        BigDecimal taxRate = BigDecimal.valueOf(0.2);
        BigDecimal tax = price.multiply(taxRate); // tax is 2.598
        price = price.add(tax).setScale(2, RoundingMode.HALF_UP); // price is 15.59

        BigDecimal taxedPrice = price.add(tax);
        price = taxedPrice.setScale(2,RoundingMode.HALF_UP);
    }
}
