import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class RulesOfReference {
    public static void main(String[] args) {
        Food x1 = new Food();
        Product x2 = new Drink();
        x1.toString();
        // x1.getName();
        x1.getBestBefore();
        Product x3 = x1;
        x3.toString();
        // x3.getName();
        /// x3.getBestBefore();
        Object x4 = x1;
        x4.toString();
        // x4.getName();
        // x4.getBestBefore();
        Product x5 = (Product) x4;
        x5.toString();
        // x5.getName();
        // x5.getBestBefore();
        Drink x6 = (Drink) x2;
        Drink x7 = (Drink) x3;
    }

    public void order(Product p) {
        BigDecimal price = p.getPrice();
        BigDecimal discount = BigDecimal.ZERO;
        if (p instanceof Food) {
            discount = (((Food) p).getBestBefore().isEqual(LocalDate.now().plusDays(1)))
                    ? price.multiply(BigDecimal.valueOf(0.1))
                    : BigDecimal.ZERO;
        }
        if (p instanceof Drink) {
            LocalTime now = LocalTime.now();
            discount = (now.isAfter(LocalTime.of(17, 30)) && now.isBefore(LocalTime.of(18, 30)))
                    ? price.multiply(BigDecimal.valueOf(0.2))
                    : BigDecimal.ZERO;
        }
        price = price.subtract(p.getDiscount());
    }

    public void order02(Product p) {
        if (p instanceof Food f && f.getBestBefore().isBefore(LocalDate.now())) {
            LocalDate bestBefore = f.getBestBefore();
            /* Food specific logic using variable f */
        } else {
            /* variable f is out of scope */
        }
    }

//    public void order03(Product p) {
//        if (p instanceof Food f || f.getBestBefore().isBefore(LocalDate.now())) {
//
//        }
//    }
}
