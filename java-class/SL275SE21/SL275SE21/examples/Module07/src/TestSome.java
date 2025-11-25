public class TestSome {
    public static void main(String[] args) {
        Some some = new Some();
        some.setValue(new Product("Tea", 1.99));
        some.setValue("something");
        Object value = some.getValue();

        if (value instanceof Product) {
            Product product = (Product) value;
        }

        if (value instanceof String) {
            String text = (String) value;
        }
    }
}
