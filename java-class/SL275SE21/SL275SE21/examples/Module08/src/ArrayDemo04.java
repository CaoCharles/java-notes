import java.util.Arrays;

public class ArrayDemo04 {
    public static void main(String[] args) {
        char[] a1 = {'a', 'c', 'm', 'e'};
        char[] a2 = {'t', 'o', ' ', ' ', ' '};
        System.arraycopy(a1, 2, a2, 3, 2);

        char[] b1 = {'a', 'c', 'm', 'e'};
        char[] b2 = Arrays.copyOf(b1, 5);

        String[] names1 = {"Mary","Ann","Jane","Tom"};
        String[] names2 = {"Mary","Ann","John","Tom"};
        boolean isTheSame = Arrays.equals(names1, names2);
        Arrays.sort(names2);
        Arrays.sort(names2, new LengthCompare());
    }
}
