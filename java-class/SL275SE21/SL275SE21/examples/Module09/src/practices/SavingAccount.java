package practices;

public class SavingAccount extends Account {
    private Level level;

    public SavingAccount(int id, String name, Level level) {
        super(id, name);
        this.level = level;
    }

    public SavingAccount(int id, String name, double balance, Level level) {
        super(id, name, balance);
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Level: " + level;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Level: " + level;
    }
}
