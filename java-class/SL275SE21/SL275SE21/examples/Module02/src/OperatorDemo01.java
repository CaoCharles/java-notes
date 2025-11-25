public class OperatorDemo01 {

    public static void main(String[] args) {
        byte v = 3;
        byte v2 = 5;
//        byte v3 = v + v2;   // compile error, int to byte
//        byte v4 = (byte) v + v2;  // casting
        int v4 = v + v2;


        int v5 = 3;
        float v6 = 5.0F;
        float v7 = v5 + v6;

        short v8 = 3;
        double v9 = 5.0;
        double v10 = v8 + v9;
    }

}
