import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class FormattingAndParsingNumeric {
    public static void main(String[] args) throws ParseException {
        BigDecimal price = BigDecimal.valueOf(2.99);
        Double tax = 0.2;
        int quantity = 12345;
        Locale locale = Locale.of("en", "GB");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

        String formattedPrice = currencyFormat.format(price);
        String formattedTax = percentageFormat.format(tax);
        String formattedQuantity = numberFormat.format(quantity);

        BigDecimal p = BigDecimal.valueOf((Double) currencyFormat.parse("£1.7"));
        Double t = (Double) percentageFormat.parse("12%");
        int q = numberFormat.parse("54,321").intValue();
    }
}
