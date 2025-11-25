import java.util.Random;
import java.util.Spliterator;

public class SpliteratorDemo {
    public static void main(String[] args) {
        Spliterator<Integer> s1 = new Random().ints(10, 0, 10).spliterator();
        s1.tryAdvance(v -> System.out.print(v));
        Spliterator s2 = s1.trySplit();
        if (s2 == null) {
            System.out.println("Did not split");
        } else {
            s1.forEachRemaining(v -> System.out.print(v));
            s2.forEachRemaining(v -> System.out.print(v));
        }
    }
}
