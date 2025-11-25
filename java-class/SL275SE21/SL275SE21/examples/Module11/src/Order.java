import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class Order {
    private List<Product> items;
    private Customer customer;
    private LocalDate date;

    public Stream<Product> items() {
        return items.stream();
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static Stream<Order> getOrders() {
        List<Order> orders = List.of(
                new Order(),
                new Order(),
                new Order()
        );

        orders.get(0).setDate(LocalDate.of(2018, 11, 21));
        orders.get(0).setCustomer(new Customer("Joe"));
        orders.get(0).setItems(
                List.of(
                        new Drink("Tea", BigDecimal.valueOf(1.99)),
                        new Food("Cake", BigDecimal.valueOf(2.99))
                ));

        orders.get(1).setDate(LocalDate.of(2018, 11, 21));
        orders.get(1).setCustomer(new Customer("Bob"));
        orders.get(1).setItems(
                List.of(
                        new Drink("Coffee", BigDecimal.valueOf(1.99))
                ));

        orders.get(2).setDate(LocalDate.of(2018, 11, 21));
        orders.get(2).setCustomer(new Customer("Bob"));
        orders.get(2).setItems(
                List.of(
                        new Drink("Coffee", BigDecimal.valueOf(1.99)),
                        new Food("Cake", BigDecimal.valueOf(2.99))
                ));

        return orders.stream();
    }
}
