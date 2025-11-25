public class MutableTextObjects {
    public static void main(String[] args) {
        StringBuilder a = new StringBuilder();
        a.append("tea");
        a.append('s');
        a.insert(3, 'm');
        a.delete(2, 4);
        a.reverse();
        int length = a.length();
        int capacity = a.capacity(); // 16 a.insert(4,'s');
    }
}
