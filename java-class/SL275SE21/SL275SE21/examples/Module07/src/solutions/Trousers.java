package solutions;

public class Trousers extends Clothing implements Returnable {
    
    private char fit = 'U';
    private char gender = 'U';
    
    public Trousers(char fit) {
        this(15.00, fit);
    }

    public Trousers(double price, char fit) {
        super(price);
        this.fit = fit;
    }
    
    public Trousers(int itemID, String desc, char colorCode, 
            double price, char fit, char gender) {
        super(itemID, desc, colorCode, price);
        this.fit = fit;
        this.gender = gender;
    }

    @Override
    public String doReturn() {
        return "Suit returns must be within 1 days";
    }

    public char getFit() {
        return fit;
    }

    public void setFit(char fit) {
        this.fit = fit;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Fit: " + getFit());
        System.out.println("Gender: " + getGender());
    }
    
}
