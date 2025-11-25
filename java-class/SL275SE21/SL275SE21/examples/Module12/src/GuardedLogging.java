import java.util.logging.Logger;
import java.util.logging.Level;

public class GuardedLogging {
    private static Logger logger =
            Logger.getLogger(GuardedLogging.class.getName());

    public static void main(String[] args) {
        int id = 101;

        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Product " + id + " has been selected");
        if (logger.isLoggable(Level.FINER)) {
            logger.log(Level.FINE, "Product " + id + " has been selected");
        }
        logger.log(Level.FINE, "Product {0} has been selected", id);
    }
}
