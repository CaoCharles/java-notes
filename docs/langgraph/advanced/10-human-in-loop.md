# Human-in-the-Loop 模式

Human-in-the-Loop (人機協作) 讓 Agent 在關鍵決策點暫停,等待人工審核和介入。

## 核心概念

1. **檢查點 (Checkpoints)**: 暫停執行的位置
2. **審核機制**: 人工檢查和批准
3. **繼續執行**: 根據人工決定繼續

## 完整實作:理賠審核系統

```python
from typing import TypedDict, Annotated, Optional
from operator import add
from langgraph.graph import StateGraph, END
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
import time

load_dotenv()

class ClaimState(TypedDict):
    # 理賠資訊
    claim_id: str
    claim_amount: float
    claim_type: str

    # 自動審核結果
    auto_decision: str  # "approve", "reject", "human_review"
    risk_score: float

    # 人工審核
    requires_human: bool
    human_decision: Optional[str]
    human_notes: Optional[str]

    # 最終結果
    final_decision: str
    approved_amount: Optional[float]

def auto_review_node(state: ClaimState) -> ClaimState:
    """自動審核"""
    llm = ChatOpenAI(model="gpt-4")

    prompt = f"""
    審核理賠申請:
    - 金額: ${state['claim_amount']}
    - 類型: {state['claim_type']}

    評估風險分數 (0-1) 和建議決策。
    回答格式: {{"risk_score": 0.3, "decision": "approve/reject/human_review"}}
    """

    import json
    result = json.loads(llm.invoke(prompt).content)

    requires_human = (
        result["risk_score"] > 0.7 or
        state["claim_amount"] > 100000 or
        result["decision"] == "human_review"
    )

    return {
        "risk_score": result["risk_score"],
        "auto_decision": result["decision"],
        "requires_human": requires_human
    }

def human_review_checkpoint(state: ClaimState) -> ClaimState:
    """人工審核檢查點"""
    print("\n" + "="*50)
    print("⏸️  等待人工審核...")
    print(f"理賠編號: {state['claim_id']}")
    print(f"理賠金額: ${state['claim_amount']:,.0f}")
    print(f"自動決策: {state['auto_decision']}")
    print(f"風險分數: {state['risk_score']:.2f}")
    print("="*50)

    # 模擬人工審核 (實際應用中這裡會暫停等待)
    decision = input("審核決定 (approve/reject/modify): ").strip()

    notes = ""
    approved_amt = state["claim_amount"]

    if decision == "modify":
        approved_amt = float(input("核准金額: "))
        notes = input("審核備註: ")

    return {
        "human_decision": decision,
        "human_notes": notes,
        "approved_amount": approved_amt if decision in ["approve", "modify"] else 0
    }

def finalize_node(state: ClaimState) -> ClaimState:
    """最終決策"""
    if state["requires_human"]:
        final = state["human_decision"]
    else:
        final = state["auto_decision"]

    return {"final_decision": final}

def route_after_auto_review(state: ClaimState) -> str:
    if state["requires_human"]:
        return "human"
    return "finalize"

# 建立工作流程
def create_claim_agent():
    workflow = StateGraph(ClaimState)

    workflow.add_node("auto_review", auto_review_node)
    workflow.add_node("human_review", human_review_checkpoint)
    workflow.add_node("finalize", finalize_node)

    workflow.set_entry_point("auto_review")

    workflow.add_conditional_edges(
        "auto_review",
        route_after_auto_review,
        {
            "human": "human_review",
            "finalize": "finalize"
        }
    )

    workflow.add_edge("human_review", "finalize")
    workflow.add_edge("finalize", END)

    return workflow.compile()

# 使用
if __name__ == "__main__":
    agent = create_claim_agent()

    # 測試案例
    test_claims = [
        {"claim_id": "C001", "claim_amount": 50000, "claim_type": "醫療"},
        {"claim_id": "C002", "claim_amount": 150000, "claim_type": "意外"},  # 需要人工
    ]

    for claim in test_claims:
        print(f"\n\n處理理賠: {claim['claim_id']}")
        result = agent.invoke(claim)

        print(f"\n最終決策: {result['final_decision']}")
        if result.get("approved_amount"):
            print(f"核准金額: ${result['approved_amount']:,.0f}")
```

## 進階功能

### 1. 異步審核隊列

```python
from queue import Queue

approval_queue = Queue()

def async_approval_node(state):
    # 放入審核隊列
    approval_queue.put(state)

    # 等待審核結果
    while True:
        if hasattr(state, 'human_decision'):
            break
        time.sleep(1)

    return state
```

### 2. 多級審核

```python
def multi_level_approval(state):
    if state["claim_amount"] < 50000:
        return "level1"  # 專員審核
    elif state["claim_amount"] < 200000:
        return "level2"  # 主管審核
    else:
        return "level3"  # 經理審核
```

## 重點整理

- 關鍵決策點需要人工介入
- 提供完整資訊供審核
- 支持修改和批注
- 記錄審核歷程

## 練習

建立包含三級審核的費用報銷系統。
