# 建立第一個 LangGraph Agent

## 概述

本章將帶您從零開始建立第一個完整的 LangGraph Agent。透過實際動手操作,您將掌握 Agent 開發的完整流程。

## 環境準備

### 安裝套件

```bash
# 建立專案目錄
mkdir my-first-langgraph-agent
cd my-first-langgraph-agent

# 建立虛擬環境
python -m venv venv
source venv/bin/activate  # Windows: venv\Scripts\activate

# 安裝必要套件
pip install langgraph langchain langchain-openai python-dotenv
```

### 設定環境變數

建立 `.env` 檔案:

```bash
# .env
OPENAI_API_KEY=sk-your-api-key-here
```

### 驗證安裝

```python
# test_setup.py
from langchain_openai import ChatOpenAI
from langgraph.graph import StateGraph
from dotenv import load_dotenv

load_dotenv()

# 測試 LLM
llm = ChatOpenAI(model="gpt-4")
print("✅ LLM 連線成功")

# 測試 LangGraph
from typing import TypedDict
class TestState(TypedDict):
    message: str

workflow = StateGraph(TestState)
print("✅ LangGraph 導入成功")

print("\n🎉 環境設定完成!")
```

## Hello World Agent

讓我們建立最簡單的 Agent:

```python
# hello_agent.py
from typing import TypedDict
from langgraph.graph import StateGraph, END
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

# 載入環境變數
load_dotenv()

# 1. 定義狀態
class HelloState(TypedDict):
    user_name: str
    greeting: str

# 2. 定義節點函數
def generate_greeting(state: HelloState) -> HelloState:
    """生成個性化問候"""
    llm = ChatOpenAI(model="gpt-4")

    prompt = f"用友善的方式向 {state['user_name']} 打招呼,用繁體中文。"
    response = llm.invoke(prompt)

    return {"greeting": response.content}

# 3. 建立工作流程
workflow = StateGraph(HelloState)

# 添加節點
workflow.add_node("greet", generate_greeting)

# 設定流程
workflow.set_entry_point("greet")
workflow.add_edge("greet", END)

# 4. 編譯
app = workflow.compile()

# 5. 執行
if __name__ == "__main__":
    result = app.invoke({"user_name": "小明"})
    print(f"問候語: {result['greeting']}")
```

執行:

```bash
python hello_agent.py
```

輸出範例:

```
問候語: 你好小明!很高興認識你,希望今天一切順利!
```

## 實戰案例:智能客服助手

現在讓我們建立一個更實用的客服 Agent:

### 完整程式碼

```python
# customer_service_agent.py
from typing import TypedDict, Annotated, Optional
from operator import add
from langgraph.graph import StateGraph, END
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
import logging

# 設定日誌
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

load_dotenv()

# ===== 狀態定義 =====
class CustomerServiceState(TypedDict):
    """客服 Agent 狀態"""
    # 使用者輸入
    user_message: str

    # 對話歷史
    conversation_history: Annotated[list[dict], add]

    # 意圖識別
    intent: str
    intent_confidence: float

    # 處理結果
    response: str
    requires_human: bool

# ===== 節點函數 =====
def recognize_intent(state: CustomerServiceState) -> CustomerServiceState:
    """識別使用者意圖"""
    logger.info("執行意圖識別...")

    llm = ChatOpenAI(model="gpt-4", temperature=0)

    prompt = f"""
    分析以下客戶訊息的意圖:
    "{state['user_message']}"

    可能的意圖:
    - greeting: 打招呼
    - question: 詢問問題
    - complaint: 投訴抱怨
    - thanks: 道謝

    以 JSON 格式回答: {{"intent": "...", "confidence": 0.95}}
    只回答 JSON,不要其他內容。
    """

    import json
    try:
        response = llm.invoke(prompt).content
        # 移除可能的 markdown 格式
        response = response.strip()
        if response.startswith("```json"):
            response = response[7:-3].strip()
        elif response.startswith("```"):
            response = response[3:-3].strip()

        result = json.loads(response)
        logger.info(f"意圖: {result['intent']}, 信心度: {result['confidence']}")

        return {
            "intent": result["intent"],
            "intent_confidence": result["confidence"],
            "conversation_history": [{
                "role": "system",
                "content": f"意圖識別: {result['intent']}"
            }]
        }
    except Exception as e:
        logger.error(f"意圖識別失敗: {e}")
        return {
            "intent": "question",
            "intent_confidence": 0.5,
            "conversation_history": [{
                "role": "system",
                "content": "意圖識別失敗,使用預設意圖"
            }]
        }

def handle_greeting(state: CustomerServiceState) -> CustomerServiceState:
    """處理打招呼"""
    logger.info("處理打招呼...")

    response = "您好!我是國泰人壽智能客服助手,很高興為您服務。請問有什麼可以幫助您的嗎?"

    return {
        "response": response,
        "requires_human": False,
        "conversation_history": [{
            "role": "assistant",
            "content": response
        }]
    }

def handle_question(state: CustomerServiceState) -> CustomerServiceState:
    """處理一般問題"""
    logger.info("處理一般問題...")

    llm = ChatOpenAI(model="gpt-4")

    # 準備上下文
    context = "\n".join([
        f"{msg['role']}: {msg['content']}"
        for msg in state["conversation_history"]
    ])

    prompt = f"""
    你是國泰人壽的客服助手。請根據以下對話歷史回答客戶問題。

    對話歷史:
    {context}

    客戶問題: {state['user_message']}

    請提供專業、友善的回答。如果不確定,建議客戶聯絡人工客服。
    """

    response = llm.invoke(prompt).content

    return {
        "response": response,
        "requires_human": False,
        "conversation_history": [{
            "role": "user",
            "content": state["user_message"]
        }, {
            "role": "assistant",
            "content": response
        }]
    }

def handle_complaint(state: CustomerServiceState) -> CustomerServiceState:
    """處理投訴"""
    logger.info("處理投訴...")

    response = f"""
    非常抱歉給您帶來不便。我們非常重視您的意見。

    您的投訴已經記錄,我們將盡快為您安排專人處理。
    同時,我也會立即為您轉接人工客服,以便更好地解決您的問題。

    再次為造成的困擾致歉!
    """

    return {
        "response": response,
        "requires_human": True,  # 投訴需要人工介入
        "conversation_history": [{
            "role": "assistant",
            "content": response
        }]
    }

def handle_thanks(state: CustomerServiceState) -> CustomerServiceState:
    """處理道謝"""
    logger.info("處理道謝...")

    response = "不客氣!很高興能為您服務。如果還有其他問題,隨時歡迎詢問。祝您有美好的一天!"

    return {
        "response": response,
        "requires_human": False,
        "conversation_history": [{
            "role": "assistant",
            "content": response
        }]
    }

# ===== 路由函數 =====
def route_by_intent(state: CustomerServiceState) -> str:
    """根據意圖路由"""
    intent = state["intent"]

    # 信心度太低,轉人工
    if state["intent_confidence"] < 0.7:
        logger.warning(f"信心度過低 ({state['intent_confidence']}),轉人工處理")
        return "complaint"  # 保守處理,轉投訴流程

    routing = {
        "greeting": "greeting",
        "question": "question",
        "complaint": "complaint",
        "thanks": "thanks"
    }

    return routing.get(intent, "question")  # 預設當作問題處理

# ===== 建立 Agent =====
def create_customer_service_agent():
    """建立客服 Agent"""
    workflow = StateGraph(CustomerServiceState)

    # 添加節點
    workflow.add_node("recognize", recognize_intent)
    workflow.add_node("greeting", handle_greeting)
    workflow.add_node("question", handle_question)
    workflow.add_node("complaint", handle_complaint)
    workflow.add_node("thanks", handle_thanks)

    # 設定流程
    workflow.set_entry_point("recognize")

    # 條件路由
    workflow.add_conditional_edges(
        "recognize",
        route_by_intent,
        {
            "greeting": "greeting",
            "question": "question",
            "complaint": "complaint",
            "thanks": "thanks"
        }
    )

    # 所有路徑都結束
    workflow.add_edge("greeting", END)
    workflow.add_edge("question", END)
    workflow.add_edge("complaint", END)
    workflow.add_edge("thanks", END)

    return workflow.compile()

# ===== 主程式 =====
def main():
    """主程式"""
    print("=== 國泰人壽智能客服系統 ===\n")

    agent = create_customer_service_agent()

    # 測試案例
    test_cases = [
        "你好,請問你是誰?",
        "我想詢問關於壽險的保障範圍",
        "你們的服務態度太差了!我要投訴!",
        "謝謝你的幫助!"
    ]

    for i, message in enumerate(test_cases, 1):
        print(f"\n[測試 {i}]")
        print(f"客戶: {message}")

        result = agent.invoke({
            "user_message": message,
            "conversation_history": []
        })

        print(f"意圖: {result['intent']} (信心度: {result['intent_confidence']:.2f})")
        print(f"助手: {result['response']}")
        print(f"需要人工: {'是' if result['requires_human'] else '否'}")
        print("-" * 50)

if __name__ == "__main__":
    main()
```

### 執行結果

```bash
python customer_service_agent.py
```

輸出範例:

```
=== 國泰人壽智能客服系統 ===

[測試 1]
客戶: 你好,請問你是誰?
意圖: greeting (信心度: 0.95)
助手: 您好!我是國泰人壽智能客服助手,很高興為您服務。請問有什麼可以幫助您的嗎?
需要人工: 否
--------------------------------------------------

[測試 2]
客戶: 我想詢問關於壽險的保障範圍
意圖: question (信心度: 0.92)
助手: 壽險的保障範圍主要包括:
1. 身故保障:被保險人身故時,給付保險金給受益人
2. 全殘保障:因疾病或意外導致全殘時的保障
3. 部分險種還包含重大疾病、醫療等附加保障

具體保障內容會依據您選擇的保單而有所不同。建議您提供保單號碼,我可以為您查詢詳細內容,或為您轉接專員進行諮詢。
需要人工: 否
--------------------------------------------------

[測試 3]
客戶: 你們的服務態度太差了!我要投訴!
意圖: complaint (信心度: 0.98)
助手: 非常抱歉給您帶來不便。我們非常重視您的意見。

您的投訴已經記錄,我們將盡快為您安排專人處理。
同時,我也會立即為您轉接人工客服,以便更好地解決您的問題。

再次為造成的困擾致歉!
需要人工: 是
--------------------------------------------------

[測試 4]
客戶: 謝謝你的幫助!
意圖: thanks (信心度: 0.99)
助手: 不客氣!很高興能為您服務。如果還有其他問題,隨時歡迎詢問。祝您有美好的一天!
需要人工: 否
--------------------------------------------------
```

## 可視化 Agent 流程

### 生成流程圖

```python
# visualize_agent.py
from customer_service_agent import create_customer_service_agent

agent = create_customer_service_agent()

# 取得圖形
graph = agent.get_graph()

# 生成 Mermaid 圖
mermaid_png = graph.draw_mermaid_png()

# 儲存圖片
with open("agent_flowchart.png", "wb") as f:
    f.write(mermaid_png)

print("✅ 流程圖已儲存為 agent_flowchart.png")
```

## 除錯技巧

### 1. 啟用詳細日誌

```python
import logging

logging.basicConfig(
    level=logging.DEBUG,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
```

### 2. 追蹤狀態變化

```python
def debug_node(state: StateType) -> StateType:
    """除錯節點:印出當前狀態"""
    import json
    print("=" * 50)
    print("當前狀態:")
    print(json.dumps(state, indent=2, ensure_ascii=False))
    print("=" * 50)
    return {}

# 在關鍵節點後添加除錯節點
workflow.add_node("debug", debug_node)
workflow.add_edge("recognize", "debug")
workflow.add_edge("debug", "next_node")
```

### 3. 使用 LangSmith 追蹤

```python
# .env
LANGCHAIN_TRACING_V2=true
LANGCHAIN_API_KEY=your-langsmith-key
LANGCHAIN_PROJECT=customer-service-agent

# 程式碼中會自動啟用追蹤
# 前往 https://smith.langchain.com/ 查看執行記錄
```

## 常見問題排查

### 問題 1: API Key 錯誤

```
Error: Incorrect API key provided
```

**解決方案**:
1. 檢查 `.env` 檔案是否存在
2. 確認 API key 格式正確
3. 確保 `load_dotenv()` 在最前面執行

### 問題 2: JSON 解析失敗

```
JSONDecodeError: Expecting value
```

**解決方案**:
```python
# 加入錯誤處理
try:
    result = json.loads(response)
except json.JSONDecodeError:
    # 使用預設值
    result = {"intent": "question", "confidence": 0.5}
```

### 問題 3: 節點返回值錯誤

```
KeyError: 'expected_field'
```

**解決方案**:
- 確保節點返回的字典包含所有需要的欄位
- 使用 Optional 標記可選欄位

## 改進建議

### 1. 添加記憶功能

```python
class StatefulAgent:
    def __init__(self):
        self.agent = create_customer_service_agent()
        self.conversation_history = []

    def chat(self, message: str):
        result = self.agent.invoke({
            "user_message": message,
            "conversation_history": self.conversation_history
        })

        # 保存對話歷史
        self.conversation_history.extend(result["conversation_history"])

        return result["response"]
```

### 2. 添加錯誤重試

```python
from tenacity import retry, stop_after_attempt, wait_exponential

@retry(stop=stop_after_attempt(3), wait=wait_exponential(multiplier=1, min=2, max=10))
def recognize_intent_with_retry(state):
    return recognize_intent(state)
```

### 3. 添加效能監控

```python
import time

def timed_node(func):
    def wrapper(state):
        start = time.time()
        result = func(state)
        duration = time.time() - start
        logger.info(f"{func.__name__} 執行時間: {duration:.2f}秒")
        return result
    return wrapper

@timed_node
def recognize_intent(state):
    # ... 原有程式碼 ...
```

## 重點整理

1. **環境設定**
   - 安裝必要套件
   - 設定環境變數
   - 驗證安裝

2. **Agent 結構**
   - 定義狀態 (TypedDict)
   - 實作節點函數
   - 設定路由邏輯
   - 編譯和執行

3. **最佳實踐**
   - 完善的錯誤處理
   - 詳細的日誌記錄
   - 清晰的狀態流動
   - 充分的測試

4. **除錯技巧**
   - 啟用詳細日誌
   - 追蹤狀態變化
   - 使用視覺化工具

## 練習建議

1. 擴展客服 Agent,增加更多意圖類型
2. 添加資料庫查詢功能
3. 實作多輪對話記憶
4. 整合實際的知識庫

## 下一步

恭喜完成第一個 LangGraph Agent!下一章我們將學習如何建立多步驟推理 Agent,處理更複雜的任務。
