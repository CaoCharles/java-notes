package practices;

public class HelloTax {
    public static void main(String[] args) {
        // 宣告單價變數
        int price = 100;
        // 宣告消費稅率變數
        double taxRate = 0.03; // 3%
        // 宣告消費稅變數
        double tax = price * taxRate; // 單價變數 x 消費稅率變數
        // 宣告含稅價格變數, 使用Math.round執行四捨五入
        // 因為 Math.round 回傳 long，而 totalPrice 我們希望用 int 表示金額。
        int totalPrice = (int) Math.round(price + tax);

        // 顯示上列變數
        System.out.println("單價: " + price);
        System.out.println("稅率: " + taxRate);
        System.out.println("稅額: " + tax);
        System.out.println("含稅價格: " + totalPrice);
    }
}
