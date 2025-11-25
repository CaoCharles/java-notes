import java.math.BigDecimal;

public class Account implements Withdrawing, Depositing {
    public BigDecimal withdraw() {
        return BigDecimal.ZERO;
    }

    public void deposit(BigDecimal amount) {
    }
}
