import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
    public static void main(String[] args) {
        String text = "Find delimited elements: -tea-, -coffee- and -cookie-";
        Pattern pattern = Pattern.compile("-(.*?)-");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
        matcher.results().map(v -> v.group()).forEach(v -> System.out.println(v));
        String result = matcher.replaceAll(v -> "<" + v.group(1) + ">");
        System.out.println(result);
    }
}
