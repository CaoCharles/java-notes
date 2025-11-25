import java.lang.annotation.Annotation;
import java.util.stream.Stream;

public class DynamicallyDiscover {
    public static void main(String[] args) {
        // All class level annotations and their types:
        Stream.of(Shop.class.getAnnotations())
                .forEach(t -> System.out.println(t));
        // Type of the first class level annotation:
        Class annotationType = Shop.class.getAnnotations()[0].annotationType();
        // Class level annotations of BusinessPolicy type:
        BusinessPolicy[] policyAnnotations =
                Shop.class.getAnnotationsByType(BusinessPolicy.class);
        // Retrieve values of annotation elements:
        for (BusinessPolicy policy : policyAnnotations) {
            System.out.println(policy.name());
            System.out.println(policy.value());
            for (String country : policy.countries()) {
                System.out.println(country);
            }
        }
    }
}
