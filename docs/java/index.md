# Java 學習路徑

歡迎來到 Java 程式語言的學習之旅！本學習路徑將帶您從基礎概念到進階應用，循序漸進地掌握 Java 開發技能。

## 🎯 學習目標

完成這個學習路徑後，您將能夠：

- 掌握 Java 語言的核心概念和語法
- 運用物件導向程式設計原則
- 處理資料結構與演算法問題
- 開發實際應用程式
- 理解 Java 生態系統和最佳實踐

## 📚 課程結構

### 🚀 基礎篇
建立 Java 程式設計的基礎知識，學習語言核心特性。

**[01. Java 技術概論](basics/01-java-overview.md)**
- Java 歷史與特色
- JDK、JRE、JVM 差異
- 開發環境設定
- 第一個 Java 程式

**[02. 基本資料型態與流程控制](basics/02-data-types-operators.md)**
- 8 種基本資料型態
- 運算子與表達式
- if-else、switch、迴圈

**[03. 文字、日期與數字物件](basics/03-string-date-number.md)**
- String 類別操作
- 新日期時間 API
- 數字包裝類別與 BigDecimal

**[08. 陣列與迴圈](basics/08-arrays-loops.md)**
- 一維與多維陣列
- for-each 迴圈
- 常見陣列操作

### 🏗️ 物件導向篇
深入學習物件導向程式設計概念，掌握 Java 的核心特性。

**[04. 類別與物件](oop/04-class-and-object.md)**
- 物件導向四大特性
- 類別定義與物件建立
- 建構子、this、存取修飾子

**[05. 設計類別與列舉](oop/05-class-design-enum.md)**
- 類別設計原則
- 方法多載
- 列舉(enum)進階應用

**[06. 繼承與記錄](oop/06-inheritance-records.md)**
- 繼承與方法覆寫
- super 關鍵字
- final 關鍵字
- Records (Java 14+)

**[07. 介面與泛型](oop/07-interface-generics.md)**
- 介面定義與實作
- 預設方法與靜態方法
- 泛型基礎與進階

### ⚡ 進階主題篇
探索 Java 的高級特性，提升程式設計技巧。

**[09. 集合框架](advanced/09-collections.md)**
- List、Set、Map 介面
- ArrayList、HashMap 等實作
- 集合選擇與效能考量

**[10. 巢狀類別與 Lambda](advanced/10-nested-class-lambda.md)**
- 內部類別與匿名類別
- Lambda 表達式
- 函數式介面

**[11. Stream API](advanced/11-stream-api.md)**
- Stream 概念與特性
- 中間操作與終端操作
- Collectors 與 Optional

**[12. 例外控制與除錯](advanced/12-exception-debug.md)**
- 例外處理機制
- 自定義例外
- 除錯技巧

**[13. 標記註解](advanced/13-annotations.md)**
- Annotation 概念
- 內建註解與自定義註解
- 註解處理

**[15. 安全性](advanced/15-security.md)**
- Java 安全性概述
- 加密解密
- 安全漏洞防範

**[16. 進階泛型](advanced/16-advanced-generics.md)**
- 萬用字元與邊界
- 型別擦除
- 泛型最佳實踐

### 🗄️ 資料庫篇
學習 Java 資料庫程式設計。

**[14. 資料庫應用開發](database/14-database-development.md)**
- JDBC 架構
- 資料庫連線與操作
- JPA 簡介

### 🎨 設計模式篇
掌握常用設計模式與進階 I/O 操作。

**[17. 設計模式與進階 IO](patterns/17-design-patterns-regex-io.md)**
- 常用設計模式
- 正規表達式
- NIO.2 與 WatchService

## 🛤️ 建議學習順序

### 初學者路徑 (4-6 週)
1. **第 1 週**: 01-03 章 (Java 基礎、資料型態、字串處理)
2. **第 2 週**: 04-05 章 (類別物件、設計原則)
3. **第 3 週**: 06-07 章 (繼承、介面、泛型)
4. **第 4 週**: 08-09 章 (陣列、集合框架)

### 進階學習路徑 (6-8 週)
1. **第 5 週**: 10-11 章 (Lambda、Stream API)
2. **第 6 週**: 12-13 章 (例外處理、註解)
3. **第 7 週**: 14-15 章 (資料庫、安全性)
4. **第 8 週**: 16-17 章 (進階泛型、設計模式)

## 📝 學習建議

### 💡 學習技巧
- **動手實作**: 每個概念都要親自寫程式驗證
- **循序漸進**: 確保理解前一章再進入下一章
- **實際專案**: 結合學習內容建立小專案
- **複習鞏固**: 定期回顧已學習的內容

### 🔧 開發環境
建議使用以下開發環境：
- **JDK**: Oracle JDK 17+ 或 OpenJDK 17+
- **IDE**: IntelliJ IDEA、Eclipse 或 VS Code
- **建構工具**: Maven 或 Gradle
- **版本控制**: Git

### 📖 補充資源
- Oracle 官方 Java 教學文件
- Java API 文件
- Stack Overflow 程式問答
- GitHub 開源專案

## 🚨 常見陷阱提醒

1. **空指標例外**: 記得檢查 null 值
2. **記憶體洩漏**: 注意物件生命週期
3. **執行緒安全**: 多執行緒環境的注意事項
4. **效能考量**: 選擇適合的資料結構和演算法

## 🎖️ 學習里程碑

完成每個階段後，您可以嘗試以下挑戰：

### 基礎階段完成
- [ ] 建立一個簡單的學生管理系統
- [ ] 實作基本的計算機程式
- [ ] 設計物件導向的圖形系統

### 進階階段完成
- [ ] 開發一個完整的 Web 應用程式
- [ ] 實作設計模式解決實際問題
- [ ] 建立高效能的資料處理程式

---

**🎉 開始您的 Java 學習之旅吧！選擇適合您當前程度的章節開始學習。**