import java.util.Locale;

public class LanguagesAndCountries {
    public static void main(String[] args) {
        Locale uk = Locale.of("en", "GB");
        Locale th = Locale.of("th", "TH", "TH");
        Locale us = Locale.of("en", "US");
        Locale fr = Locale.of("fr", "FR");
        Locale cf = Locale.of("fr", "CA");
        Locale fr2 = Locale.of("fr", "029");
        Locale es = Locale.of("fr");
        Locale current = Locale.getDefault();

        // Example constructing locale that uses Thai digits and Buddhist calendar:
        Locale th2 = Locale.forLanguageTag("th-TH-u-ca-buddhist-nu-thai");
    }
}
