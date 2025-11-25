import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class CheckedException {
    public static void main(String[] args) {
        try {
            openFile(null);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void openFile(String fileName) throws IOException {
        if (fileName == null) {
            throw new NoSuchFileException("Filename must be set");
        }
    }
}
