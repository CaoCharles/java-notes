import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AnonymousDemo02 {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Drink("Tea", BigDecimal.valueOf(2)),
                new Food("Cake", BigDecimal.valueOf(1))
        );

        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });

        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product p1, Product p2) {
                return p1.getPrice().compareTo(p2.getPrice());
            }
        });

        Collections.sort(products, (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()) );
    }
}
