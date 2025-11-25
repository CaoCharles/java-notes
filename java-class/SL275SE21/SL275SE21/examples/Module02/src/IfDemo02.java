public class IfDemo02 {
    public static void main(String[] args) {
        // 10: A, 9~7: B, 6~2: C, 1: D
        int score = 7;

        String level = "";

        if (score == 10) {
            level = "A";
        }
        else if (score >= 7 && score <= 9) {
            level = "B";
        }
        else if (score >= 2 && score <= 6) {
            level = "C";
        }
        else if (score ==1) {
            level = "D";
        }
        else {
            level = "N/A";
        }

        System.out.printf("Score: %d, Level: %s\n", score, level);
    }
}
