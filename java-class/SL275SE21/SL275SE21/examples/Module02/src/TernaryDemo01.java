public class TernaryDemo01 {
    public static void main(String[] args) {
        int age  = 19;

        String message = "";

        if (age >= 20) {
            message = "Yes";
        }
        else {
            message = "No";
        }

        System.out.println(message);

        int age02 = 19;

        String message02 = (age >= 20) ? "Yes" : "No";

        System.out.println(message02);
    }
}
