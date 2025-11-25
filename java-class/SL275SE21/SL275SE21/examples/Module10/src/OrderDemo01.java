public class OrderDemo01 {
    public static void main(String[] args) {
        Order.createShippingMode("Fast");
        Order.createShippingMode("Normal");
        Order order1 = new Order();
        Order order2 = new Order();

        Order order3 = new Order();
        Order order4 = new Order();
        order3.addItem(new Drink("Tea"), 2);
        order3.addItem(new Food("Cake"), 1);
        order4.addItem(new Drink("Tea"), 1);
    }
}
