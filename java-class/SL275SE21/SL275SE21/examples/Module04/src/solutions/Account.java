package solutions;

public class Account {
    // 宣告帳號變數
    private int id;
    // 宣告戶名變數
    private String name;
    // 宣告餘額變數
    private double balance;

    // 宣告存款方法, 接收存款金額參數
    public void deposite(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // 宣告提款方法, 接收提款金額參數, 回傳提款是否成功(true/false)
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        else {
            return false;
        }
    }

    // 傳回帳戶資訊字串
    public String getInfo() {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Balance: " + balance;
    }

    // 宣告傳回帳號方法
    public int getId() {
        return id;
    }

    // 宣告設定帳號方法, 接收帳號參數
    public void setId(int id) {
        this.id = id;
    }

    // 宣告傳回戶名方法
    public String getName() {
        return name;
    }

    // 宣告設定戶名方法, 接收戶名參數
    public void setName(String name) {
        this.name = name;
    }

    // 宣告傳回餘額方法
    public double getBalance() {
        return balance;
    }

    // 宣告設定餘額方法, 接收餘額參數
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
