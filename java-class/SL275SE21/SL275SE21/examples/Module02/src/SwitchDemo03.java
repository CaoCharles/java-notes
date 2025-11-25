public class SwitchDemo03 {
    public static void main(String[] args) {
        // 10: A, 9~7: B, 6~2: C, 1: D
        int score = 7;

        String level =
            switch (score) {
                case 10 ->  "A";
                case 9, 8, 7 -> "B";
                case 6, 5, 4, 3, 2 -> "C";
                case 1 -> "D";
                default -> {
                    System.out.println("Error!!");
                    yield "N/A";
                }
            };

        System.out.printf("Score: %d, Level: %s\n", score, level);
    }
}
