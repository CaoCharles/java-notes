package solutions;

public class Clothing implements Printable {
    
    private int itemID = 0;
    private String desc = "-description required-";
    private char colorCode = 'U';
    private double price = 0.0;
    
    public Clothing(double price ) {
        this.price = price;
    }            

    public Clothing(int itemID, String desc, char color,
            double price ) {
        this.itemID = itemID;
        this.desc = desc;
        this.colorCode = color;
        this.price = price;
    }            

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public char getColorCode() {
        return colorCode;
    }

    public void setColorCode(char colorCode) {
        this.colorCode = colorCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void display() {
        System.out.println("Item ID: " + getItemID());
        System.out.println("Item description: " + getDesc());
        System.out.println("Item price: " + getPrice());
        System.out.println("Color code: " + getColorCode());
    }

    @Override
    public String getDetails() {
        return getItemID() + ", " + getDesc() + ", " + getPrice();
    }
}
