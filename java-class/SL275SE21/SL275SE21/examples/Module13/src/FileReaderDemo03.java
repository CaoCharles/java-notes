import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo03 {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("src/FileReaderDemo03.java");
                BufferedReader br = new BufferedReader(fr)) {
            String line = null;
            
            while ((line = br.readLine()) != null) {
                System.out.println(line);
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
