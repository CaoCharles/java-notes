# 集合(Collections)

## Collection 框架介紹

Java Collection Framework 是一套設計良好的架構，用於儲存和操作物件集合。它提供了統一的介面和多種實作，讓開發者能夠有效率地處理不同類型的資料結構。

### Collection 框架架構

```
Collection (介面)
├── List (介面) - 有序集合，允許重複
│   ├── ArrayList - 動態陣列實作
│   ├── LinkedList - 雙向鏈結串列實作
│   ├── Vector - 同步化的動態陣列
│   └── Stack - 堆疊實作
├── Set (介面) - 無序集合，不允許重複
│   ├── HashSet - 雜湊表實作
│   ├── LinkedHashSet - 保持插入順序的 HashSet
│   └── TreeSet - 排序樹實作
└── Queue (介面) - 佇列介面
    ├── PriorityQueue - 優先佇列
    ├── ArrayDeque - 雙端佇列
    └── LinkedList - 也實作了 Queue 介面

Map (介面) - 鍵值對集合
├── HashMap - 雜湊表實作
├── LinkedHashMap - 保持插入順序的 HashMap
├── TreeMap - 排序樹實作
└── Hashtable - 同步化的雜湊表
```

### Collection 基本介面

```java
import java.util.*;

public class CollectionBasics {
    public static void main(String[] args) {
        System.out.println("=== Collection 基本操作 ===");
        
        // Collection 是所有集合的根介面
        Collection<String> collection = new ArrayList<>();
        
        // 基本操作
        collection.add("Apple");
        collection.add("Banana");
        collection.add("Cherry");
        
        System.out.println("集合大小: " + collection.size());
        System.out.println("是否為空: " + collection.isEmpty());
        System.out.println("包含 Apple: " + collection.contains("Apple"));
        
        // 遍歷集合的方法
        System.out.println("\n=== 遍歷方法 ===");
        
        // 1. for-each 迴圈
        System.out.println("for-each 遍歷:");
        for (String item : collection) {
            System.out.println("- " + item);
        }
        
        // 2. Iterator
        System.out.println("\nIterator 遍歷:");
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println("- " + iterator.next());
        }
        
        // 3. Stream (Java 8+)
        System.out.println("\nStream 遍歷:");
        collection.stream()
                 .forEach(item -> System.out.println("- " + item));
        
        // 批量操作
        Collection<String> moreItems = Arrays.asList("Date", "Elderberry");
        collection.addAll(moreItems);
        System.out.println("\n添加更多項目: " + collection);
        
        collection.removeAll(Arrays.asList("Apple", "Date"));
        System.out.println("移除部分項目: " + collection);
        
        // 轉換為陣列
        String[] array = collection.toArray(new String[0]);
        System.out.println("轉為陣列: " + Arrays.toString(array));
        
        // 清空集合
        collection.clear();
        System.out.println("清空後大小: " + collection.size());
    }
}
```

## List 介面與實作

List 是有序集合，允許重複元素，並提供基於索引的存取。

### ArrayList 實作

```java
import java.util.*;

public class ArrayListExample {
    public static void main(String[] args) {
        System.out.println("=== ArrayList 基本使用 ===");
        
        // 建立 ArrayList
        List<Integer> numbers = new ArrayList<>();
        
        // 添加元素
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(1, 15); // 在索引 1 插入 15
        
        System.out.println("初始列表: " + numbers);
        
        // 存取元素
        System.out.println("第一個元素: " + numbers.get(0));
        System.out.println("最後一個元素: " + numbers.get(numbers.size() - 1));
        
        // 修改元素
        numbers.set(2, 25);
        System.out.println("修改索引 2: " + numbers);
        
        // 搜尋元素
        int index = numbers.indexOf(20);
        System.out.println("20 的索引: " + index);
        
        // 移除元素
        numbers.remove(Integer.valueOf(15)); // 移除值為 15 的元素
        numbers.remove(0); // 移除索引 0 的元素
        System.out.println("移除後: " + numbers);
        
        // 子列表操作
        List<Integer> subList = numbers.subList(0, 2);
        System.out.println("子列表: " + subList);
        
        // 排序
        numbers.addAll(Arrays.asList(5, 35, 25));
        System.out.println("添加元素後: " + numbers);
        Collections.sort(numbers);
        System.out.println("排序後: " + numbers);
        
        // 反轉
        Collections.reverse(numbers);
        System.out.println("反轉後: " + numbers);
        
        // 使用 Comparator 排序
        numbers.sort(Comparator.reverseOrder());
        System.out.println("降序排序: " + numbers);
        
        // 效能測試
        performanceTest();
    }
    
    // ArrayList 效能測試
    public static void performanceTest() {
        System.out.println("\n=== ArrayList 效能測試 ===");
        
        List<Integer> list = new ArrayList<>();
        
        // 添加效能測試
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("添加 100,000 個元素時間: " + (endTime - startTime) + " ms");
        
        // 隨機存取效能
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int randomIndex = (int) (Math.random() * list.size());
            list.get(randomIndex);
        }
        endTime = System.currentTimeMillis();
        System.out.println("隨機存取 10,000 次時間: " + (endTime - startTime) + " ms");
    }
}
```

### LinkedList 實作

```java
import java.util.*;

public class LinkedListExample {
    public static void main(String[] args) {
        System.out.println("=== LinkedList 基本使用 ===");
        
        // LinkedList 實作了 List 和 Deque 介面
        LinkedList<String> linkedList = new LinkedList<>();
        
        // 基本列表操作
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");
        System.out.println("初始列表: " + linkedList);
        
        // LinkedList 特有的頭尾操作
        linkedList.addFirst("New First");
        linkedList.addLast("New Last");
        System.out.println("頭尾添加後: " + linkedList);
        
        // 獲取頭尾元素
        System.out.println("第一個元素: " + linkedList.getFirst());
        System.out.println("最後一個元素: " + linkedList.getLast());
        
        // 移除頭尾元素
        String first = linkedList.removeFirst();
        String last = linkedList.removeLast();
        System.out.println("移除的第一個: " + first);
        System.out.println("移除的最後一個: " + last);
        System.out.println("移除後: " + linkedList);
        
        // 作為佇列使用 (Queue 介面)
        System.out.println("\n=== 作為佇列使用 ===");
        Queue<Integer> queue = new LinkedList<>();
        
        // 入佇列
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println("佇列: " + queue);
        
        // 出佇列
        while (!queue.isEmpty()) {
            System.out.println("出佇列: " + queue.poll());
        }
        
        // 作為堆疊使用
        System.out.println("\n=== 作為堆疊使用 ===");
        Deque<String> stack = new LinkedList<>();
        
        // 入堆疊
        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println("堆疊: " + stack);
        
        // 出堆疊
        while (!stack.isEmpty()) {
            System.out.println("出堆疊: " + stack.pop());
        }
        
        // ArrayList vs LinkedList 效能比較
        performanceComparison();
    }
    
    public static void performanceComparison() {
        System.out.println("\n=== ArrayList vs LinkedList 效能比較 ===");
        
        int size = 50000;
        
        // ArrayList 測試
        List<Integer> arrayList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arrayList.add(0, i); // 在開頭插入
        }
        long arrayListTime = System.currentTimeMillis() - startTime;
        
        // LinkedList 測試
        List<Integer> linkedList = new LinkedList<>();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            linkedList.add(0, i); // 在開頭插入
        }
        long linkedListTime = System.currentTimeMillis() - startTime;
        
        System.out.printf("在開頭插入 %d 個元素:%n", size);
        System.out.printf("ArrayList: %d ms%n", arrayListTime);
        System.out.printf("LinkedList: %d ms%n", linkedListTime);
        
        // 隨機存取測試
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int randomIndex = (int) (Math.random() * arrayList.size());
            arrayList.get(randomIndex);
        }
        long arrayListRandomAccess = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int randomIndex = (int) (Math.random() * linkedList.size());
            linkedList.get(randomIndex);
        }
        long linkedListRandomAccess = System.currentTimeMillis() - startTime;
        
        System.out.println("\n隨機存取 10,000 次:");
        System.out.printf("ArrayList: %d ms%n", arrayListRandomAccess);
        System.out.printf("LinkedList: %d ms%n", linkedListRandomAccess);
    }
}
```

### List 實際應用範例

```java
import java.util.*;
import java.util.function.Predicate;

public class ListApplications {
    
    // 學生類別
    static class Student {
        private String name;
        private int age;
        private double gpa;
        
        public Student(String name, int age, double gpa) {
            this.name = name;
            this.age = age;
            this.gpa = gpa;
        }
        
        // Getters
        public String getName() { return name; }
        public int getAge() { return age; }
        public double getGpa() { return gpa; }
        
        @Override
        public String toString() {
            return String.format("Student{name='%s', age=%d, gpa=%.2f}", name, age, gpa);
        }
    }
    
    // 學生管理系統
    public static void studentManagementSystem() {
        System.out.println("=== 學生管理系統 ===");
        
        List<Student> students = new ArrayList<>();
        students.add(new Student("張小明", 20, 3.8));
        students.add(new Student("李小華", 19, 3.6));
        students.add(new Student("王小美", 21, 3.9));
        students.add(new Student("陳小強", 20, 3.4));
        students.add(new Student("林小麗", 22, 3.7));
        
        System.out.println("所有學生:");
        printStudents(students);
        
        // 按 GPA 排序
        students.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        System.out.println("\n按 GPA 降序排序:");
        printStudents(students);
        
        // 篩選 GPA > 3.7 的學生
        List<Student> topStudents = filterStudents(students, s -> s.getGpa() > 3.7);
        System.out.println("\nGPA > 3.7 的學生:");
        printStudents(topStudents);
        
        // 計算平均 GPA
        double averageGpa = students.stream()
                                   .mapToDouble(Student::getGpa)
                                   .average()
                                   .orElse(0.0);
        System.out.printf("\n平均 GPA: %.2f%n", averageGpa);
    }
    
    private static void printStudents(List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, students.get(i));
        }
    }
    
    private static List<Student> filterStudents(List<Student> students, Predicate<Student> predicate) {
        List<Student> filtered = new ArrayList<>();
        for (Student student : students) {
            if (predicate.test(student)) {
                filtered.add(student);
            }
        }
        return filtered;
    }
    
    // 購物車系統
    public static void shoppingCartSystem() {
        System.out.println("\n=== 購物車系統 ===");
        
        // 商品類別
        class Product {
            private String name;
            private double price;
            
            public Product(String name, double price) {
                this.name = name;
                this.price = price;
            }
            
            public String getName() { return name; }
            public double getPrice() { return price; }
            
            @Override
            public String toString() {
                return String.format("%s ($%.2f)", name, price);
            }
        }
        
        // 購物車項目
        class CartItem {
            private Product product;
            private int quantity;
            
            public CartItem(Product product, int quantity) {
                this.product = product;
                this.quantity = quantity;
            }
            
            public Product getProduct() { return product; }
            public int getQuantity() { return quantity; }
            public void setQuantity(int quantity) { this.quantity = quantity; }
            
            public double getTotalPrice() {
                return product.getPrice() * quantity;
            }
            
            @Override
            public String toString() {
                return String.format("%s x %d = $%.2f", 
                                   product.getName(), quantity, getTotalPrice());
            }
        }
        
        // 購物車
        List<CartItem> cart = new ArrayList<>();
        
        // 添加商品
        cart.add(new CartItem(new Product("筆記本電腦", 25000), 1));
        cart.add(new CartItem(new Product("滑鼠", 500), 2));
        cart.add(new CartItem(new Product("鍵盤", 1200), 1));
        cart.add(new CartItem(new Product("螢幕", 8000), 1));
        
        System.out.println("購物車內容:");
        cart.forEach(System.out::println);
        
        // 計算總金額
        double totalAmount = cart.stream()
                                .mapToDouble(CartItem::getTotalPrice)
                                .sum();
        
        System.out.printf("總金額: $%.2f%n", totalAmount);
        
        // 移除價格最低的商品
        CartItem cheapestItem = cart.stream()
                                   .min(Comparator.comparing(item -> item.getProduct().getPrice()))
                                   .orElse(null);
        
        if (cheapestItem != null) {
            cart.remove(cheapestItem);
            System.out.println("移除最便宜商品: " + cheapestItem.getProduct().getName());
            System.out.println("更新後購物車:");
            cart.forEach(System.out::println);
        }
    }
    
    public static void main(String[] args) {
        studentManagementSystem();
        shoppingCartSystem();
        
        // List 的其他實用技巧
        System.out.println("\n=== List 實用技巧 ===");
        
        // 建立不可變列表
        List<String> immutableList = Collections.unmodifiableList(
            Arrays.asList("A", "B", "C"));
        System.out.println("不可變列表: " + immutableList);
        
        // 建立重複元素的列表
        List<String> repeatedList = Collections.nCopies(5, "Hello");
        System.out.println("重複列表: " + repeatedList);
        
        // 列表的二元搜尋 (需要排序)
        List<Integer> sortedNumbers = Arrays.asList(1, 3, 5, 7, 9, 11, 13);
        int index = Collections.binarySearch(sortedNumbers, 7);
        System.out.println("7 的索引位置: " + index);
        
        // 隨機打亂列表
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.shuffle(numbers);
        System.out.println("打亂後的列表: " + numbers);
    }
}
```

!!! tip "List 選擇建議"
    - **ArrayList**: 需要頻繁隨機存取、較少插入刪除操作
    - **LinkedList**: 頻繁在頭尾插入刪除、實作佇列或堆疊
    - **Vector**: 需要執行緒安全（但建議使用 Collections.synchronizedList）

## Set 介面與實作

Set 是不允許重複元素的集合，主要用於確保元素的唯一性。

### HashSet 實作

```java
import java.util.*;

public class HashSetExample {
    public static void main(String[] args) {
        System.out.println("=== HashSet 基本使用 ===");
        
        Set<String> fruits = new HashSet<>();
        
        // 添加元素
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Apple"); // 重複元素不會被添加
        
        System.out.println("水果集合: " + fruits);
        System.out.println("集合大小: " + fruits.size());
        
        // 檢查元素是否存在
        System.out.println("包含 Apple: " + fruits.contains("Apple"));
        System.out.println("包含 Orange: " + fruits.contains("Orange"));
        
        // 遍歷集合
        System.out.println("\n遍歷集合:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }
        
        // 集合運算
        Set<String> moreFruits = new HashSet<>(Arrays.asList("Cherry", "Date", "Elderberry"));
        
        // 聯集 (Union)
        Set<String> union = new HashSet<>(fruits);
        union.addAll(moreFruits);
        System.out.println("聯集: " + union);
        
        // 交集 (Intersection)
        Set<String> intersection = new HashSet<>(fruits);
        intersection.retainAll(moreFruits);
        System.out.println("交集: " + intersection);
        
        // 差集 (Difference)
        Set<String> difference = new HashSet<>(fruits);
        difference.removeAll(moreFruits);
        System.out.println("差集: " + difference);
        
        // 移除元素
        fruits.remove("Banana");
        System.out.println("移除 Banana 後: " + fruits);
        
        // 效能測試
        performanceTest();
    }
    
    public static void performanceTest() {
        System.out.println("\n=== HashSet 效能測試 ===");
        
        Set<Integer> hashSet = new HashSet<>();
        List<Integer> arrayList = new ArrayList<>();
        
        int size = 100000;
        
        // 添加元素效能
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            hashSet.add(i);
        }
        long hashSetAddTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        long arrayListAddTime = System.currentTimeMillis() - startTime;
        
        System.out.printf("添加 %d 個元素:%n", size);
        System.out.printf("HashSet: %d ms%n", hashSetAddTime);
        System.out.printf("ArrayList: %d ms%n", arrayListAddTime);
        
        // 搜尋效能
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int randomValue = (int) (Math.random() * size);
            hashSet.contains(randomValue);
        }
        long hashSetSearchTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int randomValue = (int) (Math.random() * size);
            arrayList.contains(randomValue);
        }
        long arrayListSearchTime = System.currentTimeMillis() - startTime;
        
        System.out.println("\n搜尋 10,000 次:");
        System.out.printf("HashSet: %d ms%n", hashSetSearchTime);
        System.out.printf("ArrayList: %d ms%n", arrayListSearchTime);
    }
}
```

### LinkedHashSet 和 TreeSet

```java
import java.util.*;

public class SetVariations {
    public static void main(String[] args) {
        System.out.println("=== Set 的不同實作比較 ===");
        
        // HashSet - 無序、最快
        Set<Integer> hashSet = new HashSet<>();
        // LinkedHashSet - 維持插入順序
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        // TreeSet - 自動排序
        Set<Integer> treeSet = new TreeSet<>();
        
        // 添加相同的元素
        int[] numbers = {5, 2, 8, 1, 9, 3};
        
        for (int num : numbers) {
            hashSet.add(num);
            linkedHashSet.add(num);
            treeSet.add(num);
        }
        
        System.out.println("原始順序: " + Arrays.toString(numbers));
        System.out.println("HashSet (無序): " + hashSet);
        System.out.println("LinkedHashSet (插入順序): " + linkedHashSet);
        System.out.println("TreeSet (自然排序): " + treeSet);
        
        // TreeSet 的特殊操作
        System.out.println("\n=== TreeSet 特殊操作 ===");
        
        TreeSet<String> sortedWords = new TreeSet<>();
        sortedWords.addAll(Arrays.asList("banana", "apple", "cherry", "date"));
        
        System.out.println("排序的單詞: " + sortedWords);
        System.out.println("第一個: " + sortedWords.first());
        System.out.println("最後一個: " + sortedWords.last());
        System.out.println("小於 'cherry': " + sortedWords.headSet("cherry"));
        System.out.println("大於等於 'cherry': " + sortedWords.tailSet("cherry"));
        System.out.println("'banana' 到 'date': " + sortedWords.subSet("banana", "date"));
        
        // 自定義排序的 TreeSet
        System.out.println("\n=== 自定義排序 TreeSet ===");
        
        // 按長度排序的字串集合
        TreeSet<String> lengthSorted = new TreeSet<>(Comparator.comparing(String::length)
                                                               .thenComparing(String::compareTo));
        lengthSorted.addAll(Arrays.asList("a", "bb", "ccc", "dd", "eeee", "f"));
        System.out.println("按長度排序: " + lengthSorted);
        
        // 自定義物件的排序
        customObjectSorting();
    }
    
    public static void customObjectSorting() {
        System.out.println("\n=== 自定義物件排序 ===");
        
        // 學生類別實作 Comparable
        class Student implements Comparable<Student> {
            private String name;
            private double gpa;
            
            public Student(String name, double gpa) {
                this.name = name;
                this.gpa = gpa;
            }
            
            public String getName() { return name; }
            public double getGpa() { return gpa; }
            
            @Override
            public int compareTo(Student other) {
                // 按 GPA 降序排序
                return Double.compare(other.gpa, this.gpa);
            }
            
            @Override
            public String toString() {
                return String.format("%s(%.2f)", name, gpa);
            }
        }
        
        TreeSet<Student> students = new TreeSet<>();
        students.add(new Student("Alice", 3.8));
        students.add(new Student("Bob", 3.6));
        students.add(new Student("Charlie", 3.9));
        students.add(new Student("David", 3.7));
        
        System.out.println("按 GPA 排序的學生:");
        for (Student student : students) {
            System.out.println("- " + student);
        }
        
        // 使用不同的 Comparator
        TreeSet<Student> nameSort = new TreeSet<>(Comparator.comparing(Student::getName));
        nameSort.addAll(students);
        
        System.out.println("\n按姓名排序的學生:");
        for (Student student : nameSort) {
            System.out.println("- " + student);
        }
    }
}
```

### Set 實際應用

```java
import java.util.*;

public class SetApplications {
    public static void main(String[] args) {
        // 去重複應用
        duplicateRemoval();
        
        // 權限管理系統
        permissionSystem();
        
        // 標籤系統
        tagSystem();
    }
    
    // 去除重複元素
    public static void duplicateRemoval() {
        System.out.println("=== 去除重複元素 ===");
        
        List<Integer> numbersWithDuplicates = Arrays.asList(
            1, 2, 3, 2, 4, 5, 3, 6, 1, 7, 8, 5);
        
        System.out.println("原始列表: " + numbersWithDuplicates);
        
        // 使用 HashSet 去重複（不保持順序）
        Set<Integer> uniqueNumbers = new HashSet<>(numbersWithDuplicates);
        System.out.println("去重複後 (HashSet): " + uniqueNumbers);
        
        // 使用 LinkedHashSet 去重複（保持插入順序）
        Set<Integer> uniqueOrderedNumbers = new LinkedHashSet<>(numbersWithDuplicates);
        System.out.println("去重複後 (LinkedHashSet): " + uniqueOrderedNumbers);
        
        // 轉回 List
        List<Integer> uniqueList = new ArrayList<>(uniqueOrderedNumbers);
        System.out.println("轉回 List: " + uniqueList);
    }
    
    // 權限管理系統
    public static void permissionSystem() {
        System.out.println("\n=== 權限管理系統 ===");
        
        // 定義權限
        enum Permission {
            READ, WRITE, DELETE, ADMIN, MODIFY, EXECUTE
        }
        
        // 用戶權限
        Map<String, Set<Permission>> userPermissions = new HashMap<>();
        
        userPermissions.put("admin", EnumSet.of(Permission.READ, Permission.WRITE, 
                                               Permission.DELETE, Permission.ADMIN));
        userPermissions.put("editor", EnumSet.of(Permission.READ, Permission.WRITE, 
                                                 Permission.MODIFY));
        userPermissions.put("viewer", EnumSet.of(Permission.READ));
        userPermissions.put("manager", EnumSet.of(Permission.READ, Permission.WRITE, 
                                                  Permission.DELETE));
        
        // 顯示用戶權限
        for (Map.Entry<String, Set<Permission>> entry : userPermissions.entrySet()) {
            System.out.println(entry.getKey() + " 權限: " + entry.getValue());
        }
        
        // 檢查用戶是否有特定權限
        String user = "editor";
        Permission requiredPermission = Permission.DELETE;
        
        if (userPermissions.get(user).contains(requiredPermission)) {
            System.out.println(user + " 有 " + requiredPermission + " 權限");
        } else {
            System.out.println(user + " 沒有 " + requiredPermission + " 權限");
        }
        
        // 計算兩個用戶的共同權限
        Set<Permission> adminPerms = userPermissions.get("admin");
        Set<Permission> editorPerms = userPermissions.get("editor");
        
        Set<Permission> commonPermissions = new HashSet<>(adminPerms);
        commonPermissions.retainAll(editorPerms);
        
        System.out.println("admin 和 editor 的共同權限: " + commonPermissions);
    }
    
    // 標籤系統
    public static void tagSystem() {
        System.out.println("\n=== 文章標籤系統 ===");
        
        // 文章類別
        class Article {
            private String title;
            private Set<String> tags;
            
            public Article(String title, String... tags) {
                this.title = title;
                this.tags = new HashSet<>(Arrays.asList(tags));
            }
            
            public String getTitle() { return title; }
            public Set<String> getTags() { return tags; }
            
            @Override
            public String toString() {
                return String.format("《%s》標籤: %s", title, tags);
            }
        }
        
        // 建立文章
        List<Article> articles = new ArrayList<>();
        articles.add(new Article("Java 入門教學", "程式設計", "Java", "初學者"));
        articles.add(new Article("Spring Boot 實戰", "程式設計", "Java", "Spring", "後端"));
        articles.add(new Article("React 前端開發", "程式設計", "前端", "JavaScript", "React"));
        articles.add(new Article("資料庫設計原理", "資料庫", "設計", "SQL"));
        articles.add(new Article("機器學習入門", "AI", "機器學習", "Python"));
        
        // 顯示所有文章
        System.out.println("所有文章:");
        articles.forEach(System.out::println);
        
        // 收集所有標籤
        Set<String> allTags = new HashSet<>();
        for (Article article : articles) {
            allTags.addAll(article.getTags());
        }
        System.out.println("\n所有標籤: " + allTags);
        
        // 按標籤搜尋文章
        String searchTag = "程式設計";
        System.out.println("\n包含標籤 '" + searchTag + "' 的文章:");
        for (Article article : articles) {
            if (article.getTags().contains(searchTag)) {
                System.out.println("- " + article.getTitle());
            }
        }
        
        // 找出包含多個標籤的文章
        Set<String> requiredTags = new HashSet<>(Arrays.asList("程式設計", "Java"));
        System.out.println("\n包含標籤 " + requiredTags + " 的文章:");
        for (Article article : articles) {
            if (article.getTags().containsAll(requiredTags)) {
                System.out.println("- " + article.getTitle());
            }
        }
        
        // 計算標籤使用頻率
        Map<String, Integer> tagFrequency = new HashMap<>();
        for (Article article : articles) {
            for (String tag : article.getTags()) {
                tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
            }
        }
        
        System.out.println("\n標籤使用頻率:");
        tagFrequency.entrySet().stream()
                   .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                   .forEach(entry -> System.out.printf("%-10s: %d 次%n", 
                                                       entry.getKey(), entry.getValue()));
    }
}
```

!!! note "Set 選擇指南"
    - **HashSet**: 最快的查找和插入，無順序保證
    - **LinkedHashSet**: 保持插入順序，比 HashSet 稍慢
    - **TreeSet**: 自動排序，查找和插入較慢但有序
    - **EnumSet**: 枚舉類型的高效實作

## Map 介面與實作

Map 是儲存鍵值對的集合，每個鍵唯一對應一個值。

### HashMap 基本使用

```java
import java.util.*;

public class HashMapExample {
    public static void main(String[] args) {
        System.out.println("=== HashMap 基本使用 ===");
        
        // 建立 HashMap
        Map<String, Integer> ageMap = new HashMap<>();
        
        // 添加鍵值對
        ageMap.put("Alice", 25);
        ageMap.put("Bob", 30);
        ageMap.put("Charlie", 28);
        ageMap.put("David", 22);
        
        System.out.println("年齡映射: " + ageMap);
        
        // 獲取值
        Integer aliceAge = ageMap.get("Alice");
        System.out.println("Alice 的年齡: " + aliceAge);
        
        // 檢查鍵是否存在
        System.out.println("包含 Bob: " + ageMap.containsKey("Bob"));
        System.out.println("包含年齡 25: " + ageMap.containsValue(25));
        
        // 使用 getOrDefault 避免 null
        int eveAge = ageMap.getOrDefault("Eve", 0);
        System.out.println("Eve 的年齡 (不存在時預設 0): " + eveAge);
        
        // putIfAbsent - 只在鍵不存在時才添加
        ageMap.putIfAbsent("Alice", 26); // 不會覆蓋
        ageMap.putIfAbsent("Eve", 24);   // 會添加
        System.out.println("putIfAbsent 後: " + ageMap);
        
        // 遍歷 Map 的方法
        System.out.println("\n=== 遍歷方法 ===");
        
        // 1. 遍歷鍵
        System.out.println("所有鍵:");
        for (String key : ageMap.keySet()) {
            System.out.println("- " + key);
        }
        
        // 2. 遍歷值
        System.out.println("所有值:");
        for (Integer value : ageMap.values()) {
            System.out.println("- " + value);
        }
        
        // 3. 遍歷鍵值對
        System.out.println("所有鍵值對:");
        for (Map.Entry<String, Integer> entry : ageMap.entrySet()) {
            System.out.printf("- %s: %d%n", entry.getKey(), entry.getValue());
        }
        
        // 4. 使用 forEach (Java 8+)
        System.out.println("使用 forEach:");
        ageMap.forEach((key, value) -> System.out.printf("- %s: %d%n", key, value));
        
        // 移除操作
        ageMap.remove("David");
        System.out.println("移除 David 後: " + ageMap);
        
        // 條件移除
        ageMap.remove("Bob", 30); // 只有當值匹配時才移除
        System.out.println("條件移除 Bob 後: " + ageMap);
        
        // 替換操作
        ageMap.replace("Alice", 26);
        ageMap.replace("Charlie", 28, 29); // 只有當舊值匹配時才替換
        System.out.println("替換後: " + ageMap);
        
        // 計算操作 (Java 8+)
        ageMap.compute("Alice", (key, value) -> value + 1);
        ageMap.computeIfAbsent("Frank", key -> 35);
        ageMap.computeIfPresent("Eve", (key, value) -> value + 1);
        System.out.println("計算操作後: " + ageMap);
    }
}
```

### Map 的不同實作比較

```java
import java.util.*;

public class MapVariations {
    public static void main(String[] args) {
        System.out.println("=== Map 的不同實作比較 ===");
        
        // HashMap - 無序、最快
        Map<Integer, String> hashMap = new HashMap<>();
        // LinkedHashMap - 維持插入順序
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        // TreeMap - 按鍵排序
        Map<Integer, String> treeMap = new TreeMap<>();
        
        // 添加相同的數據
        String[] values = {"Five", "Two", "Eight", "One", "Nine", "Three"};
        int[] keys = {5, 2, 8, 1, 9, 3};
        
        for (int i = 0; i < keys.length; i++) {
            hashMap.put(keys[i], values[i]);
            linkedHashMap.put(keys[i], values[i]);
            treeMap.put(keys[i], values[i]);
        }
        
        System.out.println("插入順序: " + Arrays.toString(keys));
        System.out.println("HashMap (無序): " + hashMap);
        System.out.println("LinkedHashMap (插入順序): " + linkedHashMap);
        System.out.println("TreeMap (鍵排序): " + treeMap);
        
        // TreeMap 的特殊操作
        System.out.println("\n=== TreeMap 特殊操作 ===");
        
        TreeMap<String, Integer> scores = new TreeMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        scores.put("David", 78);
        scores.put("Eve", 89);
        
        System.out.println("排序的成績: " + scores);
        System.out.println("第一個條目: " + scores.firstEntry());
        System.out.println("最後一個條目: " + scores.lastEntry());
        System.out.println("小於 'Charlie': " + scores.headMap("Charlie"));
        System.out.println("大於等於 'Charlie': " + scores.tailMap("Charlie"));
        System.out.println("'Bob' 到 'David': " + scores.subMap("Bob", "David"));
        
        // 效能比較
        performanceComparison();
    }
    
    public static void performanceComparison() {
        System.out.println("\n=== Map 效能比較 ===");
        
        int size = 100000;
        
        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        
        // 插入效能測試
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            hashMap.put(i, "Value" + i);
        }
        long hashMapInsertTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            treeMap.put(i, "Value" + i);
        }
        long treeMapInsertTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            linkedHashMap.put(i, "Value" + i);
        }
        long linkedHashMapInsertTime = System.currentTimeMillis() - startTime;
        
        System.out.printf("插入 %d 個元素:%n", size);
        System.out.printf("HashMap: %d ms%n", hashMapInsertTime);
        System.out.printf("TreeMap: %d ms%n", treeMapInsertTime);
        System.out.printf("LinkedHashMap: %d ms%n", linkedHashMapInsertTime);
        
        // 查找效能測試
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int randomKey = (int) (Math.random() * size);
            hashMap.get(randomKey);
        }
        long hashMapSearchTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int randomKey = (int) (Math.random() * size);
            treeMap.get(randomKey);
        }
        long treeMapSearchTime = System.currentTimeMillis() - startTime;
        
        System.out.println("\n查找 10,000 次:");
        System.out.printf("HashMap: %d ms%n", hashMapSearchTime);
        System.out.printf("TreeMap: %d ms%n", treeMapSearchTime);
    }
}
```

!!! warning "Map 使用注意事項"
    - 鍵必須實作正確的 `equals()` 和 `hashCode()` 方法
    - 不要使用可變物件作為鍵
    - HashMap 不是執行緒安全的，多執行緒環境需要額外同步

## Collections 工具類別

Collections 類別提供許多靜態方法來操作集合。

### Collections 實用方法

```java
import java.util.*;

public class CollectionsUtility {
    public static void main(String[] args) {
        System.out.println("=== Collections 工具方法 ===");
        
        // 建立測試列表
        List<Integer> numbers = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));
        System.out.println("原始列表: " + numbers);
        
        // 排序
        Collections.sort(numbers);
        System.out.println("排序後: " + numbers);
        
        // 反轉
        Collections.reverse(numbers);
        System.out.println("反轉後: " + numbers);
        
        // 隨機打亂
        Collections.shuffle(numbers);
        System.out.println("打亂後: " + numbers);
        
        // 旋轉 (循環移位)
        Collections.rotate(numbers, 2);
        System.out.println("向右旋轉2位: " + numbers);
        
        // 二元搜尋 (需要排序)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 5);
        System.out.println("排序後: " + numbers);
        System.out.println("數字 5 的位置: " + index);
        
        // 查找最大值和最小值
        Integer max = Collections.max(numbers);
        Integer min = Collections.min(numbers);
        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min);
        
        // 填充和替換
        List<String> words = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        Collections.fill(words, "x");
        System.out.println("填充 'x': " + words);
        
        List<String> original = new ArrayList<>(Arrays.asList("apple", "banana", "apple", "cherry"));
        Collections.replaceAll(original, "apple", "orange");
        System.out.println("替換 apple → orange: " + original);
        
        // 頻率統計
        List<String> items = Arrays.asList("a", "b", "a", "c", "b", "a");
        int frequency = Collections.frequency(items, "a");
        System.out.println("'a' 出現次數: " + frequency);
        
        // 不相交檢查
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(3, 4, 5);
        
        System.out.println("list1 和 list2 不相交: " + Collections.disjoint(list1, list2));
        System.out.println("list1 和 list3 不相交: " + Collections.disjoint(list1, list3));
        
        // 建立不可變集合
        immutableCollections();
        
        // 同步化集合
        synchronizedCollections();
    }
    
    public static void immutableCollections() {
        System.out.println("\n=== 不可變集合 ===");
        
        List<String> mutableList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> immutableList = Collections.unmodifiableList(mutableList);
        
        System.out.println("原始列表: " + mutableList);
        System.out.println("不可變列表: " + immutableList);
        
        // 修改原始列表
        mutableList.add("D");
        System.out.println("修改原始列表後:");
        System.out.println("原始列表: " + mutableList);
        System.out.println("不可變列表: " + immutableList); // 也會變化！
        
        try {
            immutableList.add("E"); // 會拋出異常
        } catch (UnsupportedOperationException e) {
            System.out.println("無法修改不可變列表: " + e.getClass().getSimpleName());
        }
        
        // 真正不可變的集合 (Java 9+)
        List<String> trulyImmutable = List.of("X", "Y", "Z");
        System.out.println("真正不可變列表: " + trulyImmutable);
        
        // 其他不可變集合
        Set<String> immutableSet = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList("Red", "Green", "Blue")));
        
        Map<String, Integer> mutableMap = new HashMap<>();
        mutableMap.put("One", 1);
        mutableMap.put("Two", 2);
        Map<String, Integer> immutableMap = Collections.unmodifiableMap(mutableMap);
        
        System.out.println("不可變集合: " + immutableSet);
        System.out.println("不可變映射: " + immutableMap);
    }
    
    public static void synchronizedCollections() {
        System.out.println("\n=== 同步化集合 ===");
        
        // 建立同步化集合
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        Set<Integer> syncSet = Collections.synchronizedSet(new HashSet<>());
        Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
        
        // 添加一些數據
        syncList.add("Item1");
        syncList.add("Item2");
        syncSet.add(1);
        syncSet.add(2);
        syncMap.put("key1", "value1");
        syncMap.put("key2", "value2");
        
        System.out.println("同步化列表: " + syncList);
        System.out.println("同步化集合: " + syncSet);
        System.out.println("同步化映射: " + syncMap);
        
        // 注意：遍歷時仍需要手動同步
        synchronized (syncList) {
            for (String item : syncList) {
                System.out.println("安全遍歷: " + item);
            }
        }
        
        System.out.println("注意: 同步化集合的遍歷操作仍需要手動同步");
    }
}
```

## 集合選擇與效能考量

### 效能比較總結

```java
import java.util.*;

public class CollectionPerformanceGuide {
    public static void main(String[] args) {
        System.out.println("=== 集合效能比較指南 ===");
        
        comprehensivePerformanceTest();
        collectionSelectionGuide();
    }
    
    public static void comprehensivePerformanceTest() {
        System.out.println("=== 綜合效能測試 ===");
        
        int size = 50000;
        
        // List 效能比較
        System.out.println("List 效能比較 (50,000 元素):");
        testListPerformance(size);
        
        // Set 效能比較
        System.out.println("\nSet 效能比較 (50,000 元素):");
        testSetPerformance(size);
        
        // Map 效能比較
        System.out.println("\nMap 效能比較 (50,000 元素):");
        testMapPerformance(size);
    }
    
    public static void testListPerformance(int size) {
        // 建立測試集合
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        
        // 添加到尾部的效能
        long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        long arrayListAddTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        long linkedListAddTime = System.nanoTime() - startTime;
        
        // 隨機存取效能
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int randomIndex = (int) (Math.random() * arrayList.size());
            arrayList.get(randomIndex);
        }
        long arrayListGetTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int randomIndex = (int) (Math.random() * linkedList.size());
            linkedList.get(randomIndex);
        }
        long linkedListGetTime = System.nanoTime() - startTime;
        
        // 在頭部插入效能
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.add(0, i);
        }
        long arrayListInsertTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.add(0, i);
        }
        long linkedListInsertTime = System.nanoTime() - startTime;
        
        System.out.printf("  添加到尾部:     ArrayList %6.2f ms, LinkedList %6.2f ms%n", 
                         arrayListAddTime / 1_000_000.0, linkedListAddTime / 1_000_000.0);
        System.out.printf("  隨機存取(1000次): ArrayList %6.2f ms, LinkedList %6.2f ms%n", 
                         arrayListGetTime / 1_000_000.0, linkedListGetTime / 1_000_000.0);
        System.out.printf("  頭部插入(1000次): ArrayList %6.2f ms, LinkedList %6.2f ms%n", 
                         arrayListInsertTime / 1_000_000.0, linkedListInsertTime / 1_000_000.0);
    }
    
    public static void testSetPerformance(int size) {
        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> treeSet = new TreeSet<>();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        
        // 添加效能
        long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            hashSet.add(i);
        }
        long hashSetAddTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            treeSet.add(i);
        }
        long treeSetAddTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedHashSet.add(i);
        }
        long linkedHashSetAddTime = System.nanoTime() - startTime;
        
        // 查找效能
        startTime = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            int randomValue = (int) (Math.random() * size);
            hashSet.contains(randomValue);
        }
        long hashSetSearchTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            int randomValue = (int) (Math.random() * size);
            treeSet.contains(randomValue);
        }
        long treeSetSearchTime = System.nanoTime() - startTime;
        
        System.out.printf("  添加:         HashSet %6.2f ms, TreeSet %6.2f ms, LinkedHashSet %6.2f ms%n", 
                         hashSetAddTime / 1_000_000.0, treeSetAddTime / 1_000_000.0, 
                         linkedHashSetAddTime / 1_000_000.0);
        System.out.printf("  查找(5000次): HashSet %6.2f ms, TreeSet %6.2f ms%n", 
                         hashSetSearchTime / 1_000_000.0, treeSetSearchTime / 1_000_000.0);
    }
    
    public static void testMapPerformance(int size) {
        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        
        // 添加效能
        long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            hashMap.put(i, "Value" + i);
        }
        long hashMapPutTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            treeMap.put(i, "Value" + i);
        }
        long treeMapPutTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedHashMap.put(i, "Value" + i);
        }
        long linkedHashMapPutTime = System.nanoTime() - startTime;
        
        // 查找效能
        startTime = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            int randomKey = (int) (Math.random() * size);
            hashMap.get(randomKey);
        }
        long hashMapGetTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            int randomKey = (int) (Math.random() * size);
            treeMap.get(randomKey);
        }
        long treeMapGetTime = System.nanoTime() - startTime;
        
        System.out.printf("  添加:         HashMap %6.2f ms, TreeMap %6.2f ms, LinkedHashMap %6.2f ms%n", 
                         hashMapPutTime / 1_000_000.0, treeMapPutTime / 1_000_000.0, 
                         linkedHashMapPutTime / 1_000_000.0);
        System.out.printf("  查找(5000次): HashMap %6.2f ms, TreeMap %6.2f ms%n", 
                         hashMapGetTime / 1_000_000.0, treeMapGetTime / 1_000_000.0);
    }
    
    public static void collectionSelectionGuide() {
        System.out.println("\n=== 集合選擇指南 ===");
        System.out.println("List 選擇:");
        System.out.println("  • ArrayList: 需要頻繁隨機存取，較少插入/刪除");
        System.out.println("  • LinkedList: 頻繁在頭尾插入/刪除，實作佇列/堆疊");
        System.out.println("  • Vector: 需要執行緒安全 (通常不推薦)");
        
        System.out.println("\nSet 選擇:");
        System.out.println("  • HashSet: 最快的查找和插入，不需要順序");
        System.out.println("  • LinkedHashSet: 需要維持插入順序");
        System.out.println("  • TreeSet: 需要自動排序，範圍查詢");
        
        System.out.println("\nMap 選擇:");
        System.out.println("  • HashMap: 最快的查找和插入，不需要順序");
        System.out.println("  • LinkedHashMap: 需要維持插入或存取順序");
        System.out.println("  • TreeMap: 需要按鍵排序，範圍查詢");
        System.out.println("  • ConcurrentHashMap: 多執行緒環境");
        
        System.out.println("\n時間複雜度總結:");
        printComplexityTable();
    }
    
    public static void printComplexityTable() {
        System.out.printf("%-15s %-10s %-10s %-10s %-10s%n", 
                         "集合", "添加", "刪除", "查找", "存取");
        System.out.println("─".repeat(65));
        System.out.printf("%-15s %-10s %-10s %-10s %-10s%n", 
                         "ArrayList", "O(1)*", "O(n)", "O(n)", "O(1)");
        System.out.printf("%-15s %-10s %-10s %-10s %-10s%n", 
                         "LinkedList", "O(1)", "O(1)**", "O(n)", "O(n)");
        System.out.printf("%-15s %-10s %-10s %-10s %-10s%n", 
                         "HashSet", "O(1)", "O(1)", "O(1)", "N/A");
        System.out.printf("%-15s %-10s %-10s %-10s %-10s%n", 
                         "TreeSet", "O(log n)", "O(log n)", "O(log n)", "N/A");
        System.out.printf("%-15s %-10s %-10s %-10s %-10s%n", 
                         "HashMap", "O(1)", "O(1)", "O(1)", "O(1)");
        System.out.printf("%-15s %-10s %-10s %-10s %-10s%n", 
                         "TreeMap", "O(log n)", "O(log n)", "O(log n)", "O(log n)");
        
        System.out.println("\n註解:");
        System.out.println("  * ArrayList 添加到尾部是 O(1)，到中間是 O(n)");
        System.out.println("  ** LinkedList 在已知位置刪除是 O(1)");
    }
}
```

## 重點整理

1. **Collection 框架**：統一的集合介面，提供 List、Set、Map 等不同特性的集合
2. **List**：有序集合，允許重複，支援索引存取
3. **Set**：無重複集合，適合去重和集合運算
4. **Map**：鍵值對集合，適合建立映射關係
5. **Collections 工具類**：提供豐富的集合操作方法
6. **效能考量**：根據使用場景選擇適合的集合實作

!!! tip "最佳實踐建議"
    - 優先使用介面類型宣告變數：`List<String> list = new ArrayList<>();`
    - 根據實際需求選擇合適的集合實作
    - 注意執行緒安全性，多執行緒環境使用 `ConcurrentHashMap` 等
    - 善用 Java 8+ 的 Stream API 處理集合操作

## 練習建議

1. **基礎練習**
   - 實作學生成績管理系統（使用 List 和 Map）
   - 建立單詞計數程式（使用 Map）
   - 設計去重複和排序功能（使用 Set）

2. **進階練習**
   - 實作 LRU (Least Recently Used) 快取（使用 LinkedHashMap）
   - 建立圖書館管理系統（綜合使用多種集合）
   - 設計購物車和訂單系統

3. **綜合專案**
   - 建立學生選課系統
   - 設計社交網路好友關係管理
   - 實作簡單的搜尋引擎索引

下一章我們將學習巢狀類別與 Lambda 表達式，進入 Java 的函數式程式設計世界！