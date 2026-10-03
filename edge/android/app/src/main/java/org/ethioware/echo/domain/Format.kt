package org.ethioware.echo.domain

/** "12:04" under an hour, "1:02:03" above. */
fun formatClock(totalS: Int): String {
    val s = totalS.coerceAtLeast(0)
    val h = s / 3600
    val m = s % 3600 / 60
    val sec = s % 60
    return if (h > 0) "%d:%02d:%02d".format(h, m, sec) else "%02d:%02d".format(m, sec)
}

fun percent(fraction: Double): Int = Math.round(fraction * 100).toInt()
