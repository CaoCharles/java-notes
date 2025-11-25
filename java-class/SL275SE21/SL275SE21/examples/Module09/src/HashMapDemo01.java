import java.util.HashMap;
import java.util.Map;

public class HashMapDemo01 {
    public static void main(String[] args) {
        Map<Product, Integer> items1 = new HashMap<>();
        Map<Product, Integer> items2 = new HashMap<>(20);
        Map<Product, Integer> items3 = new HashMap<>(items1);
        Map<Product, Integer> items4 = Map.of(new Food("Cake"), Integer.valueOf(2),
                new Drink("Tea"), Integer.valueOf(3));

        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Map<Product, Integer> items = new HashMap<>();
        items.put(p1,Integer.valueOf(2));
        items.put(p2,Integer.valueOf(2));
        Integer n1 = items.put(p1,Integer.valueOf(5));
        Integer n2 = items.remove(p2);
        boolean hasTea = items.containsKey(p2);
        boolean hasTwo = items.containsValue(n1);
        Integer quantity = items.get(p1);
    }
}
