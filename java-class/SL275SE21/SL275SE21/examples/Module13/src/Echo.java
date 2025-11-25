import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String txt = null;
        System.out.println("To quit type: exit");
        System.out.println("Type value and press enter:");
        while (!(txt = s.nextLine()).equals("exit")) {
            System.out.println("Echo: " + txt);
        }
    }
}
