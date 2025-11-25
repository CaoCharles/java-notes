import java.io.IOException;
import java.nio.file.Path;

public class ConstructingFilesystemPaths {
    public static void main(String[] args) throws IOException {
        Path someFile = Path.of("/","users","joe","docs","some.txt");
        Path justSomeFile = someFile.getFileName();
        Path docsFolder = someFile.getParent();
        Path currentFolder = Path.of(".");
        Path acmeFile = docsFolder.resolve("../pics/acme.jpg");
        Path otherFile = someFile.resolveSibling("other.txt");
        Path normalisedAcmeFile = acmeFile.normalize();
        Path verifiedPath = acmeFile.toRealPath();
        Path betweenSomeAndAcme = someFile.relativize(acmeFile);
    }
}
