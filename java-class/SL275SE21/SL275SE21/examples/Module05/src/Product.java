import java.math.BigDecimal;

public class Product {
    private BigDecimal price;
    private BigDecimal discount = BigDecimal.ZERO;
    private BigDecimal tax;

    private String name;

    private BigDecimal rate = BigDecimal.valueOf(0.1);

    private static int maxId = 0;
    private final int id;

    {
        id = ++maxId;
    }

    private String caution;
    private Condition condition;

    public Product(String name,
                   Condition condition) {
        this.name = name;

    }

    public Product() {
    }

    public Product(String name) {
        this.name = name;
        this.price = BigDecimal.ZERO;
    }

    public Product(String name, double price) {
        this(name);
        this.price = BigDecimal.valueOf(price);
    }

    public Product(String name, BigDecimal price) {
        this(name);
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        calculateTax();
    }

    public void setPrice(BigDecimal price, BigDecimal discount) {
        this.price = price;
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void calculateTax() {
        tax = price.multiply(rate);
    }

    public void setFiscalDetails(double... values) {
        switch (values.length) {
            case 3:
                tax = BigDecimal.valueOf(values[2]);
            case 2:
                discount = BigDecimal.valueOf(values[1]);
            case 1:
                price = BigDecimal.valueOf(values[0]);
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDiscount(final BigDecimal discount) {
        return price.multiply(discount);
    }

    public Product serve() {
        switch (condition) {
            case Condition.COLD:
                this.addCaution("Warning COLD!");
                break;
            case Condition.WARM:
                this.addCaution("Just right");
                break;
            case Condition.HOT:
                this.addCaution("Warning HOT!");
        }
        return this;
    }

    private void addCaution(String caution) {
        name += caution;
    }
}
