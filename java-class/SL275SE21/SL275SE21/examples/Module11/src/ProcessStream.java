import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;

public class ProcessStream {
    public static void main(String[] args) {
        String[] values = {"RED","GREEN","BLUE"};
        long v1 = Arrays.stream(values).filter(s->s.indexOf('R') != -1).count(); // 2
        int v2 = Arrays.stream(values).mapToInt(v->v.length()).sum(); // 12
        OptionalDouble v3 = Arrays.stream(values).mapToInt(v->v.length()).average();
        double avgValue = v3.isPresent() ? v3.getAsDouble() : 0; // 4
        Optional<String> v4 = Arrays.stream(values).max((s1,s2) -> s1.compareTo(s2));
        Optional<String> v5 = Arrays.stream(values).min((s1, s2) -> s1.compareTo(s2));
        String maxValue = (v4.isPresent()) ? v4.get() : "no data"; // RED
        String minValue = (v5.isPresent()) ? v5.get() : "no data"; // BLUE
    }
}
