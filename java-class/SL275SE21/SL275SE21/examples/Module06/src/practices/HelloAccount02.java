package practices;

public class HelloAccount02 {
    // 宣告名稱為processAccount的static方法, 接收一個Account參數
    public static void processAccount(Account acc) {
        // 修改下面的判斷條件, 判斷方法的參數是否為SavingAccount物件, 並且是否為Level.VIP
        if (false) {
            // 呼叫getInfo方法取得並顯示帳戶資訊

            // 計算並顯示利息

        }
        // 修改下面的判斷條件, 判斷方法的參數是否為CreditAccount物件
        else if (false) {
            // 呼叫toString方法取得並顯示帳戶資訊

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
