package solutions;

public class StoveFuel extends Outdoors {
    private double capacity;

    public StoveFuel(int itemID, String desc, double weight, double price, double capacity) {
        super(itemID, desc, weight, price);
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Capacity: " + capacity);
    }
}
