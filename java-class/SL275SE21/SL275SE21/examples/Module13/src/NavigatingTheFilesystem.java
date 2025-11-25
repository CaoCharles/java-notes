import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NavigatingTheFilesystem {
    public static void main(String[] args) throws IOException {
        Path joe = Path.of("/", "users", "joe");
        Path p1 = Path.of("/", "users", "joe", "docs", "some.txt");

        for (int i = 0; i < p1.getNameCount(); i++) {
            Path p = p1.getName(i);
        }

        Path p2 = Path.of("./pics/s.txt");
        Files.createSymbolicLink(p2, p1);
        Files.list(joe).forEach(p -> System.out.println(p));
        Files.walk(joe).map(p -> p.toString())
                .filter(s -> s.endsWith("txt"))
                .forEach(p -> System.out.println(p));
        Path p3 = Files.readSymbolicLink(p2);
    }
}
