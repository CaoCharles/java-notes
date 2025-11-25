import java.time.LocalDate;

public class ComparingStringObjects {
    public static void main(String[] args) {
        String a = "Hello";
        String b = "Hello";
        String c = new String("Hello");
        String d = "heLLo";
        boolean sameObjectAB = (a == b); //true
        boolean sameObjectAC = (a == c); //false
        boolean sameContentAB = (a.equals(b)); //true
        boolean sameContentCA = (a.equals(c)); //true
        boolean sameContentAD = (a.equals(d)); //false
        boolean sameNoCaseAD = (a.equalsIgnoreCase(d)); //true
    }
}
