import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

public class JavaIOWatchService {
    public static void main(String[] args) {
        Path p = Path.of("src");
        try (WatchService ws = FileSystems.getDefault().newWatchService()) {
            WatchKey wk = p.register(ws, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            while ((wk = ws.take()) != null) {
                wk.pollEvents().stream().forEach(e -> System.out.println(e.kind() + " " + e.context()));
                wk.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
