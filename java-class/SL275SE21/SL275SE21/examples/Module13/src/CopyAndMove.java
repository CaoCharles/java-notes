import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CopyAndMove {
    private static Logger logger =
            Logger.getLogger(CopyAndMove.class.getName());

    public static void main(String[] args) throws IOException {
        Path source = Path.of("docs");
        Path backup = Path.of("backup");
        Path archive = Path.of("/archive");
        Files.list(source).forEach(file -> {
            try {
                Files.copy(file, backup.resolve(file),
                        StandardCopyOption.COPY_ATTRIBUTES,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error copying file", ex);
            }
        });
        Files.move(backup, archive, StandardCopyOption.COPY_ATTRIBUTES,
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.ATOMIC_MOVE);
    }
}
