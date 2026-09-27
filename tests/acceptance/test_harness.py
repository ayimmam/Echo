"""Checks the acceptance harness, not the future product."""
import json
from pathlib import Path
import subprocess
import sys
import unittest
from runner import compare, load_vectors, strict_json


class HarnessTests(unittest.TestCase):
    def test_vectors_have_unique_oracles(self):
        cases = load_vectors()
        self.assertGreaterEqual(len(cases), 18)

    def test_subset_and_absolute_tolerance(self):
        compare({'ratio': 0.5000001, 'count': 1, 'extra': True}, {'ratio': 0.5, 'count': 1})

    def test_reject_false_passes(self):
        pairs = [({}, {'count': 1}), ({'n': True}, {'n': 1}),
                 ({'n': 0}, {'n': None}), ({'ratio': 0.51}, {'ratio': 0.5}),
                 ({'ratio': float('nan')}, {'ratio': 0.5}),
                 ({'n': 1.0}, {'n': 1}), ({'x': [1]}, {'x': [1, 2]})]
        for actual, expected in pairs:
            with self.subTest(actual=actual, expected=expected):
                with self.assertRaises(AssertionError):
                    compare(actual, expected)

    def test_reject_nonfinite_json(self):
        for text in ['NaN', 'Infinity', '-Infinity', '{"extra": 1e999}', '{"n": 1, "n": 2}']:
            with self.assertRaises(ValueError):
                strict_json(text)

    def test_missing_adapter_is_not_a_pass(self):
        proc = subprocess.run([sys.executable, str(Path(__file__).with_name('runner.py'))],
                              capture_output=True, text=True)
        self.assertEqual(proc.returncode, 2)
        self.assertIn('Implementation adapter required', proc.stderr)
        self.assertNotIn('PASS', proc.stdout)

    def test_bad_adapter_results_fail_all_cases(self):
        # Deliberately wrong test double verifies IPC/failure handling only.
        command = json.dumps([sys.executable, '-c', 'print("{}")'])
        proc = subprocess.run([sys.executable, str(Path(__file__).with_name('runner.py')),
                               '--adapter', command], capture_output=True, text=True)
        self.assertEqual(proc.returncode, 1)
        self.assertEqual(proc.stdout.count('FAIL '), len(load_vectors()))
        self.assertIn('0/18 contract cases passed', proc.stdout)


if __name__ == '__main__':
    unittest.main()
