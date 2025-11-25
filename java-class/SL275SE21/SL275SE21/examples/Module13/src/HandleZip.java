import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class HandleZip {
    private static Logger logger =
            Logger.getLogger(HandleZip.class.getName());

    public static void main(String[] args) throws IOException{
        Path joe = Path.of(".");
        Path zip = Path.of("/joe.zip");
        Files.createFile(zip);
        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(zip))) {
            out.setLevel(Deflater.DEFAULT_COMPRESSION);
            Files.walk(joe).filter(p -> !Files.isDirectory(p))
                    .forEach(p -> {
                        ZipEntry zipEntry = new ZipEntry(p.relativize(p).toString());
                        try {
                            out.putNextEntry(zipEntry);
                            out.write(Files.readAllBytes(p));
                            out.closeEntry();
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Error creating zip entry", e);
                        }
                    });
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating zip archive", e);
        }
    }
}
