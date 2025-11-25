package solutions;

import java.util.Collections;
import java.util.List;

public class HelloComparator {
    public static void main(String[] args) {
        SortByBalance s = new SortByBalance();
        List<Account> data = HelloList.getAccounts();

        // 依照帳戶餘額排序data中的帳戶物件
        Collections.sort(data, s);

        for (Account a : data) {
            System.out.println(a);
        }
    }
}
