package solutions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloAccount {
    private static Logger logger =
            Logger.getLogger(solutions.HelloAccount.class.getName());

    public static void main(String[] args) {
        Account a = new Account(101, "Simon", 100);

        a.deposite(100);

        // 呼叫Account.withdraw方法提款500並執行必要的例外控制
        try {
            a.withdraw(500);
        }
        catch (OverdraftException e) {
            logger.log(Level.SEVERE, "Account exception", e);
        }
    }
}
