import java.util.ArrayList;
import java.util.List;

public class EnsureExclusive {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                synchronized (list) {
                    list.add(name + ' ' + i);
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
    }
}
