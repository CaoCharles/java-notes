import java.math.BigDecimal;

public class AnonymousDemo01 {
    public static void main(String[] args) {
        Order order = new Order() {
            @Override
            public BigDecimal getDiscount() {
                return BigDecimal.valueOf(0.1);
            }
        };
    }
}
