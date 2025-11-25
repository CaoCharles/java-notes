import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo01 {
    public static void main(String[] args) {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Product p3 = new Food("Cookie");
        List<Product> menu = new ArrayList<>();
        menu.add(p1);
        menu.add(p2);
        menu.add(p3);
        Collections.sort(menu);
        Collections.reverse(menu);
        Collections.shuffle(menu);
        int x = Collections.binarySearch(menu, p2);
        Collections.fill(menu, new Food("Pie"));
    }
}
