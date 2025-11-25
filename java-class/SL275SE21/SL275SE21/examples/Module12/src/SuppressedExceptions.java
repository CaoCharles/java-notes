import java.util.logging.Logger;
import java.util.logging.Level;

public class SuppressedExceptions {
    private static Logger logger =
            Logger.getLogger(SuppressedExceptions.class.getName());

    public static void main(String[] args) {
        try (SomeResource r = new SomeResource()) {
            r.doThings(true);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception encountered:", ex);
            Throwable[] suppressedExceptions = ex.getSuppressed();
            for (final Throwable exception : suppressedExceptions) {
                logger.log(Level.SEVERE, "Suppressed Exception:", exception);
            }
        }
    }
}
