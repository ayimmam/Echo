"""Black-box acceptance runner; contains no product/indicator implementation."""
import argparse
import json
import math
from pathlib import Path
import subprocess
import sys

ROOT = Path(__file__).resolve().parent


def strict_json(text):
    def invalid(value):
        raise ValueError(f"Non-finite JSON number: {value}")
    def pairs(items):
        result = {}
        for key, value in items:
            if key in result:
                raise ValueError("Duplicate JSON key")
            result[key] = value
        return result

    def finite(value):
        if isinstance(value, float) and not math.isfinite(value):
            raise ValueError("Non-finite JSON number")
        if isinstance(value, dict):
            for item in value.values():
                finite(item)
        elif isinstance(value, list):
            for item in value:
                finite(item)

    result = json.loads(text, parse_constant=invalid, object_pairs_hook=pairs)
    finite(result)
    return result


def compare(actual, expected, path="$"):
    """Expected dictionaries are subsets; arrays/scalars must match exactly."""
    if isinstance(expected, dict):
        if not isinstance(actual, dict):
            raise AssertionError(f"{path}: expected object")
        for key, value in expected.items():
            if key not in actual:
                raise AssertionError(f"{path}.{key}: missing")
            compare(actual[key], value, f"{path}.{key}")
    elif isinstance(expected, list):
        if not isinstance(actual, list) or len(actual) != len(expected):
            raise AssertionError(f"{path}: array length/type differs")
        for index, value in enumerate(expected):
            compare(actual[index], value, f"{path}[{index}]")
    elif isinstance(expected, float):
        if type(actual) not in (int, float) or not math.isfinite(actual):
            raise AssertionError(f"{path}: expected finite number")
        if not math.isclose(actual, expected, rel_tol=0, abs_tol=1e-6):
            raise AssertionError(f"{path}: numeric mismatch")
    elif type(actual) is not type(expected) or actual != expected:
        raise AssertionError(f"{path}: value/type mismatch")


def load_vectors():
    data = strict_json((ROOT / "vectors.json").read_text())
    seen = set()
    for case in data:
        if not isinstance(case.get("id"), str) or case["id"] in seen:
            raise ValueError("Invalid/duplicate case ID")
        seen.add(case["id"])
        if not case.get("rationale") or not isinstance(case.get("request"), dict):
            raise ValueError(f"Incomplete case: {case['id']}")
        if not isinstance(case.get("expected"), dict) or not case["expected"]:
            raise ValueError(f"Missing oracle: {case['id']}")
    if not data:
        raise ValueError("Empty suite")
    return data


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--validate", action="store_true", help="Validate fixtures only; no product tests")
    parser.add_argument("--adapter", help="JSON argv array for a future implementation adapter; no shell")
    parser.add_argument("--timeout", type=float, default=30)
    args = parser.parse_args()
    cases = load_vectors()
    if args.validate:
        print(f"Validated {len(cases)} fixture definitions; product tests NOT RUN.")
        return 0
    if not args.adapter:
        parser.error("Implementation adapter required. No tests passed; use --validate for fixture-only checks.")
    command = strict_json(args.adapter)
    if not isinstance(command, list) or not command or not all(isinstance(x, str) and x for x in command):
        parser.error("--adapter must be a non-empty JSON array of non-empty argv strings")
    if not math.isfinite(args.timeout) or args.timeout <= 0:
        parser.error("--timeout must be finite and positive")
    failed = 0
    for case in cases:
        try:
            proc = subprocess.run(command, input=json.dumps(case["request"], allow_nan=False),
                                  text=True, capture_output=True, timeout=args.timeout, check=False)
            if proc.returncode:
                raise AssertionError(f"adapter exited {proc.returncode}; output withheld (may contain secrets)")
            compare(strict_json(proc.stdout), case["expected"])
            print(f"PASS {case['id']}")
        except (AssertionError, ValueError, OSError, subprocess.TimeoutExpired) as exc:
            failed += 1
            # No raw adapter output or classroom content is printed.
            print(f"FAIL {case['id']}: {type(exc).__name__}: " +
                  (str(exc) if isinstance(exc, AssertionError) else "adapter/configuration error"))
    print(f"{len(cases) - failed}/{len(cases)} contract cases passed; hardware/infrastructure suites are separate.")
    return 1 if failed else 0


if __name__ == "__main__":
    sys.exit(main())
