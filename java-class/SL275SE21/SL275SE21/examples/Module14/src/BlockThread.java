public class BlockThread {
    public static void main(String[] args) {
        Some s = new Some();
        Runnable r = () -> {
            s.a();
            Some.b();
            synchronized (s) {
                s.c();
            }
        };
        new Thread(r).start();
        new Thread(r).start();
    }
}
