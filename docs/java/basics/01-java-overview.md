# Java 技術概論

## 什麼是 Java？

Java 是由 Sun Microsystems（現為 Oracle）於 1995 年開發的物件導向程式語言。Java 的設計理念是「一次撰寫，到處執行」（Write Once, Run Anywhere, WORA），這使得 Java 程式具有跨平台的特性。

## Java 歷史與重要版本

Java 自 1995 年發布以來，經歷了多個重要版本：

- **Java 1.0 (1996)**: 第一個正式版本
- **Java 1.2 (1998)**: 引入 Swing GUI 和 Collections Framework
- **Java 5 (2004)**: 加入泛型、自動裝箱、增強型 for 迴圈
- **Java 8 (2014)**: 引入 Lambda 表達式和 Stream API
- **Java 11 (2018)**: LTS 版本，移除部分舊功能
- **Java 17 (2021)**: 最新 LTS 版本，Records、Pattern Matching 等新特性
- **Java 21 (2023)**: 最新 LTS 版本，虛擬線程、Pattern Matching 增強

!!! note "提示"
    LTS (Long Term Support) 版本是長期支援版本，建議在生產環境中使用 LTS 版本。

## Java 的主要特色

### 1. 跨平台性
Java 程式編譯後產生位元組碼（Bytecode），可在任何安裝了 JVM 的平台上執行。

### 2. 物件導向
Java 是純物件導向語言，除了基本資料型態外，所有東西都是物件。

### 3. 自動記憶體管理
Java 具有垃圾回收機制（Garbage Collection），自動管理記憶體。

### 4. 豐富的類別庫
Java 提供大量的標準類別庫，包含網路、檔案處理、GUI 等功能。

### 5. 安全性
Java 具有沙箱機制，提供安全的執行環境。

## JDK、JRE、JVM 的差異

理解這三個概念對 Java 開發非常重要：

### JVM (Java Virtual Machine)
- Java 虛擬機器，負責執行 Java 位元組碼
- 提供跨平台執行環境
- 負責記憶體管理和垃圾回收

### JRE (Java Runtime Environment)
- Java 執行環境 = JVM + Java 標準類別庫
- 只能執行 Java 程式，無法開發

### JDK (Java Development Kit)
- Java 開發工具包 = JRE + 開發工具（編譯器 javac、除錯器等）
- 開發 Java 程式必需安裝 JDK

!!! tip "技巧"
    開發者需要安裝 JDK，而一般使用者只需要 JRE 即可執行 Java 應用程式。

```
┌─────────────────────────────────────┐
│                JDK                  │
│  ┌───────────────────────────────┐  │
│  │              JRE              │  │
│  │  ┌─────────────────────────┐  │  │
│  │  │         JVM             │  │  │
│  │  │  - 記憶體管理           │  │  │
│  │  │  - 垃圾回收             │  │  │
│  │  │  - 位元組碼執行         │  │  │
│  │  └─────────────────────────┘  │  │
│  │  - Java 標準類別庫             │  │
│  └───────────────────────────────┘  │
│  - 編譯器 (javac)                   │
│  - 除錯器 (jdb)                     │
│  - 其他開發工具                     │
└─────────────────────────────────────┘
```

## Java 開發環境設定

### 1. 安裝 JDK

#### Windows
1. 前往 [Oracle JDK 官網](https://www.oracle.com/java/technologies/downloads/)
2. 下載適合的 JDK 版本（建議 JDK 17 或 21 LTS）
3. 執行安裝程式
4. 設定環境變數 `JAVA_HOME` 和 `PATH`

#### macOS
```bash
# 使用 Homebrew 安裝
brew install openjdk@17

# 或下載 Oracle JDK 安裝
```

#### Linux
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk

# CentOS/RHEL
sudo yum install java-17-openjdk-devel
```

### 2. 驗證安裝

```bash
# 檢查 Java 版本
java -version

# 檢查編譯器版本
javac -version
```

!!! example "範例輸出"
    ```
    openjdk version "17.0.2" 2022-01-18
    OpenJDK Runtime Environment (build 17.0.2+8-Ubuntu-120.04)
    OpenJDK 64-Bit Server VM (build 17.0.2+8-Ubuntu-120.04, mixed mode, sharing)
    ```

## 第一個 Java 程式

讓我們建立第一個 Java 程式：

```java
// HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java World!");
        System.out.println("歡迎來到 Java 的世界！");
        
        // 顯示 Java 版本資訊
        System.out.println("Java 版本: " + System.getProperty("java.version"));
        System.out.println("作業系統: " + System.getProperty("os.name"));
    }
}
```

!!! note "程式說明"
    - `public class HelloWorld`: 定義一個公開的類別，檔名必須與類別名稱相同
    - `public static void main(String[] args)`: 程式進入點，JVM 執行程式時會呼叫此方法
    - `System.out.println()`: 輸出文字到控制台並換行
    - `//`: 單行註解
    - `System.getProperty()`: 取得系統屬性

## 編譯與執行流程

Java 程式的執行需要經過編譯和執行兩個步驟：

### 1. 編譯 (.java → .class)

```bash
# 編譯 Java 原始碼
javac HelloWorld.java
```

編譯成功後會產生 `HelloWorld.class` 檔案（位元組碼）。

### 2. 執行 (.class → 程式運行)

```bash
# 執行 Java 程式
java HelloWorld
```

!!! warning "注意"
    - 執行時使用類別名稱，不包含 `.class` 副檔名
    - 檔名必須與 public 類別名稱完全相同（大小寫敏感）

### 完整流程圖

```
HelloWorld.java  ──┐
                   │ javac (編譯器)
                   ▼
HelloWorld.class ──┐
                   │ java (JVM)
                   ▼
               程式執行結果
```

## 開發工具推薦

### IDE (整合開發環境)

1. **IntelliJ IDEA**
   - 功能強大的商業 IDE
   - 有免費的 Community 版本
   - 優秀的程式碼補全和重構功能

2. **Eclipse**
   - 免費開源 IDE
   - 豐富的插件生態系統
   - 適合初學者

3. **Visual Studio Code**
   - 輕量級編輯器
   - 豐富的 Java 擴充套件
   - 跨平台支援

### 文字編輯器

- **Notepad++** (Windows)
- **Sublime Text** (跨平台)
- **Vim/Emacs** (進階使用者)

!!! tip "技巧"
    初學者建議使用 IDE，可以提供語法檢查、自動補全、除錯等功能，提高開發效率。

## 常見問題與解答

### Q1: 為什麼執行 java 命令時出現「找不到主類別」錯誤？

**A1:** 
- 確認類別檔案 (.class) 存在
- 檢查類別名稱是否正確（大小寫敏感）
- 確認 main 方法的簽章正確

### Q2: 編譯時出現編碼錯誤怎麼辦？

**A2:**
```bash
# 指定編碼格式編譯
javac -encoding UTF-8 HelloWorld.java
```

### Q3: 如何檢查是否正確安裝 Java？

**A3:**
```bash
# 檢查是否安裝 JDK
javac -version

# 檢查環境變數
echo $JAVA_HOME  # Linux/macOS
echo %JAVA_HOME% # Windows
```

## 重點整理

1. **Java 是跨平台的物件導向語言**，具有「一次撰寫，到處執行」的特性
2. **JDK > JRE > JVM**，開發需要 JDK，執行只需 JRE
3. **Java 程式執行流程**：.java → 編譯 (javac) → .class → 執行 (java)
4. **檔名必須與 public 類別名稱相同**
5. **main 方法**是程式的進入點

## 練習建議

1. **環境設定練習**
   - 安裝 JDK 並設定環境變數
   - 驗證安裝是否成功

2. **第一個程式**
   - 撰寫並執行 HelloWorld 程式
   - 修改程式內容，顯示更多系統資訊

3. **實驗練習**
   - 故意寫錯類別名稱，觀察錯誤訊息
   - 嘗試不同的編譯和執行參數

4. **延伸思考**
   - 研究不同 JDK 版本的差異
   - 了解 OpenJDK 與 Oracle JDK 的區別

下一章我們將學習 Java 的基本資料型態與運算子，開始真正的程式設計之旅！