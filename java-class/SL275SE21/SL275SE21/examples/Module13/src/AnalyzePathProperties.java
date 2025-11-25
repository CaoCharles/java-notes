import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.util.Set;

public class AnalyzePathProperties {
    public static void main(String[] args) throws IOException {
        Path p1 = Path.of("/users/joe/docs/some.txt"); // absolute path
        Path p2 = Path.of("./docs/some.txt"); // relative path
        Path p3 = Path.of("./pics/s.txt"); // symbolic link
        Files.isDirectory(p1); // false
        Files.isExecutable(p1); // false
        Files.isHidden(p1); // false
        Files.isReadable(p1); // true
        Files.isWritable(p1); // true
        Files.isRegularFile(p1); // true
        Files.isSymbolicLink(p3); // true
        Files.isSameFile(p1, p2); // true
        Files.isSameFile(p1, p3); // true
        Files.probeContentType(p1); // text/plain
        PosixFileAttributes fa = Files.readAttributes(p1,PosixFileAttributes.class);
        long size = fa.size(); // 640
        FileTime t1 = fa.creationTime(); // 2019-01-24T14:23:40Z
        FileTime t2 = fa.lastModifiedTime(); // 2019-05-09T20:47:54.438626Z
        FileTime t3 = fa.lastAccessTime(); // 2019-05-10T10:16:18.715692Z
        UserPrincipal user = fa.owner(); // joe
        GroupPrincipal group = fa.group(); // staff
        Set<PosixFilePermission> permissions = fa.permissions();
        String t = PosixFilePermissions.toString(permissions); // rwxr-xr-x
    }
}
