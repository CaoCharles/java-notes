package demos;

import java.util.ServiceLoader;
import service.a.L;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<L> sl = ServiceLoader.load(L.class);
        L l = sl.findFirst().get();
        l.hello();

        sl.stream().forEach(e -> System.out.println(e.get().id()));
    }
}
