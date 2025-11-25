import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OtherIntermediate {
    public static void main(String[] args) {
        Stream.of("A", "C", "B", "D", "B", "D")
                .distinct()
                .sorted()
                .skip(2)
                .forEach(s -> System.out.print(s.toLowerCase()));

        System.out.println();

        Stream.of("B", "C", "A", "E", "D", "F")
                .takeWhile(s -> !s.equals("D"))
                .dropWhile(s -> !s.equals("C"))
                .limit(2)
                .forEach(s -> System.out.print(s.toLowerCase()));
    }
}
