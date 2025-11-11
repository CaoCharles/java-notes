# 巢狀類別與 Lambda 運算式

## 內部類別 (Inner Classes)

內部類別是定義在另一個類別內部的類別，可以存取外部類別的成員，包括私有成員。Java 提供四種巢狀類別：成員內部類別、區域內部類別、匿名內部類別和靜態巢狀類別。

### 成員內部類別 (Member Inner Class)

```java
public class OuterClass {
    private String outerField = "外部類別欄位";
    private static String staticField = "靜態欄位";
    
    // 成員內部類別
    public class InnerClass {
        private String innerField = "內部類別欄位";
        
        public void displayInfo() {
            // 可以直接存取外部類別的成員
            System.out.println("存取外部類別欄位: " + outerField);
            System.out.println("存取內部類別欄位: " + innerField);
            System.out.println("存取靜態欄位: " + staticField);
            
            // 存取外部類別的方法
            outerMethod();
        }
        
        public void accessOuterThis() {
            // 明確指定外部類別的 this
            System.out.println("外部類別的 toString: " + OuterClass.this.toString());
        }
        
        // 內部類別不能有靜態成員（除了編譯時常數）
        // static String staticInnerField = "錯誤"; // 編譯錯誤
        static final String CONSTANT = "可以有常數";
    }
    
    private void outerMethod() {
        System.out.println("外部類別的私有方法");
    }
    
    public void createInnerInstance() {
        // 外部類別建立內部類別實例
        InnerClass inner = new InnerClass();
        inner.displayInfo();
    }
    
    public static void main(String[] args) {
        System.out.println("=== 成員內部類別示範 ===");
        
        // 建立外部類別實例
        OuterClass outer = new OuterClass();
        outer.createInnerInstance();
        
        // 從外部建立內部類別實例
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.displayInfo();
        inner.accessOuterThis();
        
        // 錯誤的建立方式
        // OuterClass.InnerClass wrongInner = new OuterClass.InnerClass(); // 編譯錯誤
        
        System.out.println("內部類別常數: " + OuterClass.InnerClass.CONSTANT);
    }
}
```

### 區域內部類別 (Local Inner Class)

```java
public class LocalInnerClassExample {
    private String outerField = "外部欄位";
    
    public void methodWithLocalClass(final String parameter, String nonFinalParam) {
        String localVar = "區域變數";
        final String finalLocalVar = "final 區域變數";
        
        // 區域內部類別
        class LocalInnerClass {
            private String innerField = "區域內部類別欄位";
            
            public void display() {
                System.out.println("存取外部欄位: " + outerField);
                System.out.println("存取內部欄位: " + innerField);
                
                // 可以存取 final 或 effectively final 的區域變數
                System.out.println("存取 final 參數: " + parameter);
                System.out.println("存取 final 區域變數: " + finalLocalVar);
                
                // Java 8 前需要明確標記 final，Java 8+ 支援 effectively final
                System.out.println("存取 effectively final 變數: " + localVar);
                
                // 無法存取非 final 的區域變數（如果它被修改）
                // System.out.println(nonFinalParam); // 如果 nonFinalParam 在後面被修改，這裡會編譯錯誤
            }
            
            // 區域內部類別也不能有靜態成員
            // static String staticField; // 編譯錯誤
        }
        
        // 只能在定義的方法內使用區域內部類別
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.display();
        
        // 如果修改非 final 變數，上面存取它的程式碼就會編譯錯誤
        // nonFinalParam = "修改後的值"; // 這會讓上面的存取變成編譯錯誤
    }
    
    // 實際應用：事件處理
    public interface EventListener {
        void onEvent(String eventData);
    }
    
    public void registerEventHandler(String eventType) {
        String handlerName = "Handler for " + eventType;
        
        class CustomEventHandler implements EventListener {
            @Override
            public void onEvent(String eventData) {
                System.out.printf("[%s] 處理事件: %s%n", handlerName, eventData);
            }
        }
        
        // 註冊事件處理器
        EventListener handler = new CustomEventHandler();
        simulateEvent(handler, "測試事件");
    }
    
    private void simulateEvent(EventListener listener, String data) {
        listener.onEvent(data);
    }
    
    public static void main(String[] args) {
        System.out.println("=== 區域內部類別示範 ===");
        
        LocalInnerClassExample example = new LocalInnerClassExample();
        example.methodWithLocalClass("final參數", "非final參數");
        
        System.out.println("\n=== 事件處理應用 ===");
        example.registerEventHandler("UserClick");
        example.registerEventHandler("DataUpdate");
    }
}
```

### 匿名內部類別 (Anonymous Inner Class)

```java
import java.util.*;
import java.util.concurrent.*;

public class AnonymousInnerClassExample {
    
    // 抽象類別範例
    abstract static class Animal {
        protected String name;
        
        public Animal(String name) {
            this.name = name;
        }
        
        public abstract void makeSound();
        
        public void eat() {
            System.out.println(name + " 正在吃東西");
        }
    }
    
    // 介面範例
    interface Drawable {
        void draw();
        default void erase() {
            System.out.println("擦除繪圖");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 匿名內部類別基礎 ===");
        
        // 繼承抽象類別的匿名類別
        Animal dog = new Animal("小狗") {
            @Override
            public void makeSound() {
                System.out.println(name + " 汪汪叫");
            }
            
            // 可以添加新方法，但只能在這個匿名類別內部使用
            public void wagTail() {
                System.out.println(name + " 搖尾巴");
            }
            
            @Override
            public void eat() {
                super.eat();
                System.out.println(name + " 吃狗糧");
            }
        };
        
        dog.makeSound();
        dog.eat();
        // dog.wagTail(); // 編譯錯誤：無法存取匿名類別的新方法
        
        // 實作介面的匿名類別
        Drawable circle = new Drawable() {
            private double radius = 5.0;
            
            @Override
            public void draw() {
                System.out.println("繪製半徑為 " + radius + " 的圓形");
            }
            
            public double getArea() {
                return Math.PI * radius * radius;
            }
        };
        
        circle.draw();
        circle.erase();
        
        // 實際應用範例
        comparatorExample();
        eventHandlingExample();
        threadExample();
        timerExample();
    }
    
    // Comparator 應用
    public static void comparatorExample() {
        System.out.println("\n=== Comparator 匿名類別應用 ===");
        
        List<String> words = Arrays.asList("banana", "apple", "cherry", "date");
        System.out.println("原始順序: " + words);
        
        // 按長度排序的匿名 Comparator
        words.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int lengthComparison = Integer.compare(s1.length(), s2.length());
                if (lengthComparison != 0) {
                    return lengthComparison;
                }
                return s1.compareTo(s2); // 長度相同時按字母順序
            }
        });
        
        System.out.println("按長度排序: " + words);
        
        // 按字母倒序排序
        words.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        });
        
        System.out.println("按字母倒序: " + words);
    }
    
    // 事件處理應用
    public static void eventHandlingExample() {
        System.out.println("\n=== 事件處理匿名類別應用 ===");
        
        interface ButtonClickListener {
            void onClick(String buttonName);
        }
        
        class Button {
            private String name;
            private ButtonClickListener listener;
            
            public Button(String name) {
                this.name = name;
            }
            
            public void setClickListener(ButtonClickListener listener) {
                this.listener = listener;
            }
            
            public void click() {
                if (listener != null) {
                    listener.onClick(name);
                }
            }
        }
        
        Button saveButton = new Button("儲存按鈕");
        Button cancelButton = new Button("取消按鈕");
        
        // 為每個按鈕設定不同的點擊處理
        saveButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick(String buttonName) {
                System.out.println(buttonName + " 被點擊 - 執行儲存操作");
                System.out.println("正在儲存資料...");
            }
        });
        
        cancelButton.setClickListener(new ButtonClickListener() {
            @Override
            public void onClick(String buttonName) {
                System.out.println(buttonName + " 被點擊 - 執行取消操作");
                System.out.println("取消當前操作");
            }
        });
        
        // 模擬按鈕點擊
        saveButton.click();
        cancelButton.click();
    }
    
    // 執行緒應用
    public static void threadExample() {
        System.out.println("\n=== 執行緒匿名類別應用 ===");
        
        // 使用匿名 Runnable
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    System.out.println("執行緒 1 - 計數: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        
        // 另一個匿名 Runnable
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char c = 'A'; c <= 'C'; c++) {
                    System.out.println("執行緒 2 - 字母: " + c);
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("所有執行緒完成");
    }
    
    // Timer 應用
    public static void timerExample() {
        System.out.println("\n=== Timer 匿名類別應用 ===");
        
        Timer timer = new Timer();
        
        // 匿名 TimerTask
        timer.schedule(new TimerTask() {
            private int count = 0;
            
            @Override
            public void run() {
                count++;
                System.out.println("定時任務執行第 " + count + " 次");
                
                if (count >= 3) {
                    timer.cancel();
                    System.out.println("定時器已停止");
                }
            }
        }, 1000, 1000); // 延遲 1 秒，然後每 1 秒執行一次
        
        // 等待定時器完成
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

### 靜態巢狀類別 (Static Nested Class)

```java
public class StaticNestedClassExample {
    private static String staticOuterField = "靜態外部欄位";
    private String instanceOuterField = "實例外部欄位";
    
    // 靜態巢狀類別
    public static class StaticNestedClass {
        private String nestedField = "巢狀類別欄位";
        private static String staticNestedField = "靜態巢狀欄位";
        
        public void display() {
            // 可以存取外部類別的靜態成員
            System.out.println("存取外部靜態欄位: " + staticOuterField);
            
            // 無法直接存取外部類別的實例成員
            // System.out.println(instanceOuterField); // 編譯錯誤
            
            System.out.println("巢狀類別欄位: " + nestedField);
            System.out.println("靜態巢狀欄位: " + staticNestedField);
        }
        
        public void accessInstanceMember() {
            // 需要外部類別的實例才能存取實例成員
            StaticNestedClassExample outer = new StaticNestedClassExample();
            System.out.println("透過實例存取: " + outer.instanceOuterField);
        }
        
        // 靜態巢狀類別可以有靜態成員
        public static void staticMethod() {
            System.out.println("靜態巢狀類別的靜態方法");
            System.out.println("存取外部靜態欄位: " + staticOuterField);
            // System.out.println(staticNestedField); // 無法存取非靜態成員
        }
    }
    
    // 實際應用：Builder 模式
    public static class Person {
        private String name;
        private int age;
        private String email;
        private String phone;
        
        // 私有建構子，只能透過 Builder 建立
        private Person(Builder builder) {
            this.name = builder.name;
            this.age = builder.age;
            this.email = builder.email;
            this.phone = builder.phone;
        }
        
        // 靜態巢狀 Builder 類別
        public static class Builder {
            private String name;
            private int age;
            private String email;
            private String phone;
            
            public Builder(String name) {
                this.name = name; // name 是必要的
            }
            
            public Builder age(int age) {
                this.age = age;
                return this;
            }
            
            public Builder email(String email) {
                this.email = email;
                return this;
            }
            
            public Builder phone(String phone) {
                this.phone = phone;
                return this;
            }
            
            public Person build() {
                return new Person(this);
            }
        }
        
        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d, email='%s', phone='%s'}", 
                               name, age, email, phone);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 靜態巢狀類別示範 ===");
        
        // 建立靜態巢狀類別實例（不需要外部類別實例）
        StaticNestedClass nested = new StaticNestedClass();
        nested.display();
        nested.accessInstanceMember();
        
        // 呼叫靜態巢狀類別的靜態方法
        StaticNestedClass.staticMethod();
        
        // 存取靜態巢狀類別的靜態成員
        System.out.println("靜態巢狀欄位: " + StaticNestedClass.staticNestedField);
        
        // Builder 模式應用
        System.out.println("\n=== Builder 模式應用 ===");
        
        Person person1 = new Person.Builder("張小明")
                            .age(25)
                            .email("zhang@example.com")
                            .build();
        
        Person person2 = new Person.Builder("李小華")
                            .age(30)
                            .email("li@example.com")
                            .phone("0912-345-678")
                            .build();
        
        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);
    }
}
```

## Lambda 表達式基礎

Lambda 表達式是 Java 8 引入的重要特性，提供簡潔的方式來表示函數式介面的實例。

### Lambda 語法與基本用法

```java
import java.util.*;
import java.util.function.*;

public class LambdaBasics {
    public static void main(String[] args) {
        System.out.println("=== Lambda 基本語法 ===");
        
        // 傳統匿名內部類別
        Runnable traditional = new Runnable() {
            @Override
            public void run() {
                System.out.println("傳統匿名內部類別");
            }
        };
        
        // Lambda 表達式等價寫法
        Runnable lambda = () -> System.out.println("Lambda 表達式");
        
        traditional.run();
        lambda.run();
        
        // 不同形式的 Lambda 表達式
        lambdaSyntaxVariations();
        
        // 使用內建函數式介面
        builtInFunctionalInterfaces();
        
        // 集合操作中的 Lambda
        collectionLambdaExamples();
    }
    
    public static void lambdaSyntaxVariations() {
        System.out.println("\n=== Lambda 語法變化 ===");
        
        // 無參數 Lambda
        Runnable noParam = () -> System.out.println("無參數 Lambda");
        noParam.run();
        
        // 單參數 Lambda（可省略括號）
        Consumer<String> singleParam = s -> System.out.println("單參數: " + s);
        singleParam.accept("Hello");
        
        // 單參數 Lambda（使用括號）
        Consumer<String> singleParamWithParens = (s) -> System.out.println("括號: " + s);
        singleParamWithParens.accept("World");
        
        // 多參數 Lambda
        BinaryOperator<Integer> multiParam = (a, b) -> a + b;
        System.out.println("多參數結果: " + multiParam.apply(5, 3));
        
        // 多行 Lambda（需要大括號和 return）
        BinaryOperator<Integer> multiLine = (a, b) -> {
            int sum = a + b;
            System.out.println("計算 " + a + " + " + b + " = " + sum);
            return sum;
        };
        multiLine.apply(10, 20);
        
        // 明確指定參數型別
        Comparator<String> explicitType = (String s1, String s2) -> s1.length() - s2.length();
        System.out.println("長度比較: " + explicitType.compare("short", "longer"));
    }
    
    public static void builtInFunctionalInterfaces() {
        System.out.println("\n=== 內建函數式介面 ===");
        
        // Predicate<T> - 判斷條件
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("4 是偶數: " + isEven.test(4));
        System.out.println("5 是偶數: " + isEven.test(5));
        
        // Function<T, R> - 轉換函數
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println("字串長度: " + stringLength.apply("Lambda"));
        
        // Consumer<T> - 消費者（無返回值）
        Consumer<String> printer = s -> System.out.println("列印: " + s);
        printer.accept("Consumer 範例");
        
        // Supplier<T> - 供應者（無參數，有返回值）
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println("隨機數: " + randomValue.get());
        
        // BiFunction<T, U, R> - 雙參數函數
        BiFunction<Integer, Integer, String> calculator = (a, b) -> 
            String.format("%d + %d = %d", a, b, a + b);
        System.out.println(calculator.apply(15, 25));
        
        // UnaryOperator<T> - 一元操作符
        UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
        System.out.println("轉大寫: " + toUpperCase.apply("hello"));
        
        // BinaryOperator<T> - 二元操作符
        BinaryOperator<Integer> max = (a, b) -> Math.max(a, b);
        System.out.println("最大值: " + max.apply(10, 20));
    }
    
    public static void collectionLambdaExamples() {
        System.out.println("\n=== 集合中的 Lambda 應用 ===");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        
        // forEach
        System.out.println("所有名字:");
        names.forEach(name -> System.out.println("- " + name));
        
        // 排序
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort((a, b) -> a.length() - b.length());
        System.out.println("按長度排序: " + sortedNames);
        
        // removeIf
        List<String> filteredNames = new ArrayList<>(names);
        filteredNames.removeIf(name -> name.startsWith("A"));
        System.out.println("移除 A 開頭的名字: " + filteredNames);
        
        // Map 操作
        Map<String, Integer> nameLength = new HashMap<>();
        names.forEach(name -> nameLength.put(name, name.length()));
        
        System.out.println("名字長度對應:");
        nameLength.forEach((name, length) -> 
            System.out.printf("%-8s: %d%n", name, length));
        
        // 複雜的 Lambda 表達式
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        numbers.stream()
               .filter(n -> n % 2 == 0)           // 過濾偶數
               .map(n -> n * n)                   // 計算平方
               .forEach(n -> System.out.print(n + " "));
        
        System.out.println("\n偶數的平方");
    }
}
```

### 方法參考 (Method References)

```java
import java.util.*;
import java.util.function.*;

public class MethodReferences {
    
    // 靜態方法
    public static int staticMethod(int x, int y) {
        return x + y;
    }
    
    // 實例方法
    public int instanceMethod(int x, int y) {
        return x * y;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 方法參考 vs Lambda ===");
        
        staticMethodReferences();
        instanceMethodReferences();
        constructorReferences();
        arbitraryObjectMethodReferences();
    }
    
    // 靜態方法參考
    public static void staticMethodReferences() {
        System.out.println("\n=== 靜態方法參考 ===");
        
        // Lambda 表達式
        BinaryOperator<Integer> lambdaAdd = (x, y) -> Integer.sum(x, y);
        
        // 靜態方法參考
        BinaryOperator<Integer> methodRefAdd = Integer::sum;
        
        System.out.println("Lambda 結果: " + lambdaAdd.apply(5, 3));
        System.out.println("方法參考結果: " + methodRefAdd.apply(5, 3));
        
        // 更多靜態方法參考範例
        Function<String, Integer> parseInt = Integer::parseInt;
        System.out.println("解析字串: " + parseInt.apply("123"));
        
        Function<Double, Double> sqrt = Math::sqrt;
        System.out.println("平方根: " + sqrt.apply(16.0));
        
        // 自定義靜態方法參考
        BinaryOperator<Integer> customStatic = MethodReferences::staticMethod;
        System.out.println("自定義靜態方法: " + customStatic.apply(10, 20));
    }
    
    // 實例方法參考
    public static void instanceMethodReferences() {
        System.out.println("\n=== 實例方法參考 ===");
        
        MethodReferences obj = new MethodReferences();
        
        // 特定物件的實例方法參考
        BinaryOperator<Integer> instanceRef = obj::instanceMethod;
        System.out.println("實例方法參考: " + instanceRef.apply(4, 5));
        
        // 字串方法參考
        List<String> words = Arrays.asList("apple", "BANANA", "Cherry");
        
        // Lambda 版本
        words.stream()
             .map(s -> s.toLowerCase())
             .forEach(s -> System.out.println("Lambda: " + s));
        
        // 方法參考版本
        words.stream()
             .map(String::toLowerCase)
             .forEach(System.out::println);
    }
    
    // 建構子參考
    public static void constructorReferences() {
        System.out.println("\n=== 建構子參考 ===");
        
        // ArrayList 建構子參考
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> list = listSupplier.get();
        list.add("Item 1");
        System.out.println("建立的列表: " + list);
        
        // String 建構子參考
        Function<char[], String> stringConstructor = String::new;
        String str = stringConstructor.apply(new char[]{'H', 'e', 'l', 'l', 'o'});
        System.out.println("建構的字串: " + str);
        
        // 自定義類別建構子參考
        class Person {
            private String name;
            private int age;
            
            public Person(String name) {
                this.name = name;
                this.age = 0;
            }
            
            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
            
            @Override
            public String toString() {
                return String.format("Person{name='%s', age=%d}", name, age);
            }
        }
        
        // 單參數建構子參考
        Function<String, Person> personCreator1 = Person::new;
        Person person1 = personCreator1.apply("Alice");
        System.out.println("建立的人物1: " + person1);
        
        // 雙參數建構子參考
        BiFunction<String, Integer, Person> personCreator2 = Person::new;
        Person person2 = personCreator2.apply("Bob", 25);
        System.out.println("建立的人物2: " + person2);
    }
    
    // 任意物件的實例方法參考
    public static void arbitraryObjectMethodReferences() {
        System.out.println("\n=== 任意物件的實例方法參考 ===");
        
        List<String> words = Arrays.asList("banana", "apple", "cherry", "date");
        
        // 使用任意字串物件的 compareToIgnoreCase 方法
        words.sort(String::compareToIgnoreCase);
        System.out.println("忽略大小寫排序: " + words);
        
        // 其他範例
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // 取得長度
        names.stream()
             .mapToInt(String::length)
             .forEach(length -> System.out.println("長度: " + length));
        
        // 轉換為大寫
        names.stream()
             .map(String::toUpperCase)
             .forEach(name -> System.out.println("大寫: " + name));
        
        // 數字操作
        List<Integer> numbers = Arrays.asList(-3, -1, 2, -5, 4);
        numbers.stream()
               .map(Math::abs)  // 取絕對值
               .sorted(Integer::compareTo)  // 排序
               .forEach(System.out::println);
    }
}
```

## 函數式介面 (Functional Interface)

函數式介面是只有一個抽象方法的介面，可以使用 Lambda 表達式來實作。

### 自定義函數式介面

```java
import java.util.*;
import java.util.function.*;

// 自定義函數式介面
@FunctionalInterface
interface Calculator {
    double calculate(double a, double b);
    
    // 可以有預設方法
    default double square(double x) {
        return calculate(x, x);
    }
    
    // 可以有靜態方法
    static double pi() {
        return Math.PI;
    }
}

@FunctionalInterface
interface StringProcessor {
    String process(String input);
    
    // 組合方法
    default StringProcessor andThen(StringProcessor after) {
        return input -> after.process(this.process(input));
    }
    
    default StringProcessor compose(StringProcessor before) {
        return input -> this.process(before.process(input));
    }
}

@FunctionalInterface
interface Validator<T> {
    boolean validate(T value);
    
    default Validator<T> and(Validator<T> other) {
        return value -> this.validate(value) && other.validate(value);
    }
    
    default Validator<T> or(Validator<T> other) {
        return value -> this.validate(value) || other.validate(value);
    }
    
    default Validator<T> negate() {
        return value -> !this.validate(value);
    }
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        System.out.println("=== 自定義函數式介面 ===");
        
        calculatorExamples();
        stringProcessorExamples();
        validatorExamples();
        practicalExamples();
    }
    
    public static void calculatorExamples() {
        System.out.println("\n=== Calculator 範例 ===");
        
        // 不同的計算器實作
        Calculator adder = (a, b) -> a + b;
        Calculator multiplier = (a, b) -> a * b;
        Calculator power = Math::pow;
        
        double x = 5, y = 3;
        
        System.out.printf("%.1f + %.1f = %.1f%n", x, y, adder.calculate(x, y));
        System.out.printf("%.1f * %.1f = %.1f%n", x, y, multiplier.calculate(x, y));
        System.out.printf("%.1f ^ %.1f = %.1f%n", x, y, power.calculate(x, y));
        
        // 使用預設方法
        System.out.printf("%.1f 的平方 = %.1f%n", x, adder.square(x));
        
        // 使用靜態方法
        System.out.printf("π = %.6f%n", Calculator.pi());
        
        // 建立計算器映射
        Map<String, Calculator> calculators = new HashMap<>();
        calculators.put("add", (a, b) -> a + b);
        calculators.put("subtract", (a, b) -> a - b);
        calculators.put("multiply", (a, b) -> a * b);
        calculators.put("divide", (a, b) -> b != 0 ? a / b : Double.NaN);
        
        System.out.println("\n計算器映射範例:");
        calculators.forEach((name, calc) -> {
            double result = calc.calculate(10, 3);
            System.out.printf("%-8s: %.2f%n", name, result);
        });
    }
    
    public static void stringProcessorExamples() {
        System.out.println("\n=== StringProcessor 範例 ===");
        
        // 基本處理器
        StringProcessor toUpper = String::toUpperCase;
        StringProcessor toLower = String::toLowerCase;
        StringProcessor addPrefix = s -> ">>> " + s;
        StringProcessor addSuffix = s -> s + " <<<";
        
        String input = "Hello World";
        
        System.out.println("原始: " + input);
        System.out.println("大寫: " + toUpper.process(input));
        System.out.println("小寫: " + toLower.process(input));
        System.out.println("前綴: " + addPrefix.process(input));
        System.out.println("後綴: " + addSuffix.process(input));
        
        // 組合處理器
        StringProcessor combined = toUpper
            .andThen(addPrefix)
            .andThen(addSuffix);
        
        System.out.println("組合處理: " + combined.process(input));
        
        // 使用 compose
        StringProcessor composed = addSuffix
            .compose(addPrefix)
            .compose(toUpper);
        
        System.out.println("Compose 處理: " + composed.process(input));
        
        // 處理列表
        List<String> words = Arrays.asList("java", "lambda", "functional");
        System.out.println("\n處理列表:");
        words.stream()
             .map(combined::process)
             .forEach(System.out::println);
    }
    
    public static void validatorExamples() {
        System.out.println("\n=== Validator 範例 ===");
        
        // 不同的驗證器
        Validator<String> notNull = Objects::nonNull;
        Validator<String> notEmpty = s -> !s.isEmpty();
        Validator<String> minLength = s -> s.length() >= 3;
        Validator<String> maxLength = s -> s.length() <= 10;
        Validator<String> containsDigit = s -> s.matches(".*\\d.*");
        
        // 組合驗證器
        Validator<String> basicValidator = notNull.and(notEmpty);
        Validator<String> lengthValidator = minLength.and(maxLength);
        Validator<String> fullValidator = basicValidator
            .and(lengthValidator)
            .and(containsDigit);
        
        // 測試數據
        String[] testStrings = {
            null,
            "",
            "ab",
            "abc123",
            "verylongstring123",
            "short",
            "abc123def"
        };
        
        System.out.println("字串驗證結果:");
        for (String test : testStrings) {
            boolean isValid = fullValidator.validate(test);
            System.out.printf("%-15s: %s%n", 
                test == null ? "null" : "\"" + test + "\"", 
                isValid ? "有效" : "無效");
        }
        
        // 數字驗證器
        Validator<Integer> positive = n -> n > 0;
        Validator<Integer> lessThan100 = n -> n < 100;
        Validator<Integer> even = n -> n % 2 == 0;
        
        Validator<Integer> numberValidator = positive
            .and(lessThan100)
            .and(even);
        
        System.out.println("\n數字驗證結果:");
        int[] testNumbers = {-5, 0, 2, 15, 50, 100, 102};
        for (int num : testNumbers) {
            System.out.printf("%3d: %s%n", num, 
                numberValidator.validate(num) ? "有效" : "無效");
        }
    }
    
    public static void practicalExamples() {
        System.out.println("\n=== 實際應用範例 ===");
        
        // 事件處理系統
        eventHandlingSystem();
        
        // 資料處理管線
        dataProcessingPipeline();
    }
    
    // 事件處理系統
    @FunctionalInterface
    interface EventHandler<T> {
        void handle(T event);
        
        default EventHandler<T> andThen(EventHandler<T> after) {
            return event -> {
                this.handle(event);
                after.handle(event);
            };
        }
    }
    
    public static void eventHandlingSystem() {
        System.out.println("\n--- 事件處理系統 ---");
        
        // 不同的事件處理器
        EventHandler<String> logger = event -> System.out.println("[LOG] " + event);
        EventHandler<String> emailer = event -> System.out.println("[EMAIL] 發送通知: " + event);
        EventHandler<String> database = event -> System.out.println("[DB] 儲存事件: " + event);
        
        // 組合事件處理器
        EventHandler<String> fullHandler = logger
            .andThen(emailer)
            .andThen(database);
        
        // 觸發事件
        fullHandler.handle("用戶登入");
        fullHandler.handle("訂單創建");
    }
    
    // 資料處理管線
    @FunctionalInterface
    interface DataProcessor<T, R> {
        R process(T data);
        
        default <V> DataProcessor<T, V> andThen(DataProcessor<R, V> after) {
            return data -> after.process(this.process(data));
        }
    }
    
    public static void dataProcessingPipeline() {
        System.out.println("\n--- 資料處理管線 ---");
        
        // 處理步驟
        DataProcessor<String, String> trimmer = String::trim;
        DataProcessor<String, String> toUpper = String::toUpperCase;
        DataProcessor<String, String[]> splitter = s -> s.split("\\s+");
        DataProcessor<String[], Integer> counter = array -> array.length;
        
        // 建立處理管線
        DataProcessor<String, Integer> pipeline = trimmer
            .andThen(toUpper)
            .andThen(splitter)
            .andThen(counter);
        
        // 測試數據
        String[] testInputs = {
            "  hello world  ",
            "java lambda expressions are powerful",
            "   functional   programming   "
        };
        
        System.out.println("字數統計結果:");
        for (String input : testInputs) {
            int wordCount = pipeline.process(input);
            System.out.printf("%-35s -> %d 個字%n", "\"" + input + "\"", wordCount);
        }
    }
}
```

## Lambda 實際應用

### 集合處理進階應用

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class LambdaPracticalApplications {
    
    // 員工類別
    static class Employee {
        private String name;
        private String department;
        private double salary;
        private int age;
        
        public Employee(String name, String department, double salary, int age) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.age = age;
        }
        
        // Getters
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
        public int getAge() { return age; }
        
        @Override
        public String toString() {
            return String.format("%s(%s, $%.0f, %d歲)", 
                               name, department, salary, age);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Lambda 實際應用 ===");
        
        // 建立測試資料
        List<Employee> employees = Arrays.asList(
            new Employee("張小明", "IT", 75000, 28),
            new Employee("李小華", "HR", 55000, 32),
            new Employee("王小美", "IT", 82000, 25),
            new Employee("陳小強", "Finance", 68000, 35),
            new Employee("林小麗", "IT", 71000, 29),
            new Employee("趙小剛", "HR", 62000, 31),
            new Employee("周小文", "Finance", 73000, 33)
        );
        
        employeeAnalysis(employees);
        customFunctionApplications();
        eventDrivenProgramming();
        configurationSystem();
    }
    
    public static void employeeAnalysis(List<Employee> employees) {
        System.out.println("\n=== 員工資料分析 ===");
        
        // 基本統計
        System.out.println("所有員工:");
        employees.forEach(System.out::println);
        
        // 按部門分組
        Map<String, List<Employee>> byDepartment = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        
        System.out.println("\n按部門分組:");
        byDepartment.forEach((dept, empList) -> {
            System.out.printf("%s 部門 (%d 人):%n", dept, empList.size());
            empList.forEach(emp -> System.out.println("  " + emp));
        });
        
        // 薪資統計
        DoubleSummaryStatistics salaryStats = employees.stream()
            .mapToDouble(Employee::getSalary)
            .summaryStatistics();
        
        System.out.printf("\n薪資統計:%n");
        System.out.printf("平均薪資: $%.0f%n", salaryStats.getAverage());
        System.out.printf("最高薪資: $%.0f%n", salaryStats.getMax());
        System.out.printf("最低薪資: $%.0f%n", salaryStats.getMin());
        System.out.printf("總薪資: $%.0f%n", salaryStats.getSum());
        
        // 按薪資範圍分組
        Map<String, List<Employee>> salaryRanges = employees.stream()
            .collect(Collectors.groupingBy(emp -> {
                if (emp.getSalary() < 60000) return "低薪";
                else if (emp.getSalary() < 75000) return "中薪";
                else return "高薪";
            }));
        
        System.out.println("\n按薪資範圍分組:");
        salaryRanges.forEach((range, empList) -> {
            System.out.printf("%s (%d 人): ", range, empList.size());
            empList.stream()
                  .map(Employee::getName)
                  .forEach(name -> System.out.print(name + " "));
            System.out.println();
        });
        
        // 複雜查詢：IT 部門中薪資超過 70000 的員工
        List<Employee> highPaidIT = employees.stream()
            .filter(emp -> "IT".equals(emp.getDepartment()))
            .filter(emp -> emp.getSalary() > 70000)
            .sorted(Comparator.comparing(Employee::getSalary).reversed())
            .collect(Collectors.toList());
        
        System.out.println("\nIT 部門高薪員工:");
        highPaidIT.forEach(System.out::println);
        
        // 年齡與薪資的相關性分析
        Map<String, Double> avgSalaryByAgeGroup = employees.stream()
            .collect(Collectors.groupingBy(
                emp -> emp.getAge() < 30 ? "年輕" : "資深",
                Collectors.averagingDouble(Employee::getSalary)
            ));
        
        System.out.println("\n不同年齡組平均薪資:");
        avgSalaryByAgeGroup.forEach((group, avgSalary) -> 
            System.out.printf("%s員工平均薪資: $%.0f%n", group, avgSalary));
    }
    
    public static void customFunctionApplications() {
        System.out.println("\n=== 自定義函數應用 ===");
        
        // 數學運算函數庫
        Map<String, BinaryOperator<Double>> mathOperations = Map.of(
            "add", (a, b) -> a + b,
            "subtract", (a, b) -> a - b,
            "multiply", (a, b) -> a * b,
            "divide", (a, b) -> b != 0 ? a / b : Double.NaN,
            "power", Math::pow,
            "min", Math::min,
            "max", Math::max
        );
        
        // 測試數學運算
        double x = 12.0, y = 3.0;
        System.out.printf("對 %.1f 和 %.1f 進行運算:%n", x, y);
        mathOperations.forEach((op, func) -> {
            double result = func.apply(x, y);
            System.out.printf("%-8s: %.2f%n", op, result);
        });
        
        // 字串轉換器
        Map<String, Function<String, String>> transformers = Map.of(
            "upper", String::toUpperCase,
            "lower", String::toLowerCase,
            "reverse", s -> new StringBuilder(s).reverse().toString(),
            "capitalize", s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase(),
            "acronym", s -> Arrays.stream(s.split("\\s+"))
                                  .map(word -> word.substring(0, 1).toUpperCase())
                                  .collect(Collectors.joining())
        );
        
        String testString = "functional programming with java";
        System.out.println("\n字串轉換測試:");
        System.out.println("原始: " + testString);
        transformers.forEach((name, transformer) -> {
            String result = transformer.apply(testString);
            System.out.printf("%-10s: %s%n", name, result);
        });
        
        // 數據驗證器組合
        Predicate<String> notBlank = s -> s != null && !s.trim().isEmpty();
        Predicate<String> validEmail = s -> s.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        Predicate<String> validPhone = s -> s.matches("^\\d{4}-\\d{6}$");
        
        Map<String, Predicate<String>> validators = Map.of(
            "notBlank", notBlank,
            "email", notBlank.and(validEmail),
            "phone", notBlank.and(validPhone),
            "strongEmail", notBlank.and(validEmail).and(s -> s.length() >= 10)
        );
        
        String[] testInputs = {
            "",
            "test@example.com",
            "0912-345678",
            "short@x.y",
            "verylongemail@example.com"
        };
        
        System.out.println("\n數據驗證測試:");
        for (String input : testInputs) {
            System.out.printf("%-25s: ", "\"" + input + "\"");
            validators.forEach((name, validator) -> {
                boolean isValid = validator.test(input);
                System.out.printf("%s:%s ", name, isValid ? "✓" : "✗");
            });
            System.out.println();
        }
    }
    
    public static void eventDrivenProgramming() {
        System.out.println("\n=== 事件驅動程式設計 ===");
        
        // 事件類別
        class Event {
            private String type;
            private String data;
            private long timestamp;
            
            public Event(String type, String data) {
                this.type = type;
                this.data = data;
                this.timestamp = System.currentTimeMillis();
            }
            
            public String getType() { return type; }
            public String getData() { return data; }
            public long getTimestamp() { return timestamp; }
            
            @Override
            public String toString() {
                return String.format("Event{type='%s', data='%s', timestamp=%d}", 
                                   type, data, timestamp);
            }
        }
        
        // 事件處理器
        Map<String, Consumer<Event>> eventHandlers = new HashMap<>();
        
        // 註冊事件處理器
        eventHandlers.put("user_login", event -> {
            System.out.println("處理用戶登入: " + event.getData());
            System.out.println("記錄登入時間: " + new Date(event.getTimestamp()));
        });
        
        eventHandlers.put("order_created", event -> {
            System.out.println("處理新訂單: " + event.getData());
            System.out.println("發送確認郵件");
        });
        
        eventHandlers.put("payment_received", event -> {
            System.out.println("處理付款: " + event.getData());
            System.out.println("更新訂單狀態");
        });
        
        // 全域事件處理器
        Consumer<Event> globalHandler = event -> {
            System.out.printf("[%s] 全域處理: %s%n", 
                             new Date(event.getTimestamp()), event.getType());
        };
        
        // 事件分發器
        Consumer<Event> eventDispatcher = event -> {
            globalHandler.accept(event);
            Consumer<Event> specificHandler = eventHandlers.get(event.getType());
            if (specificHandler != null) {
                specificHandler.accept(event);
            } else {
                System.out.println("未知事件類型: " + event.getType());
            }
            System.out.println("---");
        };
        
        // 模擬事件
        List<Event> events = Arrays.asList(
            new Event("user_login", "user123"),
            new Event("order_created", "order456"),
            new Event("payment_received", "payment789"),
            new Event("unknown_event", "some_data")
        );
        
        events.forEach(eventDispatcher);
    }
    
    public static void configurationSystem() {
        System.out.println("\n=== 配置系統 ===");
        
        // 配置轉換器
        Map<String, Function<String, Object>> configParsers = Map.of(
            "int", Integer::parseInt,
            "double", Double::parseDouble,
            "boolean", Boolean::parseBoolean,
            "list", s -> Arrays.asList(s.split(",")),
            "map", s -> Arrays.stream(s.split(","))
                             .map(pair -> pair.split(":"))
                             .collect(Collectors.toMap(
                                 arr -> arr[0].trim(),
                                 arr -> arr[1].trim()
                             ))
        );
        
        // 配置數據
        Map<String, String> rawConfig = Map.of(
            "max_connections", "100",
            "timeout_seconds", "30.5",
            "enable_cache", "true",
            "allowed_domains", "example.com,test.org,demo.net",
            "database_settings", "host:localhost,port:5432,database:mydb"
        );
        
        // 配置類型映射
        Map<String, String> configTypes = Map.of(
            "max_connections", "int",
            "timeout_seconds", "double", 
            "enable_cache", "boolean",
            "allowed_domains", "list",
            "database_settings", "map"
        );
        
        System.out.println("原始配置:");
        rawConfig.forEach((key, value) -> 
            System.out.printf("%-20s: %s%n", key, value));
        
        System.out.println("\n解析後配置:");
        rawConfig.forEach((key, rawValue) -> {
            String type = configTypes.get(key);
            if (type != null) {
                Function<String, Object> parser = configParsers.get(type);
                Object parsedValue = parser.apply(rawValue);
                System.out.printf("%-20s: %-30s [%s]%n", 
                                key, parsedValue.toString(), 
                                parsedValue.getClass().getSimpleName());
            }
        });
        
        // 配置驗證器
        Map<String, Predicate<Object>> configValidators = Map.of(
            "max_connections", value -> (Integer) value > 0 && (Integer) value <= 1000,
            "timeout_seconds", value -> (Double) value > 0.0,
            "enable_cache", value -> value instanceof Boolean,
            "allowed_domains", value -> ((List<?>) value).size() > 0
        );
        
        System.out.println("\n配置驗證:");
        rawConfig.forEach((key, rawValue) -> {
            String type = configTypes.get(key);
            if (type != null && configValidators.containsKey(key)) {
                try {
                    Object parsedValue = configParsers.get(type).apply(rawValue);
                    boolean isValid = configValidators.get(key).test(parsedValue);
                    System.out.printf("%-20s: %s%n", key, isValid ? "有效" : "無效");
                } catch (Exception e) {
                    System.out.printf("%-20s: 解析失敗%n", key);
                }
            }
        });
    }
}
```

## 重點整理

1. **內部類別**：提供邏輯分組和封裝，可存取外部類別的私有成員
2. **匿名內部類別**：適合一次性使用的簡單實作，特別是事件處理
3. **靜態巢狀類別**：不依賴外部類別實例，常用於 Builder 模式
4. **Lambda 表達式**：簡化函數式介面的實作，讓程式碼更簡潔
5. **方法參考**：更簡潔的 Lambda 表達式，提高可讀性
6. **函數式介面**：支援 Lambda 表達式的基礎，可組合和擴展

!!! tip "使用建議"
    - **內部類別**: 需要存取外部類別成員時使用
    - **匿名類別**: 簡單一次性實作，但 Lambda 更簡潔
    - **Lambda**: 函數式介面實作的首選
    - **方法參考**: 當 Lambda 只是呼叫現有方法時使用

## 練習建議

1. **基礎練習**
   - 實作事件監聽器系統（內部類別、匿名類別、Lambda 三種方式）
   - 建立函數式的數據處理管線
   - 設計可組合的驗證器系統

2. **進階練習**
   - 實作函數式的狀態機
   - 建立可配置的資料轉換器
   - 設計事件驅動的架構

3. **綜合應用**
   - 建立函數式的 Web 路由系統
   - 實作響應式程式設計模式
   - 設計函數式的資料庫查詢 DSL

下一章我們將深入學習 Stream API，這是 Java 8 函數式程式設計的核心特性！