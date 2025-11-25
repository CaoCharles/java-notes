import java.io.*;

public class FileReaderDemo04 {
    public static void main(String[] args) throws IOException {
        final FileReader fr = new FileReader("src/FileReaderDemo04.java");
        final BufferedReader br = new BufferedReader(fr);
        final FileWriter fw = new FileWriter("src/FileReaderDemo04.txt");
        final BufferedWriter bw = new BufferedWriter(fw);

        try (BufferedReader bReader = br; BufferedWriter bWriter = bw ) { // Java 7 or 8
//        try (br; bw) {  // Java 9 and later
            String line = null;
            
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
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
