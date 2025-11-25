import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateTemporary {
    public static void main(String[] args) throws IOException {
        Path p1 = Files.createTempDirectory("TEMP");
        Path p2 = Files.createTempFile(p1, "TEMP",".tmp");
        Files.deleteIfExists(p2);
        Files.deleteIfExists(p1);
    }
}
