public class OperatorDemo02 {

    public static void main(String[] args) {
        int ageYears = 35;      // try 68 and 69
        int ageDays = ageYears * 365;

        // One operand is long(24L), the calculation result is long
        long ageSeconds = ageYears * 365 * 24L * 60 * 60;
        System.out.println("You are " + ageDays + " days old.");
        System.out.println("You are " + ageSeconds + " seconds old.");

        int ageDays02 = ageYears * 365;

        // All operand are int, the calculation result is int
        // int type between -2147483648 and 2147483647
        long ageSeconds02 = ageYears * 365 * 24 * 60 * 60;
        System.out.println("You are " + ageDays02 + " days old.");
        System.out.println("You are " + ageSeconds02 + " seconds old.");
    }

}
