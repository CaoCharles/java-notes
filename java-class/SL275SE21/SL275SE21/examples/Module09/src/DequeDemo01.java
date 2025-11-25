import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DequeDemo01 {
    public static void main(String[] args) {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        List<Product> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        Deque<Product> productDeque1 = new ArrayDeque<>();
        Deque<Product> productDeque2 = new ArrayDeque<>(20);
        Deque<Product> productDeque3 = new ArrayDeque<>(list);

        Product p3 = new Drink("Cookie");
        Deque<Product> menu = new ArrayDeque<>();
        Product nullProduct = menu.pollFirst();
        menu.offerFirst(p1);
        menu.offerFirst(p2);
        Product tea = menu.pollFirst();
        Product cake1 = menu.peekFirst();
        menu.offerLast(p3);
        menu.offerLast(p1);
        Product cake2 = menu.pollLast();
        Product cookie = menu.peekLast();
        menu.offerLast(null);
    }
}
