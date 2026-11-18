package org.ethioware.echo

import java.time.LocalDate
import org.ethioware.echo.pilot.WeeklyReminder
import org.json.JSONObject
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WeeklyReminderTest {
    private val sunday = LocalDate.parse("2026-11-22")
    private fun settings() = JSONObject().put("reminders", true).put("reminderDay", "SUNDAY").put("reminderMinute", 18 * 60)

    @Test fun `default reminder is eligible only Sunday at or after eighteen hundred`() {
        assertFalse(WeeklyReminder.shouldNotify(settings(), sunday.minusDays(1), 19 * 60, true))
        assertFalse(WeeklyReminder.shouldNotify(settings(), sunday, 17 * 60 + 59, true))
        assertTrue(WeeklyReminder.shouldNotify(settings(), sunday, 18 * 60, true))
        assertFalse(WeeklyReminder.shouldNotify(settings(), sunday, 18 * 60, false))
    }

    @Test fun `reminder is deduplicated by local week`() {
        assertFalse(WeeklyReminder.shouldNotify(settings().put("remindedWeek", "2026-11-16"), sunday, 20 * 60, true))
    }
}
