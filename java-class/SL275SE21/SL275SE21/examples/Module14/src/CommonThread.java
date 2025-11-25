public class CommonThread {
    public static void main(String[] args) {
        Runnable r = () -> {
            /* do work */
        };
        Thread t = new Thread(r,"My Thread");
        t.setDaemon(true);
        t.start();
        long id = t.getId();
        if (t.isDaemon()) {
            /* it will auto-terminate once all user threads have terminated */
        } t.setPriority(3);
        try {
            t.join(); // wait for the thread to terminate
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
