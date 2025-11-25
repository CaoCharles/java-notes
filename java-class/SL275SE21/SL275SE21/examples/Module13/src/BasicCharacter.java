import java.io.*;
import java.nio.charset.Charset;

public class BasicCharacter {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        try (Reader in = new FileReader("src/some.txt", utf8);
             Writer out = new FileWriter("src/other.txt", utf8)) {
            char[] buffer = new char[1024];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            /* exception handling logic */
            System.out.println(e);
        }
    }
}
