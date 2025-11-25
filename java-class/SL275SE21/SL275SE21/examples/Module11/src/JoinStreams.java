import java.util.ArrayList;
import java.util.List;

public class JoinStreams {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList();
        double x = orders.stream()
                .flatMap(order -> order.items())
                .filter(item -> item.getName().equals("Tea"))
                .mapToDouble(item -> item.getPrice().doubleValue())
                .sum();
    }
}
