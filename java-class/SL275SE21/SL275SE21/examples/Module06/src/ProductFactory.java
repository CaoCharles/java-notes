public class ProductFactory {
    public enum ProductType {
        FOOD, DRINK
    }

    public static Product createProduct(ProductType productType) {
        switch(productType) {
            case FOOD:
                return new Food();
            case DRINK:
                return new Drink();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        Product product = ProductFactory.createProduct(ProductType.FOOD);
    }
}


