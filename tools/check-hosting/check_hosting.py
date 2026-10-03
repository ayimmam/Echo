"""Offline hosting-evidence check. No probes, deployment, secrets or runtime controls."""
import argparse
from datetime import date
import json
import math
from pathlib import Path
import re

CHECKS = (
    'python_packages_and_venv', 'supported_dependency_versions',
    'managed_gateway_and_restart', 'scheduled_cli_jobs',
    'database_transactions_and_unique_constraints', 'database_connection_protected',
    'tenant_isolation_reviewed', 'private_files_outside_document_root',
    'private_secret_injection', 'https_and_proxy_trust_verified',
    'primary_database_logs_in_ethiopia', 'backups_and_replicas_in_ethiopia',
    'encryption_at_rest_verified', 'daily_encrypted_backup',
    'restore_and_deletion_replay_tested', 'redacted_logging_verified',
    'resource_load_test_passed', 'retention_and_incident_owners_assigned',
)
LIMITS = ('ram_mb', 'cpu_limit', 'process_limit', 'request_timeout_s',
          'cron_max_runtime_s', 'database_connection_limit',
          'backup_interval_hours', 'rpo_hours', 'restore_hours', 'backup_retention_days')
SELECTIONS = ('runtime', 'framework', 'database', 'python_version')
ROOT_FIELDS = {'profile', 'data_kind', 'research_mode_enabled', 'selection', 'checks', 'limits'}


def load_json(path):
    def unique(pairs):
        result = {}
        for key, value in pairs:
            if key in result:
                raise ValueError('Duplicate field')
            result[key] = value
        return result

    def bad_constant(_):
        raise ValueError('Non-finite number')

    return json.loads(Path(path).read_text(), object_pairs_hook=unique,
                      parse_constant=bad_constant)


def validate(document, today=None):
    """Return field-only errors; supplied evidence is not independently authenticated."""
    errors = []
    today = today or date.today()
    if not isinstance(document, dict) or set(document) != ROOT_FIELDS:
        return ['document: unexpected or missing fields']
    for field, expected in [('profile', 'zergaw-shared-derived-v1'),
                            ('data_kind', 'derived_only'), ('research_mode_enabled', False)]:
        if type(document[field]) is not type(expected) or document[field] != expected:
            errors.append(f'{field}: unsupported scope')

    values = {}
    for group, names in [('selection', SELECTIONS), ('checks', CHECKS), ('limits', LIMITS)]:
        records = document[group]
        if not isinstance(records, dict) or set(records) != set(names):
            errors.append(f'{group}: unexpected or missing fields')
            records = records if isinstance(records, dict) else {}
        for name in names:
            key = f'{group}.{name}'
            record = records.get(name)
            if not isinstance(record, dict) or set(record) != {'value', 'evidence', 'confirmed_on'}:
                errors.append(f'{key}: incomplete evidence record')
                continue
            values[key] = record['value']
            if not isinstance(record['evidence'], str) or not record['evidence'].strip():
                errors.append(f'{key}: evidence reference required')
            try:
                if not isinstance(record['confirmed_on'], str):
                    raise ValueError()
                confirmed = date.fromisoformat(record['confirmed_on'])
                if confirmed.isoformat() != record['confirmed_on'] or confirmed > today:
                    raise ValueError()
            except ValueError:
                errors.append(f'{key}: valid non-future confirmation date required')
            value = record['value']
            if group == 'checks' and value is not True:
                errors.append(f'{key}: not confirmed')
            if group == 'limits' and (type(value) not in (int, float) or
                                       (isinstance(value, float) and not math.isfinite(value)) or value <= 0 or value > 10 ** 12):
                errors.append(f'{key}: finite measured limit in (0, 10^12] required')

    runtime = values.get('selection.runtime')
    framework = values.get('selection.framework')
    database = values.get('selection.database')
    if (runtime, framework) not in [('asgi', 'fastapi'), ('wsgi', 'django')]:
        errors.append('selection: choose supported ASGI/FastAPI or WSGI/Django path')
    if database not in ('postgresql', 'mysql', 'mariadb'):
        errors.append('selection.database: supported engine required')
    elif framework == 'fastapi' and database != 'postgresql':
        errors.append('selection.database: this ASGI baseline requires PostgreSQL')
    python_version = values.get('selection.python_version')
    if not isinstance(python_version, str) or not re.fullmatch(r'3\.(1[0-9]|[2-9][0-9])\.\d+', python_version):
        errors.append('selection.python_version: explicit Python 3.10+ patch version required')
    # Future-version compatibility is still established by supported_dependency_versions.
    for name, maximum in [('backup_interval_hours', 24), ('rpo_hours', 24), ('restore_hours', 8)]:
        value = values.get(f'limits.{name}')
        if type(value) in (int, float) and value > maximum:
            errors.append(f'limits.{name}: exceeds project recovery target')
    return errors


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('capabilities', help='Private JSON evidence file; never include credentials')
    args = parser.parse_args()
    try:
        errors = validate(load_json(args.capabilities))
    except (OSError, ValueError, UnicodeError):
        print('BLOCKED: cannot read a valid capability record (details withheld).')
        return 2
    if errors:
        print('BLOCKED: declared hosting evidence is incomplete or incompatible.')
        for error in errors:
            print(f'- {error}')
        return 1
    print('Declared evidence is complete. Provider verification, HOST/SEC tests and cohort approvals remain required.')
    return 0


if __name__ == '__main__':
    raise SystemExit(main())
