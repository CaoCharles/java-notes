package solutions;

public class CustomShirt extends Clothing {
    private double customFee;

    public CustomShirt(int itemID, String desc, char color, double price, double customFee) {
        super(itemID, desc, color, price);
        this.customFee = customFee;
    }

    public double getCustomFee() {
        return customFee;
    }

    public void setCustomFee(double customFee) {
        this.customFee = customFee;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Custom fee: " + customFee);
    }
}
