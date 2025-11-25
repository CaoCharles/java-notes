package solutions;

public class HelloAccount {
    public static void main(String[] args) {
        // 宣告並建立帳戶物件
        Account a = new Account();
        // 呼叫setId, setName, setBalance設定帳戶資訊
        a.setId(101);
        a.setName("Simon");
        a.setBalance(100);
        // 呼叫帳戶的getInfo方法取得並顯示帳戶資訊
        System.out.println(a.getInfo());
        // 呼叫帳戶的deposite方法存款150
        a.deposite(150);
        // 呼叫帳戶的getInfo方法取得並顯示帳戶資訊
        System.out.println(a.getInfo());
        // 呼叫帳戶的withdraw方法存款120並顯示提款是否成功
        boolean w = a.withdraw(120);
        System.out.println(w ? "Successful" : "Failure");
        // 呼叫帳戶的getInfo方法取得並顯示帳戶資訊
        System.out.println(a.getInfo());
    }
}
