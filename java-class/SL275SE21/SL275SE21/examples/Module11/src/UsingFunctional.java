import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UsingFunctional {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        list.stream()
                .filter(p -> p.getDiscount().doubleValue() == 0.0)
                .peek(p -> p.applyDiscount(BigDecimal.valueOf(0.1)))
                .map(p -> p.getBestBefore())
                .forEach(d -> d.plusDays(1));
    }
}
