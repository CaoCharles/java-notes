public class StringInitialization {
    public static void main(String[] args) {
        char[] text = {'H', 'e', 'l', 'l', 'o'};
        String a = new String(text);
        String b = new String("Hello");

        System.out.println(a == b);

        String c = a.intern();
        String d = b.intern();

        System.out.println(c == d);
    }
}
