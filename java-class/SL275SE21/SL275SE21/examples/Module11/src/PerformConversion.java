import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PerformConversion {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.UK);
        String s2 = list.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingDouble(
                                p -> p.getPrice().doubleValue()),
                        n -> fmt.format(n)));
    }
}
