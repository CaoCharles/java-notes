package practices;

public class HelloArray02 {
    public static void main(String[] args) {
        String[][] calendar = new String[12][];

        for (int i = 0; i < 12; i++) {
            int days = getDays(i + 1, false);
            calendar[i] = new String[days];
        }

        // 設定生日


        // 使用迴圈顯示生日月份的行事曆

    }

    public static int getDays(int month, boolean isLeapYear) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> isLeapYear ? 29 : 28;
            case 4, 6, 9, 11 -> 30;
            default -> 0;
        };
    }
}
