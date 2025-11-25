package demos.shop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private LocalDate bestBefore = LocalDate.now().plusDays(3);

    private static Period defaultExpiryPeriod = Period.ofDays(3);

    public static final int MAX_EXPIRY_PERIOD = 5;

    static {
        defaultExpiryPeriod = Period.ofDays(3);
    }

    public static void setDefaultExpiryPeriod(int days) {
        defaultExpiryPeriod = Period.ofDays(days);
        // String name = this.name;
    }

    public static void setExpiryPeriod(int days) {
        defaultExpiryPeriod = Period.ofDays(days);
    }

    public static Period getExpiryPeriod() {
        return defaultExpiryPeriod;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(double value) {
        price = BigDecimal.valueOf(value);
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

//    public String getName() {
//        if (name == null) {
//            String dummy = "Unknown";
//            return dummy;
//        }
//        return name;
//    }

    public String consume() {
        String feedback = "Good!";
        return feedback;
    }
}
