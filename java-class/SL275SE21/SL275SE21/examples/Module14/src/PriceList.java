import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PriceList {
    private List<Product> menu = new ArrayList<>();
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock rl = rwl.readLock();
    private Lock wl = rwl.writeLock();

    public Product get(int id) {
        rl.lock();
        try {
            return menu.stream().filter(p -> p.getId() == id).findFirst().get();
        } finally {
            rl.unlock();
        }
    }

    public void add(Product product) {
        wl.lock();
        try {
            menu.add(product);
        } finally {
            wl.unlock();
        }
    }
}
