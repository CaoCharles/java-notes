package solutions;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private int id;
    private String name;
    private double balance;

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0.0;
    }

    public Account(int id, String name, double balance) {
        this(id, name);
        this.balance = balance;
    }

    // 覆寫Object類別的toString()方法, 回傳帳戶資訊字串
    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name + ", Balance: " + balance;
    }

    // 加入throws IllegalArgumentException宣告
    public void deposite(double amount) throws IllegalArgumentException {
        if (amount > 0) {
            balance += amount;
        }
        else {
            // 建立並產生IllegalArgumentException
            throw new IllegalArgumentException();
        }
    }

    // 加入throws IllegalArgumentException, OverdraftException宣告
    public void withdraw(double amount)
            throws IllegalArgumentException, OverdraftException {
        if (amount <= 0) {
            // 建立並產生IllegalArgumentException
            throw new IllegalArgumentException();
        }

        if (balance < amount) {
            // 建立並產生OverdraftException
            throw new OverdraftException("Account overdraft");
        }

        balance -= amount;
    }

    public String getInfo() {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Balance: " + balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.doubleValue();
    }

    // 覆寫Object類別的equals方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return id == account.id;
    }

    // 覆寫Object類別的hashCode方法
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
