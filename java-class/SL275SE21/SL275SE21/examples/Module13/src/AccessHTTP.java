import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class AccessHTTP {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Path.of("docs", "index.html");
        URI uri = URI.create("http://openjdk.java.net");
        HttpRequest req = HttpRequest.newBuilder(uri).GET().build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<Path> res = client.send(req, HttpResponse.BodyHandlers.ofFile(path));
    }
}
