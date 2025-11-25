import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestrictionsOnParallel {
    public static void main(String[] args) {
        List<BigDecimal> prices = new ArrayList<>();
        List<Product> list = Product.getProducts();
        list.stream()
                .parallel()
                .peek(p -> System.out.println(p))
                .map(p -> p.getPrice())
                .forEach(p -> prices.add(p));

        List<BigDecimal> prices02 = list.stream()
                .parallel()
                .map(p -> p.getPrice())
                .collect(Collectors.toList());

        Map<String, BigDecimal> namesAndPrices =
                list.stream()
                        .parallel()
                        .collect(Collectors.toConcurrentMap(p->p.getName(),
                                p->p.getPrice()));
    }
}
