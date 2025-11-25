import java.time.LocalDate;
import java.math.BigDecimal;

public final class Food extends Product {

    static {
        System.out.println("Food static initializer");
    }

    {
        System.out.println("Food instance initializer");
    }

    private LocalDate bestBefore;
    private BigDecimal discount;

    public Food() {
        super("Food");
    }

    public Food(String name, LocalDate bestBefore){
        super(name);
        this.bestBefore = bestBefore;
    }

    public Food(int id, String name, double price, LocalDate bestBefore) {
        super(id, name, BigDecimal.valueOf(price), BigDecimal.ZERO);
        this.bestBefore = bestBefore;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public BigDecimal getDiscount() {
        return price.subtract(this.discount.add(super.discount));
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal discount = bestBefore.isEqual(LocalDate.now().plusDays(1))
                ? super.getPrice().multiply(BigDecimal.valueOf(0.1))
                : BigDecimal.ZERO;
        return super.getPrice().subtract(discount);
    }

    public void serve() {
        // put food on a plate
    }

    @Override
    public String toString() {
        return super.toString()+" "+bestBefore;
    }
}
