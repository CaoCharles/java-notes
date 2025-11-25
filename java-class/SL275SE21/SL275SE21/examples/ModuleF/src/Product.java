public class Product {
    private String name;
    private double price;

    private Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static class Builder {
        private String name;
        private double price;

        public Builder() {
        }

        public Product.Builder name(String value) {
            this.name = value;
            return this;
        }

        public Product.Builder price(double value) {
            this.price = value;
            return this;
        }

        public Product build() {
            return new Product(this.name, this.price);
        }
    }
}