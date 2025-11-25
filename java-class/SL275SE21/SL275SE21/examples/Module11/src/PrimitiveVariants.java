import java.util.stream.Stream;

public class PrimitiveVariants {
    public static void main(String[] args) {
        Stream.of("ONE", "TWO", "THREE", "FOUR")
                .mapToInt(s -> s.length())
                .peek(i -> System.out.println(i))
                .filter(i -> i > 3)
                .sum();
    }
}
