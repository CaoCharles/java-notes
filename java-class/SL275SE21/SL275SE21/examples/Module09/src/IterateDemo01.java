import java.util.*;

public class IterateDemo01 {
    public static void main(String[] args) {
        Map<Product, Integer> items = new HashMap<>();
        Set<Product> keys = items.keySet();
        Collection<Integer> values = items.values();

        for (Product product : keys) {
            Integer quantity = items.get(product);
            // use product and quantity objects
        }

        for (Integer quantity : values) {
            // use quantity object
        }

        List<Product> menu = new ArrayList<>();
        menu.add(new Food("Cake"));
        menu.add(new Drink("Tea"));

        for (Product product : menu) {
            // use product object
        }
        // less automated alternative:
        Iterator<Product> iter = menu.iterator();

        while (iter.hasNext()) {
            Product product = iter.next();
            // use product object
            iter.remove();
        }
    }
}
