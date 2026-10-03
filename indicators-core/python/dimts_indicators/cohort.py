"""Cohort isolation checks (ADR-014): STEM adults and early-grade children never mix."""
from __future__ import annotations

SUPPORTED_COHORTS = ("early_grade", "undergraduate_stem")
EARLY_GRADE_GRADES = ("O", "1", "2", "3")


def validate_context(request: dict) -> dict:
    """Validate a session context. Unknown cohorts never fall back to early grade."""
    cohort = request.get("cohort")
    if cohort not in SUPPORTED_COHORTS:
        return {"valid": False, "reason": "unsupported_cohort"}
    if cohort == "undergraduate_stem":
        # An adult trial must not carry a child grade/subject context.
        if "grade" in request or "subject" in request:
            return {"valid": False, "reason": "cohort_context_mismatch"}
        return {"valid": True}
    if request.get("session_type") != "early_grade_lesson" or "course_category" in request:
        return {"valid": False, "reason": "cohort_context_mismatch"}
    if str(request.get("grade", "")) not in EARLY_GRADE_GRADES:
        return {"valid": False, "reason": "cohort_context_mismatch"}
    return {"valid": True}


def validate_model(request: dict) -> dict:
    """A model is usable only for cohorts it declares; logits are never relabelled."""
    model = request.get("model") or {}
    if request.get("cohort") not in (model.get("supported_cohorts") or []):
        return {"valid": False, "reason": "unsupported_cohort"}
    return {"valid": True}
