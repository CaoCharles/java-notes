import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextFilter {
    public static boolean removeA(String s) {
        return s.equals("remove A");
    }

    public int sortText(String s1, String s2) {
        return s1.compareTo(s2);
    }

    public static void main(String[] args) {
        TextFilter filter = new TextFilter();
        List<String> list = new ArrayList<>();
        list.removeIf(s -> TextFilter.removeA(s));
        list.removeIf(TextFilter::removeA); // same as the line above
        Collections.sort(list, (s1, s2) -> filter.sortText(s1, s2));
        Collections.sort(list, filter::sortText); // same as the line above
        Collections.sort(list, (s1, s2) -> s1.compareToIgnoreCase(s2));
        Collections.sort(list, String::compareToIgnoreCase); // same as the line above
    }
}
