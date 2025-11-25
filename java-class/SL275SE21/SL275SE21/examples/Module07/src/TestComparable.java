import java.util.Arrays;

public class TestComparable {
    public static void main(String[] args) {
        Product[] products = {new Product("Tea", 1.0),
                new Product("Coffee", 2.0),
                new Product("Cake", 3.0)};
        Arrays.sort(products);

        for (Product p : products) {
            System.out.println(p.getName());
        }
    }
}
