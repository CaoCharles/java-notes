import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class FormattingMessagePatterns {
    public static void main(String[] args) {
        Locale locale = Locale.of("en", "GB");
        ResourceBundle bundle = ResourceBundle.getBundle("resources.messages", locale);
        String pattern = bundle.getString("product");
        String name = "Cookie";
        double price = 2.29;
        int quantity = 4;
        LocalDate bestBefore = LocalDate.of(2019, 4, 1);
        String result = MessageFormat.format(pattern,
                name,price,quantity,bestBefore);
    }
}
