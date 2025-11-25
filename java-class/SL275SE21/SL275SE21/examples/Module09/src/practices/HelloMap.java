package practices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HelloMap {
    public static void main(String[] args) {
        Map<Integer, Account> data = getAccountMap();

        // 修改下面敘述, 取得data所有key
        Set<Integer> ids = null;

        for (Integer id : ids) {
            // 使用id取得並顯示帳戶資訊

        }
    }

    public static Map<Integer, Account> getAccountMap() {
        List<Account> data = HelloList.getAccounts();
        Map<Integer, Account> map = new HashMap<>();

        for (Account a : data) {
            // 加入帳戶物件到map, key指定為帳號

        }

        return map;
    }
}
