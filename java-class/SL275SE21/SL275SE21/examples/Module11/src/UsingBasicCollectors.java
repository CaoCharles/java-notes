import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class UsingBasicCollectors {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        DoubleSummaryStatistics stats =
                list.stream()
                        .collect(Collectors.summarizingDouble(p -> p.getPrice().doubleValue()));

        String s1 =
                list.stream()
                        .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(",")));

        List<Product> drinks =
                list.stream().filter(p -> p instanceof Drink).collect(Collectors.toList());
    }
}
