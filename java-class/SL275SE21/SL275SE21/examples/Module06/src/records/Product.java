package records;

public record Product(String name, double price) {
    public Product(String name, double price) {
        name = name.toUpperCase();
        this.name = name;
        this.price = price;
    }

//    public Product {
//        name = name.toUpperCase();
//        // this.name = name; is auto-generated
//        price *= 0.9;
//    }
}
