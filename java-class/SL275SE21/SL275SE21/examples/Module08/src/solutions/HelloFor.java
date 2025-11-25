package solutions;

public class HelloFor {
    public static void main(String[] args) {
        int total = 0;

        // 使用for迴圈執行1+2+3...+9+10
        for (int i = 1; i <= 10; i++) {
            total += i;
        }

        System.out.println(total);
    }
}
