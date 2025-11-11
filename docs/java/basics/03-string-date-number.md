# 文字、日期、時間與數字物件

## String 類別與字串處理

String 是 Java 中最常用的類別之一，用於處理文字資料。String 物件是不可變的（immutable），一旦建立就無法修改。

### String 基本操作

```java
public class StringBasics {
    public static void main(String[] args) {
        // 字串建立方式
        String str1 = "Hello World";        // 字串字面值
        String str2 = new String("Hello World"); // 使用建構子
        String str3 = "";                   // 空字串
        String str4 = null;                 // null 參考
        
        // 字串基本資訊
        System.out.println("字串內容: \"" + str1 + "\"");
        System.out.println("字串長度: " + str1.length());
        System.out.println("是否為空字串: " + str1.isEmpty());
        
        // null 檢查的重要性
        if (str4 != null) {
            System.out.println("str4 長度: " + str4.length());
        } else {
            System.out.println("str4 是 null，無法取得長度");
        }
        
        // 字串比較
        System.out.println("\n=== 字串比較 ===");
        System.out.println("str1 == str2: " + (str1 == str2));         // false (不同物件)
        System.out.println("str1.equals(str2): " + str1.equals(str2)); // true (內容相同)
        
        // 忽略大小寫比較
        String upper = "HELLO WORLD";
        System.out.println("忽略大小寫比較: " + str1.equalsIgnoreCase(upper));
        
        // 字串比較順序 (字典序)
        String apple = "apple";
        String banana = "banana";
        System.out.println("apple.compareTo(banana): " + apple.compareTo(banana)); // 負數
        System.out.println("banana.compareTo(apple): " + banana.compareTo(apple)); // 正數
    }
}
```

### String 常用方法

```java
public class StringMethods {
    public static void main(String[] args) {
        String text = "  Hello Java Programming World!  ";
        
        // 字串轉換
        System.out.println("原始字串: \"" + text + "\"");
        System.out.println("轉大寫: \"" + text.toUpperCase() + "\"");
        System.out.println("轉小寫: \"" + text.toLowerCase() + "\"");
        System.out.println("移除前後空白: \"" + text.trim() + "\"");
        
        // 字串搜尋
        System.out.println("\n=== 字串搜尋 ===");
        String cleanText = text.trim();
        System.out.println("包含 'Java': " + cleanText.contains("Java"));
        System.out.println("以 'Hello' 開頭: " + cleanText.startsWith("Hello"));
        System.out.println("以 '!' 結尾: " + cleanText.endsWith("!"));
        System.out.println("'Java' 的位置: " + cleanText.indexOf("Java"));
        System.out.println("'o' 最後出現位置: " + cleanText.lastIndexOf("o"));
        
        // 字串分割
        System.out.println("\n=== 字串分割 ===");
        String sentence = "apple,banana,orange,grape";
        String[] fruits = sentence.split(",");
        System.out.println("水果陣列:");
        for (int i = 0; i < fruits.length; i++) {
            System.out.println((i + 1) + ". " + fruits[i]);
        }
        
        // 字串取代
        System.out.println("\n=== 字串取代 ===");
        String message = "Hello World! Hello Java!";
        System.out.println("原始: " + message);
        System.out.println("取代第一個 Hello: " + message.replaceFirst("Hello", "Hi"));
        System.out.println("取代所有 Hello: " + message.replace("Hello", "Hi"));
        System.out.println("使用正規表達式取代: " + message.replaceAll("H\\w+", "Greetings"));
        
        // 字串擷取
        System.out.println("\n=== 字串擷取 ===");
        String programming = "Java Programming";
        System.out.println("原始字串: " + programming);
        System.out.println("從索引 5 開始: " + programming.substring(5));
        System.out.println("從索引 5 到 11: " + programming.substring(5, 11));
        System.out.println("第 6 個字元: " + programming.charAt(5));
        
        // 字串格式化
        System.out.println("\n=== 字串格式化 ===");
        String name = "張小明";
        int age = 25;
        double height = 175.5;
        
        String formatted = String.format("姓名: %s, 年齡: %d, 身高: %.1f cm", 
                                        name, age, height);
        System.out.println(formatted);
        
        // printf 樣式格式化
        System.out.printf("%-10s | %3d歲 | %6.2f cm%n", name, age, height);
    }
}
```

!!! tip "String 效能建議"
    由於 String 是不可變的，頻繁的字串串接會產生大量暫時物件。對於大量字串操作，建議使用 StringBuilder 或 StringBuffer。

### StringBuilder 與 StringBuffer

```java
public class StringBuilderExample {
    public static void main(String[] args) {
        // StringBuilder vs String 效能比較示範
        System.out.println("=== 效能比較 ===");
        
        // 使用 String (效能較差)
        long startTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += "a";  // 每次都會建立新的 String 物件
        }
        long stringTime = System.currentTimeMillis() - startTime;
        
        // 使用 StringBuilder (效能較好)
        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");  // 在同一個緩衝區中操作
        }
        String sbResult = sb.toString();
        long sbTime = System.currentTimeMillis() - startTime;
        
        System.out.println("String 串接時間: " + stringTime + "ms");
        System.out.println("StringBuilder 時間: " + sbTime + "ms");
        System.out.println("StringBuilder 快了 " + (stringTime / (double)sbTime) + " 倍");
        
        // StringBuilder 常用方法
        System.out.println("\n=== StringBuilder 方法 ===");
        StringBuilder builder = new StringBuilder();
        
        // 建構和附加
        builder.append("Hello");
        builder.append(" ");
        builder.append("World");
        builder.append("!");
        System.out.println("附加後: " + builder.toString());
        
        // 插入
        builder.insert(6, "Java ");
        System.out.println("插入後: " + builder.toString());
        
        // 刪除
        builder.delete(6, 11);  // 刪除 "Java "
        System.out.println("刪除後: " + builder.toString());
        
        // 反轉
        StringBuilder reverse = new StringBuilder("racecar");
        System.out.println("原字串: " + reverse);
        System.out.println("反轉後: " + reverse.reverse());
        
        // 取代
        StringBuilder replace = new StringBuilder("Hello World");
        replace.replace(6, 11, "Java");
        System.out.println("取代後: " + replace);
        
        // StringBuffer vs StringBuilder
        System.out.println("\n=== StringBuffer vs StringBuilder ===");
        
        // StringBuffer (執行緒安全，較慢)
        StringBuffer buffer = new StringBuffer();
        buffer.append("Thread-Safe");
        System.out.println("StringBuffer: " + buffer);
        
        // StringBuilder (非執行緒安全，較快)
        StringBuilder builder2 = new StringBuilder();
        builder2.append("Not Thread-Safe, but Faster");
        System.out.println("StringBuilder: " + builder2);
    }
}
```

!!! note "StringBuilder vs StringBuffer"
    - **StringBuilder**: 非執行緒安全，效能較好，單執行緒環境推薦使用
    - **StringBuffer**: 執行緒安全，效能較差，多執行緒環境使用

## 日期與時間處理

Java 8 引入了新的日期時間 API，位於 `java.time` 套件中，取代了舊的 `Date` 和 `Calendar` 類別。

### LocalDate 類別

```java
import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class LocalDateExample {
    public static void main(String[] args) {
        // 建立 LocalDate
        LocalDate today = LocalDate.now();                    // 今天
        LocalDate birthday = LocalDate.of(1990, 5, 15);       // 1990-05-15
        LocalDate christmas = LocalDate.of(2023, Month.DECEMBER, 25); // 使用 Month 枚舉
        LocalDate parsed = LocalDate.parse("2023-12-31");     // 解析字串
        
        System.out.println("今天: " + today);
        System.out.println("生日: " + birthday);
        System.out.println("聖誕節: " + christmas);
        System.out.println("解析日期: " + parsed);
        
        // 日期資訊取得
        System.out.println("\n=== 日期資訊 ===");
        System.out.println("年份: " + today.getYear());
        System.out.println("月份: " + today.getMonth() + " (" + today.getMonthValue() + ")");
        System.out.println("日期: " + today.getDayOfMonth());
        System.out.println("星期: " + today.getDayOfWeek());
        System.out.println("年中第幾天: " + today.getDayOfYear());
        
        // 日期運算
        System.out.println("\n=== 日期運算 ===");
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate lastMonth = today.minusMonths(1);
        LocalDate nextYear = today.plusYears(1);
        
        System.out.println("下週: " + nextWeek);
        System.out.println("上個月: " + lastMonth);
        System.out.println("明年: " + nextYear);
        
        // 日期比較
        System.out.println("\n=== 日期比較 ===");
        System.out.println("今天是否在生日之後: " + today.isAfter(birthday));
        System.out.println("聖誕節是否在今天之前: " + christmas.isBefore(today));
        System.out.println("今天是否等於今天: " + today.equals(LocalDate.now()));
        
        // 計算期間
        Period age = Period.between(birthday, today);
        System.out.println("年齡: " + age.getYears() + " 歲 " + 
                          age.getMonths() + " 個月 " + 
                          age.getDays() + " 天");
        
        // 特殊日期判斷
        System.out.println("\n=== 特殊日期 ===");
        System.out.println("今年是閏年嗎: " + today.isLeapYear());
        System.out.println("2月有幾天: " + today.withMonth(2).lengthOfMonth());
        System.out.println("今年有幾天: " + today.lengthOfYear());
        
        // 尋找特定日期
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        System.out.println("本月第一天: " + firstDayOfMonth);
        System.out.println("本月最後一天: " + lastDayOfMonth);
        
        // 尋找下一個星期一
        LocalDate nextMonday = today.with(java.time.temporal.TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("下個星期一: " + nextMonday);
    }
}
```

### LocalTime 類別

```java
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeExample {
    public static void main(String[] args) {
        // 建立 LocalTime
        LocalTime now = LocalTime.now();                      // 現在時間
        LocalTime lunchTime = LocalTime.of(12, 30);           // 12:30
        LocalTime meetingTime = LocalTime.of(14, 15, 30);     // 14:15:30
        LocalTime parsed = LocalTime.parse("18:45:30");       // 解析字串
        
        System.out.println("現在時間: " + now);
        System.out.println("午餐時間: " + lunchTime);
        System.out.println("會議時間: " + meetingTime);
        System.out.println("解析時間: " + parsed);
        
        // 時間資訊取得
        System.out.println("\n=== 時間資訊 ===");
        System.out.println("小時: " + now.getHour());
        System.out.println("分鐘: " + now.getMinute());
        System.out.println("秒數: " + now.getSecond());
        System.out.println("奈秒: " + now.getNano());
        
        // 時間運算
        System.out.println("\n=== 時間運算 ===");
        LocalTime afterHour = now.plusHours(1);
        LocalTime before30Min = now.minusMinutes(30);
        LocalTime plus2Hours30Min = now.plusHours(2).plusMinutes(30);
        
        System.out.println("一小時後: " + afterHour);
        System.out.println("30分鐘前: " + before30Min);
        System.out.println("2小時30分鐘後: " + plus2Hours30Min);
        
        // 時間比較
        System.out.println("\n=== 時間比較 ===");
        System.out.println("現在是否在午餐時間之後: " + now.isAfter(lunchTime));
        System.out.println("會議時間是否在現在之前: " + meetingTime.isBefore(now));
        
        // 時間格式化
        System.out.println("\n=== 時間格式化 ===");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("h:mm a", java.util.Locale.ENGLISH);
        
        System.out.println("24小時制: " + now.format(formatter1));
        System.out.println("12小時制: " + now.format(formatter2));
        
        // 工作時間檢查範例
        LocalTime workStart = LocalTime.of(9, 0);
        LocalTime workEnd = LocalTime.of(18, 0);
        LocalTime currentTime = LocalTime.of(15, 30);
        
        if (currentTime.isAfter(workStart) && currentTime.isBefore(workEnd)) {
            System.out.println("目前在工作時間內");
        } else {
            System.out.println("目前不在工作時間內");
        }
    }
}
```

### LocalDateTime 類別

```java
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        // 建立 LocalDateTime
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime specific = LocalDateTime.of(2023, 12, 25, 15, 30, 0);
        LocalDateTime combined = LocalDateTime.of(LocalDate.of(2023, 6, 15), LocalTime.of(10, 30));
        LocalDateTime parsed = LocalDateTime.parse("2023-07-01T09:00:00");
        
        System.out.println("現在: " + now);
        System.out.println("指定時間: " + specific);
        System.out.println("組合日期時間: " + combined);
        System.out.println("解析時間: " + parsed);
        
        // 取得日期和時間部分
        System.out.println("\n=== 分離日期時間 ===");
        LocalDate dateOnly = now.toLocalDate();
        LocalTime timeOnly = now.toLocalTime();
        System.out.println("只有日期: " + dateOnly);
        System.out.println("只有時間: " + timeOnly);
        
        // 日期時間運算
        System.out.println("\n=== 日期時間運算 ===");
        LocalDateTime tomorrow = now.plusDays(1);
        LocalDateTime nextWeek = now.plusWeeks(1);
        LocalDateTime twoHoursLater = now.plusHours(2);
        
        System.out.println("明天此時: " + tomorrow);
        System.out.println("下週此時: " + nextWeek);
        System.out.println("兩小時後: " + twoHoursLater);
        
        // 計算時間間隔
        LocalDateTime start = LocalDateTime.of(2023, 1, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2023, 1, 1, 17, 30);
        Duration workDuration = Duration.between(start, end);
        
        System.out.println("\n=== 時間間隔計算 ===");
        System.out.println("開始: " + start);
        System.out.println("結束: " + end);
        System.out.println("工作時間: " + workDuration.toHours() + " 小時 " + 
                          (workDuration.toMinutes() % 60) + " 分鐘");
        
        // 格式化顯示
        System.out.println("\n=== 格式化顯示 ===");
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        DateTimeFormatter isoFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
        System.out.println("自定格式: " + now.format(customFormat));
        System.out.println("ISO 格式: " + now.format(isoFormat));
        
        // 實用範例：會議排程
        System.out.println("\n=== 會議排程範例 ===");
        LocalDateTime meetingStart = LocalDateTime.of(2023, 6, 15, 14, 0);
        LocalDateTime meetingEnd = meetingStart.plusHours(1).plusMinutes(30);
        
        System.out.println("會議開始: " + meetingStart.format(customFormat));
        System.out.println("會議結束: " + meetingEnd.format(customFormat));
        
        Duration meetingDuration = Duration.between(meetingStart, meetingEnd);
        System.out.println("會議時長: " + meetingDuration.toMinutes() + " 分鐘");
        
        // 檢查是否在會議時間內
        LocalDateTime checkTime = LocalDateTime.of(2023, 6, 15, 14, 45);
        if (checkTime.isAfter(meetingStart) && checkTime.isBefore(meetingEnd)) {
            System.out.println(checkTime.format(customFormat) + " 在會議時間內");
        }
    }
}
```

### DateTimeFormatter 格式化

```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeFormatterExample {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        
        System.out.println("=== 預定義格式 ===");
        System.out.println("ISO_LOCAL_DATE: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("ISO_LOCAL_TIME: " + now.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("ISO_LOCAL_DATE_TIME: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        System.out.println("\n=== 自定義格式 ===");
        
        // 常用格式模式
        String[] patterns = {
            "yyyy-MM-dd HH:mm:ss",           // 標準格式
            "yyyy年MM月dd日 HH:mm:ss",        // 中文格式
            "MM/dd/yyyy h:mm a",             // 美式格式
            "dd-MMM-yyyy HH:mm",             // 英文月份簡寫
            "EEEE, MMMM d, yyyy",            // 完整星期和月份
            "yyyy-MM-dd'T'HH:mm:ss"          // ISO格式
        };
        
        for (String pattern : patterns) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.TRADITIONAL_CHINESE);
            System.out.println(String.format("%-25s: %s", pattern, now.format(formatter)));
        }
        
        // 解析字串為日期時間
        System.out.println("\n=== 解析字串 ===");
        String dateStr1 = "2023-06-15 14:30:00";
        String dateStr2 = "15/06/2023 2:30 PM";
        
        DateTimeFormatter parser1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter parser2 = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a", Locale.ENGLISH);
        
        LocalDateTime parsed1 = LocalDateTime.parse(dateStr1, parser1);
        LocalDateTime parsed2 = LocalDateTime.parse(dateStr2, parser2);
        
        System.out.println("解析 \"" + dateStr1 + "\" 為: " + parsed1);
        System.out.println("解析 \"" + dateStr2 + "\" 為: " + parsed2);
        
        // 格式化模式說明
        System.out.println("\n=== 格式化模式說明 ===");
        System.out.println("y : 年份");
        System.out.println("M : 月份 (MM=數字, MMM=簡寫, MMMM=全名)");
        System.out.println("d : 日期");
        System.out.println("H : 24小時制小時");
        System.out.println("h : 12小時制小時");
        System.out.println("m : 分鐘");
        System.out.println("s : 秒數");
        System.out.println("a : AM/PM");
        System.out.println("E : 星期 (E=縮寫, EEEE=全名)");
    }
}
```

!!! tip "日期時間最佳實踐"
    - 使用新的 `java.time` API，避免舊的 `Date` 和 `Calendar`
    - LocalDateTime 適用於不需考慮時區的場合
    - 需要時區處理時使用 `ZonedDateTime`
    - 儲存到資料庫時建議使用 UTC 時間

## 數字物件與精確運算

### 包裝類別 (Wrapper Classes)

```java
public class WrapperClassExample {
    public static void main(String[] args) {
        // 基本型態 vs 包裝類別
        int primitiveInt = 100;
        Integer wrapperInt = Integer.valueOf(100);  // 明確建立
        Integer autoboxInt = 100;                   // 自動裝箱
        
        System.out.println("基本型態: " + primitiveInt);
        System.out.println("包裝類別: " + wrapperInt);
        System.out.println("自動裝箱: " + autoboxInt);
        
        // 自動裝箱與拆箱
        System.out.println("\n=== 自動裝箱與拆箱 ===");
        Integer boxed = 50;              // 自動裝箱 int → Integer
        int unboxed = boxed;             // 自動拆箱 Integer → int
        
        System.out.println("裝箱: " + boxed);
        System.out.println("拆箱: " + unboxed);
        
        // 包裝類別的 null 處理
        Integer nullInteger = null;
        try {
            int value = nullInteger;  // 會拋出 NullPointerException
        } catch (NullPointerException e) {
            System.out.println("null 拆箱錯誤: " + e.getMessage());
        }
        
        // 字串轉換
        System.out.println("\n=== 字串轉換 ===");
        String numStr1 = "123";
        String numStr2 = "45.67";
        String numStr3 = "true";
        String hexStr = "FF";
        String binStr = "1010";
        
        int intValue = Integer.parseInt(numStr1);
        double doubleValue = Double.parseDouble(numStr2);
        boolean boolValue = Boolean.parseBoolean(numStr3);
        int hexValue = Integer.parseInt(hexStr, 16);       // 16 進制
        int binValue = Integer.parseInt(binStr, 2);        // 2 進制
        
        System.out.println("字串 \"" + numStr1 + "\" 轉整數: " + intValue);
        System.out.println("字串 \"" + numStr2 + "\" 轉浮點數: " + doubleValue);
        System.out.println("字串 \"" + numStr3 + "\" 轉布林: " + boolValue);
        System.out.println("16進制 \"" + hexStr + "\" 轉整數: " + hexValue);
        System.out.println("2進制 \"" + binStr + "\" 轉整數: " + binValue);
        
        // 數字轉字串
        System.out.println("\n=== 數字轉字串 ===");
        int number = 255;
        System.out.println("十進制: " + Integer.toString(number));
        System.out.println("二進制: " + Integer.toBinaryString(number));
        System.out.println("八進制: " + Integer.toOctalString(number));
        System.out.println("十六進制: " + Integer.toHexString(number));
        
        // 常用常數
        System.out.println("\n=== 包裝類別常數 ===");
        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
        System.out.println("Double.MAX_VALUE: " + Double.MAX_VALUE);
        System.out.println("Double.MIN_VALUE: " + Double.MIN_VALUE);
        System.out.println("Double.POSITIVE_INFINITY: " + Double.POSITIVE_INFINITY);
        System.out.println("Double.NaN: " + Double.NaN);
        
        // 數值檢查
        double testValue = Double.NaN;
        System.out.println("\n=== 數值檢查 ===");
        System.out.println("是否為無限大: " + Double.isInfinite(testValue));
        System.out.println("是否為 NaN: " + Double.isNaN(testValue));
        System.out.println("是否為有限數: " + Double.isFinite(testValue));
    }
}
```

!!! warning "自動裝箱陷阱"
    ```java
    Integer a = 127;
    Integer b = 127;
    System.out.println(a == b);  // true (快取範圍 -128 到 127)
    
    Integer c = 128;
    Integer d = 128;
    System.out.println(c == d);  // false (超出快取範圍，不同物件)
    
    // 安全的比較方式
    System.out.println(c.equals(d));  // true
    ```

### BigDecimal 精確運算

```java
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalExample {
    public static void main(String[] args) {
        // 浮點數精確度問題示範
        System.out.println("=== 浮點數精確度問題 ===");
        double d1 = 0.1;
        double d2 = 0.2;
        System.out.println("double: 0.1 + 0.2 = " + (d1 + d2));          // 0.30000000000000004
        System.out.println("預期結果應該是 0.3，但實際上有誤差");
        
        // 使用 BigDecimal 解決精確度問題
        System.out.println("\n=== BigDecimal 精確運算 ===");
        BigDecimal bd1 = new BigDecimal("0.1");
        BigDecimal bd2 = new BigDecimal("0.2");
        BigDecimal sum = bd1.add(bd2);
        System.out.println("BigDecimal: 0.1 + 0.2 = " + sum);            // 0.3
        
        // BigDecimal 建立方式
        System.out.println("\n=== BigDecimal 建立方式 ===");
        BigDecimal fromString = new BigDecimal("123.456");        // 推薦：使用字串
        BigDecimal fromDouble = BigDecimal.valueOf(123.456);      // 使用 valueOf
        BigDecimal fromInt = BigDecimal.valueOf(123);             // 整數
        
        System.out.println("從字串建立: " + fromString);
        System.out.println("從 double 建立: " + fromDouble);
        System.out.println("從整數建立: " + fromInt);
        
        // 錯誤的建立方式示範
        BigDecimal wrongWay = new BigDecimal(0.1);  // 不推薦：會有精確度問題
        System.out.println("錯誤方式 new BigDecimal(0.1): " + wrongWay);
        
        // 基本運算
        System.out.println("\n=== BigDecimal 基本運算 ===");
        BigDecimal a = new BigDecimal("10.50");
        BigDecimal b = new BigDecimal("3.25");
        
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("加法 a + b = " + a.add(b));
        System.out.println("減法 a - b = " + a.subtract(b));
        System.out.println("乘法 a × b = " + a.multiply(b));
        
        // 除法需要指定精度
        BigDecimal division = a.divide(b, 4, RoundingMode.HALF_UP);
        System.out.println("除法 a ÷ b = " + division + " (四捨五入到4位小數)");
        
        // 比較
        System.out.println("\n=== BigDecimal 比較 ===");
        BigDecimal x = new BigDecimal("2.0");
        BigDecimal y = new BigDecimal("2.00");
        
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("x.equals(y): " + x.equals(y));         // false (精度不同)
        System.out.println("x.compareTo(y): " + x.compareTo(y));   // 0 (數值相等)
        
        // 金融計算範例
        System.out.println("\n=== 金融計算範例 ===");
        BigDecimal principal = new BigDecimal("10000");     // 本金
        BigDecimal rate = new BigDecimal("0.03");           // 年利率 3%
        BigDecimal time = new BigDecimal("2");              // 2 年
        
        // 單利計算: 本金 × (1 + 利率 × 時間)
        BigDecimal simpleInterest = principal.multiply(
            BigDecimal.ONE.add(rate.multiply(time))
        );
        
        // 複利計算: 本金 × (1 + 利率)^時間
        BigDecimal compoundInterest = principal.multiply(
            BigDecimal.ONE.add(rate).pow(time.intValue())
        );
        
        System.out.println("本金: " + principal);
        System.out.println("年利率: " + rate.multiply(BigDecimal.valueOf(100)) + "%");
        System.out.println("投資期間: " + time + " 年");
        System.out.println("單利計算結果: " + simpleInterest);
        System.out.println("複利計算結果: " + compoundInterest.setScale(2, RoundingMode.HALF_UP));
        
        // 四捨五入和精度控制
        System.out.println("\n=== 四捨五入和精度控制 ===");
        BigDecimal precise = new BigDecimal("123.456789");
        
        System.out.println("原數值: " + precise);
        System.out.println("四捨五入到2位: " + precise.setScale(2, RoundingMode.HALF_UP));
        System.out.println("無條件捨去2位: " + precise.setScale(2, RoundingMode.DOWN));
        System.out.println("無條件進位2位: " + precise.setScale(2, RoundingMode.UP));
        System.out.println("銀行家捨入2位: " + precise.setScale(2, RoundingMode.HALF_EVEN));
        
        // 常見的四捨五入模式
        System.out.println("\n=== 四捨五入模式說明 ===");
        BigDecimal[] testValues = {
            new BigDecimal("2.135"),
            new BigDecimal("2.145"),
            new BigDecimal("2.155")
        };
        
        System.out.println("數值\t\tHALF_UP\t\tHALF_EVEN");
        for (BigDecimal value : testValues) {
            System.out.printf("%-10s\t%-10s\t%-10s%n",
                value,
                value.setScale(2, RoundingMode.HALF_UP),
                value.setScale(2, RoundingMode.HALF_EVEN)
            );
        }
    }
}
```

!!! note "BigDecimal 使用建議"
    - **金融系統必用**: 涉及金錢計算時必須使用 BigDecimal
    - **建立方式**: 優先使用字串建構子或 valueOf 方法
    - **比較**: 使用 compareTo() 而非 equals()
    - **效能**: BigDecimal 比基本型態慢，只在需要精確度時使用

### Math 類別數學運算

```java
import java.math.BigInteger;

public class MathClassExample {
    public static void main(String[] args) {
        // 基本數學函數
        System.out.println("=== 基本數學函數 ===");
        double x = 16.0;
        double y = -25.0;
        
        System.out.println("Math.abs(" + y + ") = " + Math.abs(y));           // 絕對值
        System.out.println("Math.sqrt(" + x + ") = " + Math.sqrt(x));         // 平方根
        System.out.println("Math.pow(" + x + ", 2) = " + Math.pow(x, 2));     // 次方
        System.out.println("Math.log(" + x + ") = " + Math.log(x));           // 自然對數
        System.out.println("Math.log10(" + x + ") = " + Math.log10(x));       // 常用對數
        
        // 三角函數 (弧度制)
        System.out.println("\n=== 三角函數 ===");
        double angle = Math.PI / 4;  // 45 度
        System.out.println("角度 π/4 弧度 (45度):");
        System.out.printf("sin(π/4) = %.4f%n", Math.sin(angle));
        System.out.printf("cos(π/4) = %.4f%n", Math.cos(angle));
        System.out.printf("tan(π/4) = %.4f%n", Math.tan(angle));
        
        // 角度轉換
        double degrees = 45.0;
        double radians = Math.toRadians(degrees);
        System.out.println("\n" + degrees + " 度 = " + radians + " 弧度");
        System.out.println(radians + " 弧度 = " + Math.toDegrees(radians) + " 度");
        
        // 最大值最小值
        System.out.println("\n=== 最大值最小值 ===");
        int a = 10, b = 20, c = 15;
        System.out.println("max(10, 20) = " + Math.max(a, b));
        System.out.println("min(10, 20) = " + Math.min(a, b));
        System.out.println("三個數的最大值: " + Math.max(Math.max(a, b), c));
        
        // 四捨五入
        System.out.println("\n=== 四捨五入 ===");
        double value = 3.14159;
        System.out.println("原值: " + value);
        System.out.println("ceil (無條件進位): " + Math.ceil(value));
        System.out.println("floor (無條件捨去): " + Math.floor(value));
        System.out.println("round (四捨五入): " + Math.round(value));
        
        // 隨機數
        System.out.println("\n=== 隨機數產生 ===");
        System.out.println("隨機 0-1: " + Math.random());
        System.out.println("隨機 1-100: " + (int)(Math.random() * 100 + 1));
        
        // 使用 Random 類別 (推薦)
        java.util.Random random = new java.util.Random();
        System.out.println("Random.nextInt(100): " + random.nextInt(100));
        System.out.println("Random.nextDouble(): " + random.nextDouble());
        System.out.println("Random.nextGaussian(): " + random.nextGaussian());
        
        // 常數
        System.out.println("\n=== 數學常數 ===");
        System.out.println("π (Pi) = " + Math.PI);
        System.out.println("e (歐拉數) = " + Math.E);
        
        // BigInteger 大整數運算
        System.out.println("\n=== BigInteger 大整數 ===");
        BigInteger big1 = new BigInteger("123456789012345678901234567890");
        BigInteger big2 = new BigInteger("987654321098765432109876543210");
        
        System.out.println("big1 = " + big1);
        System.out.println("big2 = " + big2);
        System.out.println("big1 + big2 = " + big1.add(big2));
        System.out.println("big1 × big2 = " + big1.multiply(big2));
        
        // 階乘計算範例
        System.out.println("\n=== 階乘計算 (BigInteger) ===");
        int n = 20;
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        System.out.println(n + "! = " + factorial);
    }
}
```

## 重點整理

1. **String 是不可變的**，頻繁串接使用 StringBuilder
2. **新日期時間 API** (Java 8+) 比舊的 Date/Calendar 更好用
3. **LocalDate**: 日期，**LocalTime**: 時間，**LocalDateTime**: 日期時間
4. **包裝類別**提供基本型態的物件版本，支援自動裝箱拆箱
5. **BigDecimal** 用於需要精確小數運算的場合（如金融系統）
6. **Math 類別**提供豐富的數學函數

!!! tip "最佳實踐"
    - 字串比較使用 `equals()`，不用 `==`
    - 大量字串操作使用 `StringBuilder`
    - 金融計算使用 `BigDecimal`
    - 日期時間處理使用 `java.time` 套件
    - 包裝類別比較使用 `equals()`

## 練習建議

1. **字串練習**
   - 實作字串反轉功能
   - 撰寫密碼強度檢查程式
   - 實作簡單的文字統計（字數、行數）

2. **日期時間練習**
   - 計算兩個日期間的天數差
   - 實作生日提醒系統
   - 撰寫工作時間統計程式

3. **數字運算練習**
   - 使用 BigDecimal 實作複利計算機
   - 撰寫數字格式化顯示程式
   - 實作簡單的統計計算（平均、標準差）

4. **綜合練習**
   - 設計一個日記系統（包含日期、文字處理）
   - 實作個人財務管理程式
   - 撰寫文字分析工具

下一章我們將學習物件導向程式設計的核心概念：類別與物件！