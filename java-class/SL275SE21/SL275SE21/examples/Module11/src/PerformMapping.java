import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class PerformMapping {
    public static void main(String[] args) {
        Function<Product, String> nameMapper = p -> p.getName();
        UnaryOperator<String> trimMapper = n -> n.trim();
        ToIntFunction<String> lengthMapper = n -> n.length();

        List<Product> list = new ArrayList<>();
        list.stream().map(nameMapper.andThen(trimMapper)).mapToInt(lengthMapper).sum();
    }
}
