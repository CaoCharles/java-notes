import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo01 {
    public static void main(String[] args) {
        FileReader fr = null;
        
        try {
            fr = new FileReader("src/FileReaderDemo01.java");
            
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
        finally {
            if (fr != null) {
                try {
                    fr.close();
                }
                catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
