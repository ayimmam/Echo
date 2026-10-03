"""Fail-closed settings for the synthetic demo stub (config contract: unknown => refuse to start)."""
from __future__ import annotations

import json
import os
from dataclasses import dataclass
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
DEFAULT_DATA = ROOT / "tools" / "demo" / "mock-data" / "server" / "cohort_lessons.json"


class ConfigError(RuntimeError):
    pass


@dataclass(frozen=True)
class DemoSettings:
    purpose: str
    data_path: Path
    host: str
    port: int


def load(env=os.environ) -> DemoSettings:
    purpose = env.get("ECHO_PURPOSE", "synthetic_demo")
    if purpose != "synthetic_demo":
        raise ConfigError("this stub only serves purpose=synthetic_demo; no pilot or research mode exists")
    host = env.get("ECHO_DEMO_HOST", "127.0.0.1")
    if host not in ("127.0.0.1", "localhost", "::1"):
        raise ConfigError("the demo stub binds to loopback only")
    try:
        port = int(env.get("ECHO_DEMO_PORT", "8000"))
    except ValueError:
        raise ConfigError("ECHO_DEMO_PORT must be an integer") from None
    return DemoSettings(purpose, Path(env.get("ECHO_DEMO_DATA", DEFAULT_DATA)), host, port)


def load_records(settings: DemoSettings) -> list:
    """Load fixtures, refusing anything not explicitly marked synthetic."""
    data = json.loads(settings.data_path.read_text())
    records = data.get("records", [])
    if data.get("synthetic") is not True or not all(r.get("synthetic") is True for r in records):
        raise ConfigError("refusing to load data that is not marked synthetic")
    return records
