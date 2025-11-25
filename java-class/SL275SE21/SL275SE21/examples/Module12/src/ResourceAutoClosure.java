import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ResourceAutoClosure {
    private static Logger logger =
            Logger.getLogger(ResourceAutoClosure.class.getName());

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("some.txt"));
             PrintWriter out = new PrintWriter(new FileWriter("other.txt"))) {
            String text = in.readLine();
            out.println(text);
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Opening file error", ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Read-write error", ex);
        }
    }
}
