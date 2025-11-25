public class Test {
    public static void main(String[] args) {
        Lateral la = new Lateral();
        new Thread(la).start();
        new Thread(la).start();
        new Thread(la).start();
        new Thread(la).start();
    }
}
