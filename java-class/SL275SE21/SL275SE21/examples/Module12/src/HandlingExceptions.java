import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class HandlingExceptions {
    private static Logger logger =
            Logger.getLogger(HandlingExceptions.class.getName());

    public static void main(String[] args) throws CustomException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("some.txt"));
            String text = in.readLine();
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Error opening file", ex);
            return;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error reading file", ex);
            throw new CustomException("Failed to read text", ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error closing file", ex);
            }
        }
    }
}
