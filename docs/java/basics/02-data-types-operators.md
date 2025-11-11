# 基本資料型態、運算子與流程控制敘述

## 基本資料型態 (Primitive Data Types)

Java 提供 8 種基本資料型態，這些型態直接儲存在記憶體中，不是物件。

### 整數型態

| 型態 | 大小 | 範圍 | 預設值 | 用途 |
|------|------|------|--------|------|
| `byte` | 8 位元 | -128 到 127 | 0 | 節省記憶體的小整數 |
| `short` | 16 位元 | -32,768 到 32,767 | 0 | 小範圍整數 |
| `int` | 32 位元 | -2³¹ 到 2³¹-1 | 0 | 最常用的整數型態 |
| `long` | 64 位元 | -2⁶³ 到 2⁶³-1 | 0L | 大整數 |

```java
public class DataTypesExample {
    public static void main(String[] args) {
        // 整數型態宣告與初始化
        byte smallNumber = 127;
        short mediumNumber = 32000;
        int normalNumber = 2147483647;
        long bigNumber = 9223372036854775807L; // 注意 L 後綴
        
        System.out.println("byte: " + smallNumber);
        System.out.println("short: " + mediumNumber);
        System.out.println("int: " + normalNumber);
        System.out.println("long: " + bigNumber);
        
        // 數值字面值的不同進制表示
        int decimal = 26;          // 十進制
        int octal = 032;           // 八進制 (0 開頭)
        int hexadecimal = 0x1A;    // 十六進制 (0x 開頭)
        int binary = 0b11010;      // 二進制 (0b 開頭, Java 7+)
        
        System.out.println("十進制 26 = 八進制 " + octal + 
                          " = 十六進制 " + hexadecimal + 
                          " = 二進制 " + binary);
    }
}
```

### 浮點數型態

| 型態 | 大小 | 精確度 | 預設值 | 用途 |
|------|------|--------|--------|------|
| `float` | 32 位元 | 約 7 位小數 | 0.0f | 單精度浮點數 |
| `double` | 64 位元 | 約 15 位小數 | 0.0 | 雙精度浮點數（預設） |

```java
public class FloatingPointExample {
    public static void main(String[] args) {
        // 浮點數型態
        float singlePrecision = 3.14159f;  // 注意 f 後綴
        double doublePrecision = 3.141592653589793;
        
        System.out.println("float: " + singlePrecision);
        System.out.println("double: " + doublePrecision);
        
        // 科學記號表示
        double scientific = 1.23e-4;  // 1.23 × 10⁻⁴
        System.out.println("科學記號: " + scientific);
        
        // 特殊值
        double positive_infinity = Double.POSITIVE_INFINITY;
        double negative_infinity = Double.NEGATIVE_INFINITY;
        double not_a_number = Double.NaN;
        
        System.out.println("正無窮大: " + positive_infinity);
        System.out.println("負無窮大: " + negative_infinity);
        System.out.println("非數值: " + not_a_number);
    }
}
```

!!! warning "注意"
    浮點數計算可能有精確度問題，如果需要精確計算（如金融應用），應使用 `BigDecimal` 類別。

### 字元與布林型態

```java
public class CharBooleanExample {
    public static void main(String[] args) {
        // 字元型態 (16 位元 Unicode)
        char letter = 'A';
        char chinese = '中';
        char unicode = '\u0041';  // Unicode 表示 'A'
        char number = '5';
        
        System.out.println("字母: " + letter);
        System.out.println("中文: " + chinese);
        System.out.println("Unicode: " + unicode);
        System.out.println("數字字元: " + number);
        
        // 布林型態
        boolean isJavaFun = true;
        boolean isCompleted = false;
        
        System.out.println("Java 有趣嗎？ " + isJavaFun);
        System.out.println("完成了嗎？ " + isCompleted);
        
        // 字元的數值運算
        char a = 'A';
        char z = 'Z';
        System.out.println("A 的 ASCII 值: " + (int)a);
        System.out.println("Z 的 ASCII 值: " + (int)z);
        System.out.println("A 到 Z 共有: " + (z - a + 1) + " 個字母");
    }
}
```

## 變數宣告與命名規則

### 變數宣告語法

```java
// 宣告語法: 型態 變數名稱 = 初始值;
int age = 25;
double salary;  // 宣告但未初始化
salary = 50000.0;  // 後續賦值

// 同時宣告多個相同型態的變數
int x = 10, y = 20, z;
```

### 命名規則與慣例

```java
public class NamingConventions {
    // 有效的變數名稱
    int age;              // ✓ 小駝峰命名法
    String firstName;     // ✓ 小駝峰命名法
    double accountBalance; // ✓ 小駝峰命名法
    boolean isValid;      // ✓ 布林變數通常以 is 開頭
    
    // 常數命名 (final 變數)
    final int MAX_SIZE = 100;        // ✓ 全大寫加底線
    final double PI = 3.14159;       // ✓ 數學常數
    
    public static void main(String[] args) {
        // 區域變數範例
        String studentName = "張小明";
        int totalScore = 0;
        boolean hasPassedExam = false;
        
        // 無效的變數名稱（編譯錯誤）
        // int 123number;     // ✗ 不能以數字開頭
        // String class;      // ✗ 不能使用關鍵字
        // double my-salary;  // ✗ 不能使用連字號
        
        System.out.println("學生姓名: " + studentName);
    }
}
```

!!! tip "命名最佳實踐"
    - 使用有意義的名稱：`age` 比 `a` 好
    - 避免縮寫：`studentName` 比 `stuNm` 好
    - 常數使用全大寫：`MAX_VALUE`
    - 布林變數使用 `is`、`has`、`can` 等前綴

## 運算子 (Operators)

### 算術運算子

```java
public class ArithmeticOperators {
    public static void main(String[] args) {
        int a = 15, b = 4;
        
        // 基本算術運算子
        System.out.println("a + b = " + (a + b));  // 加法: 19
        System.out.println("a - b = " + (a - b));  // 減法: 11
        System.out.println("a * b = " + (a * b));  // 乘法: 60
        System.out.println("a / b = " + (a / b));  // 除法: 3 (整數除法)
        System.out.println("a % b = " + (a % b));  // 餘數: 3
        
        // 浮點數除法
        double c = 15.0, d = 4.0;
        System.out.println("c / d = " + (c / d));  // 3.75
        
        // 混合型態運算
        System.out.println("整數除以浮點數: " + (a / d));  // 3.75
        
        // 遞增遞減運算子
        int x = 5;
        System.out.println("x = " + x);           // 5
        System.out.println("++x = " + (++x));     // 前置遞增: 6
        System.out.println("x++ = " + (x++));     // 後置遞增: 6 (顯示後遞增)
        System.out.println("x = " + x);           // 7
        
        int y = 10;
        System.out.println("--y = " + (--y));     // 前置遞減: 9
        System.out.println("y-- = " + (y--));     // 後置遞減: 9 (顯示後遞減)
        System.out.println("y = " + y);           // 8
    }
}
```

### 比較運算子

```java
public class ComparisonOperators {
    public static void main(String[] args) {
        int a = 10, b = 20;
        
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a == b: " + (a == b));  // false (等於)
        System.out.println("a != b: " + (a != b));  // true  (不等於)
        System.out.println("a < b:  " + (a < b));   // true  (小於)
        System.out.println("a <= b: " + (a <= b));  // true  (小於等於)
        System.out.println("a > b:  " + (a > b));   // false (大於)
        System.out.println("a >= b: " + (a >= b));  // false (大於等於)
        
        // 字串比較注意事項
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        
        System.out.println("\n字串比較:");
        System.out.println("str1 == str2: " + (str1 == str2));     // true  (字串池)
        System.out.println("str1 == str3: " + (str1 == str3));     // false (不同物件)
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true  (內容相同)
    }
}
```

!!! warning "字串比較陷阱"
    使用 `==` 比較字串時比較的是物件參考，不是內容。字串內容比較應使用 `equals()` 方法。

### 邏輯運算子

```java
public class LogicalOperators {
    public static void main(String[] args) {
        boolean p = true, q = false;
        
        // 邏輯運算子
        System.out.println("p = " + p + ", q = " + q);
        System.out.println("p && q: " + (p && q));  // false (邏輯 AND)
        System.out.println("p || q: " + (p || q));  // true  (邏輯 OR)
        System.out.println("!p:     " + (!p));      // false (邏輯 NOT)
        System.out.println("!q:     " + (!q));      // true  (邏輯 NOT)
        
        // 短路評估示範
        int a = 5, b = 0;
        System.out.println("\n短路評估:");
        
        // AND 短路：如果第一個條件為 false，不評估第二個條件
        if (false && (++a > 0)) {
            System.out.println("這行不會執行");
        }
        System.out.println("a = " + a);  // a 還是 5，沒有遞增
        
        // OR 短路：如果第一個條件為 true，不評估第二個條件
        if (true || (++b > 0)) {
            System.out.println("條件成立，但 b 沒有遞增");
        }
        System.out.println("b = " + b);  // b 還是 0，沒有遞增
        
        // 複雜邏輯表達式
        int age = 20;
        boolean hasLicense = true;
        boolean canDrive = (age >= 18) && hasLicense;
        System.out.println("可以開車嗎？ " + canDrive);
    }
}
```

### 位元運算子

```java
public class BitwiseOperators {
    public static void main(String[] args) {
        int a = 12;  // 二進制: 1100
        int b = 25;  // 二進制: 11001
        
        System.out.println("a = " + a + " (二進制: " + Integer.toBinaryString(a) + ")");
        System.out.println("b = " + b + " (二進制: " + Integer.toBinaryString(b) + ")");
        
        // 位元運算子
        System.out.println("a & b = " + (a & b) + " (AND)");   // 位元 AND: 8
        System.out.println("a | b = " + (a | b) + " (OR)");    // 位元 OR: 29
        System.out.println("a ^ b = " + (a ^ b) + " (XOR)");   // 位元 XOR: 21
        System.out.println("~a = " + (~a) + " (NOT)");         // 位元 NOT: -13
        
        // 位移運算子
        System.out.println("a << 2 = " + (a << 2));  // 左移 2 位: 48
        System.out.println("a >> 2 = " + (a >> 2));  // 右移 2 位: 3
        
        // 實用範例：檢查奇偶數
        int number = 17;
        if ((number & 1) == 0) {
            System.out.println(number + " 是偶數");
        } else {
            System.out.println(number + " 是奇數");
        }
    }
}
```

## 流程控制敘述

### if-else 條件敘述

```java
public class ConditionalStatements {
    public static void main(String[] args) {
        // 簡單 if 敘述
        int score = 85;
        
        if (score >= 60) {
            System.out.println("及格！");
        }
        
        // if-else 敘述
        if (score >= 90) {
            System.out.println("優秀");
        } else if (score >= 80) {
            System.out.println("良好");
        } else if (score >= 70) {
            System.out.println("普通");
        } else if (score >= 60) {
            System.out.println("及格");
        } else {
            System.out.println("不及格");
        }
        
        // 三元運算子 (簡潔的條件判斷)
        String result = (score >= 60) ? "及格" : "不及格";
        System.out.println("考試結果: " + result);
        
        // 複雜條件範例
        int age = 25;
        boolean hasJob = true;
        double income = 30000;
        
        if (age >= 18 && hasJob && income >= 25000) {
            System.out.println("符合貸款資格");
        } else {
            System.out.println("不符合貸款資格");
            
            // 詳細原因分析
            if (age < 18) {
                System.out.println("原因: 年齡不足");
            }
            if (!hasJob) {
                System.out.println("原因: 沒有工作");
            }
            if (income < 25000) {
                System.out.println("原因: 收入不足");
            }
        }
    }
}
```

### switch 敘述

```java
public class SwitchStatement {
    public static void main(String[] args) {
        // 傳統 switch 敘述
        int dayOfWeek = 3;
        String dayName;
        
        switch (dayOfWeek) {
            case 1:
                dayName = "星期一";
                break;
            case 2:
                dayName = "星期二";
                break;
            case 3:
                dayName = "星期三";
                break;
            case 4:
                dayName = "星期四";
                break;
            case 5:
                dayName = "星期五";
                break;
            case 6:
                dayName = "星期六";
                break;
            case 7:
                dayName = "星期日";
                break;
            default:
                dayName = "無效的日期";
                break;
        }
        
        System.out.println("今天是: " + dayName);
        
        // Switch 表達式 (Java 14+)
        String dayType = switch (dayOfWeek) {
            case 1, 2, 3, 4, 5 -> "工作日";
            case 6, 7 -> "週末";
            default -> "無效日期";
        };
        
        System.out.println("日期類型: " + dayType);
        
        // 字元 switch 範例
        char grade = 'B';
        switch (grade) {
            case 'A':
                System.out.println("優秀！繼續保持！");
                break;
            case 'B':
                System.out.println("良好，還有進步空間");
                break;
            case 'C':
                System.out.println("普通，需要更加努力");
                break;
            case 'D':
                System.out.println("及格，但要加強學習");
                break;
            case 'F':
                System.out.println("不及格，需要重修");
                break;
            default:
                System.out.println("無效的成績");
                break;
        }
        
        // 字串 switch (Java 7+)
        String month = "三月";
        switch (month) {
            case "三月":
            case "四月":
            case "五月":
                System.out.println("春季");
                break;
            case "六月":
            case "七月":
            case "八月":
                System.out.println("夏季");
                break;
            case "九月":
            case "十月":
            case "十一月":
                System.out.println("秋季");
                break;
            case "十二月":
            case "一月":
            case "二月":
                System.out.println("冬季");
                break;
            default:
                System.out.println("無效的月份");
                break;
        }
    }
}
```

!!! tip "Switch 最佳實踐"
    - 永遠包含 `default` case 處理未預期的值
    - 記得在每個 case 後加上 `break`（除非故意要執行下一個 case）
    - 考慮使用新的 switch 表達式語法（Java 14+）來避免遺漏 `break`

### 迴圈敘述

#### for 迴圈

```java
public class ForLoops {
    public static void main(String[] args) {
        // 基本 for 迴圈
        System.out.println("1 到 10 的數字:");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // 倒數迴圈
        System.out.println("10 到 1 的倒數:");
        for (int i = 10; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // 步進值不為 1 的迴圈
        System.out.println("偶數 (2 到 20):");
        for (int i = 2; i <= 20; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // 巢狀迴圈 - 九九乘法表
        System.out.println("\n九九乘法表:");
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%d×%d=%2d  ", i, j, i * j);
            }
            System.out.println();
        }
        
        // 空的 for 迴圈部分
        int sum = 0;
        int n = 100;
        for (int i = 1; i <= n; sum += i, i++) {
            // 迴圈體可以是空的，所有邏輯都在迴圈條件中
        }
        System.out.println("1 到 " + n + " 的總和: " + sum);
    }
}
```

#### while 與 do-while 迴圈

```java
public class WhileLoops {
    public static void main(String[] args) {
        // while 迴圈
        System.out.println("while 迴圈範例:");
        int count = 1;
        while (count <= 5) {
            System.out.println("第 " + count + " 次");
            count++;
        }
        
        // do-while 迴圈 (至少執行一次)
        System.out.println("\ndo-while 迴圈範例:");
        int number = 10;
        do {
            System.out.println("數字: " + number);
            number--;
        } while (number > 5);
        
        // 實用範例：輸入驗證 (模擬)
        System.out.println("\n數字猜測遊戲模擬:");
        int targetNumber = 42;
        int guess = 30;  // 模擬使用者輸入
        int attempts = 0;
        
        while (guess != targetNumber) {
            attempts++;
            System.out.println("第 " + attempts + " 次猜測: " + guess);
            
            if (guess < targetNumber) {
                System.out.println("太小了！");
                guess += 5;  // 模擬下一次猜測
            } else {
                System.out.println("太大了！");
                guess -= 3;  // 模擬下一次猜測
            }
            
            // 避免無限迴圈
            if (attempts > 10) {
                System.out.println("嘗試次數過多，遊戲結束");
                break;
            }
        }
        
        if (guess == targetNumber) {
            System.out.println("恭喜！猜對了！答案是 " + targetNumber);
            System.out.println("總共嘗試了 " + attempts + " 次");
        }
    }
}
```

### break 與 continue

```java
public class BreakContinue {
    public static void main(String[] args) {
        // break 範例
        System.out.println("break 範例 - 找到第一個偶數就停止:");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println("找到偶數: " + i);
                break;  // 跳出迴圈
            }
            System.out.println("奇數: " + i);
        }
        
        // continue 範例
        System.out.println("\ncontinue 範例 - 只印出奇數:");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;  // 跳過本次迴圈，進入下一次
            }
            System.out.println("奇數: " + i);
        }
        
        // 標籤 (label) 與巢狀迴圈
        System.out.println("\n標籤範例 - 巢狀迴圈中的 break:");
        outer: for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    System.out.println("在 i=" + i + ", j=" + j + " 時跳出外層迴圈");
                    break outer;  // 跳出標籤指定的迴圈
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
        
        // continue 與標籤
        System.out.println("\n標籤範例 - 巢狀迴圈中的 continue:");
        outer2: for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    System.out.println("跳過 i=" + i + ", j=" + j);
                    continue outer2;  // 繼續外層迴圈的下一次
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
    }
}
```

## 綜合範例

```java
import java.util.Random;

public class ComprehensiveExample {
    public static void main(String[] args) {
        // 模擬一個簡單的成績統計系統
        System.out.println("=== 學生成績統計系統 ===");
        
        // 模擬學生成績
        Random random = new Random();
        int studentCount = 10;
        int totalScore = 0;
        int passCount = 0;
        int maxScore = 0;
        int minScore = 100;
        
        System.out.println("學生成績列表:");
        
        for (int i = 1; i <= studentCount; i++) {
            // 生成 0-100 的隨機成績
            int score = random.nextInt(101);
            totalScore += score;
            
            // 統計及格人數
            if (score >= 60) {
                passCount++;
            }
            
            // 找出最高分和最低分
            if (score > maxScore) {
                maxScore = score;
            }
            if (score < minScore) {
                minScore = score;
            }
            
            // 判斷等第
            String grade;
            if (score >= 90) {
                grade = "優秀";
            } else if (score >= 80) {
                grade = "良好";
            } else if (score >= 70) {
                grade = "普通";
            } else if (score >= 60) {
                grade = "及格";
            } else {
                grade = "不及格";
            }
            
            // 顯示結果
            System.out.printf("學生 %2d: %3d 分 - %s%s%n", 
                            i, score, grade, 
                            (score == maxScore) ? " (最高分)" : 
                            (score == minScore) ? " (最低分)" : "");
        }
        
        // 統計結果
        double averageScore = (double) totalScore / studentCount;
        double passRate = (double) passCount / studentCount * 100;
        
        System.out.println("\n=== 統計結果 ===");
        System.out.printf("總人數: %d%n", studentCount);
        System.out.printf("總分: %d%n", totalScore);
        System.out.printf("平均分數: %.2f%n", averageScore);
        System.out.printf("最高分: %d%n", maxScore);
        System.out.printf("最低分: %d%n", minScore);
        System.out.printf("及格人數: %d%n", passCount);
        System.out.printf("及格率: %.1f%%%n", passRate);
        
        // 成績分布統計
        int[] gradeDistribution = new int[5]; // 優秀、良好、普通、及格、不及格
        
        // 重新生成相同的隨機數序列來統計分布
        random.setSeed(0);  // 設定種子確保相同結果
        for (int i = 0; i < studentCount; i++) {
            int score = random.nextInt(101);
            
            if (score >= 90) {
                gradeDistribution[0]++;
            } else if (score >= 80) {
                gradeDistribution[1]++;
            } else if (score >= 70) {
                gradeDistribution[2]++;
            } else if (score >= 60) {
                gradeDistribution[3]++;
            } else {
                gradeDistribution[4]++;
            }
        }
        
        System.out.println("\n=== 成績分布 ===");
        String[] gradeLabels = {"優秀(90+)", "良好(80-89)", "普通(70-79)", "及格(60-69)", "不及格(<60)"};
        
        for (int i = 0; i < gradeDistribution.length; i++) {
            System.out.printf("%-12s: %d 人%n", gradeLabels[i], gradeDistribution[i]);
        }
    }
}
```

## 重點整理

1. **基本資料型態**: byte、short、int、long、float、double、char、boolean
2. **變數命名**: 使用小駝峰命名法，避免關鍵字，名稱要有意義
3. **運算子優先順序**: `()` > `!` `++` `--` > `*` `/` `%` > `+` `-` > `<` `>` `<=` `>=` > `==` `!=` > `&&` > `||`
4. **流程控制**: if-else、switch、for、while、do-while
5. **跳躍敘述**: break（跳出迴圈）、continue（跳過本次迴圈）

!!! example "型態轉換重要提醒"
    ```java
    // 自動轉換 (小範圍 → 大範圍)
    int i = 100;
    long l = i;     // 自動轉換
    double d = l;   // 自動轉換
    
    // 強制轉換 (大範圍 → 小範圍，可能遺失資料)
    double pi = 3.14159;
    int intPi = (int) pi;  // 強制轉換，結果為 3
    ```

## 練習建議

1. **基礎練習**
   - 宣告各種型態的變數並進行運算
   - 撰寫程式計算圓的面積和周長
   - 實作溫度轉換程式（攝氏 ↔ 華氏）

2. **流程控制練習**
   - 撰寫程式判斷年份是否為閏年
   - 實作簡單的計算機（加減乘除）
   - 設計成績等第判斷程式

3. **迴圈練習**
   - 印出九九乘法表
   - 計算階乘 (n!)
   - 找出指定範圍內的質數

4. **綜合練習**
   - 設計一個數字猜測遊戲
   - 實作統計分析程式（平均數、最大值、最小值）
   - 撰寫星號圖案列印程式

下一章我們將學習 String、日期時間和數字物件的處理！