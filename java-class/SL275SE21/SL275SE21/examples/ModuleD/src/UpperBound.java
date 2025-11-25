import java.util.List;

public class UpperBound {
    public static void setProducts(List<Product> products) {
    }

    public static void setProductAndSubtypes(List<? extends Product> products) {
    }

    public static void main(String[] args) {
        Product p1 = new Food("Cake", 2.99);
        Product p2 = new Drink("Tea", 1.99);
        Product p3 = new Food("Cookie", 2.99);
        List<Product> products = List.of(p1, p2, p3);
        List<Food> foods = List.of((Food) p1, (Food) p3);
        setProducts(products);
//        setProducts(foods);
        setProductAndSubtypes(products);
        setProductAndSubtypes(foods);
    }
}
