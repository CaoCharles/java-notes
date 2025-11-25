package practices;

public class CreditAccount extends Account {
    private double limit;

    public CreditAccount(int id, String name, double limit) {
        // 修改下面的敘述, 使用super呼叫父類別constructor, 傳送參數id, name
        super(0, "");
        // 設定limit

    }

    public CreditAccount(int id, String name, double balance, double limit) {
        // 修改下面的敘述, 使用super呼叫父類別constructor, 傳送參數id, name, balance
        super(0, "", 0.0);
        // 設定limit

    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    // 覆寫父類別的withdraw方法, 提款的金額是餘額(balance)+額度(limit)


    // 覆寫父類別的getInfo方法


    // 覆寫父類別的toString方法

}
