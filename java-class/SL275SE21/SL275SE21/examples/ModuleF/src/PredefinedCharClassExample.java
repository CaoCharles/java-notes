import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PredefinedCharClassExample {

    public static void main(String[] args) {
        String t = "Jo told me 20 ways to San Jose in 15 minutes.";

        Pattern p1 = Pattern.compile("\\d\\d");
        Matcher m1 = p1.matcher(t);
        
        while (m1.find()) {
            System.out.println("Found: " + m1.group());
        }
    }

}
