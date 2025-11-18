# 多步驟推理 Agent

多步驟推理 Agent 能夠將複雜問題分解為多個步驟,逐步解決。本章將建立一個完整的分析型 Agent。

## 核心概念

多步驟推理的關鍵:
1. 問題分解
2. 逐步執行
3. 結果整合
4. 品質驗證

## 完整實作:業績分析 Agent

```python
from typing import TypedDict, Annotated, List
from operator import add
from langgraph.graph import StateGraph, END
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()

class AnalysisState(TypedDict):
    # 輸入
    query: str
    raw_data: dict

    # 分解步驟
    sub_tasks: List[str]
    current_task_index: int

    # 中間結果
    task_results: Annotated[List[dict], add]

    # 最終輸出
    final_analysis: str
    confidence: float

# 步驟 1: 分解任務
def decompose_task(state: AnalysisState) -> AnalysisState:
    llm = ChatOpenAI(model="gpt-4")

    prompt = f"""
    將以下分析需求分解為 3-5 個具體步驟:
    需求: {state['query']}

    以列表形式回答,每行一個步驟。
    """

    response = llm.invoke(prompt).content
    tasks = [line.strip() for line in response.split('\n') if line.strip() and not line.strip().startswith('#')]

    return {
        "sub_tasks": tasks[:5],  # 最多 5 個步驟
        "current_task_index": 0
    }

# 步驟 2: 執行單一任務
def execute_task(state: AnalysisState) -> AnalysisState:
    llm = ChatOpenAI(model="gpt-4")

    idx = state["current_task_index"]
    task = state["sub_tasks"][idx]

    # 準備上下文
    previous_results = "\n".join([
        f"步驟 {i+1}: {r['result']}"
        for i, r in enumerate(state.get("task_results", []))
    ])

    prompt = f"""
    執行以下分析步驟:
    當前任務: {task}

    可用數據: {state['raw_data']}

    之前的分析結果:
    {previous_results}

    請提供分析結果。
    """

    result = llm.invoke(prompt).content

    return {
        "task_results": [{
            "task": task,
            "result": result,
            "index": idx
        }],
        "current_task_index": idx + 1
    }

# 步驟 3: 整合結果
def synthesize_results(state: AnalysisState) -> AnalysisState:
    llm = ChatOpenAI(model="gpt-4")

    all_results = "\n\n".join([
        f"**{r['task']}**\n{r['result']}"
        for r in state["task_results"]
    ])

    prompt = f"""
    綜合以下分析結果,提供最終結論:

    原始問題: {state['query']}

    分析過程:
    {all_results}

    請提供:
    1. 綜合結論
    2. 關鍵發現
    3. 建議行動
    """

    final = llm.invoke(prompt).content

    return {
        "final_analysis": final,
        "confidence": 0.9  # 簡化的信心分數
    }

# 路由函數
def should_continue(state: AnalysisState) -> str:
    if state["current_task_index"] >= len(state["sub_tasks"]):
        return "synthesize"
    return "execute"

# 建立 Agent
def create_analysis_agent():
    workflow = StateGraph(AnalysisState)

    workflow.add_node("decompose", decompose_task)
    workflow.add_node("execute", execute_task)
    workflow.add_node("synthesize", synthesize_results)

    workflow.set_entry_point("decompose")
    workflow.add_edge("decompose", "execute")

    workflow.add_conditional_edges(
        "execute",
        should_continue,
        {
            "execute": "execute",
            "synthesize": "synthesize"
        }
    )

    workflow.add_edge("synthesize", END)

    return workflow.compile()

# 使用範例
if __name__ == "__main__":
    agent = create_analysis_agent()

    result = agent.invoke({
        "query": "分析國泰人壽 2024 Q1 業績表現",
        "raw_data": {
            "total_premium": 1500000000,
            "new_policies": 5000,
            "customer_satisfaction": 4.2
        },
        "task_results": []
    })

    print("=== 分析結果 ===")
    print(f"\n步驟分解: {result['sub_tasks']}")
    print(f"\n最終分析:\n{result['final_analysis']}")
```

## 重點整理

- 使用循環執行多個步驟
- 累積中間結果
- 最終整合所有發現
- 可視化推理過程

## 練習

建立一個「市場調研 Agent」,分析競爭對手並提出策略建議。
