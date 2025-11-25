public class ProductDemo {
    public static void main(String[] args) {
        Product p = new Product.Builder()
                .name("Cookie")
                .price(3)
                .build();
    }
}
