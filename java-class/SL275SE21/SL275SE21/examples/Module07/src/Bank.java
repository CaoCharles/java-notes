import java.math.BigDecimal;

public class Bank
        implements Withdrawing,
                   Depositing,
                   Authentication {
    private Account a;
    private Security s;

    public BigDecimal withdraw() {
        authenticate();
        return a.withdraw();
    }

    public void deposit(BigDecimal amount) {
        authenticate();
        a.deposit(amount);
    }

    public void authenticate() {
        s.authenticate();
    }
}
