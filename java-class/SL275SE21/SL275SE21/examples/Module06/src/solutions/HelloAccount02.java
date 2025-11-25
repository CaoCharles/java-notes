package solutions;

import java.math.BigDecimal;

public class HelloAccount02 {
    // 宣告名稱為processAccount的static方法, 接收一個Account參數
    public static void processAccount(Account acc) {
        // 判斷方法的參數是否為SavingAccount物件, 並且是否為Level.VIP
        if (acc instanceof SavingAccount sa && sa.getLevel() == Level.VIP) {
            // 呼叫getInfo方法取得並顯示帳戶資訊
            System.out.println(sa.getInfo());
            // 計算並顯示利息
            System.out.println("Interest: " + sa.getBalance() * sa.getLevel().getRate());
        }
        // 判斷方法的參數是否為CreditAccount物件
        else if (acc instanceof CreditAccount) {
            // 呼叫toString方法取得並顯示帳戶資訊
            System.out.println(acc.toString());
        }
    }

    public static void main(String[] args) {
        SavingAccount sa =
                new SavingAccount(101, "Simon", 100.0, Level.VIP);
        CreditAccount ca =
                new CreditAccount(102, "Mary", 150.0, 200);

        System.out.println(sa.getInfo());
        System.out.println();
        System.out.println(ca.getInfo());
        System.out.println();

        processAccount(sa);
        System.out.println();
        processAccount(ca);
    }
}
