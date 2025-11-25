public class ArrayDemo07 {
    public static void main(String[] args) {
        char[][] matrix = {{'A', 'B', 'C', 'D', 'E'},
                {'F', 'G', 'H', 'I', 'K'},
                {'L', 'M', 'N', 'O', 'P'},
                {'Q', 'R', 'S', 'T', 'U'},
                {'V', 'W', 'X', 'Y', 'Z'}};
        StringBuilder txt = new StringBuilder();

        outerLoopLabel:
        for (char[] row : matrix) {
            for (char value : row) {
                if (value == 'C') {
                    continue;
                }
                if (value == 'H') {
                    continue outerLoopLabel;
                }
                if (value == 'N') {
                    break;
                }
                if (value == 'S') {
                    break outerLoopLabel;
                }
                txt.append(value);
            }
            txt.append('\n');
        }
    }
}
