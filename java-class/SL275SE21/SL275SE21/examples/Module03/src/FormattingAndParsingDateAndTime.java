import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class FormattingAndParsingDateAndTime {
    public static void main(String[] args) throws ParseException {
        LocalDate date = LocalDate.of(2019, Month.APRIL, 1);
        Locale locale = Locale.of("en", "GB");
        DateTimeFormatter dateTimeFormat =
                DateTimeFormatter.ofPattern("EEEE dd MMM yyyy", locale);
        String result = date.format(dateTimeFormat); // dateTimeFormat.format(date)

        date = LocalDate.parse("Tuesday 31 Mar 2020", dateTimeFormat);
        locale = Locale.of("ru");
        dateTimeFormat =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(locale);
        result = date.format(dateTimeFormat); // dateTimeFormat.format(date);
    }
}
