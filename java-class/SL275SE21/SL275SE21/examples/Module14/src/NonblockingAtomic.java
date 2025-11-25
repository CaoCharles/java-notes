public class NonblockingAtomic {
    public static void main(String[] args) {
        Shared s = new Shared();
        Runnable r = () -> {
            int y = 0;
            while(y < 10) {
                y = s.z.incrementAndGet();
            }
        };
        new Thread(r).start();
        new Thread(r).start();
    }
}
