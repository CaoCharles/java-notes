import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class WorkingWithFilesystems {
    public static void main(String[] args) {
        FileSystem fs = FileSystems.getDefault();
        fs.getFileStores().forEach(s -> System.out.println(s.type() + ' ' + s.name()));
        fs.getRootDirectories().forEach(p -> System.out.println(p));
        String separator = fs.getSeparator();
    }
}
