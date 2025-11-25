package solutions;

public class HelloTax {
    public static void main(String[] args) {
        // 宣告單價變數
        int price = 100;
        // 宣告消費稅率變數
        double taxRate = 0.05;
        // 宣告消費稅變數
        double tax = price * taxRate;
        // 宣告含稅價格變數, 使用Math.round執行四捨五入
        long total = Math.round(price + tax);

        // 顯示上列變數
        System.out.println("price: " + price);
        System.out.println("taxRate: " + taxRate);
        System.out.println("tax: " + tax);
        System.out.println("total: " + total);
    }
}
