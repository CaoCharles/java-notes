package solutions;

public class HelloMonthSwitch {

    public static void main(String[] args) {
        // 宣告月份變數並指另為3月
        int month = 3;
        // 宣告是否為閏年的變數並指定為false
        boolean isLeapYear = false;

        // 使用switch判斷month變數
        switch (month) {
            // 使用case判斷是否為31天的月份並顯示31 days.
            case 1, 3, 5, 7, 8, 10, 12 ->
                    System.out.println("31 days.");
            // 使用case判斷是否為2月並判斷是否閏年顯示28 days或29 days.
            case 2 ->
                    System.out.println(isLeapYear ? "29 days." : "28 days.");
            // 使用case判斷是否為30天的月份並顯示30 days.
            case 4, 6, 9, 11 ->
                    System.out.println("30 days.");
            // 使用default顯示Invalid month.
            default ->
                    System.out.println("Invalid month.");
        }
    }
}
