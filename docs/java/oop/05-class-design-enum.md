# 設計類別與列舉(enum)

## 類別設計原則

良好的類別設計是物件導向程式設計的基礎。一個設計良好的類別應該具有清晰的職責、適當的封裝和合理的介面。

### 單一職責原則

每個類別應該只有一個改變的理由，也就是只負責一項功能。

```java
// 不良設計：一個類別處理多種職責
class BadUserManager {
    private String username;
    private String email;
    private String password;
    
    // 使用者資料管理
    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }
    
    // 資料庫操作
    public void saveToDatabase() {
        System.out.println("儲存使用者到資料庫");
    }
    
    // 郵件發送
    public void sendWelcomeEmail() {
        System.out.println("發送歡迎郵件到: " + email);
    }
    
    // 密碼驗證
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
```

```java
// 良好設計：職責分離
// 使用者資料類別
class User {
    private String username;
    private String email;
    private String password;
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    // Getter 和 Setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    // 只負責基本的資料驗證
    public boolean isValidEmail() {
        return email != null && email.contains("@");
    }
    
    @Override
    public String toString() {
        return String.format("User{username='%s', email='%s'}", username, email);
    }
}

// 資料庫操作類別
class UserRepository {
    public void save(User user) {
        System.out.println("儲存使用者到資料庫: " + user.getUsername());
        // 實際的資料庫操作邏輯
    }
    
    public User findByUsername(String username) {
        System.out.println("從資料庫尋找使用者: " + username);
        // 實際的查詢邏輯
        return null; // 這裡簡化處理
    }
}

// 郵件服務類別
class EmailService {
    public void sendWelcomeEmail(User user) {
        if (user.isValidEmail()) {
            System.out.println("發送歡迎郵件到: " + user.getEmail());
            // 實際的郵件發送邏輯
        } else {
            System.out.println("無效的郵件地址: " + user.getEmail());
        }
    }
}

// 密碼驗證類別
class PasswordValidator {
    public boolean validate(String password, String inputPassword) {
        return password != null && password.equals(inputPassword);
    }
    
    public boolean isStrongPassword(String password) {
        return password != null && 
               password.length() >= 8 && 
               password.matches(".*[A-Z].*") &&  // 包含大寫字母
               password.matches(".*[0-9].*");    // 包含數字
    }
}
```

### 使用良好設計的範例

```java
public class UserManagementDemo {
    public static void main(String[] args) {
        // 建立服務物件
        UserRepository userRepository = new UserRepository();
        EmailService emailService = new EmailService();
        PasswordValidator passwordValidator = new PasswordValidator();
        
        // 建立使用者
        User user = new User("john_doe", "john@example.com", "MyPassword123");
        
        System.out.println("=== 使用者註冊流程 ===");
        
        // 1. 驗證密碼強度
        if (passwordValidator.isStrongPassword("MyPassword123")) {
            System.out.println("密碼強度驗證通過");
            
            // 2. 儲存使用者
            userRepository.save(user);
            
            // 3. 發送歡迎郵件
            emailService.sendWelcomeEmail(user);
            
        } else {
            System.out.println("密碼強度不足");
        }
        
        System.out.println("\n=== 使用者登入流程 ===");
        
        // 登入驗證
        String inputPassword = "MyPassword123";
        if (passwordValidator.validate("MyPassword123", inputPassword)) {
            System.out.println("登入成功");
        } else {
            System.out.println("密碼錯誤");
        }
    }
}
```

!!! tip "設計原則總結"
    - **單一職責**: 一個類別只做一件事
    - **開放封閉**: 對擴展開放，對修改封閉
    - **依賴倒轉**: 依賴抽象而非具體實作
    - **介面隔離**: 不強迫依賴不需要的介面

## 方法多載 (Method Overloading)

方法多載允許同一個類別中有多個同名但參數不同的方法。編譯器會根據呼叫時提供的參數自動選擇適合的方法。

### 基本方法多載

```java
public class Calculator {
    
    // 整數加法
    public int add(int a, int b) {
        System.out.println("執行整數加法: " + a + " + " + b);
        return a + b;
    }
    
    // 三個整數加法
    public int add(int a, int b, int c) {
        System.out.println("執行三個整數加法: " + a + " + " + b + " + " + c);
        return a + b + c;
    }
    
    // 浮點數加法
    public double add(double a, double b) {
        System.out.println("執行浮點數加法: " + a + " + " + b);
        return a + b;
    }
    
    // 字串連接
    public String add(String a, String b) {
        System.out.println("執行字串連接: " + a + " + " + b);
        return a + b;
    }
    
    // 陣列加法（元素相加）
    public int[] add(int[] array1, int[] array2) {
        System.out.println("執行陣列加法");
        int length = Math.min(array1.length, array2.length);
        int[] result = new int[length];
        
        for (int i = 0; i < length; i++) {
            result[i] = array1[i] + array2[i];
        }
        return result;
    }
    
    // 方法多載的另一個範例：格式化輸出
    public void print(int value) {
        System.out.println("整數: " + value);
    }
    
    public void print(double value) {
        System.out.println("浮點數: " + String.format("%.2f", value));
    }
    
    public void print(String value) {
        System.out.println("字串: \"" + value + "\"");
    }
    
    public void print(boolean value) {
        System.out.println("布林值: " + (value ? "真" : "假"));
    }
    
    // 可變參數的多載
    public int sum(int... numbers) {
        System.out.println("使用可變參數計算總和");
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }
}
```

### 方法多載使用範例

```java
public class OverloadingDemo {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        System.out.println("=== 方法多載示範 ===");
        
        // 不同參數類型的加法
        System.out.println("結果: " + calc.add(5, 3));              // int add(int, int)
        System.out.println("結果: " + calc.add(5, 3, 2));           // int add(int, int, int)
        System.out.println("結果: " + calc.add(5.5, 3.2));          // double add(double, double)
        System.out.println("結果: " + calc.add("Hello", "World"));   // String add(String, String)
        
        // 陣列加法
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int[] result = calc.add(arr1, arr2);
        System.out.print("陣列結果: ");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 格式化輸出示範 ===");
        
        // 不同資料類型的輸出
        calc.print(42);              // print(int)
        calc.print(3.14159);         // print(double)
        calc.print("Java");          // print(String)
        calc.print(true);            // print(boolean)
        
        System.out.println("\n=== 可變參數示範 ===");
        
        // 可變參數的使用
        System.out.println("總和: " + calc.sum());                    // 無參數
        System.out.println("總和: " + calc.sum(5));                   // 一個參數
        System.out.println("總和: " + calc.sum(1, 2, 3, 4, 5));       // 多個參數
        System.out.println("總和: " + calc.sum(10, 20, 30, 40));      // 不同數量參數
    }
}
```

### 建構子多載範例

```java
public class Rectangle {
    private double width;
    private double height;
    private String color;
    
    // 預設建構子
    public Rectangle() {
        this(1.0, 1.0, "白色");  // 呼叫其他建構子
        System.out.println("使用預設建構子");
    }
    
    // 正方形建構子（只指定一個邊長）
    public Rectangle(double side) {
        this(side, side, "白色");
        System.out.println("建立正方形，邊長: " + side);
    }
    
    // 指定寬高的建構子
    public Rectangle(double width, double height) {
        this(width, height, "白色");
        System.out.println("建立矩形，寬: " + width + ", 高: " + height);
    }
    
    // 完整建構子
    public Rectangle(double width, double height, String color) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("寬度和高度必須大於零");
        }
        this.width = width;
        this.height = height;
        this.color = color;
        System.out.println("建立" + color + "矩形，寬: " + width + ", 高: " + height);
    }
    
    // 複製建構子
    public Rectangle(Rectangle other) {
        this(other.width, other.height, other.color);
        System.out.println("複製矩形");
    }
    
    // 計算面積
    public double getArea() {
        return width * height;
    }
    
    // 計算周長
    public double getPerimeter() {
        return 2 * (width + height);
    }
    
    // 判斷是否為正方形
    public boolean isSquare() {
        return Math.abs(width - height) < 1e-10; // 浮點數比較
    }
    
    // Getter 方法
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public String getColor() { return color; }
    
    @Override
    public String toString() {
        return String.format("%s矩形 (寬:%.2f, 高:%.2f, 面積:%.2f)", 
                           color, width, height, getArea());
    }
}
```

```java
public class RectangleDemo {
    public static void main(String[] args) {
        System.out.println("=== 建構子多載示範 ===");
        
        Rectangle rect1 = new Rectangle();                    // 預設建構子
        Rectangle rect2 = new Rectangle(5.0);                 // 正方形
        Rectangle rect3 = new Rectangle(4.0, 6.0);            // 寬高
        Rectangle rect4 = new Rectangle(3.0, 3.0, "紅色");    // 完整建構子
        Rectangle rect5 = new Rectangle(rect4);               // 複製建構子
        
        System.out.println("\n=== 矩形資訊 ===");
        System.out.println("rect1: " + rect1);
        System.out.println("rect2: " + rect2 + ", 是正方形: " + rect2.isSquare());
        System.out.println("rect3: " + rect3 + ", 周長: " + rect3.getPerimeter());
        System.out.println("rect4: " + rect4);
        System.out.println("rect5: " + rect5);
    }
}
```

!!! warning "方法多載注意事項"
    - 只有參數列表不同才算多載（數量、類型、順序）
    - 回傳型態不能作為多載的區分標準
    - 可變參數方法的優先權最低
    - 編譯器會選擇最符合的方法，如有歧義會報錯

## 列舉 (enum) 基礎

列舉是一種特殊的類別，用於表示一組固定的常數值。Java 的 enum 比其他語言的列舉更強大，可以包含欄位、方法和建構子。

### 基本列舉定義

```java
// 基本列舉：星期
enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// 基本列舉：優先等級
enum Priority {
    LOW, MEDIUM, HIGH, URGENT
}

// 基本列舉：顏色
enum Color {
    RED, GREEN, BLUE, YELLOW, BLACK, WHITE
}
```

### 列舉的基本使用

```java
public class BasicEnumDemo {
    public static void main(String[] args) {
        System.out.println("=== 基本列舉使用 ===");
        
        // 宣告列舉變數
        DayOfWeek today = DayOfWeek.MONDAY;
        Priority taskPriority = Priority.HIGH;
        Color favoriteColor = Color.BLUE;
        
        System.out.println("今天是: " + today);
        System.out.println("任務優先級: " + taskPriority);
        System.out.println("喜歡的顏色: " + favoriteColor);
        
        // 列舉比較
        if (today == DayOfWeek.MONDAY) {
            System.out.println("星期一，新的一週開始！");
        }
        
        // 列舉的內建方法
        System.out.println("\n=== 列舉內建方法 ===");
        
        // name() - 取得列舉常數名稱
        System.out.println("today.name(): " + today.name());
        
        // ordinal() - 取得列舉常數的順序位置（從0開始）
        System.out.println("today.ordinal(): " + today.ordinal());
        
        // toString() - 預設等同於 name()
        System.out.println("today.toString(): " + today.toString());
        
        // values() - 取得所有列舉常數的陣列
        System.out.println("\n所有星期:");
        DayOfWeek[] allDays = DayOfWeek.values();
        for (int i = 0; i < allDays.length; i++) {
            System.out.println((i + 1) + ". " + allDays[i]);
        }
        
        // valueOf() - 從字串轉換為列舉
        String dayName = "FRIDAY";
        DayOfWeek friday = DayOfWeek.valueOf(dayName);
        System.out.println("\n從字串轉換: " + dayName + " -> " + friday);
        
        // 處理錯誤的字串
        try {
            DayOfWeek invalid = DayOfWeek.valueOf("INVALID_DAY");
        } catch (IllegalArgumentException e) {
            System.out.println("無效的列舉值: " + e.getMessage());
        }
    }
}
```

## 列舉搭配 switch

列舉與 switch 敘述的搭配非常自然，也是列舉最常用的場景之一。

### 列舉 switch 範例

```java
public class EnumSwitchDemo {
    
    // 根據星期判斷工作日或週末
    public static String getDayType(DayOfWeek day) {
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                return "工作日";
            case SATURDAY:
            case SUNDAY:
                return "週末";
            default:
                return "未知";
        }
    }
    
    // 根據優先級決定處理策略
    public static void handleTask(String taskName, Priority priority) {
        System.out.print("處理任務 \"" + taskName + "\" (優先級: " + priority + ") - ");
        
        switch (priority) {
            case URGENT:
                System.out.println("立即處理！");
                break;
            case HIGH:
                System.out.println("今日內完成");
                break;
            case MEDIUM:
                System.out.println("本週內完成");
                break;
            case LOW:
                System.out.println("有時間再處理");
                break;
            default:
                System.out.println("未知優先級");
                break;
        }
    }
    
    // 根據顏色選擇 RGB 值（簡化版）
    public static String getColorCode(Color color) {
        switch (color) {
            case RED:
                return "#FF0000";
            case GREEN:
                return "#00FF00";
            case BLUE:
                return "#0000FF";
            case YELLOW:
                return "#FFFF00";
            case BLACK:
                return "#000000";
            case WHITE:
                return "#FFFFFF";
            default:
                return "#UNKNOWN";
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 列舉 Switch 示範 ===");
        
        // 星期類型判斷
        System.out.println("星期類型判斷:");
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day + " -> " + getDayType(day));
        }
        
        // 任務優先級處理
        System.out.println("\n任務優先級處理:");
        handleTask("修復系統Bug", Priority.URGENT);
        handleTask("撰寫文件", Priority.MEDIUM);
        handleTask("程式碼重構", Priority.LOW);
        handleTask("新功能開發", Priority.HIGH);
        
        // 顏色代碼轉換
        System.out.println("\n顏色代碼轉換:");
        for (Color color : Color.values()) {
            System.out.println(color + " -> " + getColorCode(color));
        }
        
        // 使用 switch 表達式 (Java 14+)
        System.out.println("\n=== Switch 表達式 (Java 14+) ===");
        
        DayOfWeek testDay = DayOfWeek.SATURDAY;
        String dayMessage = switch (testDay) {
            case MONDAY -> "週一，新的開始！";
            case TUESDAY, WEDNESDAY, THURSDAY -> "平日，努力工作！";
            case FRIDAY -> "週五，快到週末了！";
            case SATURDAY, SUNDAY -> "週末，好好休息！";
        };
        System.out.println(testDay + ": " + dayMessage);
        
        // 更複雜的 switch 表達式
        Priority testPriority = Priority.HIGH;
        int estimatedHours = switch (testPriority) {
            case URGENT -> {
                System.out.println("緊急任務分析中...");
                yield 1;  // 1 小時內
            }
            case HIGH -> 8;    // 8 小時內
            case MEDIUM -> 40;  // 40 小時內（一週）
            case LOW -> 160;    // 160 小時內（一個月）
        };
        System.out.println("優先級 " + testPriority + " 預估完成時間: " + estimatedHours + " 小時");
    }
}
```

!!! tip "Switch 與列舉最佳實踐"
    - 總是包含 `default` case，除非確定處理了所有可能的值
    - 使用 switch 表達式（Java 14+）可以避免 break 遺漏的問題
    - 在 switch 中直接使用列舉常數名稱，不需要 `EnumName.`

## 列舉進階功能

Java 的列舉遠比單純的常數列表強大，可以包含欄位、方法，甚至是抽象方法。

### 含有欄位和方法的列舉

```java
// 行星列舉：包含質量和半徑
enum Planet {
    // 列舉常數及其參數
    MERCURY(3.303e+23, 2.4397e6),
    VENUS  (4.869e+24, 6.0518e6),
    EARTH  (5.976e+24, 6.37814e6),
    MARS   (6.421e+23, 3.3972e6),
    JUPITER(1.9e+27,   7.1492e7),
    SATURN (5.688e+26, 6.0268e7),
    URANUS (8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);
    
    // 私有欄位
    private final double mass;   // 質量（公斤）
    private final double radius; // 半徑（公尺）
    
    // 建構子（列舉建構子必須是 private）
    private Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    
    // 公共方法
    public double getMass() { return mass; }
    public double getRadius() { return radius; }
    
    // 通用重力常數
    public static final double G = 6.67300E-11;
    
    // 計算表面重力加速度
    public double surfaceGravity() {
        return G * mass / (radius * radius);
    }
    
    // 計算在該行星上的重量
    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}
```

### 狀態機列舉

```java
// 訂單狀態列舉
enum OrderStatus {
    PENDING("待處理", "訂單已建立，等待處理") {
        @Override
        public OrderStatus nextStatus() {
            return CONFIRMED;
        }
        
        @Override
        public boolean canCancel() {
            return true;
        }
    },
    
    CONFIRMED("已確認", "訂單已確認，準備出貨") {
        @Override
        public OrderStatus nextStatus() {
            return SHIPPED;
        }
        
        @Override
        public boolean canCancel() {
            return true;
        }
    },
    
    SHIPPED("已出貨", "商品已出貨，運送中") {
        @Override
        public OrderStatus nextStatus() {
            return DELIVERED;
        }
        
        @Override
        public boolean canCancel() {
            return false; // 已出貨不能取消
        }
    },
    
    DELIVERED("已送達", "商品已成功送達") {
        @Override
        public OrderStatus nextStatus() {
            return COMPLETED;
        }
        
        @Override
        public boolean canCancel() {
            return false;
        }
    },
    
    COMPLETED("已完成", "訂單處理完成") {
        @Override
        public OrderStatus nextStatus() {
            return this; // 已完成，保持不變
        }
        
        @Override
        public boolean canCancel() {
            return false;
        }
    },
    
    CANCELLED("已取消", "訂單已被取消") {
        @Override
        public OrderStatus nextStatus() {
            return this; // 已取消，保持不變
        }
        
        @Override
        public boolean canCancel() {
            return false;
        }
    };
    
    // 欄位
    private final String displayName;
    private final String description;
    
    // 建構子
    private OrderStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    // 抽象方法：每個列舉常數必須實作
    public abstract OrderStatus nextStatus();
    public abstract boolean canCancel();
    
    // 一般方法
    public String getDisplayName() { return displayName; }
    public String getDescription() { return description; }
    
    // 取消訂單
    public OrderStatus cancel() {
        if (canCancel()) {
            return CANCELLED;
        } else {
            throw new IllegalStateException("訂單在 " + this.displayName + " 狀態下無法取消");
        }
    }
    
    @Override
    public String toString() {
        return displayName + " (" + description + ")";
    }
}
```

### 進階列舉使用範例

```java
public class AdvancedEnumDemo {
    public static void main(String[] args) {
        System.out.println("=== 行星列舉示範 ===");
        
        // 計算各行星的表面重力
        double earthWeight = 75.0; // 地球上的體重（公斤）
        System.out.println("如果你在地球上重 " + earthWeight + " 公斤，在其他行星上的重量：");
        
        for (Planet planet : Planet.values()) {
            double weight = planet.surfaceWeight(earthWeight);
            System.out.printf("%-8s: %6.2f 公斤 (重力: %8.2f m/s²)%n", 
                            planet.name(), weight, planet.surfaceGravity());
        }
        
        System.out.println("\n=== 訂單狀態機示範 ===");
        
        // 模擬訂單流程
        OrderStatus currentStatus = OrderStatus.PENDING;
        System.out.println("初始狀態: " + currentStatus);
        
        try {
            // 正常流程
            for (int i = 0; i < 5; i++) {
                OrderStatus nextStatus = currentStatus.nextStatus();
                if (nextStatus != currentStatus) {
                    currentStatus = nextStatus;
                    System.out.println("狀態轉換到: " + currentStatus);
                } else {
                    System.out.println("已達到最終狀態: " + currentStatus);
                    break;
                }
            }
            
            // 測試取消功能
            System.out.println("\n=== 取消訂單測試 ===");
            
            OrderStatus pendingOrder = OrderStatus.PENDING;
            System.out.println("嘗試取消 " + pendingOrder.getDisplayName() + " 狀態的訂單:");
            if (pendingOrder.canCancel()) {
                OrderStatus cancelled = pendingOrder.cancel();
                System.out.println("成功取消: " + cancelled);
            } else {
                System.out.println("無法取消此狀態的訂單");
            }
            
            // 嘗試取消已出貨的訂單
            OrderStatus shippedOrder = OrderStatus.SHIPPED;
            System.out.println("\n嘗試取消 " + shippedOrder.getDisplayName() + " 狀態的訂單:");
            try {
                shippedOrder.cancel();
            } catch (IllegalStateException e) {
                System.out.println("取消失敗: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("發生錯誤: " + e.getMessage());
        }
        
        System.out.println("\n=== 所有訂單狀態 ===");
        for (OrderStatus status : OrderStatus.values()) {
            System.out.printf("%-10s: %-8s (可取消: %s)%n", 
                            status.name(), 
                            status.getDisplayName(), 
                            status.canCancel() ? "是" : "否");
        }
    }
}
```

### 列舉集合操作

```java
import java.util.EnumSet;
import java.util.EnumMap;

// 工作日列舉
enum Weekday {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    
    public boolean isWorkday() {
        return this != SATURDAY && this != SUNDAY;
    }
    
    public boolean isWeekend() {
        return this == SATURDAY || this == SUNDAY;
    }
}

public class EnumCollectionDemo {
    public static void main(String[] args) {
        System.out.println("=== EnumSet 示範 ===");
        
        // 建立包含所有工作日的 EnumSet
        EnumSet<Weekday> workdays = EnumSet.of(
            Weekday.MONDAY, Weekday.TUESDAY, Weekday.WEDNESDAY, 
            Weekday.THURSDAY, Weekday.FRIDAY
        );
        
        System.out.println("工作日: " + workdays);
        
        // 建立週末的 EnumSet
        EnumSet<Weekday> weekend = EnumSet.of(Weekday.SATURDAY, Weekday.SUNDAY);
        System.out.println("週末: " + weekend);
        
        // 建立所有天的 EnumSet
        EnumSet<Weekday> allDays = EnumSet.allOf(Weekday.class);
        System.out.println("所有天: " + allDays);
        
        // 建立空的 EnumSet
        EnumSet<Weekday> noDays = EnumSet.noneOf(Weekday.class);
        System.out.println("空集合: " + noDays);
        
        // 範圍操作
        EnumSet<Weekday> midWeek = EnumSet.range(Weekday.TUESDAY, Weekday.THURSDAY);
        System.out.println("週二到週四: " + midWeek);
        
        // 補集操作
        EnumSet<Weekday> notWorkdays = EnumSet.complementOf(workdays);
        System.out.println("非工作日: " + notWorkdays);
        
        System.out.println("\n=== EnumMap 示範 ===");
        
        // 建立 EnumMap 來儲存每天的工作時數
        EnumMap<Weekday, Integer> workHours = new EnumMap<>(Weekday.class);
        
        // 設定工作時數
        workHours.put(Weekday.MONDAY, 8);
        workHours.put(Weekday.TUESDAY, 8);
        workHours.put(Weekday.WEDNESDAY, 8);
        workHours.put(Weekday.THURSDAY, 8);
        workHours.put(Weekday.FRIDAY, 6);    // 週五早下班
        workHours.put(Weekday.SATURDAY, 0);  // 週末不工作
        workHours.put(Weekday.SUNDAY, 0);
        
        System.out.println("每日工作時數:");
        for (Weekday day : Weekday.values()) {
            int hours = workHours.getOrDefault(day, 0);
            System.out.printf("%-9s: %d 小時%n", day, hours);
        }
        
        // 計算總工作時數
        int totalHours = workHours.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("週總工作時數: " + totalHours + " 小時");
        
        // 統計工作日和週末
        System.out.println("\n=== 統計資訊 ===");
        long workdayCount = EnumSet.allOf(Weekday.class).stream()
                                  .filter(Weekday::isWorkday)
                                  .count();
        long weekendCount = EnumSet.allOf(Weekday.class).stream()
                                  .filter(Weekday::isWeekend)
                                  .count();
        
        System.out.println("工作日數量: " + workdayCount);
        System.out.println("週末日數量: " + weekendCount);
    }
}
```

!!! note "EnumSet 和 EnumMap 優勢"
    - **EnumSet**: 專為列舉設計的 Set，效能比 HashSet 更好
    - **EnumMap**: 專為列舉設計的 Map，效能比 HashMap 更好
    - 兩者都是類型安全的，只能使用特定的列舉類型

## 重點整理

1. **類別設計原則**: 單一職責、適當封裝、清晰介面
2. **方法多載**: 同名方法不同參數，編譯期決定呼叫哪個版本
3. **基本列舉**: 表示固定常數集合，比整數常數更安全
4. **列舉 + Switch**: 天然的搭配，程式碼清晰易懂
5. **進階列舉**: 可包含欄位、方法、建構子，功能強大
6. **列舉集合**: EnumSet 和 EnumMap 提供高效的集合操作

!!! tip "最佳實踐"
    - 優先使用列舉而非整數常數
    - 列舉名稱使用全大寫加底線
    - 為複雜列舉提供有意義的方法
    - 使用 EnumSet 和 EnumMap 處理列舉集合
    - 在 switch 中總是包含 default case

## 練習建議

1. **基礎練習**
   - 設計一個 `Season` 列舉表示四季
   - 建立 `Grade` 列舉表示成績等第（A、B、C、D、F）
   - 實作 `TrafficLight` 列舉控制交通號誌

2. **進階練習**
   - 設計具有欄位和方法的 `Currency` 列舉
   - 建立狀態機列舉模擬自動販賣機
   - 實作 `HttpStatus` 列舉表示 HTTP 狀態碼

3. **綜合練習**
   - 設計完整的訂單管理系統，包含多種狀態
   - 建立遊戲角色系統，使用列舉表示職業和技能
   - 實作排程系統，使用列舉管理任務優先級和狀態

下一章我們將學習繼承與記錄（Records），探索物件導向的核心特性！