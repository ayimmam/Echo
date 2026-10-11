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

/** Best-effort weekend notice. No exact alarm, wake-up recording, network or personal notification text. */
object WeeklyReminder {
    fun maybeNotify(context: Context, store: PilotStore) {
        val today = LocalDate.now()
        val settings = store.settings()
        val week = monday(today).toString()
        if (!settings.optBoolean("reminders", false) || today.dayOfWeek !in setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY) ||
            settings.optString("remindedWeek") == week || store.lessons().none { it.capturedMs > 0 }) return
        if (Build.VERSION.SDK_INT >= 33 && context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) return
        val manager = context.getSystemService(NotificationManager::class.java)
        if (!manager.areNotificationsEnabled()) return
        val channel = "echo-weekly-reflection"
        manager.createNotificationChannel(NotificationChannel(channel, "Weekly reflection", NotificationManager.IMPORTANCE_DEFAULT))
        val intent = PendingIntent.getActivity(context, 3102, Intent(context, MainActivity::class.java)
            .putExtra("reflection", true).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        manager.notify(3102, Notification.Builder(context, channel).setSmallIcon(android.R.drawable.ic_menu_agenda)
            .setContentTitle("Your weekly reflection")
            .setContentText("Take a few minutes to reflect and choose a practice for next week.")
            .setContentIntent(intent).setAutoCancel(true).build())
        store.patchSettings(JSONObject().put("remindedWeek", week))
    }
}
