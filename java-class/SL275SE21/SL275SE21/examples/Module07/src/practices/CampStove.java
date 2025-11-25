package practices;

public class CampStove extends Outdoors implements Returnable {

    private char size;

    public CampStove(int itemID, String desc, double weight, 
            double price, char size) {
        super(itemID, desc, weight, price);
        this.size = size;
    }

    @Override
    public String doReturn() {
        return "Suit returns must not be used";
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Size: " + size);
    }
    
}
