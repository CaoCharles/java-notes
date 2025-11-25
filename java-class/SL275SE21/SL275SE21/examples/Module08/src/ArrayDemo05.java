public class ArrayDemo05 {
    public static void main(String[] args) {
        int[] values = {1, 2, 3};
        StringBuilder txt = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            txt.append(value);
        }

        for (int value : values) {
            txt.append(value);
        }

        int[] values02 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int sum = 0;
        for (int i = 0; i < values02.length; sum += i++) ;

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        for (int i = 0, j = 2; !(i == 3 || j == -1); i++, j--) {
            int value = matrix[i][j];
        }
    }
}
