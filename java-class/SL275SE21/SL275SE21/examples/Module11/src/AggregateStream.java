import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AggregateStream {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();

        Optional<String> x1 = list.stream()
                .map(p->p.getName())
                .reduce((s1,s2)->s1+" "+s2);
                /*simple reduction*/

        String x2 = list.stream()
                .map(p->p.getName())
                .reduce("",(s1,s2)->s1+" "+s2);
                /*reduction with initial(default) value*/

        String x3 = list.stream()
                .parallel()
                .reduce("",(s,p)->p.getName()+" "+s,(s1,s2)->s1+s2);
                /*reduction with initial(default) value and a parallel combiner*/
    }
}
