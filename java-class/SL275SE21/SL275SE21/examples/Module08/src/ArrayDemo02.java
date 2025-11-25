public class ArrayDemo02 {
    public static void main(String[] args) {
        Product[] products;
        // Product products[];
        products = new Product[3];
        products[0] = new Food("Cake");
        products[2] = new Drink("Tea");
        products[2].setPrice(1.99);
        products[3] = new Product("NA");

        Product[] products02;
        products02 = new Product[]{ new Food("Cake"),
                                    new Drink("Tea"),
                                    new Food("Cookie")};

        Product[] products03 = { new Food("Cake"),
                                 new Drink("Tea"),
                                 new Food("Cookie")};
    }
}
