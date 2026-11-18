#!/usr/bin/env python3
import argparse
import hashlib
import json
import subprocess
from datetime import datetime, timezone
from pathlib import Path

def run(*args):
    return subprocess.check_output(args, text=True).strip()

parser = argparse.ArgumentParser(description="Create an Echo pilot build manifest without secrets.")
parser.add_argument("--apk", required=True, type=Path)
parser.add_argument("--environment", required=True, choices=("offline", "synthetic", "live"))
parser.add_argument("--output", required=True, type=Path)
parser.add_argument("--design-revision", default="repository ECHO Android App Design exports @ current commit")
args = parser.parse_args()

apk = args.apk.resolve()
if not apk.is_file():
    parser.error(f"APK not found: {apk}")
digest = hashlib.sha256(apk.read_bytes()).hexdigest()
status = run("git", "status", "--porcelain")
manifest = {
    "schema": 1,
    "created_at_utc": datetime.now(timezone.utc).isoformat(),
    "app_commit": run("git", "rev-parse", "HEAD"),
    "backend_commit": run("git", "rev-parse", "HEAD"),
    "worktree_clean": not bool(status),
    "apk_path": str(apk),
    "apk_sha256": digest,
    "environment": args.environment,
    "research_contracts": ["RESEARCH_SUMMARY_V1", "RESEARCH_SUMMARY_V2"],
    "coaching_content_version": "hawassa-coaching-2026-10-draft-2",
    "design_revision": args.design_revision,
    "warning": "A dirty or unsigned build is evidence for engineering rehearsal only, not field release.",
}
args.output.parent.mkdir(parents=True, exist_ok=True)
args.output.write_text(json.dumps(manifest, indent=2) + "\n")
print(args.output)
