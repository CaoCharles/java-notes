public class TestGenerics {
    public static void main(String[] args) {
        SomeGenerics<Product> some = new SomeGenerics<>();
        some.setValue(new Product("Tea",1.99));
//        some.setValue("something");
        Product product = some.getValue();
    }
}
