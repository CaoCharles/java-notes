import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NonblockingConcurrency {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> copyOnWriteList = new CopyOnWriteArrayList<>(list);
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                copyOnWriteList.add(name + ' ' + i);
            }
        };
        /* start threads and wait for their completions (see full code in notes) */
        Iterator i = copyOnWriteList.iterator();
        while (i.hasNext())
            System.out.println(i.next());
    }
}