package solutions;

public class HelloMonth {

    public static void main(String[] args) {
        // 宣告月份變數並指另為3月
        int month = 3;
        // 宣告是否為閏年的變數並指定為false
        boolean isLeapYear = false;

        // 使用if判斷是否為31天的月份並顯示31 days.
        if (month == 1 || month ==3||month==5||month==7
                || month == 8 || month == 10 || month == 12) {
            System.out.println("31 days.");
        }
        // 使用else if判斷是否為2月並判斷是否閏年顯示28 days或29 days.
        else if (month == 2) {
            if(!isLeapYear){
                System.out.println("28 days.");
            }
            else {
                System.out.println("29 days.");
            }
        }
        // 使用else if判斷是否為30天的月份並顯示30 days.
        else if (month ==4 || month == 6 || month == 9
                || month == 11) {
            System.out.println("30 days in the month.");
        }
        // 使用else顯示Invalid month.
        else {
            System.out.println("Invalid month.");
        }
    }
}
