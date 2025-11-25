package practices;

import java.math.BigDecimal;

public class HelloAccount {
    public static void main(String[] args) {
        // 宣告並建立帳戶物件, 傳送帳號, 戶名兩個參數
        Account a = new Account(101, "Simon");

        // 呼叫帳戶的setBalance方法, 傳送的參數為BigDecimal物件
        a.setBalance(BigDecimal.valueOf(100));
        System.out.println(a.getInfo());

        double rate = 0.0;
        // 為變數rate設定VIP的利率值
        rate = Level.VIP.getRate();
        double interest = 0.0;
        // 為變數interest設定使用利率rate計算的利息
        interest = a.getBalance() * rate;
        System.out.println("Interest: " + interest);
    }
}
