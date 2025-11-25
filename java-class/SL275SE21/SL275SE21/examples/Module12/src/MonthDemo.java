public class MonthDemo {

    public static void main(String[] args) {
        int month = 3;
        boolean isLeapYear = false;
        
        if (month == 1 || month ==3||month==5||month==7
                || month == 8 || month == 10 || month == 12) {
            System.out.println("31 days in the month.");
        }
        else if (month == 2) {
            if(!isLeapYear){
                System.out.println("28 days in the month.");
            }
            else 
                System.out.println("29 days in the month.");
        }
        else if (month ==4 || month == 6 || month == 9
                || month == 11) {
            System.out.println("30 days in the month.");
        }
        else
            System.out.println("Invalid month.");
    }

}
