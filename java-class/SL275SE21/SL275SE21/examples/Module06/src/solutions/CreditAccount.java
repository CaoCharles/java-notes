package solutions;

public class CreditAccount extends Account {
    private double limit;

    public CreditAccount(int id, String name, double limit) {
        super(id, name);
        this.limit = limit;
    }

    public CreditAccount(int id, String name, double balance, double limit) {
        super(id, name, balance);
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance() + limit) {
            setBalance(getBalance() - amount);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\n" +
                "Limit: " + limit;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Limit: " + limit;
    }
}
