public class MakeThreadWait {
    public static void main(String[] args) {
        Object obj = new Object();
        Runnable r = () -> {
            try {
                synchronized (obj) {
                    obj.wait();
                }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        };
        Thread t = new Thread(r);
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        synchronized (obj) {
            obj.notify();
        }
    }
}
