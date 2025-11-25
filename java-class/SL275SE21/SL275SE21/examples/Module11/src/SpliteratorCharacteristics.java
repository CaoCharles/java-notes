import java.util.Random;
import java.util.Spliterator;

public class SpliteratorCharacteristics {
    public static void main(String[] args) {
        Spliterator<Integer> s = new Random().ints(10, 0, 10).spliterator();
        String characteristics =
                "Concurrent " + s.hasCharacteristics(Spliterator.CONCURRENT) + "\n" +
                        "Distinct " + s.hasCharacteristics(Spliterator.DISTINCT) + "\n" +
                        "Immutable " + s.hasCharacteristics(Spliterator.IMMUTABLE) + "\n" +
                        "NonNull " + s.hasCharacteristics(Spliterator.NONNULL) + "\n" +
                        "Ordered " + s.hasCharacteristics(Spliterator.ORDERED) + "\n" +
                        "Sized " + s.hasCharacteristics(Spliterator.SIZED) + "\n" +
                        "Sorted " + s.hasCharacteristics(Spliterator.SORTED) + "\n" +
                        "Subsized " + s.hasCharacteristics(Spliterator.SUBSIZED);
        System.out.println(characteristics);
        System.out.println("Size " + s.getExactSizeIfKnown());
        System.out.println("Estimate Size " + s.estimateSize());
    }
}
