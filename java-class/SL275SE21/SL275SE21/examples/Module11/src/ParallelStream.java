import java.util.List;

public class ParallelStream {
    public static void main(String[] args) {
        List<Product> list = Product.getProducts();
        // populate list before processing starts
        double discount = list.stream().parallel()
                .mapToDouble(p -> p.getDiscount().doubleValue())
                .sum();
    }
}
