import java.util.concurrent.atomic.AtomicInteger;

public class Shared {
    public int x;
    public volatile int y;
    public AtomicInteger z = new AtomicInteger(0);
}
