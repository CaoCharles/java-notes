import java.util.concurrent.*;

public class ImplementingExecutor {
    public static void main(String[] args) {
        Callable<String> t = new Callable<>() {
            public String call() throws Exception {
                /* perform concurrent actions */
                return "some value";
            }};
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<String> result = es.submit(t);
        try {
            String value = result.get(10, TimeUnit.SECONDS);
        } catch(Exception e) {
            /* see notes for details */
        }
    }
}
