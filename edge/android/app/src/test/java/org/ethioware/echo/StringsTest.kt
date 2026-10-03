package org.ethioware.echo

import org.ethioware.echo.data.AppLocale
import org.ethioware.echo.ui.i18n.AmharicStrings
import org.ethioware.echo.ui.i18n.EnglishStrings
import org.ethioware.echo.ui.i18n.S
import org.ethioware.echo.ui.i18n.Translator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StringsTest {
    private val placeholder = Regex("%(\\d+)\\$[-#+ 0,(]*\\d*([sd])")

    private fun placeholders(template: String): List<Pair<Int, String>> =
        placeholder.findAll(template).map { it.groupValues[1].toInt() to it.groupValues[2] }.distinct().sortedBy { it.first }.toList()

    @Test
    fun `English has every key`() {
        assertEquals(emptyList<S>(), S.entries.filter { it !in EnglishStrings })
    }

    @Test
    fun `Amharic has every key except the Sidaamu banner`() {
        assertEquals(listOf(S.SID_BANNER), S.entries.filter { it !in AmharicStrings })
    }

    @Test
    fun `placeholders match between English and Amharic`() {
        for ((key, am) in AmharicStrings) {
            assertEquals("placeholders differ for $key", placeholders(EnglishStrings.getValue(key)), placeholders(am))
        }
    }

    @Test
    fun `every string formats without error in every locale`() {
        for (locale in AppLocale.entries) {
            val t = Translator(locale)
            for (key in S.entries) {
                val template = EnglishStrings.getValue(key)
                val args: Array<Any> = placeholders(template).map<Pair<Int, String>, Any> { (_, type) -> if (type == "d") 7 else "x" }.toTypedArray()
                val text = t(key, *args)
                assertTrue("$key/$locale is blank", text.isNotBlank())
            }
        }
    }

    @Test
    fun `Amharic text is actually Ethiopic script`() {
        val ethiopic = 'ሀ'..'፿'
        val latinOnly = AmharicStrings.filter { (_, v) -> v.none { it in ethiopic } }.keys
        assertEquals(emptySet<S>(), latinOnly)
    }

    @Test
    fun `Sidaamu Afoo shows English and flags the draft`() {
        val sid = Translator(AppLocale.SID)
        assertTrue(sid.showsDraftFallback)
        assertEquals(Translator(AppLocale.EN)(S.TAB_TODAY), sid(S.TAB_TODAY))
        assertTrue(!Translator(AppLocale.EN).showsDraftFallback)
        assertTrue(sid(S.SID_BANNER).contains("[SID-DRAFT]"))
    }

    @Test
    fun `copy never evaluates, ranks or compares the teacher`() {
        val all = EnglishStrings.values.joinToString(" ").lowercase()
        for (banned in listOf("rank", "good teacher", "bad teacher", "your score", "improve your", "well done", "poor")) {
            assertTrue("found '$banned' in UI copy", !all.contains(banned))
        }
    }
}
