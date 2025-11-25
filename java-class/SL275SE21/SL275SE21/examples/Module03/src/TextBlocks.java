public class TextBlocks {
    public static void main(String[] args) {
        String basicText = "Tea\tprice 1.99 quantity 2\n\"English Breakfast\"\n";
        String textBlock = """
                Tea price 1.99 quantity 2
                "English Breakfast"
                """;
        // All String operations apply as usual:
        // int p1 = basicText.indexOf("price");
        int p2 = textBlock.indexOf("price");
    }
}
