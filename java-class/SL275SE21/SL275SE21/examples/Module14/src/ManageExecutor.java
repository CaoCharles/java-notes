import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ManageExecutor {
    public static void main(String[] args) {
        /* create pool of 3 threads */
        ExecutorService es = Executors.newFixedThreadPool(3);
        /* launch 10 Runnable tasks with up to 3 running at any time */
        for (int i = 0; i < 10; i++) {
            es.execute(()->{
                /* perform concurrent actions and check for interruption */
            });
        }
        es.shutdown(); /* stop accepting new tasks */
        try {
            /* wait for existing tasks to terminate and check if they all have actually stopped */
            if (!es.awaitTermination(30, TimeUnit.SECONDS)){
                /* request cancelation of tasks that are still running */
                es.shutdownNow();
            }
        }catch(InterruptedException e){
            /* request cancelation of runnings tasks when launcher thread was interrupted */
            es.shutdownNow();
            Thread.currentThread().interrupt(); /* continue launcher thread interrupt process */
        }
    }
}
