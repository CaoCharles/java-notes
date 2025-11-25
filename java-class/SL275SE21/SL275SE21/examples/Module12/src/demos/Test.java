package demos;

import java.util.logging.*;

public class Test {
    private static Logger logger =
            Logger.getLogger(demos.Test.class.getName());

    public static void main(String[] args) {
        try {
            /* actions that can throw exceptions */
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Your error message", e);
        }
        logger.log(Level.INFO, "Your message");
        logger.info("Your message");
    }
}
