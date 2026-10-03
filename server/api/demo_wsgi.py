"""Framework-neutral SYNTHETIC demo: read-only WSGI app over the generated cohort fixtures.

    python3 server/api/demo_wsgi.py          # http://127.0.0.1:8000/

Throwaway stub. It is NOT the selected backend (FastAPI vs Django is decided only after Zergaw
capability evidence, ADR-015): no database, no auth, no ingest, no personal data. It exists to give
the dashboard design something real to render and the suppression rules something to test.
"""
from __future__ import annotations

import json
import sys
from html import escape
from pathlib import Path
from string import Template
from wsgiref.simple_server import make_server

ROOT = Path(__file__).resolve().parents[2]
for extra in (ROOT, ROOT / "indicators-core" / "python"):
    if str(extra) not in sys.path:
        sys.path.insert(0, str(extra))

from server.config import demo_settings  # noqa: E402
from server.domain.aggregation import K_MIN, aggregate_by_grade  # noqa: E402

TEMPLATES = ROOT / "server" / "templates"
WINDOW = "2026-10-19 to 2026-11-08"
HEADERS = [
    ("X-Content-Type-Options", "nosniff"),
    ("Referrer-Policy", "no-referrer"),
    ("Cache-Control", "no-store"),
    ("Content-Security-Policy", "default-src 'none'; style-src 'self'; base-uri 'none'; form-action 'none'"),
]
GRADE_LABEL = {"O": "O-class", "1": "Grade 1", "2": "Grade 2", "3": "Grade 3"}


def render_dashboard(cells: list) -> str:
    rows = []
    for c in cells:
        label = escape(GRADE_LABEL.get(c["grade"], c["grade"]))
        if c["suppressed"]:
            rows.append(f'    <tr><th scope="row">{label}</th>'
                        f'<td class="hidden" colspan="3">Hidden: fewer than {K_MIN} teachers</td></tr>')
        else:
            rows.append(f'    <tr><th scope="row">{label}</th><td>{c["teachers"]}</td>'
                        f'<td>{c["I1_mean"]:.0%}</td><td>{c["I5_mean"]:.1f}</td></tr>')
    page = Template((TEMPLATES / "demo_dashboard.html").read_text())
    return page.substitute(rows="\n".join(rows), window=escape(WINDOW), k=K_MIN)


def make_app(records: list):
    cells = aggregate_by_grade(records)  # computed once; fixtures are immutable
    body_json = json.dumps({"synthetic": True, "window": WINDOW, "k_min": K_MIN, "cells": cells}).encode()
    body_html = render_dashboard(cells).encode()
    css = (TEMPLATES / "demo.css").read_bytes()
    routes = {
        "/": ("text/html; charset=utf-8", body_html),
        "/healthz": ("application/json", b'{"status":"ok","purpose":"synthetic_demo"}'),
        "/api/v1/demo/aggregate": ("application/json", body_json),
        "/static/demo.css": ("text/css; charset=utf-8", css),
    }

    def application(environ, start_response):
        method = environ.get("REQUEST_METHOD", "GET")
        if method not in ("GET", "HEAD"):
            start_response("405 Method Not Allowed", [("Allow", "GET, HEAD"), *HEADERS])
            return [b""]
        route = routes.get(environ.get("PATH_INFO", "/"))
        if route is None:
            start_response("404 Not Found", [("Content-Type", "text/plain; charset=utf-8"), *HEADERS])
            return [b"Not found"]
        ctype, body = route
        start_response("200 OK", [("Content-Type", ctype), ("Content-Length", str(len(body))), *HEADERS])
        return [b"" if method == "HEAD" else body]

    return application


def main() -> int:
    try:
        settings = demo_settings.load()
        app = make_app(demo_settings.load_records(settings))
    except (demo_settings.ConfigError, OSError, ValueError) as exc:
        print(f"refusing to start: {exc}", file=sys.stderr)
        return 1
    print(f"Synthetic demo on http://{settings.host}:{settings.port}/  (Ctrl-C to stop)")
    with make_server(settings.host, settings.port, app) as server:
        server.serve_forever()
    return 0


if __name__ == "__main__":
    sys.exit(main())
