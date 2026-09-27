# Post-implementation acceptance tests

Prepared 2026-09-27. No product implementation is included. Python 3.10+ standard library only. `vectors.json` contains hand-calculated indicator oracles and cohort-contract cases; `runner.py` sends them to a future implementation adapter. The device, infrastructure and field suites are specified in [the test plan](../../docs/testing/POST_IMPLEMENTATION_TEST_PLAN.md). They are not automated by this runner.

## Before implementation

Validate the fixture format (does not test the product):

```sh
python3 tests/acceptance/runner.py --validate
python3 -m unittest discover -s tests/acceptance -p 'test_harness.py'
```

Ratify the proposed contract in ADR-012/014 before implementing its adapter. Resolve remaining postprocessing/quantile/quality thresholds at G1. These fixtures deliberately avoid unresolved smoothing and multi-value quantile choices; they are a starter contract suite, not exhaustive model or pipeline coverage.

## After implementation

Create adapters that call the actual Python reference and Kotlin implementation. Each adapter must accept **one JSON request on stdin**, emit **one JSON result on stdout**, and exit; logging goes to stderr. Implementations may map their public field names to the normalized acceptance fields below, but must not calculate replacement results or read expected fixtures. No adapter exists yet. Run each independently against the same vectors:

```sh
python3 tests/acceptance/runner.py --adapter '["python3", "path/to/python_adapter.py"]'
python3 tests/acceptance/runner.py --adapter '["java", "-jar", "path/to/kotlin-adapter.jar"]'
```

Replace the paths with actual future adapters. The runner never invokes a shell, uses a timeout per case, fails on missing/malformed/non-finite output and returns nonzero on any failure. No adapter means an error, not a skip or pass. Actual output may contain additional fields, but every oracle field must exist. Counts and categorical/null values are exact; floating values use absolute tolerance 1e-6.

## Adapter protocol (test-only, acceptance-v1)

- `indicators`: fields `indicator_spec_version`, `cohort`, `tick_ms=10`, `duration_ticks`, `timeline`. Timeline uses half-open `start_tick`/`end_tick`, `label`, optional `lang`. It is already postprocessed, preserves original timestamps and includes missing intervals. Use the real indicator engine, bypassing only feature/model inference. Result: `observed_s`, `missing_s`, `I1` ratio, `I2` single/choral quantiles with `n`, `I3` duration ratio, `I4` `{count, per_10min}`, `I5` per-minute rate, `I6` language shares or null, `I7` coverage or null, `I8` count. Invalid cohort labels return `{"error":"label_schema_mismatch"}`. This normalized shape is not the sync API schema.
- `validate_context`: invoke actual session/cohort validation. Return `{"valid":true}` or `{"valid":false,"reason":"cohort_context_mismatch|unsupported_cohort"}` (one reason value). STEM cannot contain an O–3 grade; early-grade sessions retain grade/subject.
- `validate_model`: invoke actual cohort/manifest compatibility check. Unsupported cohort returns `{"valid":false,"reason":"unsupported_cohort"}`. The provided case checks only that incompatibility, not complete manifest verification.

Never use a self-reported mock pass as implementation evidence. Store full runs with git SHA, adapter SHA and model/spec versions in the private evidence store. Contract vectors contain no real audio or personal data. Keep real classroom evidence and test credentials out of this repository.
