public interface Y {
    void e();

    public default void b() {
        System.out.println("Y.b()");
    }

    private void c() {
        System.out.println("Y.c()");
    }

    public static void d() {
        System.out.println("Y.d()");
    }
}
