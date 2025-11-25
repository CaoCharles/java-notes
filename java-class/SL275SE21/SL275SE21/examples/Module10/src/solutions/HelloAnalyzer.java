package solutions;

public class HelloAnalyzer {

    public static void searchArr(String[] strList, String searchStr, 
            StringAnalyzer analyzer) {
        for (String currentStr : strList) {
            if (analyzer.analyze(currentStr, searchStr)) {
                System.out.println(currentStr);
            }
        }

        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        String[] strList =
                {"tomorrow", "toto", "to", "timbukto", "the", "hello", "heat"};
        String searchStr = "to";
        
        System.out.println("===字串中包含searchStr===");
        // 將下面匿名類別的實作改為Lambda expression
        HelloAnalyzer.searchArr(strList, searchStr,
            new StringAnalyzer() {
                @Override
                public boolean analyze(String t, String s) {
                    return t.contains(s);
                }
            }
        );

        System.out.println("===字串開頭為searchStr===");
        // 將下面匿名類別的實作改為Lambda expression
        StringAnalyzer sa = new StringAnalyzer() {
                @Override
                public boolean analyze(String t, String s) {
                    return t.startsWith(s);
                }
            };

        HelloAnalyzer.searchArr(strList, searchStr, sa);

        System.out.println("===字串開頭為th===");
        HelloAnalyzer.searchArr(strList, "th", sa);
    }
}
