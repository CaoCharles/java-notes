import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CreateExecutor {
    public static void main(String[] args) {
        Runnable task = () -> {
            /* perform concurrent actions */
        };
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        ses.scheduleAtFixedRate(task, 10, 5, TimeUnit.SECONDS);
        ExecutorService es = Executors.unconfigurableExecutorService(ses);
    }
}
