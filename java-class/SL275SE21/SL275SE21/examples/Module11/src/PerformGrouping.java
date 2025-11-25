import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PerformGrouping {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();

        Map<Boolean, List<Product>> productTypes =
                list.stream()
                        .collect(Collectors.partitioningBy(p->p instanceof Drink));

        Map<LocalDate, List<Product>> productGroups =
                list.stream()
                        .collect(Collectors.groupingBy(p->p.getBestBefore()));
    }
}
