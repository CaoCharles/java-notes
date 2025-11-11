# 類別與物件

## 物件導向程式設計概念

物件導向程式設計（Object-Oriented Programming, OOP）是一種程式設計範型，將現實世界的事物抽象化為程式中的「物件」。每個物件都有自己的「屬性」（資料）和「行為」（方法）。

### 物件導向的四大特性

1. **封裝（Encapsulation）**: 將資料和操作資料的方法包裝在一起
2. **繼承（Inheritance）**: 新類別可以繼承現有類別的特性
3. **多型（Polymorphism）**: 同樣的介面可以有不同的實作
4. **抽象（Abstraction）**: 隱藏複雜的實作細節，只暴露必要的介面

## 類別定義與物件建立

### 基本類別定義

```java
// Student.java - 學生類別
public class Student {
    // 實例變數（屬性）
    private String name;        // 姓名
    private int age;           // 年齡
    private String studentId;   // 學號
    private double gpa;        // 平均成績
    
    // 預設建構子
    public Student() {
        this.name = "未設定";
        this.age = 0;
        this.studentId = "0000";
        this.gpa = 0.0;
        System.out.println("建立了一個新的學生物件（預設建構子）");
    }
    
    // 參數建構子
    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.gpa = 0.0;
        System.out.println("建立了學生: " + name);
    }
    
    // 完整參數建構子
    public Student(String name, int age, String studentId, double gpa) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.gpa = gpa;
        System.out.println("建立了學生: " + name + "，GPA: " + gpa);
    }
    
    // Getter 方法（存取器）
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    // Setter 方法（修改器）
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("姓名不能為空");
        }
    }
    
    public void setAge(int age) {
        if (age >= 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("年齡必須在 0-120 之間");
        }
    }
    
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            System.out.println("GPA 必須在 0.0-4.0 之間");
        }
    }
    
    // 實例方法
    public void study(String subject) {
        System.out.println(name + " 正在學習 " + subject);
    }
    
    public void takeExam(String subject, double score) {
        System.out.println(name + " 參加了 " + subject + " 考試，得分: " + score);
        
        // 更新 GPA (簡化計算)
        this.gpa = (this.gpa + score / 25.0) / 2.0;  // 假設滿分 100，轉換為 4.0 制
    }
    
    public String getGradeLevel() {
        if (gpa >= 3.7) return "優秀";
        else if (gpa >= 3.0) return "良好";
        else if (gpa >= 2.0) return "普通";
        else return "需要加強";
    }
    
    // 顯示學生資訊
    public void displayInfo() {
        System.out.println("=== 學生資訊 ===");
        System.out.println("姓名: " + name);
        System.out.println("年齡: " + age);
        System.out.println("學號: " + studentId);
        System.out.printf("GPA: %.2f (%s)%n", gpa, getGradeLevel());
    }
    
    // toString 方法（物件字串表示）
    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, studentId='%s', gpa=%.2f}", 
                           name, age, studentId, gpa);
    }
}
```

### 使用類別建立物件

```java
public class StudentDemo {
    public static void main(String[] args) {
        System.out.println("=== 建立學生物件 ===");
        
        // 使用不同建構子建立物件
        Student student1 = new Student();  // 預設建構子
        Student student2 = new Student("張小明", 20, "A001");
        Student student3 = new Student("李小華", 19, "A002", 3.5);
        
        // 設定 student1 的資料
        student1.setName("王小美");
        student1.setAge(21);
        student1.setGpa(3.2);
        
        System.out.println("\n=== 學生資訊顯示 ===");
        student1.displayInfo();
        student2.displayInfo();
        student3.displayInfo();
        
        // 呼叫方法
        System.out.println("\n=== 學習活動 ===");
        student2.study("Java 程式設計");
        student3.study("資料結構");
        
        System.out.println("\n=== 考試活動 ===");
        student2.takeExam("Java 程式設計", 85);
        student3.takeExam("資料結構", 92);
        
        System.out.println("\n=== 更新後的資訊 ===");
        student2.displayInfo();
        student3.displayInfo();
        
        // 使用 toString
        System.out.println("\n=== 使用 toString ===");
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
    }
}
```

!!! example "執行結果"
    ```
    建立了一個新的學生物件（預設建構子）
    建立了學生: 張小明
    建立了學生: 李小華，GPA: 3.5
    
    === 學生資訊顯示 ===
    === 學生資訊 ===
    姓名: 王小美
    年齡: 21
    學號: 0000
    GPA: 3.20 (良好)
    ```

## 建構子 (Constructor)

建構子是特殊的方法，用於初始化新建立的物件。

### 建構子特性

```java
public class Car {
    private String brand;      // 品牌
    private String model;      // 型號
    private int year;         // 年份
    private double price;     // 價格
    private String color;     // 顏色
    
    // 預設建構子
    public Car() {
        System.out.println("預設建構子被呼叫");
        this.brand = "未知品牌";
        this.model = "未知型號";
        this.year = 2023;
        this.price = 0.0;
        this.color = "白色";
    }
    
    // 品牌和型號建構子
    public Car(String brand, String model) {
        System.out.println("兩參數建構子被呼叫");
        this.brand = brand;
        this.model = model;
        this.year = 2023;       // 預設值
        this.price = 0.0;       // 預設值
        this.color = "白色";    // 預設值
    }
    
    // 完整建構子
    public Car(String brand, String model, int year, double price, String color) {
        System.out.println("完整建構子被呼叫");
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
    }
    
    // 建構子鏈接 (Constructor Chaining)
    public Car(String brand, String model, int year) {
        this(brand, model, year, 0.0, "白色");  // 呼叫完整建構子
        System.out.println("三參數建構子被呼叫");
    }
    
    // 複製建構子
    public Car(Car other) {
        System.out.println("複製建構子被呼叫");
        this.brand = other.brand;
        this.model = other.model;
        this.year = other.year;
        this.price = other.price;
        this.color = other.color;
    }
    
    // Getter 和 Setter 方法
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    public String getColor() { return color; }
    
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setYear(int year) { 
        if (year >= 1886) {  // 第一輛汽車的年份
            this.year = year; 
        }
    }
    public void setPrice(double price) { 
        if (price >= 0) {
            this.price = price; 
        }
    }
    public void setColor(String color) { this.color = color; }
    
    // 其他方法
    public void start() {
        System.out.println(brand + " " + model + " 已啟動！");
    }
    
    public void displayInfo() {
        System.out.printf("汽車資訊: %s %s (%d年) - %s - $%.2f%n", 
                         brand, model, year, color, price);
    }
    
    @Override
    public String toString() {
        return String.format("%s %s (%d)", brand, model, year);
    }
}
```

### 建構子使用範例

```java
public class CarDemo {
    public static void main(String[] args) {
        System.out.println("=== 建構子示範 ===");
        
        // 使用不同建構子
        Car car1 = new Car();                                    // 預設建構子
        Car car2 = new Car("Toyota", "Camry");                   // 兩參數建構子
        Car car3 = new Car("Honda", "Civic", 2022);              // 三參數建構子（會呼叫建構子鏈接）
        Car car4 = new Car("BMW", "X5", 2023, 75000.0, "黑色"); // 完整建構子
        Car car5 = new Car(car4);                                // 複製建構子
        
        System.out.println("\n=== 汽車資訊 ===");
        car1.displayInfo();
        car2.displayInfo();
        car3.displayInfo();
        car4.displayInfo();
        car5.displayInfo();
        
        // 修改資料
        System.out.println("\n=== 修改資料 ===");
        car2.setYear(2021);
        car2.setPrice(28000.0);
        car2.setColor("紅色");
        car2.displayInfo();
        
        // 啟動汽車
        System.out.println("\n=== 啟動汽車 ===");
        car1.start();
        car2.start();
        car3.start();
    }
}
```

!!! note "建構子重點"
    - 建構子名稱必須與類別名稱相同
    - 建構子沒有回傳型態（連 void 都不用寫）
    - 如果沒有定義建構子，Java 會自動提供預設建構子
    - `this()` 可以呼叫其他建構子（必須是第一行敘述）

## this 關鍵字

`this` 是指向當前物件的參考，主要用途：

### this 的用途

```java
public class Person {
    private String name;
    private int age;
    private String email;
    
    // 1. 解決參數名稱與實例變數名稱相同的問題
    public Person(String name, int age, String email) {
        this.name = name;    // this.name 指實例變數，name 指參數
        this.age = age;
        this.email = email;
    }
    
    // 2. 在方法中呼叫其他方法
    public void introduce() {
        System.out.println("大家好，我是 " + name);
        this.showDetails();  // 呼叫同一物件的其他方法（this 可省略）
    }
    
    private void showDetails() {
        System.out.println("年齡: " + age + ", 信箱: " + email);
    }
    
    // 3. 回傳當前物件（方法鏈接 Method Chaining）
    public Person setName(String name) {
        this.name = name;
        return this;  // 回傳自己
    }
    
    public Person setAge(int age) {
        this.age = age;
        return this;
    }
    
    public Person setEmail(String email) {
        this.email = email;
        return this;
    }
    
    // 4. 將當前物件傳遞給其他方法
    public void registerToSystem() {
        UserManager.register(this);  // 將自己傳給其他類別的方法
    }
    
    // Getter 方法
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    
    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, email='%s'}", 
                           name, age, email);
    }
}

// 模擬的用戶管理類別
class UserManager {
    public static void register(Person person) {
        System.out.println("註冊用戶: " + person.getName());
    }
}
```

### this 使用範例

```java
public class PersonDemo {
    public static void main(String[] args) {
        // 建立 Person 物件
        Person person = new Person("張三", 25, "zhang@email.com");
        
        // 呼叫方法
        System.out.println("=== 自我介紹 ===");
        person.introduce();
        
        // 方法鏈接（Method Chaining）
        System.out.println("\n=== 方法鏈接 ===");
        person.setName("張三豐")
              .setAge(30)
              .setEmail("zhangsan@email.com");
        
        System.out.println("修改後: " + person);
        
        // 註冊到系統
        System.out.println("\n=== 註冊系統 ===");
        person.registerToSystem();
    }
}
```

!!! tip "this 使用技巧"
    - 當參數名稱與實例變數相同時，必須使用 `this` 區分
    - 方法鏈接可以讓程式碼更簡潔流暢
    - 在一般情況下，呼叫同一物件的方法時 `this` 可以省略

## 存取修飾子 (Access Modifiers)

Java 提供四種存取修飾子來控制類別、方法和變數的可見性：

### 存取修飾子說明

| 修飾子 | 同一類別 | 同一套件 | 不同套件的子類別 | 不同套件的其他類別 |
|--------|----------|----------|------------------|-------------------|
| `private` | ✓ | ✗ | ✗ | ✗ |
| (package-private) | ✓ | ✓ | ✗ | ✗ |
| `protected` | ✓ | ✓ | ✓ | ✗ |
| `public` | ✓ | ✓ | ✓ | ✓ |

### 存取修飾子範例

```java
// BankAccount.java
public class BankAccount {
    // private: 只有本類別可以存取
    private String accountNumber;
    private double balance;
    private String pin;
    
    // protected: 本類別、同套件、子類別可以存取
    protected String accountType;
    
    // package-private (沒有修飾子): 本類別和同套件可以存取
    String bankName;
    
    // public: 所有地方都可以存取
    public String ownerName;
    
    public BankAccount(String accountNumber, String ownerName, String pin) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.pin = pin;
        this.balance = 0.0;
        this.accountType = "一般帳戶";
        this.bankName = "範例銀行";
    }
    
    // public 方法：提供安全的存取介面
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    // 存款方法
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存款成功，金額: " + amount);
            logTransaction("存款", amount);
        } else {
            System.out.println("存款金額必須大於零");
        }
    }
    
    // 提款方法
    public boolean withdraw(double amount, String inputPin) {
        if (!verifyPin(inputPin)) {
            System.out.println("密碼錯誤");
            return false;
        }
        
        if (amount <= 0) {
            System.out.println("提款金額必須大於零");
            return false;
        }
        
        if (amount > balance) {
            System.out.println("餘額不足");
            return false;
        }
        
        balance -= amount;
        System.out.println("提款成功，金額: " + amount);
        logTransaction("提款", amount);
        return true;
    }
    
    // private 方法：內部使用，外部無法直接呼叫
    private boolean verifyPin(String inputPin) {
        return this.pin.equals(inputPin);
    }
    
    private void logTransaction(String type, double amount) {
        System.out.println("[交易記錄] " + type + ": " + amount + 
                          ", 餘額: " + balance);
    }
    
    // protected 方法：子類別可以覆寫
    protected void calculateInterest() {
        double interest = balance * 0.001;  // 0.1% 利息
        balance += interest;
        System.out.println("利息計算完成: " + interest);
    }
    
    // package-private 方法：同套件可以呼叫
    void bankInternalOperation() {
        System.out.println("銀行內部作業");
    }
    
    public void displayAccountInfo() {
        System.out.println("=== 帳戶資訊 ===");
        System.out.println("帳號: " + accountNumber);
        System.out.println("戶名: " + ownerName);
        System.out.println("銀行: " + bankName);
        System.out.println("帳戶類型: " + accountType);
        System.out.println("餘額: " + balance);
    }
}
```

### 存取修飾子測試

```java
public class AccessModifierDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("123-456-789", "張小明", "1234");
        
        // public 成員：可以存取
        System.out.println("戶名: " + account.ownerName);
        
        // package-private 成員：同套件可以存取
        System.out.println("銀行: " + account.bankName);
        
        // protected 成員：在同套件中可以存取
        System.out.println("帳戶類型: " + account.accountType);
        
        // private 成員：無法直接存取（編譯錯誤）
        // System.out.println(account.balance);     // 編譯錯誤
        // System.out.println(account.pin);         // 編譯錯誤
        
        // 透過 public 方法間接存取 private 資料
        System.out.println("餘額: " + account.getBalance());
        
        // 測試帳戶操作
        System.out.println("\n=== 帳戶操作測試 ===");
        account.deposit(1000);
        account.withdraw(200, "1234");  // 正確密碼
        account.withdraw(100, "0000");  // 錯誤密碼
        
        // 呼叫 protected 方法（在同套件中可以）
        account.calculateInterest();
        
        // 呼叫 package-private 方法
        account.bankInternalOperation();
        
        // 顯示帳戶資訊
        account.displayAccountInfo();
        
        // 無法直接呼叫 private 方法
        // account.verifyPin("1234");       // 編譯錯誤
        // account.logTransaction("測試", 0); // 編譯錯誤
    }
}
```

!!! warning "封裝原則"
    - 實例變數通常設為 `private`，透過 getter/setter 方法存取
    - 只有需要被外部呼叫的方法才設為 `public`
    - 內部輔助方法設為 `private`
    - 需要被子類別存取的成員設為 `protected`

## static 靜態成員

`static` 關鍵字表示成員屬於類別本身，而不是特定的物件實例。

### 靜態變數和方法

```java
public class Counter {
    // 靜態變數：所有物件共用
    private static int totalCount = 0;
    
    // 實例變數：每個物件各自擁有
    private int instanceCount;
    private String name;
    
    // 靜態常數
    public static final String VERSION = "1.0";
    public static final int MAX_COUNT = 1000;
    
    // 建構子
    public Counter(String name) {
        this.name = name;
        this.instanceCount = 0;
        totalCount++;  // 每建立一個物件，總數加一
        System.out.println("建立了 Counter: " + name);
    }
    
    // 實例方法
    public void increment() {
        instanceCount++;
        totalCount++;
        System.out.println(name + " 增加了一次，實例計數: " + instanceCount);
    }
    
    // 靜態方法：屬於類別，不需要物件就能呼叫
    public static int getTotalCount() {
        return totalCount;
    }
    
    public static void resetTotalCount() {
        totalCount = 0;
        System.out.println("總計數器已重設");
    }
    
    // 靜態方法：工具方法
    public static Counter createNamedCounter(String baseName, int number) {
        return new Counter(baseName + "_" + number);
    }
    
    // 靜態方法中不能直接存取實例成員
    public static void printSystemInfo() {
        System.out.println("Counter 系統資訊:");
        System.out.println("版本: " + VERSION);
        System.out.println("最大計數: " + MAX_COUNT);
        System.out.println("目前總計數: " + totalCount);
        
        // 以下會編譯錯誤，因為靜態方法中不能存取實例成員
        // System.out.println("實例計數: " + instanceCount);  // 錯誤
        // System.out.println("名稱: " + name);               // 錯誤
    }
    
    // 實例方法可以存取靜態成員
    public void printAllInfo() {
        System.out.println("=== " + name + " 的資訊 ===");
        System.out.println("實例計數: " + instanceCount);
        System.out.println("總計數: " + totalCount);        // 可以存取靜態變數
        System.out.println("版本: " + VERSION);            // 可以存取靜態常數
    }
    
    // Getter 方法
    public int getInstanceCount() {
        return instanceCount;
    }
    
    public String getName() {
        return name;
    }
}
```

### 靜態成員使用範例

```java
public class StaticDemo {
    public static void main(String[] args) {
        // 呼叫靜態方法，不需要建立物件
        System.out.println("=== 靜態方法呼叫 ===");
        Counter.printSystemInfo();
        System.out.println("初始總計數: " + Counter.getTotalCount());
        
        // 建立物件
        System.out.println("\n=== 建立物件 ===");
        Counter counter1 = new Counter("計數器1");
        Counter counter2 = new Counter("計數器2");
        Counter counter3 = new Counter("計數器3");
        
        System.out.println("建立物件後總計數: " + Counter.getTotalCount());
        
        // 物件操作
        System.out.println("\n=== 計數操作 ===");
        counter1.increment();
        counter1.increment();
        counter2.increment();
        
        System.out.println("操作後總計數: " + Counter.getTotalCount());
        
        // 顯示各物件資訊
        System.out.println("\n=== 物件資訊 ===");
        counter1.printAllInfo();
        counter2.printAllInfo();
        counter3.printAllInfo();
        
        // 使用靜態工廠方法
        System.out.println("\n=== 靜態工廠方法 ===");
        Counter counter4 = Counter.createNamedCounter("自動", 1);
        Counter counter5 = Counter.createNamedCounter("自動", 2);
        
        System.out.println("使用工廠方法後總計數: " + Counter.getTotalCount());
        
        // 重設計數器
        System.out.println("\n=== 重設計數器 ===");
        Counter.resetTotalCount();
        System.out.println("重設後總計數: " + Counter.getTotalCount());
        
        // 存取靜態常數
        System.out.println("\n=== 靜態常數 ===");
        System.out.println("版本: " + Counter.VERSION);
        System.out.println("最大計數: " + Counter.MAX_COUNT);
    }
}
```

### 靜態初始化區塊

```java
public class DatabaseConfig {
    private static String dbUrl;
    private static String username;
    private static String password;
    private static boolean isConnected;
    
    // 靜態初始化區塊：在類別被載入時執行一次
    static {
        System.out.println("正在初始化資料庫設定...");
        
        // 模擬從設定檔讀取
        dbUrl = "jdbc:mysql://localhost:3306/mydb";
        username = "admin";
        password = "password123";
        isConnected = false;
        
        System.out.println("資料庫設定初始化完成");
    }
    
    // 也可以有多個靜態初始化區塊
    static {
        System.out.println("執行額外的初始化...");
        // 進行連線測試等
        testConnection();
    }
    
    private static void testConnection() {
        System.out.println("測試資料庫連線...");
        // 模擬連線測試
        isConnected = true;
        System.out.println("連線測試成功");
    }
    
    // 靜態方法
    public static String getDbUrl() {
        return dbUrl;
    }
    
    public static boolean isConnected() {
        return isConnected;
    }
    
    public static void printConfig() {
        System.out.println("資料庫 URL: " + dbUrl);
        System.out.println("使用者名稱: " + username);
        System.out.println("連線狀態: " + (isConnected ? "已連線" : "未連線"));
    }
}
```

```java
public class StaticInitDemo {
    public static void main(String[] args) {
        System.out.println("=== 主程式開始 ===");
        
        // 第一次存取類別時，會執行靜態初始化區塊
        System.out.println("第一次存取 DatabaseConfig...");
        DatabaseConfig.printConfig();
        
        // 再次存取，不會重複執行靜態初始化區塊
        System.out.println("\n第二次存取 DatabaseConfig...");
        DatabaseConfig.printConfig();
    }
}
```

!!! note "static 重點整理"
    - **靜態變數**: 所有物件共用，類別被載入時初始化
    - **靜態方法**: 不需物件就能呼叫，不能存取實例成員
    - **靜態常數**: 通常搭配 `final` 使用
    - **靜態初始化區塊**: 在類別載入時執行，用於複雜的初始化邏輯

## 封裝 (Encapsulation)

封裝是將資料和操作資料的方法包裝在一起，並隱藏內部實作細節的概念。

### 良好封裝的範例

```java
public class Temperature {
    private double celsius;     // 攝氏溫度（內部儲存格式）
    
    // 建構子
    public Temperature(double celsius) {
        setCelsius(celsius);
    }
    
    // 設定攝氏溫度（含驗證）
    public void setCelsius(double celsius) {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("溫度不能低於絕對零度 (-273.15°C)");
        }
        this.celsius = celsius;
    }
    
    // 取得攝氏溫度
    public double getCelsius() {
        return celsius;
    }
    
    // 取得華氏溫度
    public double getFahrenheit() {
        return celsius * 9.0 / 5.0 + 32;
    }
    
    // 設定華氏溫度
    public void setFahrenheit(double fahrenheit) {
        setCelsius((fahrenheit - 32) * 5.0 / 9.0);
    }
    
    // 取得開氏溫度
    public double getKelvin() {
        return celsius + 273.15;
    }
    
    // 設定開氏溫度
    public void setKelvin(double kelvin) {
        setCelsius(kelvin - 273.15);
    }
    
    // 溫度狀態判斷
    public boolean isFreezing() {
        return celsius <= 0;
    }
    
    public boolean isBoiling() {
        return celsius >= 100;
    }
    
    public String getStateOfMatter() {
        if (celsius < 0) return "冰";
        else if (celsius < 100) return "水";
        else return "蒸氣";
    }
    
    @Override
    public String toString() {
        return String.format("%.2f°C (%.2f°F, %.2fK)", 
                           celsius, getFahrenheit(), getKelvin());
    }
}
```

### 封裝範例使用

```java
public class TemperatureDemo {
    public static void main(String[] args) {
        System.out.println("=== 溫度類別示範 ===");
        
        // 建立溫度物件
        Temperature temp = new Temperature(25.0);
        System.out.println("初始溫度: " + temp);
        
        // 測試不同溫度單位的轉換
        System.out.println("\n=== 溫度轉換 ===");
        System.out.println("攝氏: " + temp.getCelsius() + "°C");
        System.out.println("華氏: " + temp.getFahrenheit() + "°F");
        System.out.println("開氏: " + temp.getKelvin() + "K");
        
        // 設定不同單位的溫度
        System.out.println("\n=== 設定溫度 ===");
        temp.setFahrenheit(77);  // 25°C
        System.out.println("設定華氏 77°F 後: " + temp);
        
        temp.setKelvin(310.15);  // 37°C (體溫)
        System.out.println("設定開氏 310.15K 後: " + temp);
        
        // 溫度狀態檢查
        System.out.println("\n=== 溫度狀態 ===");
        System.out.println("是否結冰: " + temp.isFreezing());
        System.out.println("是否沸騰: " + temp.isBoiling());
        System.out.println("物質狀態: " + temp.getStateOfMatter());
        
        // 測試邊界值
        System.out.println("\n=== 邊界值測試 ===");
        Temperature ice = new Temperature(0);
        Temperature boiling = new Temperature(100);
        
        System.out.println("冰點: " + ice + ", 狀態: " + ice.getStateOfMatter());
        System.out.println("沸點: " + boiling + ", 狀態: " + boiling.getStateOfMatter());
        
        // 錯誤處理測試
        System.out.println("\n=== 錯誤處理測試 ===");
        try {
            Temperature invalid = new Temperature(-300);  // 低於絕對零度
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲錯誤: " + e.getMessage());
        }
    }
}
```

## 重點整理

1. **類別是物件的藍圖**，定義了物件的屬性和行為
2. **建構子用於初始化物件**，可以有多個不同參數的建構子
3. **this 關鍵字**指向當前物件，用於區分參數與實例變數
4. **存取修飾子**控制成員的可見性：private < package-private < protected < public
5. **static 成員屬於類別**，不需要建立物件就能使用
6. **封裝**透過 private 變數和 public 方法來隱藏實作細節

!!! tip "設計原則"
    - **資料隱藏**: 實例變數設為 private
    - **介面一致**: 提供清楚的 public 方法
    - **驗證輸入**: 在 setter 方法中檢查參數的有效性
    - **職責單一**: 每個類別應該只負責一項功能

## 練習建議

1. **基礎練習**
   - 設計一個 `Book` 類別（書名、作者、價格、頁數）
   - 建立 `Circle` 類別計算圓的面積和周長
   - 實作 `Time` 類別表示一天中的時間

2. **進階練習**
   - 設計銀行帳戶系統（支票帳戶、儲蓄帳戶）
   - 建立學生管理系統（學號、姓名、成績管理）
   - 實作購物車系統（商品、數量、總價計算）

3. **綜合練習**
   - 設計一個簡單的圖書館管理系統
   - 建立員工薪資計算系統
   - 實作基本的停車場管理系統

下一章我們將學習如何設計類別和使用列舉（enum），進一步提升物件導向設計能力！