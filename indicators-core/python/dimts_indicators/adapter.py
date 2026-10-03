"""acceptance-v1 adapter: one JSON request on stdin, one JSON result on stdout.

    python3 tests/acceptance/runner.py \
        --adapter '["python3", "indicators-core/python/dimts_indicators/adapter.py"]'

Logging goes to stderr. Structural errors are returned as ``{"error": ...}`` (exit 0) so the
runner can compare them with the oracle; the adapter never reads the expected fixtures.
"""
from __future__ import annotations

import json
import sys
from pathlib import Path

if __package__ in (None, ""):  # executed as a script path rather than ``-m``
    sys.path.insert(0, str(Path(__file__).resolve().parents[1]))

from dimts_indicators.cohort import validate_context, validate_model  # noqa: E402
from dimts_indicators.indicators import compute_indicators  # noqa: E402
from dimts_indicators.timeline import (  # noqa: E402
    LabelSchemaMismatch,
    TimelineError,
    parse_timeline,
    schema_for_spec,
)

CONTRACT = "acceptance-v1"


def run_indicators(request: dict) -> dict:
    try:
        schema = schema_for_spec(request.get("indicator_spec_version"))
        if request.get("tick_ms") != 10:
            raise TimelineError("unsupported_tick_ms")
        duration = request.get("duration_ticks")
        segments = parse_timeline(request.get("timeline", []), duration, schema)
        return compute_indicators(segments, duration, schema)
    except LabelSchemaMismatch:
        return {"error": "label_schema_mismatch"}
    except TimelineError as exc:
        return {"error": str(exc)}


def handle(request: dict) -> dict:
    if request.get("contract") != CONTRACT:
        return {"error": "unsupported_contract"}
    operation = request.get("operation")
    if operation == "indicators":
        return run_indicators(request)
    if operation == "validate_context":
        return validate_context(request)
    if operation == "validate_model":
        return validate_model(request)
    return {"error": "unsupported_operation"}


def main() -> int:
    try:
        request = json.load(sys.stdin)
        if not isinstance(request, dict):
            raise ValueError("request must be an object")
        result = handle(request)
    except (ValueError, TypeError) as exc:
        print(f"adapter: malformed request: {type(exc).__name__}", file=sys.stderr)
        result = {"error": "malformed_request"}
    json.dump(result, sys.stdout, allow_nan=False)
    return 0


if __name__ == "__main__":
    sys.exit(main())
