import java.math.BigDecimal;
import java.util.Objects;

public sealed class Product
        permits Food, Drink {

    static {
        System.out.println("Product static initializer");
    }

    {
        System.out.println("Product instance initializer");
    }

    private int id;
    private String name;
    public BigDecimal price;
    public BigDecimal discount;

    public Product(String name) {
        this.name = name;
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public Product(int id, String name, BigDecimal price, BigDecimal discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    // public abstract void serve();

    public int getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return BigDecimal.ONE;
    }

    public final void processPayment() {
        // method can not be overridden by subclasses
    }

    @Override
    public String toString() {
        return id + " " + name + " " + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return this.id;
//        return Objects.hash(name, price);
    }
}
