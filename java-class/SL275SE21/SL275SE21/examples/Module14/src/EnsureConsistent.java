public class EnsureConsistent {
    public static void main(String[] args) {
        Shared s = new Shared();
        new Thread(() -> {
            while (s.y < 1) {
                int x = s.x;
            }
        }).start();
        new Thread(() -> {
            s.x = 2;
            s.y = 2;
        }).start();
    }
}
