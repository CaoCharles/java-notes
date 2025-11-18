# 效能優化

建立生產級 Agent 需要注重效能。本章介紹常見優化技巧。

## 優化策略

### 1. 快取機制

```python
from functools import lru_cache
import hashlib

class CachedLLM:
    def __init__(self):
        self.cache = {}

    def invoke(self, prompt: str) -> str:
        # 使用 prompt hash 作為 key
        key = hashlib.md5(prompt.encode()).hexdigest()

        if key in self.cache:
            print("✅ 快取命中")
            return self.cache[key]

        # 實際調用 LLM
        from langchain_openai import ChatOpenAI
        llm = ChatOpenAI(model="gpt-4")
        result = llm.invoke(prompt).content

        self.cache[key] = result
        return result

# 使用
cached_llm = CachedLLM()
response = cached_llm.invoke("分析市場趨勢")  # 第一次調用 API
response = cached_llm.invoke("分析市場趨勢")  # 從快取取得
```

### 2. 並行處理

```python
from concurrent.futures import ThreadPoolExecutor, as_completed
from typing import List

def parallel_processing(items: List[str]) -> List[str]:
    """並行處理多個項目"""

    def process_item(item):
        from langchain_openai import ChatOpenAI
        llm = ChatOpenAI(model="gpt-4")
        return llm.invoke(f"處理: {item}").content

    results = []

    with ThreadPoolExecutor(max_workers=5) as executor:
        # 提交所有任務
        futures = {
            executor.submit(process_item, item): item
            for item in items
        }

        # 收集結果
        for future in as_completed(futures):
            try:
                result = future.result()
                results.append(result)
            except Exception as e:
                print(f"處理失敗: {e}")

    return results

# 使用
items = ["項目1", "項目2", "項目3"]
results = parallel_processing(items)
```

### 3. 流式輸出

```python
from langchain_openai import ChatOpenAI

def streaming_response(prompt: str):
    """流式輸出,即時顯示結果"""
    llm = ChatOpenAI(model="gpt-4", streaming=True)

    print("回應: ", end="", flush=True)
    for chunk in llm.stream(prompt):
        print(chunk.content, end="", flush=True)
    print()  # 換行

# 使用
streaming_response("寫一首詩")
```

### 4. 批次處理

```python
def batch_processing(queries: List[str], batch_size: int = 5):
    """批次處理查詢"""
    from langchain_openai import ChatOpenAI

    llm = ChatOpenAI(model="gpt-4")
    results = []

    for i in range(0, len(queries), batch_size):
        batch = queries[i:i+batch_size]

        # 合併查詢
        combined_prompt = "\n".join([
            f"{idx}. {q}"
            for idx, q in enumerate(batch, 1)
        ])

        # 單次調用處理多個
        response = llm.invoke(f"回答以下問題:\n{combined_prompt}")

        results.extend(response.content.split('\n'))

    return results
```

### 5. Token 優化

```python
def optimize_tokens(text: str, max_tokens: int = 1000) -> str:
    """優化 token 使用"""
    from tiktoken import encoding_for_model

    enc = encoding_for_model("gpt-4")
    tokens = enc.encode(text)

    if len(tokens) <= max_tokens:
        return text

    # 截斷並保留重要部分
    truncated = tokens[:max_tokens]
    return enc.decode(truncated)

# 使用
long_text = "..." * 10000
optimized = optimize_tokens(long_text, max_tokens=500)
```

### 6. 模型選擇策略

```python
def smart_model_selection(query: str, complexity: str):
    """根據複雜度選擇模型"""
    from langchain_openai import ChatOpenAI

    if complexity == "simple":
        llm = ChatOpenAI(model="gpt-3.5-turbo")  # 快速便宜
    elif complexity == "medium":
        llm = ChatOpenAI(model="gpt-4-turbo")
    else:
        llm = ChatOpenAI(model="gpt-4")  # 最強但慢

    return llm.invoke(query).content
```

## 完整優化案例

```python
from typing import TypedDict
from langgraph.graph import StateGraph, END
from functools import lru_cache
import time

class OptimizedState(TypedDict):
    query: str
    result: str
    cache_hit: bool
    processing_time: float

# 快取裝飾器
@lru_cache(maxsize=100)
def cached_process(query: str) -> str:
    from langchain_openai import ChatOpenAI
    llm = ChatOpenAI(model="gpt-3.5-turbo")  # 使用較快的模型
    return llm.invoke(query).content

def optimized_node(state: OptimizedState) -> OptimizedState:
    start = time.time()

    # 嘗試從快取取得
    result = cached_process(state["query"])

    processing_time = time.time() - start

    return {
        "result": result,
        "processing_time": processing_time
    }

# 建立優化的 Agent
def create_optimized_agent():
    workflow = StateGraph(OptimizedState)
    workflow.add_node("process", optimized_node)
    workflow.set_entry_point("process")
    workflow.add_edge("process", END)
    return workflow.compile()

# 使用並測試
if __name__ == "__main__":
    agent = create_optimized_agent()

    # 第一次查詢
    result1 = agent.invoke({"query": "什麼是 AI?"})
    print(f"第一次: {result1['processing_time']:.2f}秒")

    # 相同查詢(快取)
    result2 = agent.invoke({"query": "什麼是 AI?"})
    print(f"第二次(快取): {result2['processing_time']:.2f}秒")
```

## 效能監控

```python
import time
from functools import wraps

def monitor_performance(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        duration = time.time() - start

        print(f"⏱️ {func.__name__}: {duration:.2f}秒")
        return result
    return wrapper

@monitor_performance
def slow_operation():
    time.sleep(2)
    return "完成"
```

## 重點整理

### 核心優化技術
- **快取**: 避免重複計算
- **並行**: 同時處理多個任務
- **流式**: 即時輸出提升體驗
- **批次**: 減少 API 調用次數

### 選擇策略
- 簡單任務用快速模型
- 複雜任務用強大模型
- 根據預算和時間要求調整

### 監控指標
- 回應時間
- Token 使用量
- 快取命中率
- 錯誤率

## 最終建議

1. **量測優先**: 先測量再優化
2. **逐步改進**: 一次優化一個瓶頸
3. **平衡取捨**: 速度 vs 品質 vs 成本
4. **持續監控**: 生產環境持續追蹤

## 練習

1. 為你的 Agent 添加快取機制
2. 實作並行處理提升吞吐量
3. 建立效能監控儀表板

---

## 🎉 課程完成!

恭喜您完成所有 12 章的學習!現在您已經掌握:

✅ LangGraph 核心概念
✅ 狀態管理和工作流程設計
✅ 實際 Agent 開發
✅ 生產環境最佳實踐

### 下一步行動

1. **實踐專案**: 建立自己的 Agent 應用
2. **深入研究**: 探索 LangGraph 進階功能
3. **社群交流**: 分享經驗和學習成果
4. **持續學習**: 關注 LangGraph 更新

祝您在 AI Agent 開發之路上順利!🚀
