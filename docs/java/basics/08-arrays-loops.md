# 陣列與迴圈

## 一維陣列基礎

陣列是儲存相同型態資料的容器，在 Java 中陣列是物件，具有固定大小。

### 陣列宣告與初始化

```java
public class ArrayBasics {
    public static void main(String[] args) {
        System.out.println("=== 陣列宣告與初始化 ===");
        
        // 方法一：宣告後指定大小
        int[] numbers1 = new int[5];  // 建立大小為 5 的整數陣列，預設值為 0
        System.out.println("空陣列長度: " + numbers1.length);
        System.out.println("預設值: " + java.util.Arrays.toString(numbers1));
        
        // 方法二：宣告時直接初始化
        int[] numbers2 = {1, 2, 3, 4, 5};
        System.out.println("直接初始化: " + java.util.Arrays.toString(numbers2));
        
        // 方法三：使用 new 關鍵字初始化
        int[] numbers3 = new int[]{10, 20, 30, 40, 50};
        System.out.println("new 初始化: " + java.util.Arrays.toString(numbers3));
        
        // 不同資料型態的陣列
        String[] names = {"張三", "李四", "王五"};
        double[] prices = {19.99, 25.50, 32.75};
        boolean[] flags = new boolean[3]; // 預設值為 false
        
        System.out.println("字串陣列: " + java.util.Arrays.toString(names));
        System.out.println("價格陣列: " + java.util.Arrays.toString(prices));
        System.out.println("布林陣列: " + java.util.Arrays.toString(flags));
        
        // 陣列賦值
        numbers1[0] = 100;
        numbers1[1] = 200;
        numbers1[2] = 300;
        numbers1[3] = 400;
        numbers1[4] = 500;
        
        System.out.println("賦值後的陣列: " + java.util.Arrays.toString(numbers1));
        
        // 存取陣列元素
        System.out.println("\n=== 陣列存取 ===");
        System.out.println("第一個元素: " + numbers2[0]);
        System.out.println("最後一個元素: " + numbers2[numbers2.length - 1]);
        System.out.println("中間元素: " + numbers2[numbers2.length / 2]);
        
        // 修改陣列元素
        numbers2[2] = 999;
        System.out.println("修改後: " + java.util.Arrays.toString(numbers2));
        
        // 陣列邊界檢查
        System.out.println("\n=== 邊界檢查 ===");
        try {
            System.out.println("正常存取: " + numbers2[0]);
            System.out.println("錯誤存取: " + numbers2[10]); // 會拋出例外
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("陣列索引超出範圍: " + e.getMessage());
        }
    }
}
```

### 陣列操作方法

```java
import java.util.Arrays;
import java.util.Scanner;

public class ArrayOperations {
    
    // 顯示陣列內容
    public static void printArray(int[] array, String title) {
        System.out.println(title + ": " + Arrays.toString(array));
    }
    
    // 計算陣列總和
    public static int sum(int[] array) {
        int total = 0;
        for (int value : array) {
            total += value;
        }
        return total;
    }
    
    // 計算陣列平均值
    public static double average(int[] array) {
        if (array.length == 0) return 0;
        return (double) sum(array) / array.length;
    }
    
    // 尋找最大值
    public static int findMax(int[] array) {
        if (array.length == 0) throw new IllegalArgumentException("陣列不能為空");
        
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    
    // 尋找最小值
    public static int findMin(int[] array) {
        if (array.length == 0) throw new IllegalArgumentException("陣列不能為空");
        
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
    
    // 線性搜尋
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i; // 回傳找到的索引
            }
        }
        return -1; // 找不到
    }
    
    // 計算特定值的出現次數
    public static int countOccurrences(int[] array, int target) {
        int count = 0;
        for (int value : array) {
            if (value == target) {
                count++;
            }
        }
        return count;
    }
    
    // 反轉陣列
    public static void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        
        while (left < right) {
            // 交換元素
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            
            left++;
            right--;
        }
    }
    
    // 複製陣列
    public static int[] copyArray(int[] source) {
        int[] copy = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
        // 或者使用 System.arraycopy 或 Arrays.copyOf
    }
    
    // 合併兩個陣列
    public static int[] mergeArrays(int[] array1, int[] array2) {
        int[] merged = new int[array1.length + array2.length];
        
        // 複製第一個陣列
        for (int i = 0; i < array1.length; i++) {
            merged[i] = array1[i];
        }
        
        // 複製第二個陣列
        for (int i = 0; i < array2.length; i++) {
            merged[array1.length + i] = array2[i];
        }
        
        return merged;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 陣列操作示範 ===");
        
        // 建立測試陣列
        int[] numbers = {23, 45, 12, 67, 34, 89, 23, 56, 12};
        printArray(numbers, "原始陣列");
        
        // 基本統計
        System.out.println("\n=== 基本統計 ===");
        System.out.println("陣列長度: " + numbers.length);
        System.out.println("總和: " + sum(numbers));
        System.out.println("平均值: " + String.format("%.2f", average(numbers)));
        System.out.println("最大值: " + findMax(numbers));
        System.out.println("最小值: " + findMin(numbers));
        
        // 搜尋操作
        System.out.println("\n=== 搜尋操作 ===");
        int searchTarget = 23;
        int index = linearSearch(numbers, searchTarget);
        if (index != -1) {
            System.out.println("數字 " + searchTarget + " 在索引 " + index + " 位置");
        } else {
            System.out.println("找不到數字 " + searchTarget);
        }
        
        int count = countOccurrences(numbers, searchTarget);
        System.out.println("數字 " + searchTarget + " 出現 " + count + " 次");
        
        // 陣列操作
        System.out.println("\n=== 陣列操作 ===");
        
        // 複製陣列
        int[] copied = copyArray(numbers);
        printArray(copied, "複製的陣列");
        
        // 反轉陣列
        reverseArray(copied);
        printArray(copied, "反轉後的陣列");
        
        // 合併陣列
        int[] additional = {100, 200, 300};
        int[] merged = mergeArrays(numbers, additional);
        printArray(merged, "合併後的陣列");
        
        // 使用 Arrays 類別的方法
        System.out.println("\n=== Arrays 類別方法 ===");
        
        int[] forSorting = copyArray(numbers);
        Arrays.sort(forSorting);
        printArray(forSorting, "排序後的陣列");
        
        // 二元搜尋（需要排序後的陣列）
        int binaryResult = Arrays.binarySearch(forSorting, 45);
        System.out.println("二元搜尋 45 的結果: " + binaryResult);
        
        // 比較陣列
        boolean isEqual = Arrays.equals(numbers, copied);
        System.out.println("原始陣列與反轉陣列相等: " + isEqual);
        
        // 填充陣列
        int[] fillArray = new int[5];
        Arrays.fill(fillArray, 42);
        printArray(fillArray, "填充陣列");
    }
}
```

!!! tip "陣列使用技巧"
    - 使用 `array.length` 取得陣列長度
    - 索引從 0 開始到 `length - 1`
    - 使用 `Arrays` 類別提供的實用方法
    - 注意 `ArrayIndexOutOfBoundsException` 例外

## 多維陣列

多維陣列是陣列的陣列，最常用的是二維陣列，適合表示表格、矩陣等結構。

### 二維陣列

```java
import java.util.Arrays;
import java.util.Random;

public class TwoDimensionalArrays {
    
    // 列印二維陣列
    public static void print2DArray(int[][] array, String title) {
        System.out.println(title + ":");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%4d ", array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // 計算二維陣列總和
    public static int sum2D(int[][] array) {
        int total = 0;
        for (int[] row : array) {
            for (int value : row) {
                total += value;
            }
        }
        return total;
    }
    
    // 尋找二維陣列最大值
    public static int findMax2D(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("陣列不能為空");
        }
        
        int max = array[0][0];
        for (int[] row : array) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }
    
    // 轉置矩陣
    public static int[][] transpose(int[][] matrix) {
        if (matrix.length == 0) return new int[0][0];
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        
        return transposed;
    }
    
    // 矩陣相加
    public static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length || 
            matrix1[0].length != matrix2[0].length) {
            throw new IllegalArgumentException("矩陣維度必須相同");
        }
        
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        
        return result;
    }
    
    // 矩陣相乘
    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        if (matrix1[0].length != matrix2.length) {
            throw new IllegalArgumentException("第一個矩陣的列數必須等於第二個矩陣的行數");
        }
        
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;
        
        int[][] result = new int[rows1][cols2];
        
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 二維陣列基礎 ===");
        
        // 建立二維陣列的不同方法
        
        // 方法一：指定大小後賦值
        int[][] matrix1 = new int[3][4];
        
        // 填充資料
        int value = 1;
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                matrix1[i][j] = value++;
            }
        }
        print2DArray(matrix1, "矩陣1 (3×4)");
        
        // 方法二：直接初始化
        int[][] matrix2 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        print2DArray(matrix2, "矩陣2 (3×3)");
        
        // 方法三：不規則陣列（鋸齒陣列）
        int[][] jaggedArray = {
            {1, 2},
            {3, 4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("不規則陣列:");
        for (int i = 0; i < jaggedArray.length; i++) {
            System.out.printf("第 %d 行: %s%n", i, Arrays.toString(jaggedArray[i]));
        }
        System.out.println();
        
        // 基本統計
        System.out.println("=== 統計資訊 ===");
        System.out.println("矩陣2 總和: " + sum2D(matrix2));
        System.out.println("矩陣2 最大值: " + findMax2D(matrix2));
        
        // 矩陣轉置
        System.out.println("=== 矩陣轉置 ===");
        int[][] transposed = transpose(matrix2);
        print2DArray(transposed, "轉置後的矩陣");
        
        // 矩陣運算
        System.out.println("=== 矩陣運算 ===");
        
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixB = {{5, 6}, {7, 8}};
        
        print2DArray(matrixA, "矩陣 A");
        print2DArray(matrixB, "矩陣 B");
        
        int[][] sum = addMatrices(matrixA, matrixB);
        print2DArray(sum, "A + B");
        
        int[][] product = multiplyMatrices(matrixA, matrixB);
        print2DArray(product, "A × B");
        
        // 實際應用：成績表
        System.out.println("=== 實際應用：成績表 ===");
        
        String[] subjects = {"數學", "英文", "科學", "歷史"};
        String[] students = {"張同學", "李同學", "王同學"};
        int[][] scores = {
            {85, 92, 78, 88}, // 張同學的成績
            {90, 85, 95, 82}, // 李同學的成績
            {78, 88, 85, 90}  // 王同學的成績
        };
        
        // 顯示成績表
        System.out.print("      ");
        for (String subject : subjects) {
            System.out.printf("%6s ", subject);
        }
        System.out.println();
        
        for (int i = 0; i < students.length; i++) {
            System.out.printf("%-6s", students[i]);
            for (int j = 0; j < subjects.length; j++) {
                System.out.printf("%6d ", scores[i][j]);
            }
            System.out.println();
        }
        
        // 計算每個學生的平均成績
        System.out.println("\n學生平均成績:");
        for (int i = 0; i < students.length; i++) {
            double avg = average(scores[i]);
            System.out.printf("%s: %.1f%n", students[i], avg);
        }
        
        // 計算每科的平均成績
        System.out.println("\n各科平均成績:");
        for (int j = 0; j < subjects.length; j++) {
            int total = 0;
            for (int i = 0; i < students.length; i++) {
                total += scores[i][j];
            }
            double avg = (double) total / students.length;
            System.out.printf("%s: %.1f%n", subjects[j], avg);
        }
    }
    
    // 計算一維陣列平均值（輔助方法）
    private static double average(int[] array) {
        if (array.length == 0) return 0;
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }
}
```

### 三維陣列與動態陣列

```java
public class MultiDimensionalArrays {
    
    // 建立並初始化三維陣列
    public static int[][][] create3DArray(int depth, int rows, int cols) {
        int[][][] array3D = new int[depth][rows][cols];
        int value = 1;
        
        for (int d = 0; d < depth; d++) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    array3D[d][r][c] = value++;
                }
            }
        }
        
        return array3D;
    }
    
    // 列印三維陣列
    public static void print3DArray(int[][][] array, String title) {
        System.out.println(title + ":");
        for (int d = 0; d < array.length; d++) {
            System.out.println("第 " + (d + 1) + " 層:");
            for (int r = 0; r < array[d].length; r++) {
                for (int c = 0; c < array[d][r].length; c++) {
                    System.out.printf("%3d ", array[d][r][c]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    // 動態增長陣列（模擬 ArrayList）
    public static class DynamicArray {
        private int[] array;
        private int size;
        private int capacity;
        
        public DynamicArray() {
            this.capacity = 2;
            this.array = new int[capacity];
            this.size = 0;
        }
        
        public void add(int value) {
            if (size >= capacity) {
                resize();
            }
            array[size++] = value;
        }
        
        private void resize() {
            capacity *= 2;
            int[] newArray = new int[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            System.out.println("陣列擴展到容量: " + capacity);
        }
        
        public int get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("索引超出範圍");
            }
            return array[index];
        }
        
        public int size() {
            return size;
        }
        
        public void display() {
            System.out.print("DynamicArray: [");
            for (int i = 0; i < size; i++) {
                System.out.print(array[i]);
                if (i < size - 1) System.out.print(", ");
            }
            System.out.println("] (容量: " + capacity + ")");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 三維陣列示範 ===");
        
        // 建立 2×3×4 的三維陣列
        int[][][] cube = create3DArray(2, 3, 4);
        print3DArray(cube, "3D 陣列 (2×3×4)");
        
        // 存取三維陣列元素
        System.out.println("存取 cube[1][2][3]: " + cube[1][2][3]);
        
        // 修改元素
        cube[0][1][2] = 999;
        System.out.println("修改 cube[0][1][2] 為 999");
        System.out.println("新值: " + cube[0][1][2]);
        
        // 實際應用：RGB 圖像資料
        System.out.println("\n=== RGB 圖像資料示例 ===");
        
        // 假設 4×4 像素的 RGB 圖像
        int[][][] image = new int[4][4][3]; // [高度][寬度][RGB]
        
        // 填充顏色資料
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                image[y][x][0] = (y + x) * 30 % 256; // Red
                image[y][x][1] = (y * x) * 20 % 256; // Green
                image[y][x][2] = (y + x * 2) * 40 % 256; // Blue
            }
        }
        
        // 顯示圖像資料
        System.out.println("圖像 RGB 資料:");
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.printf("(%3d,%3d,%3d) ", 
                    image[y][x][0], image[y][x][1], image[y][x][2]);
            }
            System.out.println();
        }
        
        System.out.println("\n=== 動態陣列示範 ===");
        
        DynamicArray dynArray = new DynamicArray();
        
        // 添加元素測試自動擴展
        for (int i = 1; i <= 10; i++) {
            dynArray.add(i * 10);
            dynArray.display();
        }
        
        // 存取元素
        System.out.println("索引 5 的元素: " + dynArray.get(5));
        System.out.println("陣列大小: " + dynArray.size());
        
        // 不規則三維陣列
        System.out.println("\n=== 不規則三維陣列 ===");
        
        int[][][] irregular3D = {
            {
                {1, 2},
                {3, 4, 5}
            },
            {
                {6, 7, 8, 9},
                {10, 11}
            }
        };
        
        System.out.println("不規則三維陣列內容:");
        for (int i = 0; i < irregular3D.length; i++) {
            System.out.println("第 " + (i + 1) + " 層:");
            for (int j = 0; j < irregular3D[i].length; j++) {
                System.out.println("  行 " + (j + 1) + ": " + 
                    Arrays.toString(irregular3D[i][j]));
            }
        }
    }
}
```

!!! example "多維陣列應用"
    - **二維陣列**: 表格、矩陣、棋盤、圖像
    - **三維陣列**: 3D 空間、RGB 圖像、時間序列資料
    - **不規則陣列**: 層次結構、樹狀資料

## for-each 迴圈

for-each 迴圈（增強型 for 迴圈）提供了簡潔的方式來遍歷陣列和集合。

### for-each 基本語法

```java
import java.util.List;
import java.util.ArrayList;

public class ForEachLoops {
    
    public static void main(String[] args) {
        System.out.println("=== for-each 迴圈基礎 ===");
        
        // 一維陣列的 for-each
        int[] numbers = {10, 20, 30, 40, 50};
        
        System.out.println("傳統 for 迴圈:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
        
        System.out.println("for-each 迴圈:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // 字串陣列的 for-each
        String[] fruits = {"蘋果", "香蕉", "橘子", "葡萄"};
        
        System.out.println("\n水果列表:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }
        
        // 二維陣列的 for-each
        System.out.println("\n=== 二維陣列 for-each ===");
        
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("使用巢狀 for-each:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%3d ", value);
            }
            System.out.println();
        }
        
        // 集合的 for-each
        System.out.println("\n=== 集合 for-each ===");
        
        List<String> cities = new ArrayList<>();
        cities.add("台北");
        cities.add("台中");
        cities.add("台南");
        cities.add("高雄");
        
        System.out.println("城市列表:");
        for (String city : cities) {
            System.out.println("城市: " + city);
        }
        
        // for-each 的限制
        System.out.println("\n=== for-each 限制 ===");
        
        int[] scores = {85, 90, 78, 92, 88};
        
        // 無法直接修改陣列元素（這不會改變原陣列）
        System.out.println("原始分數: " + java.util.Arrays.toString(scores));
        
        System.out.println("嘗試用 for-each 修改分數:");
        for (int score : scores) {
            score += 5; // 這只修改了區域變數，不會影響陣列
            System.out.print(score + " ");
        }
        System.out.println();
        System.out.println("修改後分數: " + java.util.Arrays.toString(scores)); // 沒變
        
        // 正確的修改方式：使用傳統 for 迴圈
        System.out.println("\n使用傳統 for 迴圈修改:");
        for (int i = 0; i < scores.length; i++) {
            scores[i] += 5;
        }
        System.out.println("修改後分數: " + java.util.Arrays.toString(scores));
        
        // for-each 無法取得索引
        System.out.println("\n=== 需要索引時的處理 ===");
        
        String[] subjects = {"數學", "英文", "科學", "歷史"};
        
        // 傳統方式：可以取得索引
        System.out.println("使用傳統 for 迴圈（有索引）:");
        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%d. %s%n", i + 1, subjects[i]);
        }
        
        // for-each 無法直接取得索引，需要額外變數
        System.out.println("\nfor-each 搭配計數器:");
        int index = 1;
        for (String subject : subjects) {
            System.out.printf("%d. %s%n", index++, subject);
        }
        
        // 實際應用範例
        System.out.println("\n=== 實際應用範例 ===");
        
        // 計算陣列總和
        int[] values = {12, 25, 8, 33, 19, 7, 41};
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        System.out.println("數值: " + java.util.Arrays.toString(values));
        System.out.println("總和: " + sum);
        System.out.println("平均: " + (double) sum / values.length);
        
        // 尋找最大值
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > max) {
                max = value;
            }
        }
        System.out.println("最大值: " + max);
        
        // 字串處理
        String[] words = {"Java", "Python", "JavaScript", "C++", "Go"};
        System.out.println("\n程式語言統計:");
        
        int totalLength = 0;
        for (String word : words) {
            System.out.printf("%-12s (長度: %d)%n", word, word.length());
            totalLength += word.length();
        }
        System.out.println("平均長度: " + (double) totalLength / words.length);
    }
}
```

### for-each 進階應用

```java
import java.util.*;

public class AdvancedForEach {
    
    // 自定義學生類別
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
    
    // 使用 for-each 處理物件陣列
    public static void processStudents(Student[] students) {
        System.out.println("=== 學生資料處理 ===");
        
        // 顯示所有學生
        System.out.println("所有學生:");
        for (Student student : students) {
            System.out.println("- " + student);
        }
        
        // 計算平均年齡
        double totalAge = 0;
        for (Student student : students) {
            totalAge += student.getAge();
        }
        System.out.printf("平均年齡: %.1f 歲%n", totalAge / students.length);
        
        // 計算平均 GPA
        double totalGpa = 0;
        for (Student student : students) {
            totalGpa += student.getGpa();
        }
        System.out.printf("平均 GPA: %.2f%n", totalGpa / students.length);
        
        // 尋找最高 GPA
        Student topStudent = students[0];
        for (Student student : students) {
            if (student.getGpa() > topStudent.getGpa()) {
                topStudent = student;
            }
        }
        System.out.println("最高 GPA 學生: " + topStudent);
        
        // 統計年齡分布
        Map<Integer, Integer> ageCount = new HashMap<>();
        for (Student student : students) {
            int age = student.getAge();
            ageCount.put(age, ageCount.getOrDefault(age, 0) + 1);
        }
        
        System.out.println("年齡分布:");
        for (Map.Entry<Integer, Integer> entry : ageCount.entrySet()) {
            System.out.printf("%d 歲: %d 人%n", entry.getKey(), entry.getValue());
        }
    }
    
    // 巢狀集合的 for-each
    public static void processNestedCollections() {
        System.out.println("\n=== 巢狀集合處理 ===");
        
        // 建立部門和員工結構
        Map<String, List<String>> departments = new HashMap<>();
        
        departments.put("IT", Arrays.asList("張工程師", "李程式師", "王架構師"));
        departments.put("HR", Arrays.asList("陳主管", "林專員"));
        departments.put("Finance", Arrays.asList("劉會計", "黃出納", "錢分析師", "孫主任"));
        
        // 使用巢狀 for-each 處理
        System.out.println("公司部門結構:");
        for (Map.Entry<String, List<String>> entry : departments.entrySet()) {
            String deptName = entry.getKey();
            List<String> employees = entry.getValue();
            
            System.out.println(deptName + " 部門 (" + employees.size() + " 人):");
            for (String employee : employees) {
                System.out.println("  - " + employee);
            }
        }
        
        // 統計總員工數
        int totalEmployees = 0;
        for (List<String> employees : departments.values()) {
            totalEmployees += employees.size();
        }
        System.out.println("公司總員工數: " + totalEmployees);
    }
    
    // 使用 for-each 處理多維陣列
    public static void processMultiDimensionalData() {
        System.out.println("\n=== 多維資料處理 ===");
        
        // 季度銷售資料：[季度][月份][產品]
        int[][][] salesData = {
            { // Q1
                {100, 150, 200}, // 1月：產品A, B, C
                {120, 160, 180}, // 2月
                {110, 140, 220}  // 3月
            },
            { // Q2
                {130, 170, 210},
                {140, 180, 190},
                {125, 155, 205}
            },
            { // Q3
                {135, 175, 225},
                {145, 185, 195},
                {150, 190, 230}
            },
            { // Q4
                {160, 200, 240},
                {170, 210, 250},
                {180, 220, 260}
            }
        };
        
        String[] quarters = {"Q1", "Q2", "Q3", "Q4"};
        String[] months = {"1月", "2月", "3月"};
        String[] products = {"產品A", "產品B", "產品C"};
        
        // 計算年度總銷售額
        int yearlyTotal = 0;
        for (int[][] quarter : salesData) {
            for (int[] month : quarter) {
                for (int sales : month) {
                    yearlyTotal += sales;
                }
            }
        }
        System.out.println("年度總銷售額: " + yearlyTotal);
        
        // 計算各季度總額
        System.out.println("\n各季度銷售額:");
        int quarterIndex = 0;
        for (int[][] quarter : salesData) {
            int quarterTotal = 0;
            for (int[] month : quarter) {
                for (int sales : month) {
                    quarterTotal += sales;
                }
            }
            System.out.printf("%s: %d%n", quarters[quarterIndex++], quarterTotal);
        }
        
        // 計算各產品年度銷售額
        System.out.println("\n各產品年度銷售額:");
        for (int productIndex = 0; productIndex < products.length; productIndex++) {
            int productTotal = 0;
            for (int[][] quarter : salesData) {
                for (int[] month : quarter) {
                    productTotal += month[productIndex];
                }
            }
            System.out.printf("%s: %d%n", products[productIndex], productTotal);
        }
    }
    
    public static void main(String[] args) {
        // 建立學生陣列
        Student[] students = {
            new Student("張小明", 20, 3.8),
            new Student("李小華", 19, 3.6),
            new Student("王小美", 21, 3.9),
            new Student("陳小強", 20, 3.4),
            new Student("林小麗", 22, 3.7)
        };
        
        processStudents(students);
        processNestedCollections();
        processMultiDimensionalData();
        
        // for-each 與其他迴圈的效能比較
        System.out.println("\n=== 效能比較 ===");
        
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i;
        }
        
        // 傳統 for 迴圈
        long startTime = System.currentTimeMillis();
        long sum1 = 0;
        for (int i = 0; i < largeArray.length; i++) {
            sum1 += largeArray[i];
        }
        long time1 = System.currentTimeMillis() - startTime;
        
        // for-each 迴圈
        startTime = System.currentTimeMillis();
        long sum2 = 0;
        for (int value : largeArray) {
            sum2 += value;
        }
        long time2 = System.currentTimeMillis() - startTime;
        
        System.out.println("處理 " + largeArray.length + " 個元素:");
        System.out.println("傳統 for 迴圈時間: " + time1 + " ms");
        System.out.println("for-each 迴圈時間: " + time2 + " ms");
        System.out.println("結果相同: " + (sum1 == sum2));
    }
}
```

!!! note "for-each 特點總結"
    - **優點**: 程式碼簡潔、不會索引超出範圍、自動處理陣列長度
    - **限制**: 無法修改元素、無法取得索引、無法向後遍歷
    - **適用**: 唯讀遍歷、統計計算、搜尋操作
    - **不適用**: 需要修改元素、需要索引、需要特定遍歷順序

## 陣列常見操作

### 排序演算法

```java
import java.util.Arrays;
import java.util.Random;

public class ArraySorting {
    
    // 氣泡排序法
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // 交換元素
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // 如果沒有交換發生，表示已排序完成
            if (!swapped) break;
        }
    }
    
    // 選擇排序法
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            // 找到最小元素的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // 交換找到的最小元素與第一個元素
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
    
    // 插入排序法
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            
            // 將大於 key 的元素往後移
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
    
    // 快速排序法
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
    
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // 合併排序法
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }
    
    private static void merge(int[] array, int left, int middle, int right) {
        int[] leftArray = Arrays.copyOfRange(array, left, middle + 1);
        int[] rightArray = Arrays.copyOfRange(array, middle + 1, right + 1);
        
        int i = 0, j = 0, k = left;
        
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        
        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }
        
        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
    }
    
    // 測試排序效能
    public static void testSortingPerformance() {
        int[] sizes = {1000, 5000, 10000};
        
        for (int size : sizes) {
            System.out.println("\n=== 陣列大小: " + size + " ===");
            
            // 生成隨機資料
            Random random = new Random(42); // 固定種子確保可重現
            int[] originalData = new int[size];
            for (int i = 0; i < size; i++) {
                originalData[i] = random.nextInt(1000);
            }
            
            // 測試各種排序演算法
            testSortAlgorithm("氣泡排序", originalData, 
                (arr) -> bubbleSort(arr));
            
            testSortAlgorithm("選擇排序", originalData, 
                (arr) -> selectionSort(arr));
            
            testSortAlgorithm("插入排序", originalData, 
                (arr) -> insertionSort(arr));
            
            testSortAlgorithm("快速排序", originalData, 
                (arr) -> quickSort(arr, 0, arr.length - 1));
            
            testSortAlgorithm("合併排序", originalData, 
                (arr) -> mergeSort(arr, 0, arr.length - 1));
            
            testSortAlgorithm("Arrays.sort", originalData, 
                (arr) -> Arrays.sort(arr));
        }
    }
    
    @FunctionalInterface
    interface SortAlgorithm {
        void sort(int[] array);
    }
    
    private static void testSortAlgorithm(String name, int[] originalData, SortAlgorithm algorithm) {
        // 複製資料
        int[] data = Arrays.copyOf(originalData, originalData.length);
        
        // 測量時間
        long startTime = System.currentTimeMillis();
        algorithm.sort(data);
        long endTime = System.currentTimeMillis();
        
        // 驗證排序正確性
        boolean isSorted = isSorted(data);
        
        System.out.printf("%-10s: %4d ms %s%n", name, endTime - startTime, 
                         isSorted ? "✓" : "✗");
    }
    
    private static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 排序演算法示範 ===");
        
        // 測試小型陣列
        int[] testData = {64, 34, 25, 12, 22, 11, 90, 88, 76, 50, 42};
        System.out.println("原始資料: " + Arrays.toString(testData));
        
        // 氣泡排序
        int[] bubbleData = Arrays.copyOf(testData, testData.length);
        bubbleSort(bubbleData);
        System.out.println("氣泡排序: " + Arrays.toString(bubbleData));
        
        // 選擇排序
        int[] selectionData = Arrays.copyOf(testData, testData.length);
        selectionSort(selectionData);
        System.out.println("選擇排序: " + Arrays.toString(selectionData));
        
        // 插入排序
        int[] insertionData = Arrays.copyOf(testData, testData.length);
        insertionSort(insertionData);
        System.out.println("插入排序: " + Arrays.toString(insertionData));
        
        // 快速排序
        int[] quickData = Arrays.copyOf(testData, testData.length);
        quickSort(quickData, 0, quickData.length - 1);
        System.out.println("快速排序: " + Arrays.toString(quickData));
        
        // 合併排序
        int[] mergeData = Arrays.copyOf(testData, testData.length);
        mergeSort(mergeData, 0, mergeData.length - 1);
        System.out.println("合併排序: " + Arrays.toString(mergeData));
        
        // 內建排序
        int[] systemData = Arrays.copyOf(testData, testData.length);
        Arrays.sort(systemData);
        System.out.println("Arrays.sort: " + Arrays.toString(systemData));
        
        // 效能測試
        System.out.println("\n=== 效能測試 ===");
        testSortingPerformance();
    }
}
```

### 搜尋演算法

```java
import java.util.Arrays;

public class ArraySearching {
    
    // 線性搜尋
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; // 找不到
    }
    
    // 二元搜尋（陣列必須已排序）
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int middle = left + (right - left) / 2; // 避免溢位
            
            if (array[middle] == target) {
                return middle;
            } else if (array[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        
        return -1; // 找不到
    }
    
    // 遞迴版本的二元搜尋
    public static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int middle = left + (right - left) / 2;
        
        if (array[middle] == target) {
            return middle;
        } else if (array[middle] < target) {
            return binarySearchRecursive(array, target, middle + 1, right);
        } else {
            return binarySearchRecursive(array, target, left, middle - 1);
        }
    }
    
    // 尋找所有符合條件的索引
    public static int[] findAllOccurrences(int[] array, int target) {
        int[] tempResult = new int[array.length];
        int count = 0;
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                tempResult[count++] = i;
            }
        }
        
        // 建立適當大小的結果陣列
        return Arrays.copyOf(tempResult, count);
    }
    
    // 尋找最大值的索引
    public static int findMaxIndex(int[] array) {
        if (array.length == 0) return -1;
        
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    // 尋找最小值的索引
    public static int findMinIndex(int[] array) {
        if (array.length == 0) return -1;
        
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    // 尋找第二大的值
    public static int findSecondLargest(int[] array) {
        if (array.length < 2) {
            throw new IllegalArgumentException("陣列至少需要 2 個元素");
        }
        
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        
        for (int value : array) {
            if (value > largest) {
                secondLargest = largest;
                largest = value;
            } else if (value > secondLargest && value < largest) {
                secondLargest = value;
            }
        }
        
        if (secondLargest == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("找不到第二大的值");
        }
        
        return secondLargest;
    }
    
    // 搜尋效能測試
    public static void performanceTest() {
        System.out.println("\n=== 搜尋效能測試 ===");
        
        int[] sizes = {10000, 100000, 1000000};
        
        for (int size : sizes) {
            System.out.println("\n陣列大小: " + size);
            
            // 建立排序陣列
            int[] sortedArray = new int[size];
            for (int i = 0; i < size; i++) {
                sortedArray[i] = i * 2; // 偶數序列
            }
            
            int target = size; // 搜尋中間值
            
            // 線性搜尋測試
            long startTime = System.currentTimeMillis();
            int linearResult = linearSearch(sortedArray, target);
            long linearTime = System.currentTimeMillis() - startTime;
            
            // 二元搜尋測試
            startTime = System.currentTimeMillis();
            int binaryResult = binarySearch(sortedArray, target);
            long binaryTime = System.currentTimeMillis() - startTime;
            
            // Arrays.binarySearch 測試
            startTime = System.currentTimeMillis();
            int systemResult = Arrays.binarySearch(sortedArray, target);
            long systemTime = System.currentTimeMillis() - startTime;
            
            System.out.printf("線性搜尋: %3d ms (索引: %d)%n", linearTime, linearResult);
            System.out.printf("二元搜尋: %3d ms (索引: %d)%n", binaryTime, binaryResult);
            System.out.printf("系統搜尋: %3d ms (索引: %d)%n", systemTime, systemResult);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 搜尋演算法示範 ===");
        
        // 測試資料
        int[] numbers = {15, 23, 8, 42, 16, 23, 4, 35, 23, 19};
        System.out.println("測試陣列: " + Arrays.toString(numbers));
        
        // 線性搜尋測試
        int target = 23;
        int index = linearSearch(numbers, target);
        System.out.printf("線性搜尋 %d: 索引 %d%n", target, index);
        
        // 尋找所有出現位置
        int[] allIndices = findAllOccurrences(numbers, target);
        System.out.printf("數字 %d 的所有位置: %s%n", target, Arrays.toString(allIndices));
        
        // 尋找最大值和最小值
        int maxIndex = findMaxIndex(numbers);
        int minIndex = findMinIndex(numbers);
        System.out.printf("最大值 %d 在索引 %d%n", numbers[maxIndex], maxIndex);
        System.out.printf("最小值 %d 在索引 %d%n", numbers[minIndex], minIndex);
        
        // 尋找第二大的值
        try {
            int secondLargest = findSecondLargest(numbers);
            System.out.println("第二大的值: " + secondLargest);
        } catch (IllegalArgumentException e) {
            System.out.println("錯誤: " + e.getMessage());
        }
        
        // 二元搜尋測試（需要排序陣列）
        System.out.println("\n=== 二元搜尋測試 ===");
        int[] sortedNumbers = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sortedNumbers);
        System.out.println("排序後陣列: " + Arrays.toString(sortedNumbers));
        
        target = 23;
        index = binarySearch(sortedNumbers, target);
        System.out.printf("二元搜尋 %d: 索引 %d%n", target, index);
        
        // 遞迴版本測試
        int recursiveIndex = binarySearchRecursive(sortedNumbers, target, 0, sortedNumbers.length - 1);
        System.out.printf("遞迴二元搜尋 %d: 索引 %d%n", target, recursiveIndex);
        
        // 系統內建方法
        int systemIndex = Arrays.binarySearch(sortedNumbers, target);
        System.out.printf("Arrays.binarySearch %d: 索引 %d%n", target, systemIndex);
        
        // 搜尋不存在的值
        target = 100;
        index = binarySearch(sortedNumbers, target);
        System.out.printf("二元搜尋 %d (不存在): %d%n", target, index);
        
        // 效能測試
        performanceTest();
        
        // 實際應用範例：字典搜尋
        System.out.println("\n=== 實際應用：字典搜尋 ===");
        
        String[] dictionary = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
        System.out.println("字典: " + Arrays.toString(dictionary));
        
        String word = "cherry";
        int wordIndex = Arrays.binarySearch(dictionary, word);
        if (wordIndex >= 0) {
            System.out.printf("找到 '%s' 在位置 %d%n", word, wordIndex);
        } else {
            System.out.printf("'%s' 不在字典中，應插入位置 %d%n", word, -wordIndex - 1);
        }
    }
}
```

!!! tip "演算法選擇建議"
    - **搜尋**：排序陣列用二元搜尋，未排序用線性搜尋
    - **排序**：小陣列用插入排序，大陣列用快速排序或合併排序
    - **Java 內建**：優先使用 `Arrays.sort()` 和 `Arrays.binarySearch()`

## 重點整理

1. **陣列基礎**：固定大小、索引從 0 開始、型態安全
2. **多維陣列**：陣列的陣列，支援不規則結構
3. **for-each 迴圈**：簡潔的遍歷語法，適合唯讀操作
4. **常見操作**：排序、搜尋、統計、轉換
5. **Arrays 工具類別**：提供豐富的陣列操作方法

!!! warning "常見陷阱"
    - 陣列索引越界 (`ArrayIndexOutOfBoundsException`)
    - for-each 迴圈無法修改陣列元素
    - 二元搜尋需要排序陣列
    - 陣列是物件，賦值時是參考複製

## 練習建議

1. **基礎練習**
   - 實作矩陣運算（加法、乘法、轉置）
   - 撰寫學生成績統計程式
   - 建立簡單的圖像處理程式（使用三維陣列）

2. **演算法練習**
   - 實作不同的排序演算法
   - 設計高效的搜尋方法
   - 練習遞迴與迭代的轉換

3. **綜合應用**
   - 建立學生選課系統
   - 設計簡單的遊戲棋盤
   - 實作資料分析工具

下一章我們將學習 Java 的集合框架（Collections），它提供比陣列更強大和靈活的資料結構！