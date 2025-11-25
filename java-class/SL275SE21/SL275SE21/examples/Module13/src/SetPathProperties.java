import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.time.Instant;
import java.util.Set;

public class SetPathProperties {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("/users/joe/docs/some.txt");
        Files.setLastModifiedTime(path, FileTime.from(Instant.now()));
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-r--");
        Files.setPosixFilePermissions(path, perms);
        FileSystem fs = path.getFileSystem();
        UserPrincipalLookupService uls = fs.getUserPrincipalLookupService();
        UserPrincipal user = uls.lookupPrincipalByName("joe");
        GroupPrincipal group = uls.lookupPrincipalByGroupName("staff");
        Files.setOwner(path, user);
        Files.getFileAttributeView(path, PosixFileAttributeView.class).setGroup(group);
    }
}
