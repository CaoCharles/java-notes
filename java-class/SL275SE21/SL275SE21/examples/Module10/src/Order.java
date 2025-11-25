import java.math.BigDecimal;
import java.util.Set;
import java.util.HashSet;

public class Order {
    private Set<Item> items = new HashSet<>();

    public void manageTax(final String saleLocation) {
        class OrderTaxManager {
            private BigDecimal findRate(Product product) {
                // use saleLocation and product to find the tax rate
                return BigDecimal.ZERO;
            }

            BigDecimal calculateTax() {
                // find tax rate in a given sale location for each product
                // calculate tax value
                return BigDecimal.ZERO;
            }
        }
        OrderTaxManager taxManager = new OrderTaxManager();
        BigDecimal taxTotal = taxManager.calculateTax();
    }

    public static void createShippingMode(String description) {
        new ShippingMode(description);
    }

    private static class ShippingMode {
        private String description;
        public ShippingMode(String description) {
            this.description = description;
        }
        // other methods and variables of the ShippingMode class
    }

    public void addItem(Product product, int quantity) {
        items.add(new Item(product, quantity));
    }

    class Item {
        private Product product;
        private int quantity;

        private Item(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
        // other methods of the Item class
    }

    public BigDecimal getDiscount() {
        return BigDecimal.ZERO;
    }
}
