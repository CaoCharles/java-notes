import java.math.BigDecimal;

public class ProductDemo01 {
    public static void main(String[] args) {
        Product p = new Product();

        p.setPrice(1.99);
        p.setPrice(BigDecimal.valueOf(1.99));
        p.setPrice(BigDecimal.valueOf(1.99), BigDecimal.valueOf(0.9));

        p.setFiscalDetails(1.99);
        p.setFiscalDetails(1.99, 0.9, 0.1);
        p.setFiscalDetails(1.99, 0.9);
    }
}
