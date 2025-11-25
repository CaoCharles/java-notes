package practices;

public class HelloMonth {

    public static void main(String[] args) {
        // 宣告月份變數並指另為3月
        int month = 3;

        // 宣告是否為閏年的變數並指定為false
        boolean isLeap = false;

        // 使用if判斷是否為31天的月份
        if (month == 1 || month == 3 || month == 5 ||
                month == 7 || month == 8 || month == 10 || month == 12) {
            System.out.println("31 days.");

            // 使用else if判斷是否為2月
        } else if (month == 2) {
            if (isLeap) {
                System.out.println("29 days.");
            } else {
                System.out.println("28 days.");
            }

            // 使用else if判斷是否為30天的月份
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            System.out.println("30 days.");

            // 使用else顯示Invalid month.
        } else {
            System.out.println("Invalid month.");
        }
    }
}
