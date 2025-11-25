public class ApplicationDemo {
    public static void main(String[] args) {
        Application app1 = Application.get();
        Application app2 = Application.get();
        boolean alwaysTrue = app1 == app2;
    }
}
