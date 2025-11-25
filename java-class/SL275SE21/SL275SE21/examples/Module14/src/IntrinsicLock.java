import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IntrinsicLock {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> sList = Collections.synchronizedList(list);
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                sList.add(name + ' ' + i);
            }
        };
        /* start threads and wait for their completions (see full code in notes) */
        synchronized (sList) {
            Iterator i = sList.iterator();
            while (i.hasNext())
                System.out.println(i.next());
        }
    }
}
