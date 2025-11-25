package solutions;

import java.util.Comparator;

public class SortByBalance implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        // 修改下面敘述, 傳回比較帳戶餘額大小的結果
        return Double.compare(a1.getBalance(), a2.getBalance());
    }
}
