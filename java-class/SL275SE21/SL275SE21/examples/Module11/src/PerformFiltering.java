import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

public class PerformFiltering {
    public static void main(String[] args) {
        Predicate<Product> foodFilter = p -> p instanceof Food;
        Predicate<Product> priceFilter = p -> p.getPrice().compareTo(BigDecimal.valueOf(2)) < 0;

        List<Product> list = Product.getProducts();

        list.stream().filter(foodFilter.negate().or(priceFilter))
                .forEach(p -> p.setDiscount(BigDecimal.valueOf(0.1)));
        list.stream().filter(Predicate.isEqual(new Food("Cake", BigDecimal.valueOf(1.99))))
                .forEach(p -> p.setDiscount(BigDecimal.valueOf(0.1)));
    }
}
