package solutions;

public class Tent extends Outdoors implements Returnable {

    private String type;

    public Tent(int itemID, String desc, double weight, 
            double price, String type) {
        super(itemID, desc, weight, price);
        this.type = type;
    }

    @Override
    public String doReturn() {
        return "Suit returns must not be unpacked";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Type: " + type);
    }
}
