import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStreams {
    public static void main(String[] args) {
        IntStream.generate(() -> (int) (Math.random() * 10)).takeWhile(n -> n != 3).sum();
        Stream.of(new Product("Cookie", BigDecimal.valueOf(3), BigDecimal.ZERO),
                        new Product("Tea", BigDecimal.TWO, BigDecimal.ZERO))
                .forEach(p -> p.setPrice(BigDecimal.ONE));
        List<Product> list = Product.getProducts();
        Product[] array = list.toArray(new Product[3]);
        list.stream().parallel().mapToDouble(p -> p.getPrice().doubleValue()).sum();
        Arrays.stream(array).filter(p -> p.getPrice().doubleValue() > 2).forEach(p -> p.setDiscount(BigDecimal.valueOf(0.1)));
    }
}
