import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeletePaths {
    private static Logger logger =
            Logger.getLogger(DeletePaths.class.getName());

    public static void main(String[] args) throws IOException {
        Path backup = Path.of("backup");
        Files.walk(backup)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException ex) {
                        logger.log(Level.SEVERE, "Error deleting file", ex);
                    }
                });
    }
}
