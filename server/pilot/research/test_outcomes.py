from django.test import SimpleTestCase
from research.outcomes import calculate_usefulness

class UsefulnessOutcomeTests(SimpleTestCase):
    def test_exactly_eighty_percent_fails_strict_target(self):
        result = calculate_usefulness([('a', 4), ('b', 5), ('c', 4), ('d', 5), ('e', 3)])
        self.assertEqual(result['positive_rate'], .8)
        self.assertFalse(result['strictly_above_80_percent'])

    def test_reports_missing_data_and_teacher_coverage(self):
        result = calculate_usefulness([('a', 5), ('a', None), ('b', 4), ('c', None), ('d', 5), ('e', 4), ('f', 5)])
        self.assertEqual(result['prompted'], 7)
        self.assertEqual(result['submitted'], 5)
        self.assertEqual(result['positive'], 5)
        self.assertEqual(result['unrated'], 2)
        self.assertEqual(result['distinct_teachers'], 6)
        self.assertEqual(result['teachers_with_rating'], 5)
        self.assertTrue(result['strictly_above_80_percent'])

    def test_rejects_invalid_values(self):
        with self.assertRaises(ValueError):
            calculate_usefulness([('a', 6)])
