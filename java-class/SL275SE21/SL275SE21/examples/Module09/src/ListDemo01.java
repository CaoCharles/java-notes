import java.util.*;

public class ListDemo01 {
    public static void main(String[] args) {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");

        Set<Product> set1 = new HashSet<>();
        set1.add(p1);
        set1.add(p2);

        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>(20);
        List<Product> list3 = new ArrayList<>(set1);
        List<Product> list4 = Arrays.asList(p1,p2);
        List<Product> list5 = List.of(p1,p2);

        List<Product> menu = new ArrayList<>();
        menu.add(p1); //insert first element
        menu.add(p2); //insert next element
        menu.add(2, null); //insert null
        menu.add(3, p1); //insert element
        menu.add(2, p1); //insert element
        menu.set(2, p2); //update element
        menu.remove(0); //remove element
        menu.remove(p2); //remove element
        boolean hasTea = menu.contains(p2);
        int index = menu.indexOf(p1);
        menu.get(index).setName("Cookie");
        menu.add(4, p2); // throws exception
    }
}
