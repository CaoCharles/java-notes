# 工具調用整合

本章學習如何讓 Agent 使用外部工具,擴展 Agent 的能力。

## 工具類型

1. **資料庫查詢**
2. **API 調用**
3. **文件操作**
4. **計算工具**

## 完整實作:保單查詢 Agent

```python
from typing import TypedDict, Annotated, List
from operator import add
from langgraph.graph import StateGraph, END
from langgraph.prebuilt import ToolExecutor
from langchain_openai import ChatOpenAI
from langchain.tools import Tool
from dotenv import load_dotenv

load_dotenv()

# 定義工具
def search_policy(policy_number: str) -> dict:
    """查詢保單資訊"""
    # 模擬資料庫查詢
    db = {
        "P123456": {
            "holder": "王大明",
            "type": "終身壽險",
            "amount": 1000000,
            "status": "有效"
        }
    }
    return db.get(policy_number, {"error": "查無此保單"})

def calculate_premium(age: int, amount: int) -> dict:
    """計算保費"""
    base_rate = 0.01
    age_factor = 1 + (age - 30) * 0.01
    annual_premium = amount * base_rate * age_factor
    return {
        "annual_premium": round(annual_premium, 2),
        "monthly_premium": round(annual_premium / 12, 2)
    }

def send_notification(customer_id: str, message: str) -> str:
    """發送通知"""
    # 模擬發送
    return f"✅ 已發送通知給客戶 {customer_id}"

# 建立工具列表
tools = [
    Tool(
        name="search_policy",
        func=search_policy,
        description="查詢保單資訊。輸入保單號碼。"
    ),
    Tool(
        name="calculate_premium",
        func=lambda x: calculate_premium(*map(int, x.split(','))),
        description="計算保費。輸入: age,amount (例如: 35,1000000)"
    ),
    Tool(
        name="send_notification",
        func=lambda x: send_notification(*x.split(',')),
        description="發送通知。輸入: customer_id,message"
    )
]

tool_executor = ToolExecutor(tools)

# 狀態定義
class ToolAgentState(TypedDict):
    user_query: str
    messages: Annotated[List, add]
    tool_results: Annotated[List[dict], add]
    final_response: str

# 節點:決定使用什麼工具
def agent_node(state: ToolAgentState):
    llm = ChatOpenAI(model="gpt-4")
    llm_with_tools = llm.bind_tools(tools)

    messages = [{"role": "user", "content": state["user_query"]}]
    response = llm_with_tools.invoke(messages)

    return {"messages": [response]}

# 節點:執行工具
def tool_node(state: ToolAgentState):
    last_message = state["messages"][-1]

    if hasattr(last_message, "tool_calls") and last_message.tool_calls:
        results = []
        for tool_call in last_message.tool_calls:
            result = tool_executor.invoke(tool_call)
            results.append({
                "tool": tool_call["name"],
                "result": result
            })

        return {"tool_results": results}

    return {}

# 節點:生成最終回應
def respond_node(state: ToolAgentState):
    llm = ChatOpenAI(model="gpt-4")

    tool_results_text = "\n".join([
        f"工具 {r['tool']}: {r['result']}"
        for r in state["tool_results"]
    ])

    prompt = f"""
    使用者問題: {state['user_query']}

    工具執行結果:
    {tool_results_text}

    請根據結果回答使用者。
    """

    response = llm.invoke(prompt).content

    return {"final_response": response}

# 路由
def should_use_tools(state: ToolAgentState) -> str:
    last_message = state["messages"][-1]

    if hasattr(last_message, "tool_calls") and last_message.tool_calls:
        return "tools"
    return "respond"

# 建立 Agent
def create_tool_agent():
    workflow = StateGraph(ToolAgentState)

    workflow.add_node("agent", agent_node)
    workflow.add_node("tools", tool_node)
    workflow.add_node("respond", respond_node)

    workflow.set_entry_point("agent")

    workflow.add_conditional_edges(
        "agent",
        should_use_tools,
        {
            "tools": "tools",
            "respond": "respond"
        }
    )

    workflow.add_edge("tools", "respond")
    workflow.add_edge("respond", END)

    return workflow.compile()

# 使用
if __name__ == "__main__":
    agent = create_tool_agent()

    queries = [
        "查詢保單 P123456 的資訊",
        "35 歲購買 100 萬保額需要多少保費?",
    ]

    for query in queries:
        print(f"\n用戶: {query}")
        result = agent.invoke({
            "user_query": query,
            "messages": [],
            "tool_results": []
        })
        print(f"回應: {result['final_response']}")
```

## 重點整理

- 使用 `Tool` 定義外部功能
- `ToolExecutor` 執行工具調用
- Agent 自動決定何時使用工具
- 整合工具結果生成回應

## 練習

建立包含至少 5 個實用工具的 Agent,如天氣查詢、匯率轉換等。
