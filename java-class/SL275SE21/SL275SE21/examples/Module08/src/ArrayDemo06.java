public class ArrayDemo06 {
    public static void main(String[] args) {
        char[][] game = {{'O', 'X', 'X'},
                         {'X', 'X', 'O'},
                         {'O', ' ', 'O'}};
        StringBuilder txt = new StringBuilder();

        for (int x = 0; x < game.length; x++) {
            int y = 0;
            while (y < game[x].length) {
                txt.append(game[x][y]);
                y++;
            }
            txt.append('\n');
        }

        for (char[] row : game) {
            for (char value : row) {
                txt.append(value);
            }
            txt.append('\n');
        }
    }
}
