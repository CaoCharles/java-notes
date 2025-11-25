public class ArrayDemo03 {
    public static void main(String[] args) {
        int[][] matrix = new int[2][3];
        matrix[0][1] = 5;
        matrix[1][2] = 7;

        int[][] matrix02 = {{4, 1}, {2, 0, 5}};

        Product[][] products = new Product[2][2];
        products[1][1] = new Food("Cake");
        products[0][0] = new Drink("Tea");

        Product[][] products02 =
                { {new Food("Cake"), null, new Food("Cookie")},
                  {null, new Drink("Tea")} };
    }
}
