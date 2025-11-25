package solutions;

import java.util.*;

public class HelloMap {
    public static void main(String[] args) {
        Map<Integer, Account> data = getAccountMap();

        // 修改下面敘述, 取得data所有key
        Set<Integer> ids = data.keySet();

        for (Integer id : ids) {
            // 使用id取得並顯示帳戶資訊
            System.out.println(data.get(id));
        }
    }

    public static Map<Integer, Account> getAccountMap() {
        List<Account> data = HelloList.getAccounts();
        Map<Integer, Account> map = new HashMap<>();

        for (Account a : data) {
            // 加入帳戶物件到map, key指定為帳號
            map.put(a.getId(), a);
        }

        return map;
    }
}
