package practices;

public class Shirt extends Clothing implements Returnable {
    
    private char fit = 'U';
    
    public Shirt(char fit) {
        this(15.00, fit);
    }

    public Shirt(double price, char fit) {
        super(price);
        this.fit = fit;
    }
    
    public Shirt(int itemID, String desc, char colorCode, 
            double price, char fit) {
        super(itemID, desc, colorCode, price);
        this.fit = fit;
    }

    @Override
    public String doReturn() {
        return "Suit returns must be within 3 days";
    }

    public char getFit() {
        return fit;
    }

    public void setFit(char fit) {
        this.fit = fit;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Fit: " + getFit());
    }
}
