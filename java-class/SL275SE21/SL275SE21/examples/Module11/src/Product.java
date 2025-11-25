import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Product implements Comparable<Product> {
    private String name;
    private BigDecimal price;
    private BigDecimal discount;
    private LocalDate bestBefore;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, BigDecimal price, BigDecimal discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public Product(String name, BigDecimal price, BigDecimal discount, LocalDate bestBefore) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.bestBefore = bestBefore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    public void applyDiscount(BigDecimal discount) {
        price = price.subtract(discount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public int compareTo(Product p) {
        return name.compareTo(p.name);
    }

    public static List<Product> getProducts() {
        return List.of(
                new Product("Cookie", BigDecimal.valueOf(3), BigDecimal.ZERO, LocalDate.now()),
                new Product("Tea", BigDecimal.valueOf(2), BigDecimal.ZERO, LocalDate.now()),
                new Product("Sandwich", BigDecimal.valueOf(5), BigDecimal.ONE, LocalDate.now())
        );
    }
}
