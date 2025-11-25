import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class PerformActions {
    public static void main(String[] args) {
        Consumer<Product> expireProduct = (p) -> p.setBestBefore(LocalDate.now());
        Consumer<Product> discountProduct = (p) -> p.setDiscount(BigDecimal.valueOf(0.1));

        List<Product> list = Product.getProducts();

        list.stream().forEach(expireProduct.andThen(discountProduct));
        list.stream().peek(expireProduct)
                .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(2)) < 0)
                .forEach(discountProduct);
    }
}
