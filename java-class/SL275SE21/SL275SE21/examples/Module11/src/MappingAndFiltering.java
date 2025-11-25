import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MappingAndFiltering {
    public static void main(String[] args) {
        Stream<Order> orders = Order.getOrders();

        Map<Customer, Set<Product>> customerProducts =
                orders.collect(Collectors.groupingBy(o -> o.getCustomer(),
                        Collectors.flatMapping(o -> o.getItems().stream(),
                                Collectors.toSet())));

        Map<Customer, Set<Order>> customerOrdersOnDate =
                orders.collect(Collectors.groupingBy(o -> o.getCustomer(),
                        Collectors.filtering(o -> o.getDate().equals(LocalDate.of(2018, 11, 22)),
                                Collectors.toSet())));
    }
}
