import java.io.*;

public class BasicBinary {
    public static void main(String[] args) {
        try (InputStream in = new FileInputStream("some.xyz");
             OutputStream out = new FileOutputStream("other.xyz");) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            /* exception handling logic */
        }
    }
}
