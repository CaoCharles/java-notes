# 實作繼承與使用紀錄

## 繼承 (Inheritance) 概念

繼承是物件導向程式設計的核心概念之一，允許新類別基於現有類別來定義，繼承其屬性和方法，同時可以添加新功能或修改現有行為。

### 繼承的基本語法

```java
// 父類別（基礎類別、超類別）
class Vehicle {
    protected String brand;      // protected 讓子類別可以存取
    protected String model;
    protected int year;
    protected double price;
    
    // 建構子
    public Vehicle(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        System.out.println("建立交通工具: " + brand + " " + model);
    }
    
    // 公共方法
    public void start() {
        System.out.println(brand + " " + model + " 啟動了");
    }
    
    public void stop() {
        System.out.println(brand + " " + model + " 停止了");
    }
    
    public void displayInfo() {
        System.out.println("=== 交通工具資訊 ===");
        System.out.println("品牌: " + brand);
        System.out.println("型號: " + model);
        System.out.println("年份: " + year);
        System.out.println("價格: $" + price);
    }
    
    // 可以被覆寫的方法
    public double calculateMaintenanceCost() {
        return price * 0.05; // 假設維修費用是價格的 5%
    }
    
    // Getter 方法
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
}
```

### 子類別定義

```java
// 子類別：汽車
class Car extends Vehicle {
    private int doors;           // 車門數
    private String fuelType;     // 燃料類型
    private double engineSize;   // 引擎大小
    
    // 建構子
    public Car(String brand, String model, int year, double price, 
               int doors, String fuelType, double engineSize) {
        super(brand, model, year, price);  // 呼叫父類別建構子
        this.doors = doors;
        this.fuelType = fuelType;
        this.engineSize = engineSize;
        System.out.println("這是一輛汽車，有 " + doors + " 扇門");
    }
    
    // 新增的方法
    public void honk() {
        System.out.println(brand + " " + model + " 按喇叭: 嗶嗶！");
    }
    
    public void openTrunk() {
        System.out.println("打開 " + brand + " " + model + " 的後車廂");
    }
    
    // 覆寫父類別方法
    @Override
    public void start() {
        System.out.println("汽車啟動程序:");
        System.out.println("1. 插入鑰匙");
        System.out.println("2. 踩煞車");
        System.out.println("3. 啟動引擎");
        super.start();  // 呼叫父類別的 start() 方法
    }
    
    @Override
    public double calculateMaintenanceCost() {
        double baseCost = super.calculateMaintenanceCost();
        double carSpecificCost = engineSize * 100;  // 引擎越大維修費越高
        return baseCost + carSpecificCost;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();  // 先顯示父類別資訊
        System.out.println("車門數: " + doors);
        System.out.println("燃料類型: " + fuelType);
        System.out.println("引擎大小: " + engineSize + "L");
        System.out.println("維修費用: $" + String.format("%.2f", calculateMaintenanceCost()));
    }
    
    // Getter 方法
    public int getDoors() { return doors; }
    public String getFuelType() { return fuelType; }
    public double getEngineSize() { return engineSize; }
}

// 子類別：機車
class Motorcycle extends Vehicle {
    private boolean hasSidecar;  // 是否有側車
    private String type;         // 機車類型
    
    public Motorcycle(String brand, String model, int year, double price,
                     boolean hasSidecar, String type) {
        super(brand, model, year, price);
        this.hasSidecar = hasSidecar;
        this.type = type;
        System.out.println("這是一輛" + type + "機車" + 
                          (hasSidecar ? "，有側車" : ""));
    }
    
    public void wheelie() {
        if (type.equals("重機")) {
            System.out.println(brand + " " + model + " 翹孤輪！");
        } else {
            System.out.println("輕型機車不適合翹孤輪");
        }
    }
    
    @Override
    public void start() {
        System.out.println("機車啟動程序:");
        System.out.println("1. 插入鑰匙");
        System.out.println("2. 踩發動踏板或按啟動鈕");
        super.start();
    }
    
    @Override
    public double calculateMaintenanceCost() {
        double baseCost = super.calculateMaintenanceCost();
        double motorcycleDiscount = baseCost * 0.3; // 機車維修費較低
        return baseCost - motorcycleDiscount;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("機車類型: " + type);
        System.out.println("有側車: " + (hasSidecar ? "是" : "否"));
        System.out.println("維修費用: $" + String.format("%.2f", calculateMaintenanceCost()));
    }
    
    public boolean isHasSidecar() { return hasSidecar; }
    public String getType() { return type; }
}
```

### 繼承使用範例

```java
public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== 繼承示範 ===");
        
        // 建立不同類型的交通工具
        Vehicle[] vehicles = {
            new Vehicle("通用", "基本車", 2020, 15000),
            new Car("Toyota", "Camry", 2022, 30000, 4, "汽油", 2.5),
            new Car("Tesla", "Model 3", 2023, 45000, 4, "電動", 0.0),
            new Motorcycle("Harley", "Sportster", 2021, 12000, false, "重機"),
            new Motorcycle("Honda", "Super Cub", 2022, 3000, false, "輕機")
        };
        
        // 測試多型性：用相同的方式處理不同類型的物件
        System.out.println("\n=== 啟動所有交通工具 ===");
        for (Vehicle vehicle : vehicles) {
            vehicle.start();  // 會呼叫各自覆寫的 start() 方法
            System.out.println();
        }
        
        // 測試特定類型的方法
        System.out.println("=== 特定功能測試 ===");
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                car.honk();
                car.openTrunk();
            } else if (vehicle instanceof Motorcycle) {
                Motorcycle motorcycle = (Motorcycle) vehicle;
                motorcycle.wheelie();
            }
        }
        
        // 顯示所有交通工具資訊
        System.out.println("\n=== 所有交通工具資訊 ===");
        for (Vehicle vehicle : vehicles) {
            vehicle.displayInfo();
            System.out.println();
        }
        
        // 計算總維修費用
        double totalMaintenanceCost = 0;
        for (Vehicle vehicle : vehicles) {
            totalMaintenanceCost += vehicle.calculateMaintenanceCost();
        }
        System.out.println("所有交通工具總維修費用: $" + 
                          String.format("%.2f", totalMaintenanceCost));
    }
}
```

!!! note "繼承重點"
    - 使用 `extends` 關鍵字建立繼承關係
    - 子類別繼承父類別的所有 public 和 protected 成員
    - 子類別可以添加新的欄位和方法
    - 子類別可以覆寫父類別的方法

## 方法覆寫 (Method Overriding)

方法覆寫允許子類別提供父類別方法的特定實作。

### 覆寫規則和範例

```java
// 動物基礎類別
class Animal {
    protected String name;
    protected int age;
    protected double weight;
    
    public Animal(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
    
    // 可以被覆寫的方法
    public void makeSound() {
        System.out.println(name + " 發出聲音");
    }
    
    public void eat() {
        System.out.println(name + " 正在吃東西");
    }
    
    public void sleep() {
        System.out.println(name + " 正在睡覺");
    }
    
    // 計算食物需求量
    public double calculateFoodNeed() {
        return weight * 0.03; // 體重的 3%
    }
    
    // final 方法不能被覆寫
    public final void breathe() {
        System.out.println(name + " 正在呼吸");
    }
    
    public void displayInfo() {
        System.out.println("動物資訊:");
        System.out.println("名字: " + name);
        System.out.println("年齡: " + age + " 歲");
        System.out.println("體重: " + weight + " 公斤");
    }
}

// 狗類別
class Dog extends Animal {
    private String breed; // 品種
    
    public Dog(String name, int age, double weight, String breed) {
        super(name, age, weight);
        this.breed = breed;
    }
    
    // 覆寫 makeSound 方法
    @Override
    public void makeSound() {
        System.out.println(name + " (狗) 汪汪叫");
    }
    
    // 覆寫 eat 方法
    @Override
    public void eat() {
        System.out.println(name + " 正在吃狗糧，尾巴搖個不停");
    }
    
    // 覆寫 calculateFoodNeed 方法
    @Override
    public double calculateFoodNeed() {
        double baseFoodNeed = super.calculateFoodNeed();
        // 狗需要更多食物，因為活動量大
        return baseFoodNeed * 1.5;
    }
    
    // 新增的方法
    public void fetch() {
        System.out.println(name + " 正在撿球");
    }
    
    public void wagTail() {
        System.out.println(name + " 開心地搖尾巴");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo(); // 呼叫父類別方法
        System.out.println("品種: " + breed);
        System.out.println("每日食物需求: " + 
                          String.format("%.2f", calculateFoodNeed()) + " 公斤");
    }
}

// 貓類別
class Cat extends Animal {
    private boolean isIndoor; // 是否為室內貓
    
    public Cat(String name, int age, double weight, boolean isIndoor) {
        super(name, age, weight);
        this.isIndoor = isIndoor;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " (貓) 喵喵叫");
    }
    
    @Override
    public void eat() {
        System.out.println(name + " 優雅地吃貓糧");
    }
    
    @Override
    public void sleep() {
        System.out.println(name + " 蜷縮成一球睡覺（貓一天睡 16 小時）");
    }
    
    @Override
    public double calculateFoodNeed() {
        double baseFoodNeed = super.calculateFoodNeed();
        // 室內貓活動量少，需要較少食物
        if (isIndoor) {
            return baseFoodNeed * 0.8;
        }
        return baseFoodNeed;
    }
    
    // 新增的方法
    public void purr() {
        System.out.println(name + " 滿足地發出呼嚕聲");
    }
    
    public void scratch() {
        if (isIndoor) {
            System.out.println(name + " 在貓抓板上磨爪子");
        } else {
            System.out.println(name + " 在樹上磨爪子");
        }
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("居住環境: " + (isIndoor ? "室內" : "室外"));
        System.out.println("每日食物需求: " + 
                          String.format("%.2f", calculateFoodNeed()) + " 公斤");
    }
}

// 鳥類別
class Bird extends Animal {
    private boolean canFly; // 是否會飛
    private double wingSpan; // 翅膀張開長度
    
    public Bird(String name, int age, double weight, boolean canFly, double wingSpan) {
        super(name, age, weight);
        this.canFly = canFly;
        this.wingSpan = wingSpan;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " (鳥) 啁啾聲");
    }
    
    @Override
    public void eat() {
        System.out.println(name + " 用鳥嘴啄食種子和蟲子");
    }
    
    @Override
    public double calculateFoodNeed() {
        // 鳥類新陳代謝快，需要更多食物
        return super.calculateFoodNeed() * 2.0;
    }
    
    // 新增的方法
    public void fly() {
        if (canFly) {
            System.out.println(name + " 張開 " + wingSpan + " 公分的翅膀飛翔");
        } else {
            System.out.println(name + " 是不會飛的鳥");
        }
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("會飛: " + (canFly ? "是" : "否"));
        System.out.println("翅膀張開長度: " + wingSpan + " 公分");
        System.out.println("每日食物需求: " + 
                          String.format("%.2f", calculateFoodNeed()) + " 公斤");
    }
}
```

### 覆寫測試範例

```java
public class OverridingDemo {
    public static void main(String[] args) {
        System.out.println("=== 方法覆寫示範 ===");
        
        // 建立不同動物
        Animal[] animals = {
            new Dog("小白", 3, 25.0, "拉布拉多"),
            new Cat("小咪", 2, 4.5, true),
            new Bird("小黃", 1, 0.5, true, 20.0),
            new Animal("通用動物", 5, 10.0) // 基礎動物類別
        };
        
        // 測試多型性：相同的方法呼叫，不同的行為
        System.out.println("=== 多型性測試：動物叫聲 ===");
        for (Animal animal : animals) {
            animal.makeSound(); // 每個動物都有不同的叫聲
        }
        
        System.out.println("\n=== 多型性測試：進食行為 ===");
        for (Animal animal : animals) {
            animal.eat(); // 每個動物都有不同的進食方式
        }
        
        System.out.println("\n=== 睡眠行為測試 ===");
        for (Animal animal : animals) {
            animal.sleep(); // 有些動物覆寫了睡眠方法
        }
        
        // 測試 final 方法（所有動物都一樣）
        System.out.println("\n=== Final 方法測試：呼吸 ===");
        for (Animal animal : animals) {
            animal.breathe(); // final 方法不能被覆寫
        }
        
        // 計算食物需求
        System.out.println("\n=== 食物需求計算 ===");
        double totalFoodNeed = 0;
        for (Animal animal : animals) {
            double foodNeed = animal.calculateFoodNeed();
            totalFoodNeed += foodNeed;
            System.out.printf("%s 的食物需求: %.2f 公斤%n", 
                            animal.name, foodNeed);
        }
        System.out.printf("總食物需求: %.2f 公斤%n", totalFoodNeed);
        
        // 測試特定動物的特殊行為
        System.out.println("\n=== 特殊行為測試 ===");
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                Dog dog = (Dog) animal;
                dog.fetch();
                dog.wagTail();
            } else if (animal instanceof Cat) {
                Cat cat = (Cat) animal;
                cat.purr();
                cat.scratch();
            } else if (animal instanceof Bird) {
                Bird bird = (Bird) animal;
                bird.fly();
            }
        }
        
        // 顯示所有動物詳細資訊
        System.out.println("\n=== 所有動物資訊 ===");
        for (Animal animal : animals) {
            animal.displayInfo();
            System.out.println();
        }
    }
}
```

!!! warning "覆寫注意事項"
    - 必須使用 `@Override` 註解（編譯器會檢查）
    - 覆寫方法的存取權限不能比父類別更嚴格
    - 覆寫方法的回傳型態必須相同或為子型態
    - `final` 方法不能被覆寫

## super 關鍵字

`super` 關鍵字用於存取父類別的成員，特別是在覆寫方法時仍想使用父類別的實作。

### super 的用途範例

```java
// 員工基礎類別
class Employee {
    protected String name;
    protected String id;
    protected double baseSalary;
    protected String department;
    
    public Employee(String name, String id, double baseSalary, String department) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
        this.department = department;
        System.out.println("建立員工記錄: " + name);
    }
    
    public double calculateSalary() {
        return baseSalary;
    }
    
    public void work() {
        System.out.println(name + " 正在工作");
    }
    
    public void displayInfo() {
        System.out.println("=== 員工資訊 ===");
        System.out.println("姓名: " + name);
        System.out.println("員工編號: " + id);
        System.out.println("部門: " + department);
        System.out.println("基本薪水: $" + String.format("%.2f", baseSalary));
    }
    
    // Getter 方法
    public String getName() { return name; }
    public String getId() { return id; }
    public double getBaseSalary() { return baseSalary; }
    public String getDepartment() { return department; }
}

// 管理員類別
class Manager extends Employee {
    private double bonus;           // 績效獎金
    private int teamSize;          // 團隊人數
    private String managementLevel; // 管理層級
    
    public Manager(String name, String id, double baseSalary, String department,
                   double bonus, int teamSize, String managementLevel) {
        // 使用 super() 呼叫父類別建構子
        super(name, id, baseSalary, department);
        this.bonus = bonus;
        this.teamSize = teamSize;
        this.managementLevel = managementLevel;
        System.out.println(name + " 是 " + managementLevel + " 主管，管理 " + teamSize + " 人");
    }
    
    @Override
    public double calculateSalary() {
        // 使用 super.method() 呼叫父類別方法
        double basePay = super.calculateSalary();
        double managementAllowance = teamSize * 500; // 每個下屬 $500 管理費
        return basePay + bonus + managementAllowance;
    }
    
    @Override
    public void work() {
        // 先執行父類別的工作
        super.work();
        // 再執行管理相關工作
        System.out.println(name + " 正在管理團隊和制定策略");
        conductMeeting();
    }
    
    private void conductMeeting() {
        System.out.println(name + " 正在主持會議");
    }
    
    public void reviewPerformance() {
        System.out.println(name + " 正在評估下屬的績效");
    }
    
    @Override
    public void displayInfo() {
        // 使用 super.method() 顯示基本資訊
        super.displayInfo();
        // 添加管理者特有資訊
        System.out.println("管理層級: " + managementLevel);
        System.out.println("團隊人數: " + teamSize);
        System.out.println("績效獎金: $" + String.format("%.2f", bonus));
        System.out.println("總薪水: $" + String.format("%.2f", calculateSalary()));
    }
}

// 開發人員類別
class Developer extends Employee {
    private String[] programmingLanguages; // 程式語言
    private int yearsOfExperience;          // 工作經驗
    private String specialization;          // 專長領域
    
    public Developer(String name, String id, double baseSalary, String department,
                    String[] programmingLanguages, int yearsOfExperience, String specialization) {
        super(name, id, baseSalary, department);
        this.programmingLanguages = programmingLanguages;
        this.yearsOfExperience = yearsOfExperience;
        this.specialization = specialization;
        System.out.println(name + " 是 " + specialization + " 開發人員");
    }
    
    @Override
    public double calculateSalary() {
        double basePay = super.calculateSalary();
        
        // 根據經驗年數給予加薪
        double experienceBonus = yearsOfExperience * 2000;
        
        // 根據會的程式語言數量給予技能獎金
        double skillBonus = programmingLanguages.length * 1000;
        
        return basePay + experienceBonus + skillBonus;
    }
    
    @Override
    public void work() {
        super.work(); // 執行基本工作
        System.out.println(name + " 正在用 " + getMainLanguage() + " 開發程式");
        if (yearsOfExperience >= 5) {
            System.out.println(name + " 正在指導初級開發人員");
        }
    }
    
    private String getMainLanguage() {
        return programmingLanguages.length > 0 ? programmingLanguages[0] : "未知語言";
    }
    
    public void writeCode() {
        System.out.println(name + " 正在撰寫 " + specialization + " 相關程式碼");
    }
    
    public void debugCode() {
        System.out.println(name + " 正在除錯程式");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("專長: " + specialization);
        System.out.println("工作經驗: " + yearsOfExperience + " 年");
        System.out.print("程式語言: ");
        for (int i = 0; i < programmingLanguages.length; i++) {
            System.out.print(programmingLanguages[i]);
            if (i < programmingLanguages.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("總薪水: $" + String.format("%.2f", calculateSalary()));
    }
}
```

### super 使用示範

```java
public class SuperDemo {
    public static void main(String[] args) {
        System.out.println("=== super 關鍵字示範 ===");
        
        // 建立不同類型的員工
        Employee[] employees = {
            new Employee("張基本", "EMP001", 40000, "行政部"),
            new Manager("李主管", "MGR001", 60000, "IT部", 15000, 5, "中階主管"),
            new Developer("王工程師", "DEV001", 55000, "IT部", 
                         new String[]{"Java", "Python", "JavaScript"}, 3, "後端開發"),
            new Developer("陳資深", "DEV002", 50000, "IT部",
                         new String[]{"Java", "Spring", "React", "Docker"}, 7, "全端開發")
        };
        
        System.out.println("\n=== 薪水計算 ===");
        double totalSalaryCost = 0;
        for (Employee emp : employees) {
            double salary = emp.calculateSalary();
            totalSalaryCost += salary;
            System.out.printf("%s 的薪水: $%.2f%n", emp.getName(), salary);
        }
        System.out.printf("公司總薪水支出: $%.2f%n", totalSalaryCost);
        
        System.out.println("\n=== 工作狀況 ===");
        for (Employee emp : employees) {
            emp.work(); // 每個員工的工作方式不同
            System.out.println();
        }
        
        // 測試特殊功能
        System.out.println("=== 特殊功能測試 ===");
        for (Employee emp : employees) {
            if (emp instanceof Manager) {
                Manager manager = (Manager) emp;
                manager.reviewPerformance();
            } else if (emp instanceof Developer) {
                Developer dev = (Developer) emp;
                dev.writeCode();
                dev.debugCode();
            }
        }
        
        // 顯示詳細資訊
        System.out.println("\n=== 員工詳細資訊 ===");
        for (Employee emp : employees) {
            emp.displayInfo();
            System.out.println();
        }
        
        // 示範 super 在建構子中的使用
        System.out.println("=== 建構子鏈接示範 ===");
        Developer seniorDev = new Developer("林高手", "DEV003", 70000, "IT部",
                                          new String[]{"Java", "Kotlin", "Go", "Rust"}, 
                                          10, "架構師");
    }
}
```

!!! tip "super 使用要點"
    - `super()` 呼叫父類別建構子，必須是子類別建構子的第一行
    - `super.method()` 呼叫父類別的方法
    - `super.field` 存取父類別的欄位（如果 protected 或 public）
    - 在靜態方法中不能使用 `super`

## final 關鍵字

`final` 關鍵字可以用於類別、方法和變數，表示「最終」、「不可改變」的意思。

### final 的用法

```java
// final 類別：不能被繼承
final class MathUtils {
    // final 靜態常數
    public static final double PI = 3.141592653589793;
    public static final double E = 2.718281828459045;
    
    // final 方法：不能被覆寫
    public final double circleArea(double radius) {
        return PI * radius * radius;
    }
    
    public final double circleCircumference(double radius) {
        return 2 * PI * radius;
    }
}

// 一般類別示範 final 的各種用法
class FinalDemo {
    // final 實例變數：必須在宣告時或建構子中初始化
    private final String id;
    private final long creationTime;
    
    // final 靜態變數：類別常數
    public static final int MAX_CONNECTIONS = 100;
    public static final String VERSION = "1.0.0";
    
    private String name;
    private int value;
    
    // 建構子
    public FinalDemo(String id, String name) {
        this.id = id;                    // final 變數初始化
        this.creationTime = System.currentTimeMillis(); // final 變數初始化
        this.name = name;
        this.value = 0;
    }
    
    // final 方法：不能被子類別覆寫
    public final String getId() {
        return id;
    }
    
    public final long getCreationTime() {
        return creationTime;
    }
    
    // 一般方法：可以被覆寫
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    // 方法中的 final 變數
    public void processData(final int input) {
        // input = 10; // 編譯錯誤：final 參數不能修改
        
        final int multiplier = 2;
        // multiplier = 3; // 編譯錯誤：final 區域變數不能修改
        
        int result = input * multiplier;
        this.value = result; // 實例變數可以修改
    }
    
    // final 與集合
    public void demonstrateFinalCollections() {
        final java.util.List<String> finalList = new java.util.ArrayList<>();
        
        // 可以修改集合內容
        finalList.add("item1");
        finalList.add("item2");
        finalList.remove(0);
        
        // 但不能重新指派
        // finalList = new ArrayList<>(); // 編譯錯誤
        
        System.out.println("Final list: " + finalList);
    }
}

// 嘗試繼承 final 類別會編譯錯誤
// class ExtendedMathUtils extends MathUtils { } // 編譯錯誤

// 繼承一般類別，測試 final 方法
class ExtendedFinalDemo extends FinalDemo {
    public ExtendedFinalDemo(String id, String name) {
        super(id, name);
    }
    
    // 可以覆寫一般方法
    @Override
    public void setName(String name) {
        super.setName(name.toUpperCase()); // 名字轉大寫
    }
    
    // 不能覆寫 final 方法
    // public String getId() { return "new_" + super.getId(); } // 編譯錯誤
}
```

### final 使用範例

```java
public class FinalExampleDemo {
    public static void main(String[] args) {
        System.out.println("=== final 關鍵字示範 ===");
        
        // 使用 final 類別
        System.out.println("圓周率: " + MathUtils.PI);
        System.out.println("自然對數 e: " + MathUtils.E);
        
        double radius = 5.0;
        MathUtils mathUtils = new MathUtils();
        System.out.printf("半徑 %.1f 的圓面積: %.2f%n", 
                         radius, mathUtils.circleArea(radius));
        System.out.printf("半徑 %.1f 的圓周長: %.2f%n", 
                         radius, mathUtils.circleCircumference(radius));
        
        // 使用含有 final 成員的類別
        System.out.println("\n=== Final 變數示範 ===");
        FinalDemo demo = new FinalDemo("ID_001", "測試物件");
        
        System.out.println("ID: " + demo.getId()); // final 方法
        System.out.println("建立時間: " + demo.getCreationTime());
        System.out.println("名稱: " + demo.getName());
        
        // 修改一般變數
        demo.setName("新名稱");
        System.out.println("修改後名稱: " + demo.getName());
        
        // final 變數不能修改
        // demo.id = "new_id"; // 編譯錯誤，假設有 public 存取權
        
        // 處理資料
        demo.processData(10);
        
        // 示範 final 集合
        demo.demonstrateFinalCollections();
        
        // 使用子類別
        System.out.println("\n=== 子類別示範 ===");
        ExtendedFinalDemo extended = new ExtendedFinalDemo("ID_002", "extended object");
        System.out.println("子類別 ID: " + extended.getId());
        System.out.println("原始名稱: " + extended.getName());
        
        extended.setName("lowercase name");
        System.out.println("修改後名稱: " + extended.getName()); // 會轉為大寫
        
        // 靜態常數
        System.out.println("\n=== 靜態常數 ===");
        System.out.println("最大連線數: " + FinalDemo.MAX_CONNECTIONS);
        System.out.println("版本: " + FinalDemo.VERSION);
        
        // final 區域變數示範
        final String message = "這是 final 變數";
        System.out.println(message);
        // message = "不能修改"; // 編譯錯誤
        
        // final 與物件參考
        final StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");  // 可以修改物件內容
        System.out.println("StringBuilder 內容: " + sb);
        // sb = new StringBuilder(); // 編譯錯誤：不能重新指派
    }
}
```

!!! note "final 重要概念"
    - **final 類別**: 不能被繼承（如 String、Integer）
    - **final 方法**: 不能被覆寫
    - **final 變數**: 不能重新指派，但物件內容可以修改
    - **final 參數**: 方法參數在方法內不能修改

## Records (Java 14+)

Records 是 Java 14 引入的新特性，用於建立不可變的資料承載類別，大幅簡化了資料類別的撰寫。

### 基本 Record 定義

```java
// 傳統的資料類別寫法
class TraditionalPerson {
    private final String name;
    private final int age;
    private final String email;
    
    public TraditionalPerson(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    public String name() { return name; }
    public int age() { return age; }
    public String email() { return email; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TraditionalPerson that = (TraditionalPerson) o;
        return age == that.age &&
               java.util.Objects.equals(name, that.name) &&
               java.util.Objects.equals(email, that.email);
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, age, email);
    }
    
    @Override
    public String toString() {
        return "TraditionalPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}

// 使用 Record 的簡潔寫法
public record Person(String name, int age, String email) {
    // Record 自動提供：
    // - 私有 final 欄位
    // - 建構子
    // - accessor 方法 (name(), age(), email())
    // - equals(), hashCode(), toString()
}

// 含有驗證邏輯的 Record
public record Student(String studentId, String name, int age, double gpa) {
    
    // 精簡建構子：用於驗證
    public Student {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("姓名不能為空");
        }
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("年齡必須在 0-150 之間");
        }
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA 必須在 0.0-4.0 之間");
        }
        
        // 正規化資料
        name = name.trim();
    }
    
    // 自定義方法
    public String getGradeLevel() {
        if (gpa >= 3.7) return "優秀";
        else if (gpa >= 3.0) return "良好";
        else if (gpa >= 2.0) return "普通";
        else return "需要加強";
    }
    
    public boolean isAdult() {
        return age >= 18;
    }
    
    // 靜態工廠方法
    public static Student createWithDefaults(String studentId, String name) {
        return new Student(studentId, name, 18, 0.0);
    }
}

// 複雜的 Record 範例
public record BankAccount(String accountNumber, String holderName, 
                         double balance, AccountType type, 
                         java.time.LocalDate openDate) {
    
    // 內嵌列舉
    public enum AccountType {
        SAVINGS("儲蓄帳戶"), 
        CHECKING("支票帳戶"), 
        CREDIT("信用帳戶");
        
        private final String displayName;
        
        AccountType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // 建構子驗證
    public BankAccount {
        if (accountNumber == null || accountNumber.length() != 10) {
            throw new IllegalArgumentException("帳號必須是 10 位數字");
        }
        if (holderName == null || holderName.trim().isEmpty()) {
            throw new IllegalArgumentException("帳戶持有人姓名不能為空");
        }
        if (balance < 0 && type != AccountType.CREDIT) {
            throw new IllegalArgumentException("非信用帳戶餘額不能為負數");
        }
        if (openDate == null) {
            openDate = java.time.LocalDate.now();
        }
        
        // 正規化
        holderName = holderName.trim();
    }
    
    // 替代建構子
    public BankAccount(String accountNumber, String holderName, AccountType type) {
        this(accountNumber, holderName, 0.0, type, java.time.LocalDate.now());
    }
    
    // 自定義方法
    public String getAccountInfo() {
        return String.format("帳號: %s, 持有人: %s, 類型: %s, 餘額: $%.2f", 
                           accountNumber, holderName, type.getDisplayName(), balance);
    }
    
    public boolean isOverdrawn() {
        return balance < 0;
    }
    
    public int getAccountAge() {
        return java.time.Period.between(openDate, java.time.LocalDate.now()).getYears();
    }
    
    // Record 可以實作介面
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BankAccount other)) return false;
        return accountNumber.equals(other.accountNumber);
    }
    
    public int hashCode() {
        return accountNumber.hashCode();
    }
}
```

### Records 使用範例

```java
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class RecordsDemo {
    public static void main(String[] args) {
        System.out.println("=== Records 基本使用 ===");
        
        // 建立 Person Record
        Person person1 = new Person("張小明", 25, "zhang@email.com");
        Person person2 = new Person("李小華", 30, "li@email.com");
        
        System.out.println("Person 1: " + person1);
        System.out.println("姓名: " + person1.name());
        System.out.println("年齡: " + person1.age());
        System.out.println("信箱: " + person1.email());
        
        // 測試 equals 和 hashCode
        Person person3 = new Person("張小明", 25, "zhang@email.com");
        System.out.println("person1.equals(person3): " + person1.equals(person3));
        System.out.println("person1 hashCode: " + person1.hashCode());
        System.out.println("person3 hashCode: " + person3.hashCode());
        
        System.out.println("\n=== Student Record 示範 ===");
        
        // 建立學生 Record
        List<Student> students = new ArrayList<>();
        
        try {
            students.add(new Student("S001", "王同學", 20, 3.8));
            students.add(new Student("S002", "陳同學", 19, 3.2));
            students.add(Student.createWithDefaults("S003", "林同學"));
            
            // 嘗試建立無效學生（會拋出例外）
            students.add(new Student("S004", "", -5, 5.0));
            
        } catch (IllegalArgumentException e) {
            System.out.println("建立學生時發生錯誤: " + e.getMessage());
        }
        
        // 顯示學生資訊
        System.out.println("學生列表:");
        for (Student student : students) {
            System.out.printf("%-6s %-8s (年齡:%d, GPA:%.1f) - %s%s%n",
                            student.studentId(), student.name(), student.age(), 
                            student.gpa(), student.getGradeLevel(),
                            student.isAdult() ? " [成年]" : " [未成年]");
        }
        
        System.out.println("\n=== BankAccount Record 示範 ===");
        
        // 建立銀行帳戶
        List<BankAccount> accounts = new ArrayList<>();
        
        accounts.add(new BankAccount("1234567890", "張富豪", 50000.0, 
                    BankAccount.AccountType.SAVINGS, LocalDate.of(2020, 1, 15)));
        
        accounts.add(new BankAccount("9876543210", "李小康", 
                    BankAccount.AccountType.CHECKING));
        
        accounts.add(new BankAccount("5555666677", "王負債", -1000.0, 
                    BankAccount.AccountType.CREDIT, LocalDate.of(2022, 6, 1)));
        
        // 顯示帳戶資訊
        System.out.println("銀行帳戶列表:");
        for (BankAccount account : accounts) {
            System.out.println(account.getAccountInfo());
            System.out.println("  開戶年數: " + account.getAccountAge() + " 年");
            System.out.println("  是否透支: " + (account.isOverdrawn() ? "是" : "否"));
            System.out.println("  完整資訊: " + account);
            System.out.println();
        }
        
        // Record 作為 Map 的 key
        System.out.println("=== Record 作為 Map Key ===");
        java.util.Map<Person, String> personRoles = new java.util.HashMap<>();
        personRoles.put(person1, "軟體工程師");
        personRoles.put(person2, "專案經理");
        
        for (var entry : personRoles.entrySet()) {
            System.out.println(entry.getKey().name() + " -> " + entry.getValue());
        }
        
        // 測試 Record 不可變性
        System.out.println("\n=== Record 不可變性 ===");
        System.out.println("Records 的欄位都是 final，無法修改");
        System.out.println("person1.name() = " + person1.name());
        // person1.name = "新名字"; // 編譯錯誤：沒有 setter 方法
        
        // 要修改 Record，必須建立新的實例
        Person modifiedPerson = new Person("張小明_修改", person1.age(), person1.email());
        System.out.println("修改後的新 Person: " + modifiedPerson);
    }
}
```

### Records vs 傳統類別

```java
public class RecordsVsClassesDemo {
    
    // Record 版本：座標點
    public record Point(double x, double y) {
        public double distanceFromOrigin() {
            return Math.sqrt(x * x + y * y);
        }
        
        public Point translate(double dx, double dy) {
            return new Point(x + dx, y + dy);
        }
    }
    
    // 傳統類別版本：座標點
    public static class TraditionalPoint {
        private final double x;
        private final double y;
        
        public TraditionalPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public double getX() { return x; }
        public double getY() { return y; }
        
        public double distanceFromOrigin() {
            return Math.sqrt(x * x + y * y);
        }
        
        public TraditionalPoint translate(double dx, double dy) {
            return new TraditionalPoint(x + dx, y + dy);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TraditionalPoint that = (TraditionalPoint) o;
            return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
        }
        
        @Override
        public int hashCode() {
            return java.util.Objects.hash(x, y);
        }
        
        @Override
        public String toString() {
            return "TraditionalPoint{x=" + x + ", y=" + y + '}';
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Records vs 傳統類別比較 ===");
        
        // 建立物件
        Point recordPoint = new Point(3.0, 4.0);
        TraditionalPoint traditionalPoint = new TraditionalPoint(3.0, 4.0);
        
        // 功能比較
        System.out.println("Record Point: " + recordPoint);
        System.out.println("Traditional Point: " + traditionalPoint);
        
        System.out.println("Record 存取: x=" + recordPoint.x() + ", y=" + recordPoint.y());
        System.out.println("Traditional 存取: x=" + traditionalPoint.getX() + 
                          ", y=" + traditionalPoint.getY());
        
        // 方法呼叫
        System.out.println("Record 距離: " + recordPoint.distanceFromOrigin());
        System.out.println("Traditional 距離: " + traditionalPoint.distanceFromOrigin());
        
        // 移動點
        Point movedRecord = recordPoint.translate(1.0, 1.0);
        TraditionalPoint movedTraditional = traditionalPoint.translate(1.0, 1.0);
        
        System.out.println("移動後 Record: " + movedRecord);
        System.out.println("移動後 Traditional: " + movedTraditional);
        
        // equals 測試
        Point anotherRecord = new Point(3.0, 4.0);
        TraditionalPoint anotherTraditional = new TraditionalPoint(3.0, 4.0);
        
        System.out.println("Record equals: " + recordPoint.equals(anotherRecord));
        System.out.println("Traditional equals: " + traditionalPoint.equals(anotherTraditional));
        
        // 程式碼長度比較
        System.out.println("\n=== 程式碼簡潔度比較 ===");
        System.out.println("Record 定義：1 行 + 自定義方法");
        System.out.println("Traditional 定義：約 40 行程式碼");
        System.out.println("Record 優勢：自動生成 constructor, getters, equals, hashCode, toString");
    }
}
```

!!! tip "Records 最佳實踐"
    - 用於不可變的資料承載類別
    - 適合 DTO (Data Transfer Object) 和值物件
    - 可以包含自定義方法和靜態方法
    - 可以實作介面但不能繼承其他類別
    - 所有欄位都是 `private final`

## 重點整理

1. **繼承**：使用 `extends` 建立 is-a 關係，子類別繼承父類別的屬性和方法
2. **方法覆寫**：子類別可以重新定義父類別的方法，使用 `@Override` 註解
3. **super 關鍵字**：存取父類別的建構子、方法和欄位
4. **final 關鍵字**：防止繼承、覆寫或重新指派
5. **Records**：Java 14+ 的簡潔資料類別語法，自動提供常用方法

!!! warning "繼承注意事項"
    - Java 只支援單一繼承（一個類別只能繼承一個父類別）
    - 繼承會建立強耦合關係，要謹慎使用
    - 優先使用組合而非繼承
    - Records 不能被繼承，也不能繼承其他類別

## 練習建議

1. **基礎練習**
   - 建立 Shape 父類別和 Circle、Rectangle 子類別
   - 實作動物繼承體系（Animal → Dog, Cat, Bird）
   - 使用 Records 建立學生、課程、成績等資料類別

2. **進階練習**
   - 設計員工管理系統（Employee → Manager, Developer, Designer）
   - 建立交通工具繼承體系，實作不同的啟動方式
   - 使用 Records 重構現有的資料類別

3. **綜合練習**
   - 設計圖形繪製系統，包含多種形狀和操作
   - 建立遊戲角色系統，包含不同職業和技能
   - 實作訂單管理系統，結合繼承和 Records

下一章我們將學習介面與泛型，探索 Java 更進階的類型系統！