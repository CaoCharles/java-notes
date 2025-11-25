import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class FormattingAndLocalizationSummary {
    public static void main(String[] args) {
        Locale locale = Locale.of("en", "GB");
        ResourceBundle bundle = ResourceBundle.getBundle("resources.messages", locale);
        String pattern = bundle.getString("product");
        String name = "Cookie";
        BigDecimal price = BigDecimal.valueOf(2.99);
        int quantity = 4;
        LocalDate bestBefore = LocalDate.of(2019, Month.APRIL, 1);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy", locale);
        String fPrice = currencyFormat.format(price);
        String fQuantity = numberFormat.format(quantity);
        String fBestBefore = dateFormat.format(bestBefore); // or bestBefore.format(dateFormat);
        String result = MessageFormat.format(pattern, name, fPrice, fQuantity, fBestBefore);
    }
}
