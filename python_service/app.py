import os
from typing import Literal

from fastapi import FastAPI, HTTPException
from langchain_openai import ChatOpenAI
from pydantic import BaseModel, Field
from dotenv import load_dotenv


load_dotenv()


class AnalyzeRequest(BaseModel):
    logText: str = Field(..., description="The raw log text to analyze.")


class AnalyzeResponse(BaseModel):
    summary: str = Field(..., description="A concise Korean summary of the issue.")
    cause: str = Field(..., description="The most likely root cause in Korean.")
    solutions: list[str] = Field(
        ..., description="A short list of actionable remediation steps in Korean."
    )
    severity: Literal["LOW", "MEDIUM", "HIGH", "CRITICAL"] = Field(
        ..., description="Estimated severity level."
    )


app = FastAPI(title="LogInsight Python Analyzer", version="1.0.0")


def build_llm() -> ChatOpenAI:
    api_key = os.getenv("OPENAI_API_KEY")
    if not api_key:
        raise RuntimeError("OPENAI_API_KEY is not set.")

    model = os.getenv("OPENAI_MODEL", "gpt-4o-mini")
    return ChatOpenAI(
        model=model,
        temperature=0,
        api_key=api_key,
    )


@app.get("/health")
def health() -> dict[str, str]:
    return {"status": "ok"}


@app.post("/analyze", response_model=AnalyzeResponse)
def analyze(request: AnalyzeRequest) -> AnalyzeResponse:
    log_text = request.logText.strip()
    if not log_text:
        raise HTTPException(status_code=400, detail="logText must not be blank.")

    try:
        llm = build_llm()
        structured_llm = llm.with_structured_output(
            AnalyzeResponse,
            method="json_schema",
            strict=True,
        )

        result = structured_llm.invoke(
            [
                (
                    "system",
                    "You are a senior SRE and backend incident analyst. "
                    "Analyze the given log text and return only the structured schema. "
                    "Write all fields in Korean except severity, which must be one of "
                    "LOW, MEDIUM, HIGH, CRITICAL. "
                    "Prefer practical, concrete remediation steps.",
                ),
                (
                    "human",
                    f"Analyze this log text:\n\n{log_text}",
                ),
            ]
        )
    except Exception as exc:
        raise HTTPException(status_code=500, detail=f"LLM analysis failed: {exc}") from exc

    return result


if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)