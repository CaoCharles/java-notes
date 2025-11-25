public class Drink implements Consumable, Liquid {
    @Override
    public int measure() {
        return 0;
    }

    @Override
    public void consume() {
        // drink
    }
}
