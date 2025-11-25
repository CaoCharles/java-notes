public class StringOperations {
    public static void main(String[] args) {
        String a = " Hello ";
        a = a.trim();
        String b = a.concat("World");
        String c = a + "World";
        String d = c.toLowerCase(); //false

        String s = "";
        s = 1 + 1 + "u";    // s is 2u
        s = "u" + 1 + 1;    // s is u11
        s = "u" + (1 + 1);  // s is u2
    }
}
