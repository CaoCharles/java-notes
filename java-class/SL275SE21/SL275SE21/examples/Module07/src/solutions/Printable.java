package solutions;

public interface Printable {
    // 宣告名稱為getDetails的抽象方法, 沒有參數, 回傳String
    public String getDetails();

    // 宣告名稱為report的default方法, 沒有參數, 沒有回傳
    public default void report() {
        System.out.println(getDetails());
    }
}
