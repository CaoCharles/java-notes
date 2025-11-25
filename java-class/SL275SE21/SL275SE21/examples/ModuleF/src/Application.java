public class Application {
    private static final Application theApp = new Application();
    private Application() { }
    public static Application get() {
        return theApp;
    }
}
