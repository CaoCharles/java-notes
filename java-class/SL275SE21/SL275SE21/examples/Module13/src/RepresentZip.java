import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepresentZip {
    private static Logger logger =
            Logger.getLogger(HandleZip.class.getName());

    public static void main(String[] args) throws IOException {
        Path joe = Path.of(".");
        Path zip = Path.of("/joe.zip");
        Files.createFile(zip);
        try (FileSystem fs = FileSystems.newFileSystem(zip)) {
            Files.walk(joe).forEach(source -> {
                try {
                    Path target = fs.getPath("/" + source.toString());
                    Files.copy(source, target);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error archiving file", e);
                }
            });
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating archive", e);
        }
    }
}
