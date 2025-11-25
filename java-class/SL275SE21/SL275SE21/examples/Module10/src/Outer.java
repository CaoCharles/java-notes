import java.math.BigDecimal;

public class Outer {
    public static void createInstance() {
        new StaticNested();
    }
    public static class StaticNested {
        // code of the nested class
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
}
