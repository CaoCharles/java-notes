public class SwitchDemo02 {
    public static void main(String[] args) {
        // 10: A, 9~7: B, 6~2: C, 1: D
        int score = 7;

        String level = "";

        switch (score) {
            case 10 -> level = "A";
            case 9, 8, 7-> level = "B";
            case 6, 5, 4, 3, 2 -> level = "C";
            case 1 -> level = "D";
            default -> level = "N/A";
        }

        System.out.printf("Score: %d, Level: %s\n", score, level);
    }
}
