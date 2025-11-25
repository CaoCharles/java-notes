import java.io.*;
import java.nio.charset.Charset;

public class ConnectingStreams {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        try (BufferedReader in =
                     new BufferedReader(new InputStreamReader(new FileInputStream("src/some.txt"), utf8));
             PrintWriter out =
                     new PrintWriter(new OutputStreamWriter(new FileOutputStream("src/other.txt"), utf8))) {
            String line = null;
            while ((line = in.readLine()) != null) { // null indicates the end of stream
                out.println(line);
            }
        } catch (IOException e) {
            /* exception handling logic */
            System.out.println(e);
        }
    }
}
