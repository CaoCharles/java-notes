package provider.b;

public class M implements service.a.L {

    @Override
    public int id() {
        return 1;
    }

    @Override
    public void hello() {
        System.out.println("Hello provider");
    }
}
