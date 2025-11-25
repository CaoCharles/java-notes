import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo01 {
    public static void main(String[] args) {
        List<Product> menu = new ArrayList<>();
        menu.add(new Food("Cake", BigDecimal.valueOf(1.99)));
        menu.add(new Food("Cookie", BigDecimal.valueOf(2.99)));
        menu.add(new Drink("Tea", BigDecimal.valueOf(2.99)));
        menu.add(new Drink("Coffee", BigDecimal.valueOf(2.99)));

        Comparator<Product> sortNames = (p1, p2) -> p1.getName().compareTo(p2.getName());
        Comparator<Product> sortPrices = (p1, p2) -> p1.getPrice().compareTo(p2.getPrice());
        Collections.sort(menu, sortNames.thenComparing(sortPrices).reversed());

        menu.add(null);
        Collections.sort(menu, Comparator.nullsFirst(sortNames));
    }
}
