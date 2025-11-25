public interface X {
    void a();

    public default void b() {
        System.out.println("X.b()");
    }

    private void c() {
        System.out.println("X.c()");
    }

    public static void d() {
        System.out.println("X.d()");
    }
}
