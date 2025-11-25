package provider02.b;

public class M02 implements service.a.L {

    @Override
    public int id() {
        return 2;
    }

    @Override
    public void hello() {
        System.out.println("Hello provider02");
    }
}
