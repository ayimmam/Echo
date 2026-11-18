package org.ethioware.echo.pilot

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import org.ethioware.echo.MainActivity
import org.json.JSONObject
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

/** Best-effort weekend notice. No exact alarm, wake-up recording, network or personal notification text. */
object WeeklyReminder {
    fun shouldNotify(settings: JSONObject, today: LocalDate, minuteNow: Int, hasLessons: Boolean): Boolean {
        val configuredDay = runCatching { DayOfWeek.valueOf(settings.optString("reminderDay", "SUNDAY")) }.getOrDefault(DayOfWeek.SUNDAY)
        val configuredMinute = settings.optInt("reminderMinute", 18 * 60).coerceIn(17 * 60, 23 * 60 + 59)
        return settings.optBoolean("reminders", false) && today.dayOfWeek == configuredDay &&
            minuteNow >= configuredMinute && settings.optString("remindedWeek") != monday(today).toString() && hasLessons
    }
    fun maybeNotify(context: Context, store: PilotStore) {
        val today = LocalDate.now()
        val settings = store.settings()
        val week = monday(today).toString()
        val minuteNow = LocalTime.now().hour * 60 + LocalTime.now().minute
        if (!shouldNotify(settings, today, minuteNow, store.lessons().any { it.capturedMs > 0 })) return
        if (Build.VERSION.SDK_INT >= 33 && context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) return
        val manager = context.getSystemService(NotificationManager::class.java)
        if (!manager.areNotificationsEnabled()) return
        val channel = "echo-weekly-reflection"
        val am = settings.optString("locale", "en") == "am"
        manager.createNotificationChannel(NotificationChannel(channel, if (am) "ሳምንታዊ ምልከታ" else "Weekly reflection", NotificationManager.IMPORTANCE_DEFAULT))
        val intent = PendingIntent.getActivity(context, 3102, Intent(context, MainActivity::class.java)
            .putExtra("reflection", true).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        manager.notify(3102, Notification.Builder(context, channel).setSmallIcon(android.R.drawable.ic_menu_agenda)
            .setContentTitle(if (am) "ሳምንታዊ ምልከታዎ" else "Your weekly reflection")
            .setContentText(if (am) "ለጥቂት ደቂቃዎች ያስቡና ለሚቀጥለው ሳምንት አንድ ልምምድ ይምረጡ።" else "Take a few minutes to reflect and choose a practice for next week.")
            .setContentIntent(intent).setAutoCancel(true).build())
        store.patchSettings(JSONObject().put("remindedWeek", week))
    }
}
