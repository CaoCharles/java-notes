import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;
import java.util.logging.Level;

public class HandleExceptionCause {
    private static Logger logger =
            Logger.getLogger(SuppressedExceptions.class.getName());

    public static void main(String[] args) {
        try {
            doThings();
        } catch (CustomException e) {
            logger.log(Level.SEVERE, "Exception encountered:", e);
            Throwable cause = e.getCause();
        }
    }

    public static void doThings() throws CustomException {
        try (Reader in = new FileReader("some.txt")) {
            char[] buffer = new char[1024];
            in.read(buffer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File error", e);
            throw new CustomException("Failed to read text", e);
        }
    }
}
