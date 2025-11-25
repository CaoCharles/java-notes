package solutions;

public class Outdoors implements Printable {
    
    private int itemID;
    private String desc;
    private double weight;
    private double price;

    public Outdoors(int itemID, String desc, double weight, double price) {
        this.itemID = itemID;
        this.desc = desc;
        this.weight = weight;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
        System.out.println("Item weight: " + getWeight());
    }

    @Override
    public String getDetails() {
        return getItemID() + ", " + getDesc() + ", " + getPrice() + ", " + getWeight();
    }
}
