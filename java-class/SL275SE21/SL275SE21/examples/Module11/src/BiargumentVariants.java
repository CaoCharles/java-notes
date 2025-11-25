import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BiargumentVariants {
    public static void main(String[] args) {
        Product p1 = new Food("Cake", BigDecimal.valueOf(3.10));
        Product p2 = new Drink("Tea", BigDecimal.valueOf(1.20));
        Map<Product, Integer> items = new HashMap();
        items.put(p1, Integer.valueOf(1));
        items.put(p2, Integer.valueOf(3));
        items.forEach(
                (p, q) ->
                System.out.println(p.getPrice().multiply(BigDecimal.valueOf(q.intValue())))
        );
    }
}
