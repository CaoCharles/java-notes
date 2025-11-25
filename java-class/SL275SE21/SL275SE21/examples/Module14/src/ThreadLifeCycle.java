public class ThreadLifeCycle {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        boolean x = t2.isAlive();
        Thread.State phase = t1.getState();
        t1.start();
    }
}
