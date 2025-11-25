package demos.shop;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.Math.random;

public class Shop {
    public static void main(String[] args) {
        new Product();
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = p2;
        p1.setName("Tea");
        p2.setName("Cake");
        System.out.println(p1.getName() + " in a cup");
        System.out.println(p2.getName() + " on a plate");
        System.out.println(p3.getName() + " to share");
//        p1.name = "Coffee";

        Product.setExpiryPeriod(4);
        Product p4 = new Product();
        Product p5 = new Product();
        p4.setExpiryPeriod(2);
        p5.getExpiryPeriod();
        Product p6 = new Product();
        Product.getExpiryPeriod();
        p4.getName();

        Period expiry = Period.ofDays(Product.MAX_EXPIRY_PERIOD);

        Math.round(1.99);
        double value = random();
        BigDecimal.valueOf(1.99);
        LocalDateTime.now();
        ZoneId.of("Europe/London");
        ResourceBundle.getBundle("messages", Locale.UK);
        NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println("Hello World");
    }
}
