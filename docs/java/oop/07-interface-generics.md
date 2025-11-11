# 介面與泛型(Generics)

## 介面 (Interface) 基礎

介面是 Java 中定義契約的機制，它指定一個類別必須實作哪些方法，但不提供實作細節。介面支援多重實作，解決了 Java 單一繼承的限制。

### 基本介面定義

```java
// 基本介面：可繪製的物件
interface Drawable {
    // 抽象方法（介面中方法預設是 public abstract）
    void draw();
    void erase();
    
    // 介面中可以有常數（預設是 public static final）
    String DEFAULT_COLOR = "BLACK";
    int MAX_SIZE = 1000;
}

// 可移動的介面
interface Movable {
    void move(int x, int y);
    void rotate(double angle);
    
    // 預設方法（Java 8+）
    default void resetPosition() {
        move(0, 0);
        rotate(0);
        System.out.println("物件已重設到原點");
    }
}

// 可縮放的介面
interface Scalable {
    void scale(double factor);
    
    // 靜態方法（Java 8+）
    static void validateScaleFactor(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("縮放因子必須大於 0");
        }
    }
    
    // 預設方法
    default void doubleSize() {
        scale(2.0);
    }
    
    default void halfSize() {
        scale(0.5);
    }
}

// 可計算面積的介面
interface AreaCalculable {
    double calculateArea();
    
    // 預設方法提供額外功能
    default String getAreaInfo() {
        return String.format("面積: %.2f 平方單位", calculateArea());
    }
    
    default boolean isLargeArea() {
        return calculateArea() > 100.0;
    }
}
```

### 實作介面

```java
// 圓形類別實作多個介面
class Circle implements Drawable, Movable, Scalable, AreaCalculable {
    private double x, y;      // 位置
    private double radius;    // 半徑
    private String color;
    
    public Circle(double x, double y, double radius, String color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }
    
    // 實作 Drawable 介面
    @Override
    public void draw() {
        System.out.printf("繪製%s圓形：中心(%.1f, %.1f), 半徑%.1f%n", 
                         color, x, y, radius);
    }
    
    @Override
    public void erase() {
        System.out.printf("擦除位於(%.1f, %.1f)的圓形%n", x, y);
    }
    
    // 實作 Movable 介面
    @Override
    public void move(int newX, int newY) {
        System.out.printf("圓形從(%.1f, %.1f)移動到(%d, %d)%n", x, y, newX, newY);
        this.x = newX;
        this.y = newY;
    }
    
    @Override
    public void rotate(double angle) {
        System.out.printf("圓形旋轉 %.1f 度（圓形旋轉無視覺變化）%n", angle);
    }
    
    // 實作 Scalable 介面
    @Override
    public void scale(double factor) {
        Scalable.validateScaleFactor(factor); // 呼叫介面的靜態方法
        System.out.printf("圓形半徑從 %.1f 縮放到 %.1f%n", radius, radius * factor);
        this.radius *= factor;
    }
    
    // 實作 AreaCalculable 介面
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    // Getter 方法
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }
    public String getColor() { return color; }
    
    @Override
    public String toString() {
        return String.format("Circle{位置:(%.1f,%.1f), 半徑:%.1f, 顏色:%s}", 
                           x, y, radius, color);
    }
}

// 矩形類別
class Rectangle implements Drawable, Movable, Scalable, AreaCalculable {
    private double x, y;        // 位置
    private double width, height; // 寬高
    private String color;
    
    public Rectangle(double x, double y, double width, double height, String color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    @Override
    public void draw() {
        System.out.printf("繪製%s矩形：位置(%.1f, %.1f), 大小%.1f×%.1f%n", 
                         color, x, y, width, height);
    }
    
    @Override
    public void erase() {
        System.out.printf("擦除位於(%.1f, %.1f)的矩形%n", x, y);
    }
    
    @Override
    public void move(int newX, int newY) {
        System.out.printf("矩形從(%.1f, %.1f)移動到(%d, %d)%n", x, y, newX, newY);
        this.x = newX;
        this.y = newY;
    }
    
    @Override
    public void rotate(double angle) {
        System.out.printf("矩形旋轉 %.1f 度%n", angle);
        // 簡化實作，實際應該計算旋轉後的座標
    }
    
    @Override
    public void scale(double factor) {
        Scalable.validateScaleFactor(factor);
        System.out.printf("矩形從 %.1f×%.1f 縮放到 %.1f×%.1f%n", 
                         width, height, width * factor, height * factor);
        this.width *= factor;
        this.height *= factor;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
    
    // Getter 方法
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    
    @Override
    public String toString() {
        return String.format("Rectangle{位置:(%.1f,%.1f), 大小:%.1f×%.1f, 顏色:%s}", 
                           x, y, width, height, color);
    }
}
```

### 介面使用範例

```java
import java.util.ArrayList;
import java.util.List;

public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("=== 介面基礎示範 ===");
        
        // 建立不同形狀的物件
        Circle circle = new Circle(10, 20, 5, "紅色");
        Rectangle rectangle = new Rectangle(0, 0, 10, 8, "藍色");
        
        System.out.println("初始物件:");
        System.out.println(circle);
        System.out.println(rectangle);
        
        System.out.println("\n=== 測試 Drawable 功能 ===");
        // 多型性：用介面參考指向不同實作
        Drawable[] drawables = {circle, rectangle};
        
        for (Drawable drawable : drawables) {
            drawable.draw();
        }
        
        System.out.println("\n=== 測試 Movable 功能 ===");
        List<Movable> movables = new ArrayList<>();
        movables.add(circle);
        movables.add(rectangle);
        
        for (Movable movable : movables) {
            movable.move(50, 30);
            movable.resetPosition(); // 使用預設方法
        }
        
        System.out.println("\n=== 測試 Scalable 功能 ===");
        List<Scalable> scalables = List.of(circle, rectangle);
        
        for (Scalable scalable : scalables) {
            try {
                scalable.doubleSize(); // 使用預設方法
                scalable.halfSize();   // 使用預設方法
                scalable.scale(1.5);
            } catch (IllegalArgumentException e) {
                System.out.println("縮放錯誤: " + e.getMessage());
            }
        }
        
        System.out.println("\n=== 測試 AreaCalculable 功能 ===");
        List<AreaCalculable> calculables = List.of(circle, rectangle);
        
        double totalArea = 0;
        for (AreaCalculable calculable : calculables) {
            System.out.println(calculable.getAreaInfo()); // 使用預設方法
            System.out.println("是否為大面積: " + calculable.isLargeArea());
            totalArea += calculable.calculateArea();
        }
        System.out.printf("總面積: %.2f%n", totalArea);
        
        // 測試靜態方法
        System.out.println("\n=== 測試介面靜態方法 ===");
        try {
            Scalable.validateScaleFactor(2.0); // 有效
            System.out.println("縮放因子 2.0 有效");
            
            Scalable.validateScaleFactor(-1.0); // 無效
        } catch (IllegalArgumentException e) {
            System.out.println("靜態驗證失敗: " + e.getMessage());
        }
        
        // 測試常數
        System.out.println("\n=== 介面常數 ===");
        System.out.println("預設顏色: " + Drawable.DEFAULT_COLOR);
        System.out.println("最大尺寸: " + Drawable.MAX_SIZE);
        
        System.out.println("\n=== 最終狀態 ===");
        System.out.println(circle);
        System.out.println(rectangle);
    }
}
```

!!! note "介面重點概念"
    - 介面定義契約，類別必須實作所有抽象方法
    - 一個類別可以實作多個介面
    - 介面支援預設方法（default）和靜態方法（static）
    - 介面中的欄位自動是 `public static final`

## 預設方法 (Default Methods) 與多重實作

Java 8 引入預設方法，允許在介面中提供方法的預設實作，這解決了介面演化的問題。

### 預設方法衝突解決

```java
// 介面 A
interface InterfaceA {
    default void commonMethod() {
        System.out.println("InterfaceA 的 commonMethod");
    }
    
    default void methodA() {
        System.out.println("InterfaceA 的 methodA");
    }
}

// 介面 B
interface InterfaceB {
    default void commonMethod() {
        System.out.println("InterfaceB 的 commonMethod");
    }
    
    default void methodB() {
        System.out.println("InterfaceB 的 methodB");
    }
}

// 介面 C 繼承 InterfaceA
interface InterfaceC extends InterfaceA {
    @Override
    default void commonMethod() {
        System.out.println("InterfaceC 覆寫的 commonMethod");
        InterfaceA.super.commonMethod(); // 呼叫父介面的預設方法
    }
    
    default void methodC() {
        System.out.println("InterfaceC 的 methodC");
    }
}

// 實作多個介面的類別
class MultiInterfaceImplementation implements InterfaceA, InterfaceB {
    // 必須覆寫衝突的預設方法
    @Override
    public void commonMethod() {
        System.out.println("MultiInterfaceImplementation 解決衝突的 commonMethod");
        
        // 可以選擇呼叫特定介面的預設方法
        InterfaceA.super.commonMethod();
        InterfaceB.super.commonMethod();
    }
}

// 繼承和實作的衝突
class BaseClass {
    public void conflictMethod() {
        System.out.println("BaseClass 的 conflictMethod");
    }
}

interface ConflictInterface {
    default void conflictMethod() {
        System.out.println("ConflictInterface 的 conflictMethod");
    }
}

class ConflictResolution extends BaseClass implements ConflictInterface {
    // 類別的方法優先於介面的預設方法
    // 這裡不需要覆寫，會使用 BaseClass 的方法
    
    public void testConflict() {
        conflictMethod(); // 使用 BaseClass 的方法
        ConflictInterface.super.conflictMethod(); // 明確使用介面的方法
    }
}
```

### 函數式介面範例

```java
// 自定義函數式介面
@FunctionalInterface
interface Calculator {
    double calculate(double a, double b);
    
    // 函數式介面可以有預設方法
    default void printResult(double a, double b) {
        System.out.printf("計算結果: %.2f%n", calculate(a, b));
    }
    
    // 靜態方法
    static Calculator add() {
        return (a, b) -> a + b;
    }
    
    static Calculator multiply() {
        return (a, b) -> a * b;
    }
}

// 更複雜的函數式介面
@FunctionalInterface
interface StringProcessor {
    String process(String input);
    
    // 組合方法
    default StringProcessor andThen(StringProcessor after) {
        return input -> after.process(this.process(input));
    }
    
    // 靜態工廠方法
    static StringProcessor uppercase() {
        return String::toUpperCase;
    }
    
    static StringProcessor addPrefix(String prefix) {
        return input -> prefix + input;
    }
    
    static StringProcessor addSuffix(String suffix) {
        return input -> input + suffix;
    }
}

// 函數式介面使用範例
public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        System.out.println("=== 函數式介面示範 ===");
        
        // 使用 Lambda 表達式
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> b != 0 ? a / b : 0;
        
        double x = 10, y = 3;
        
        // 測試計算器
        System.out.printf("%.1f + %.1f = %.2f%n", x, y, add.calculate(x, y));
        System.out.printf("%.1f - %.1f = %.2f%n", x, y, subtract.calculate(x, y));
        System.out.printf("%.1f × %.1f = %.2f%n", x, y, multiply.calculate(x, y));
        System.out.printf("%.1f ÷ %.1f = %.2f%n", x, y, divide.calculate(x, y));
        
        // 使用預設方法
        add.printResult(x, y);
        
        // 使用靜態工廠方法
        Calculator staticAdd = Calculator.add();
        Calculator staticMultiply = Calculator.multiply();
        staticAdd.printResult(5, 3);
        staticMultiply.printResult(4, 6);
        
        System.out.println("\n=== 字串處理器示範 ===");
        
        // 建立不同的字串處理器
        StringProcessor upperCase = StringProcessor.uppercase();
        StringProcessor addHello = StringProcessor.addPrefix("Hello ");
        StringProcessor addExclamation = StringProcessor.addSuffix("!");
        
        String input = "world";
        System.out.println("原始字串: " + input);
        System.out.println("大寫: " + upperCase.process(input));
        System.out.println("加前綴: " + addHello.process(input));
        System.out.println("加後綴: " + addExclamation.process(input));
        
        // 組合處理器
        StringProcessor combined = addHello
            .andThen(StringProcessor.uppercase())
            .andThen(addExclamation);
        
        System.out.println("組合處理: " + combined.process(input));
        
        // 使用方法參考
        List<String> words = List.of("java", "python", "javascript");
        System.out.println("\n原始單詞: " + words);
        
        // 使用 map 和方法參考
        List<String> processedWords = words.stream()
            .map(upperCase::process)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("大寫單詞: " + processedWords);
    }
}
```

## 泛型 (Generics) 基礎

泛型允許在編寫程式時使用型態參數，提供型態安全和程式碼重用性。

### 基本泛型類別

```java
// 泛型類別：自定義容器
public class Box<T> {
    private T content;
    
    public Box() {
        this.content = null;
    }
    
    public Box(T content) {
        this.content = content;
    }
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
    
    public boolean isEmpty() {
        return content == null;
    }
    
    public void clear() {
        this.content = null;
    }
    
    @Override
    public String toString() {
        return "Box{" + (isEmpty() ? "empty" : "content=" + content) + "}";
    }
    
    // 泛型方法
    public <U> Box<U> map(java.util.function.Function<T, U> mapper) {
        if (isEmpty()) {
            return new Box<>();
        }
        return new Box<>(mapper.apply(content));
    }
}

// 多型別參數的泛型類別
public class Pair<T, U> {
    private T first;
    private U second;
    
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() { return first; }
    public U getSecond() { return second; }
    
    public void setFirst(T first) { this.first = first; }
    public void setSecond(U second) { this.second = second; }
    
    // 交換兩個值（如果類型相同）
    @SuppressWarnings("unchecked")
    public void swap() {
        if (first.getClass().equals(second.getClass())) {
            T temp = first;
            first = (T) second;
            second = (U) temp;
        } else {
            throw new IllegalStateException("無法交換不同類型的值");
        }
    }
    
    @Override
    public String toString() {
        return String.format("Pair{first=%s, second=%s}", first, second);
    }
    
    // 靜態泛型方法
    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }
}

// 有界泛型類別（繼承 Number 的類型）
public class NumberBox<T extends Number> {
    private T value;
    
    public NumberBox(T value) {
        this.value = value;
    }
    
    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }
    
    // 可以呼叫 Number 的方法
    public double getDoubleValue() {
        return value.doubleValue();
    }
    
    public int getIntValue() {
        return value.intValue();
    }
    
    // 比較數字大小
    public boolean isGreaterThan(NumberBox<? extends Number> other) {
        return this.getDoubleValue() > other.getDoubleValue();
    }
    
    // 計算與另一個數字的和
    public double add(NumberBox<? extends Number> other) {
        return this.getDoubleValue() + other.getDoubleValue();
    }
    
    @Override
    public String toString() {
        return String.format("NumberBox{value=%s, type=%s}", 
                           value, value.getClass().getSimpleName());
    }
}
```

### 泛型方法

```java
public class GenericMethods {
    
    // 泛型方法：交換陣列中的兩個元素
    public static <T> void swap(T[] array, int i, int j) {
        if (i >= 0 && i < array.length && j >= 0 && j < array.length) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    
    // 泛型方法：尋找最大值（要求元素可比較）
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }
    
    // 泛型方法：轉換 List
    public static <T, U> java.util.List<U> mapList(java.util.List<T> source, 
                                                   java.util.function.Function<T, U> mapper) {
        java.util.List<U> result = new java.util.ArrayList<>();
        for (T item : source) {
            result.add(mapper.apply(item));
        }
        return result;
    }
    
    // 泛型方法：過濾 List
    public static <T> java.util.List<T> filterList(java.util.List<T> source,
                                                   java.util.function.Predicate<T> predicate) {
        java.util.List<T> result = new java.util.ArrayList<>();
        for (T item : source) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
    
    // 多型別參數的泛型方法
    public static <T, U, R> R combine(T first, U second, 
                                     java.util.function.BiFunction<T, U, R> combiner) {
        return combiner.apply(first, second);
    }
    
    // 有界泛型方法
    public static <T extends Number> double calculateAverage(java.util.List<T> numbers) {
        if (numbers.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (T number : numbers) {
            sum += number.doubleValue();
        }
        return sum / numbers.size();
    }
}
```

### 泛型使用範例

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GenericsDemo {
    public static void main(String[] args) {
        System.out.println("=== 基本泛型使用 ===");
        
        // 使用 Box 泛型類別
        Box<String> stringBox = new Box<>("Hello Generics");
        Box<Integer> intBox = new Box<>(42);
        Box<Double> emptyBox = new Box<>();
        
        System.out.println("String Box: " + stringBox);
        System.out.println("Integer Box: " + intBox);
        System.out.println("Empty Box: " + emptyBox);
        
        // 測試 Box 方法
        System.out.println("String Box 內容: " + stringBox.get());
        System.out.println("Integer Box 是否為空: " + intBox.isEmpty());
        
        intBox.set(100);
        System.out.println("修改後 Integer Box: " + intBox);
        
        // 測試泛型方法 map
        Box<String> mappedBox = intBox.map(i -> "數字: " + i);
        System.out.println("對應後的 Box: " + mappedBox);
        
        System.out.println("\n=== Pair 泛型類別 ===");
        
        // 使用 Pair
        Pair<String, Integer> nameAge = new Pair<>("張小明", 25);
        Pair<Double, Double> coordinates = Pair.of(10.5, 20.3);
        
        System.out.println("姓名年齡: " + nameAge);
        System.out.println("座標: " + coordinates);
        
        // 修改 Pair 內容
        nameAge.setSecond(26);
        System.out.println("修改年齡後: " + nameAge);
        
        // 測試同類型 Pair 的交換
        Pair<Integer, Integer> numbers = new Pair<>(5, 10);
        System.out.println("交換前: " + numbers);
        numbers.swap();
        System.out.println("交換後: " + numbers);
        
        System.out.println("\n=== NumberBox 有界泛型 ===");
        
        // 使用有界泛型
        NumberBox<Integer> intNumberBox = new NumberBox<>(42);
        NumberBox<Double> doubleNumberBox = new NumberBox<>(3.14);
        NumberBox<Float> floatNumberBox = new NumberBox<>(2.5f);
        
        System.out.println("Integer NumberBox: " + intNumberBox);
        System.out.println("Double NumberBox: " + doubleNumberBox);
        System.out.println("Float NumberBox: " + floatNumberBox);
        
        // 測試數字操作
        System.out.println("Int 的 double 值: " + intNumberBox.getDoubleValue());
        System.out.println("Double 的 int 值: " + doubleNumberBox.getIntValue());
        
        // 比較和計算
        System.out.println("Int > Double: " + intNumberBox.isGreaterThan(doubleNumberBox));
        System.out.println("Int + Double: " + intNumberBox.add(doubleNumberBox));
        System.out.println("Double + Float: " + doubleNumberBox.add(floatNumberBox));
        
        System.out.println("\n=== 泛型方法測試 ===");
        
        // 測試 swap 方法
        String[] strings = {"Apple", "Banana", "Cherry"};
        System.out.println("交換前: " + Arrays.toString(strings));
        GenericMethods.swap(strings, 0, 2);
        System.out.println("交換後: " + Arrays.toString(strings));
        
        Integer[] integers = {3, 1, 4, 1, 5, 9};
        System.out.println("數字陣列: " + Arrays.toString(integers));
        System.out.println("最大值: " + GenericMethods.findMax(integers));
        
        // 測試 List 轉換
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<String> numberStrings = GenericMethods.mapList(numbers, 
            num -> "數字" + num);
        System.out.println("原始數字: " + numbers);
        System.out.println("轉換後: " + numberStrings);
        
        // 測試 List 過濾
        List<Integer> evenNumbers = GenericMethods.filterList(numbers, 
            num -> num % 2 == 0);
        System.out.println("偶數: " + evenNumbers);
        
        // 測試組合方法
        String combined = GenericMethods.combine("Hello", 123, 
            (str, num) -> str + " " + num);
        System.out.println("組合結果: " + combined);
        
        // 測試平均值計算
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5, 4.5, 5.5);
        double average = GenericMethods.calculateAverage(doubles);
        System.out.println("數字: " + doubles);
        System.out.println("平均值: " + average);
        
        System.out.println("\n=== 型態安全示範 ===");
        
        // 編譯期型態檢查
        Box<String> stringOnlyBox = new Box<>("只能放字串");
        // stringOnlyBox.set(123); // 編譯錯誤：型態不符
        
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        // integerList.add("string"); // 編譯錯誤：型態不符
        
        System.out.println("泛型提供編譯期型態安全檢查");
        System.out.println("避免 ClassCastException 執行期錯誤");
    }
}
```

!!! tip "泛型最佳實踐"
    - 使用有意義的型別參數名稱（T, E, K, V）
    - 優先使用有界泛型來限制型別
    - 避免使用原始型別（raw types）
    - 使用萬用字元處理不確定的型別

## 泛型類別與方法

### 泛型集合包裝器

```java
import java.util.*;
import java.util.function.*;

// 增強版泛型列表
public class EnhancedList<T> {
    private List<T> items;
    
    public EnhancedList() {
        this.items = new ArrayList<>();
    }
    
    public EnhancedList(Collection<T> collection) {
        this.items = new ArrayList<>(collection);
    }
    
    // 基本操作
    public void add(T item) {
        items.add(item);
    }
    
    public void addAll(T... items) {
        this.items.addAll(Arrays.asList(items));
    }
    
    public T get(int index) {
        return items.get(index);
    }
    
    public int size() {
        return items.size();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    // 泛型方法：轉換列表
    public <U> EnhancedList<U> map(Function<T, U> mapper) {
        EnhancedList<U> result = new EnhancedList<>();
        for (T item : items) {
            result.add(mapper.apply(item));
        }
        return result;
    }
    
    // 過濾列表
    public EnhancedList<T> filter(Predicate<T> predicate) {
        EnhancedList<T> result = new EnhancedList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
    
    // 歸約操作
    public <U> U reduce(U initial, BiFunction<U, T, U> accumulator) {
        U result = initial;
        for (T item : items) {
            result = accumulator.apply(result, item);
        }
        return result;
    }
    
    // 尋找第一個符合條件的元素
    public Optional<T> find(Predicate<T> predicate) {
        for (T item : items) {
            if (predicate.test(item)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
    
    // 檢查是否所有元素都符合條件
    public boolean allMatch(Predicate<T> predicate) {
        for (T item : items) {
            if (!predicate.test(item)) {
                return false;
            }
        }
        return true;
    }
    
    // 檢查是否有任何元素符合條件
    public boolean anyMatch(Predicate<T> predicate) {
        for (T item : items) {
            if (predicate.test(item)) {
                return true;
            }
        }
        return false;
    }
    
    // 對每個元素執行操作
    public void forEach(Consumer<T> action) {
        for (T item : items) {
            action.accept(item);
        }
    }
    
    // 排序（要求元素可比較）
    @SuppressWarnings("unchecked")
    public EnhancedList<T> sorted() {
        if (!items.isEmpty() && items.get(0) instanceof Comparable) {
            List<T> sortedItems = new ArrayList<>(items);
            sortedItems.sort((Comparator<T>) Comparator.naturalOrder());
            return new EnhancedList<>(sortedItems);
        }
        throw new IllegalStateException("元素必須實作 Comparable 介面");
    }
    
    // 使用自定義比較器排序
    public EnhancedList<T> sorted(Comparator<T> comparator) {
        List<T> sortedItems = new ArrayList<>(items);
        sortedItems.sort(comparator);
        return new EnhancedList<>(sortedItems);
    }
    
    // 轉為一般 List
    public List<T> toList() {
        return new ArrayList<>(items);
    }
    
    // 轉為陣列
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] array) {
        return items.toArray(array);
    }
    
    @Override
    public String toString() {
        return items.toString();
    }
}

// 泛型工具類別
public class GenericUtils {
    
    // 安全的型別轉換
    @SuppressWarnings("unchecked")
    public static <T> T safeCast(Object obj, Class<T> targetType) {
        if (targetType.isInstance(obj)) {
            return (T) obj;
        }
        return null;
    }
    
    // 建立指定大小的陣列
    @SuppressWarnings("unchecked")
    public static <T> T[] createArray(Class<T> componentType, int size) {
        return (T[]) java.lang.reflect.Array.newInstance(componentType, size);
    }
    
    // 合併多個列表
    @SafeVarargs
    public static <T> List<T> merge(List<T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<T> list : lists) {
            result.addAll(list);
        }
        return result;
    }
    
    // 建立不可變列表
    @SafeVarargs
    public static <T> List<T> immutableList(T... elements) {
        return Collections.unmodifiableList(Arrays.asList(elements));
    }
    
    // 分組操作
    public static <T, K> Map<K, List<T>> groupBy(List<T> list, Function<T, K> keyExtractor) {
        Map<K, List<T>> groups = new HashMap<>();
        for (T item : list) {
            K key = keyExtractor.apply(item);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
        }
        return groups;
    }
    
    // 建立 Pair
    public static <T, U> Pair<T, U> pair(T first, U second) {
        return new Pair<>(first, second);
    }
}
```

### 泛型使用進階範例

```java
import java.util.*;
import java.util.function.*;

public class AdvancedGenericsDemo {
    
    // 定義一些測試類別
    static class Person {
        private String name;
        private int age;
        private String department;
        
        public Person(String name, int age, String department) {
            this.name = name;
            this.age = age;
            this.department = department;
        }
        
        // Getters
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDepartment() { return department; }
        
        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d, dept='%s'}", 
                               name, age, department);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 進階泛型示範 ===");
        
        // 建立 EnhancedList
        EnhancedList<Integer> numbers = new EnhancedList<>();
        numbers.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        System.out.println("原始數字: " + numbers);
        
        // 測試 map 轉換
        EnhancedList<String> numberStrings = numbers.map(n -> "數字" + n);
        System.out.println("轉換為字串: " + numberStrings);
        
        EnhancedList<Integer> squares = numbers.map(n -> n * n);
        System.out.println("平方數: " + squares);
        
        // 測試 filter 過濾
        EnhancedList<Integer> evenNumbers = numbers.filter(n -> n % 2 == 0);
        EnhancedList<Integer> bigNumbers = numbers.filter(n -> n > 5);
        
        System.out.println("偶數: " + evenNumbers);
        System.out.println("大於5的數: " + bigNumbers);
        
        // 測試 reduce 歸約
        Integer sum = numbers.reduce(0, Integer::sum);
        Integer product = numbers.reduce(1, (a, b) -> a * b);
        String concatenated = numbers.reduce("", (str, num) -> str + num + " ");
        
        System.out.println("總和: " + sum);
        System.out.println("乘積: " + product);
        System.out.println("串聯: " + concatenated.trim());
        
        // 測試查找
        Optional<Integer> firstEven = numbers.find(n -> n % 2 == 0);
        Optional<Integer> firstNegative = numbers.find(n -> n < 0);
        
        System.out.println("第一個偶數: " + firstEven.orElse(-1));
        System.out.println("第一個負數: " + firstNegative.orElse(-1));
        
        // 測試條件檢查
        boolean allPositive = numbers.allMatch(n -> n > 0);
        boolean anyBigger = numbers.anyMatch(n -> n > 8);
        
        System.out.println("全部為正數: " + allPositive);
        System.out.println("有大於8的數: " + anyBigger);
        
        // 測試排序
        EnhancedList<Integer> shuffledNumbers = new EnhancedList<>();
        shuffledNumbers.addAll(5, 2, 8, 1, 9, 3);
        System.out.println("打亂的數字: " + shuffledNumbers);
        System.out.println("排序後: " + shuffledNumbers.sorted());
        System.out.println("逆序排序: " + shuffledNumbers.sorted(Comparator.reverseOrder()));
        
        System.out.println("\n=== 複雜物件處理 ===");
        
        // 建立人員列表
        EnhancedList<Person> people = new EnhancedList<>();
        people.addAll(
            new Person("張三", 25, "IT"),
            new Person("李四", 30, "HR"),
            new Person("王五", 22, "IT"),
            new Person("趙六", 35, "Finance"),
            new Person("周七", 28, "IT")
        );
        
        System.out.println("所有人員:");
        people.forEach(System.out::println);
        
        // 按部門過濾
        EnhancedList<Person> itPeople = people.filter(p -> "IT".equals(p.getDepartment()));
        System.out.println("\nIT 部門人員: " + itPeople);
        
        // 按年齡排序
        EnhancedList<Person> sortedByAge = people.sorted(
            Comparator.comparingInt(Person::getAge));
        System.out.println("\n按年齡排序:");
        sortedByAge.forEach(System.out::println);
        
        // 提取姓名
        EnhancedList<String> names = people.map(Person::getName);
        System.out.println("\n所有姓名: " + names);
        
        // 計算平均年齡
        double avgAge = people.reduce(0.0, (avg, person) -> avg + person.getAge()) / people.size();
        System.out.println("平均年齡: " + String.format("%.1f", avgAge));
        
        System.out.println("\n=== 工具類別測試 ===");
        
        // 測試分組
        Map<String, List<Person>> groupedByDept = GenericUtils.groupBy(
            people.toList(), Person::getDepartment);
        
        System.out.println("按部門分組:");
        groupedByDept.forEach((dept, peopleList) -> 
            System.out.println(dept + ": " + peopleList.size() + " 人"));
        
        // 測試合併列表
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);
        
        List<Integer> merged = GenericUtils.merge(list1, list2, list3);
        System.out.println("合併列表: " + merged);
        
        // 測試不可變列表
        List<String> immutable = GenericUtils.immutableList("Apple", "Banana", "Cherry");
        System.out.println("不可變列表: " + immutable);
        
        // 測試型別轉換
        Object obj = "Hello World";
        String str = GenericUtils.safeCast(obj, String.class);
        Integer num = GenericUtils.safeCast(obj, Integer.class);
        
        System.out.println("安全轉換為 String: " + str);
        System.out.println("安全轉換為 Integer: " + num);
        
        // 測試 Pair
        Pair<String, Integer> nameAge = GenericUtils.pair("Alice", 25);
        System.out.println("Pair: " + nameAge);
    }
}
```

!!! note "泛型進階概念"
    - **型別擦除**: 泛型資訊在編譯後被移除
    - **萬用字元**: `?`, `? extends T`, `? super T`
    - **PECS 原則**: Producer extends, Consumer super
    - **泛型限制**: 不能建立泛型陣列、不能用於靜態欄位

## 重點整理

1. **介面**：定義契約，支援多重實作，可包含預設方法和靜態方法
2. **預設方法**：解決介面演化問題，提供預設實作
3. **函數式介面**：只有一個抽象方法的介面，支援 Lambda 表達式
4. **泛型**：提供編譯期型別安全，避免型別轉換錯誤
5. **泛型方法**：在方法層級使用型別參數，更靈活的型別處理

!!! tip "最佳實踐建議"
    - 優先使用介面而非抽象類別
    - 善用預設方法避免破壞現有實作
    - 使用泛型提供型別安全
    - 避免使用原始型別
    - 合理使用有界泛型限制型別

## 練習建議

1. **基礎練習**
   - 設計形狀介面系統（Shape, Drawable, Movable）
   - 建立泛型堆疊（Stack）和佇列（Queue）類別
   - 實作函數式介面進行資料處理

2. **進階練習**
   - 設計完整的集合框架（List, Set, Map 介面）
   - 建立泛型樹狀結構（Tree<T>）
   - 實作觀察者模式使用介面和泛型

3. **綜合練習**
   - 建立通用的 Repository 介面系統
   - 設計事件處理系統使用函數式介面
   - 實作類型安全的建構者模式

下一章我們將學習陣列與迴圈的進階使用技巧！