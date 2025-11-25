package solutions;

public enum Level {
    NORMAL(0.01), VIP(0.02);

    private double rate;

    private Level(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
