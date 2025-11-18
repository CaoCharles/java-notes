# 錯誤處理與重試機制

建立健壯的 Agent 需要完善的錯誤處理。本章介紹常見錯誤類型和處理策略。

## 常見錯誤類型

1. **API 錯誤**: 超時、限流、無效回應
2. **資料錯誤**: 格式錯誤、缺失欄位
3. **邏輯錯誤**: 無法完成任務
4. **資源錯誤**: 配額超限

## 完整實作:健壯的查詢 Agent

```python
from typing import TypedDict, Optional
from langgraph.graph import StateGraph, END
from langchain_openai import ChatOpenAI
from tenacity import retry, stop_after_attempt, wait_exponential
from dotenv import load_dotenv
import logging

load_dotenv()
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

class RobustState(TypedDict):
    query: str
    result: Optional[str]
    error: Optional[str]
    retry_count: int
    max_retries: int

# 帶重試的 LLM 調用
@retry(
    stop=stop_after_attempt(3),
    wait=wait_exponential(multiplier=1, min=2, max=10)
)
def call_llm_with_retry(prompt: str) -> str:
    llm = ChatOpenAI(model="gpt-4", timeout=30)
    return llm.invoke(prompt).content

def query_node(state: RobustState) -> RobustState:
    """查詢節點with錯誤處理"""
    try:
        logger.info(f"執行查詢,嘗試次數: {state['retry_count'] + 1}")

        # 調用 LLM
        result = call_llm_with_retry(state["query"])

        # 驗證結果
        if not result or len(result) < 10:
            raise ValueError("回應內容過短")

        return {
            "result": result,
            "error": None,
            "retry_count": 0
        }

    except Exception as e:
        error_msg = str(e)
        logger.error(f"查詢失敗: {error_msg}")

        return {
            "error": error_msg,
            "retry_count": state["retry_count"] + 1
        }

def fallback_node(state: RobustState) -> RobustState:
    """降級處理"""
    logger.warning("使用降級回應")

    fallback_response = f"很抱歉,系統暫時無法處理您的請求:{state['query']}"
    fallback_response += "\n請稍後再試或聯絡客服。"

    return {
        "result": fallback_response,
        "error": None
    }

def error_analysis_node(state: RobustState) -> RobustState:
    """錯誤分析"""
    error = state["error"]

    # 分析錯誤類型
    if "timeout" in error.lower():
        logger.error("超時錯誤")
    elif "rate limit" in error.lower():
        logger.error("API 限流")
    elif "invalid" in error.lower():
        logger.error("無效請求")

    return {}

def route_after_query(state: RobustState) -> str:
    # 成功
    if state.get("result") and not state.get("error"):
        return "success"

    # 達到最大重試次數
    if state["retry_count"] >= state["max_retries"]:
        return "fallback"

    # 繼續重試
    return "retry"

# 建立 Agent
def create_robust_agent():
    workflow = StateGraph(RobustState)

    workflow.add_node("query", query_node)
    workflow.add_node("error_analysis", error_analysis_node)
    workflow.add_node("fallback", fallback_node)

    workflow.set_entry_point("query")

    workflow.add_conditional_edges(
        "query",
        route_after_query,
        {
            "success": END,
            "retry": "error_analysis",
            "fallback": "fallback"
        }
    )

    workflow.add_edge("error_analysis", "query")  # 重試
    workflow.add_edge("fallback", END)

    return workflow.compile()

# 使用
if __name__ == "__main__":
    agent = create_robust_agent()

    result = agent.invoke({
        "query": "分析市場趨勢",
        "retry_count": 0,
        "max_retries": 3
    })

    if result.get("result"):
        print(f"✅ 成功: {result['result']}")
    else:
        print(f"❌ 失敗: {result.get('error')}")
```

## 錯誤處理策略

### 1. 指數退避重試

```python
from tenacity import retry, stop_after_attempt, wait_exponential

@retry(
    stop=stop_after_attempt(5),
    wait=wait_exponential(multiplier=2, min=4, max=60)
)
def resilient_function():
    # 第 1 次失敗後等待 4 秒
    # 第 2 次失敗後等待 8 秒
    # 第 3 次失敗後等待 16 秒
    # ...最多等待 60 秒
    pass
```

### 2. 電路斷路器

```python
class CircuitBreaker:
    def __init__(self, failure_threshold=5):
        self.failure_count = 0
        self.threshold = failure_threshold
        self.is_open = False

    def call(self, func):
        if self.is_open:
            raise Exception("電路已斷開")

        try:
            result = func()
            self.failure_count = 0
            return result
        except:
            self.failure_count += 1
            if self.failure_count >= self.threshold:
                self.is_open = True
            raise
```

### 3. 降級處理

```python
def process_with_fallback(data):
    try:
        return advanced_processing(data)
    except:
        try:
            return standard_processing(data)
        except:
            return basic_processing(data)
```

## 重點整理

- 使用 tenacity 實現重試
- 實作降級機制
- 記錄詳細錯誤日誌
- 提供有意義的錯誤訊息

## 練習

為多步驟 Agent 添加每個步驟的錯誤處理和重試邏輯。
