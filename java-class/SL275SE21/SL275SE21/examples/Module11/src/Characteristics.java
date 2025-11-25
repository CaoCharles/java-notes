import java.math.BigDecimal;
import java.util.List;

public class Characteristics {
    public static void main(String[] args) {
        List<Product> list = Product.getProducts();

        for (Product p : list) {
            if (p.getPrice().doubleValue() > 10) {
                p.setDiscount(BigDecimal.valueOf(0.2));
            }
        }

        list.stream().parallel()
                .filter(p -> p.getPrice().doubleValue() > 10)
                .forEach(p -> p.setDiscount(BigDecimal.valueOf(0.2)));
    }
}
