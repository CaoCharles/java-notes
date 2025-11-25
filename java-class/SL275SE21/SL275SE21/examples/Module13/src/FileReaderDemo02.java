import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo02 {
    public static void main(String[] args) {

        try (FileReader fr = new FileReader("src/FileReaderDemo02.java")) {
            int read = 0;
            
            while ((read = fr.read()) != -1) {
                System.out.print((char) read);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
