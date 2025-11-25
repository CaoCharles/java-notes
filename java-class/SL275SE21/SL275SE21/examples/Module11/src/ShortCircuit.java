import java.util.Arrays;
import java.util.Optional;

public class ShortCircuit {
    public static void main(String[] args) {
        String[] values = {"RED","GREEN","BLUE"};
        boolean allGreen = Arrays.stream(values).allMatch(s->s.equals("GREEN"));
        boolean anyGreen = Arrays.stream(values).anyMatch(s->s.equals("GREEN"));
        boolean noneGreen = Arrays.stream(values).noneMatch(s->s.equals("GREEN"));
        Optional<String> anyColour = Arrays.stream(values).findAny();
        Optional<String> firstColour = Arrays.stream(values).findFirst();
    }
}
