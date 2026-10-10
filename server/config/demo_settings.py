"""Fail-closed settings for the synthetic demo stub (config contract: unknown => refuse to start)."""
from __future__ import annotations

import json
import os
from dataclasses import dataclass
from pathlib import Path
from typing import Mapping

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
    if host not in ("127.0.0.1", "localhost"):
        raise ConfigError("the demo stub binds to IPv4 loopback only")
    try:
        port = int(env.get("ECHO_DEMO_PORT", "8000"))
    except ValueError:
        raise ConfigError("ECHO_DEMO_PORT must be an integer") from None
    if not 1 <= port <= 65535:
        raise ConfigError("ECHO_DEMO_PORT must be between 1 and 65535")
    return DemoSettings(purpose, Path(env.get("ECHO_DEMO_DATA", DEFAULT_DATA)), host, port)


def load_records(settings: DemoSettings) -> list:
    """Load fixtures, refusing anything not explicitly marked synthetic."""
    data = json.loads(settings.data_path.read_text())
    if not isinstance(data, Mapping) or data.get("synthetic") is not True:
        raise ConfigError("refusing to load data that is not marked synthetic")
    records = data.get("records")
    if not isinstance(records, list) or not all(
        isinstance(r, Mapping) and r.get("synthetic") is True for r in records
    ):
        raise ConfigError("refusing to load data that is not marked synthetic")
    return records
