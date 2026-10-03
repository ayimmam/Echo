"""Synthetic tests of evidence checker only; not provider/application security tests."""
from datetime import date
import importlib.util
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest

ROOT = Path(__file__).resolve().parents[2]
SCRIPT = ROOT / 'tools/check-hosting/check_hosting.py'
spec = importlib.util.spec_from_file_location('hosting_check', SCRIPT)
checker = importlib.util.module_from_spec(spec)
spec.loader.exec_module(checker)


def evidence(value):
    return {'value': value, 'evidence': 'synthetic-test-fixture-only', 'confirmed_on': '2026-10-02'}


def complete():
    return {
        'profile': 'zergaw-shared-derived-v1', 'data_kind': 'derived_only', 'research_mode_enabled': False,
        'selection': {key: evidence(value) for key, value in
                      zip(checker.SELECTIONS, ['wsgi', 'django', 'mariadb', '3.12.12'])},
        'checks': {key: evidence(True) for key in checker.CHECKS},
        'limits': {key: evidence(value) for key, value in zip(checker.LIMITS,
                   [512, 1, 2, 60, 120, 10, 24, 24, 8, 30])},
    }


class HostingReadinessTests(unittest.TestCase):
    def check(self, document):
        return checker.validate(document, today=date(2026, 10, 2))

    def test_supported_wsgi_engines(self):
        for database in ('postgresql', 'mysql', 'mariadb'):
            record = complete()
            record['selection']['database']['value'] = database
            self.assertEqual(self.check(record), [])

    def test_supported_asgi_path(self):
        record = complete()
        for key, value in [('runtime', 'asgi'), ('framework', 'fastapi'), ('database', 'postgresql')]:
            record['selection'][key]['value'] = value
        self.assertEqual(self.check(record), [])

    def test_unconfirmed_template_blocks(self):
        path = ROOT / 'infra/in-country/shared-hosting/capabilities.example.json'
        self.assertTrue(self.check(checker.load_json(path)))
        result = subprocess.run([sys.executable, str(SCRIPT), str(path)], capture_output=True, text=True)
        self.assertEqual(result.returncode, 1)
        self.assertIn('BLOCKED', result.stdout)

    def test_each_required_check_must_be_true_and_evidenced(self):
        for name in checker.CHECKS:
            for value in (None, False, 'true', 1):
                record = complete()
                record['checks'][name]['value'] = value
                with self.subTest(name=name, value=value):
                    self.assertTrue(self.check(record))
            record = complete()
            record['checks'][name]['evidence'] = ''
            self.assertTrue(self.check(record))

    def test_security_scope_and_unknown_fields_block(self):
        mutations = [('research_mode_enabled', True), ('data_kind', 'raw_audio'),
                     ('profile', 'demo'), ('unexpected', 'secret')]
        for field, value in mutations:
            record = complete()
            record[field] = value
            self.assertTrue(self.check(record))

    def test_invalid_limits_and_recovery_targets_block(self):
        for value in (0, -1, True, '512', None, float('inf'), float('nan'), 10 ** 400):
            record = complete()
            record['limits']['ram_mb']['value'] = value
            self.assertTrue(self.check(record))
        for field, value in [('backup_interval_hours', 168), ('rpo_hours', 25), ('restore_hours', 9)]:
            record = complete()
            record['limits'][field]['value'] = value
            self.assertTrue(self.check(record))

    def test_missing_invalid_or_future_evidence_dates_block(self):
        for value in (None, '2026-10-03', 'yesterday', 12):
            record = complete()
            record['checks']['daily_encrypted_backup']['confirmed_on'] = value
            self.assertTrue(self.check(record))

    def test_runtime_and_database_mismatch_blocks(self):
        for field, value in [('framework', 'fastapi'), ('database', 'opengauss'), ('runtime', 'cgi'),
                             ('python_version', 'latest')]:
            record = complete()
            record['selection'][field]['value'] = value
            self.assertTrue(self.check(record))

    def test_malformed_or_duplicate_json_is_rejected_without_echo(self):
        with tempfile.TemporaryDirectory() as folder:
            path = Path(folder) / 'input.json'
            for content in ('{"secret":"sensitive-canary",', '{"a":1,"a":2}', '{"a":NaN}'):
                path.write_text(content)
                result = subprocess.run([sys.executable, str(SCRIPT), str(path)], capture_output=True, text=True)
                self.assertEqual(result.returncode, 2)
                self.assertNotIn('sensitive-canary', result.stdout + result.stderr)

    def test_bad_shapes_block_without_crashing(self):
        for record in ([], None, {}, {'profile':'wrong'}):
            self.assertTrue(self.check(record))
        record = complete()
        record['checks'] = []
        self.assertTrue(self.check(record))
        record = complete()
        record['selection']['framework'] = None
        self.assertTrue(self.check(record))


if __name__ == '__main__':
    unittest.main()
