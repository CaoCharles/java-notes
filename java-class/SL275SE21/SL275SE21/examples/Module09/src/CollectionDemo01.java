import java.util.ArrayList;
import java.util.List;

public class CollectionDemo01 {
    public static void main(String[] args) {
        List<Product> menu = new ArrayList<>();
        menu.add(new Food("Cake"));
        menu.add(new Drink("Tea"));
        menu.add(new Food("Cookie"));
        Product[] array = new Product[2];
        array = menu.toArray(array);
        menu.removeIf(new LongProductsNames());
    }
}
