package org.ethioware.echo.data

import android.content.Context

/** Tiny key-value seam so state logic can be unit-tested without Android. */
interface KeyValueStore {
    fun get(key: String): String?
    fun put(key: String, value: String)
    fun clear()
}

class MemoryStore : KeyValueStore {
    private val map = HashMap<String, String>()
    override fun get(key: String): String? = map[key]
    override fun put(key: String, value: String) { map[key] = value }
    override fun clear() = map.clear()
}

/**
 * Demo persistence: plain SharedPreferences. The real app stores summaries in Room over SQLCipher
 * with a Keystore-wrapped key (README s5.1); this stand-in is NOT encrypted and holds synthetic data only.
 */
class SharedPrefsStore(context: Context) : KeyValueStore {
    private val prefs = context.applicationContext.getSharedPreferences("echo_demo_state", Context.MODE_PRIVATE)
    override fun get(key: String): String? = prefs.getString(key, null)
    override fun put(key: String, value: String) { prefs.edit().putString(key, value).apply() }
    override fun clear() { prefs.edit().clear().apply() }
}

/** Where the bundled mock data comes from (APK assets in the app, repo files in unit tests). */
fun interface MockSource {
    fun read(name: String): String
}

object DemoRepository {
    fun load(source: MockSource): DemoBundle = DemoBundle(
        profile = MockJson.parseProfile(source.read("teacher_profile.json")),
        lessons = MockJson.parseLessons(source.read("lessons.json")),
        localState = MockJson.parseLocalState(source.read("local_state.json")),
        cards = MockJson.parseCards(source.read("practice_cards.json")),
    )

    fun fromAssets(context: Context): DemoBundle = load { name ->
        context.assets.open("mock/$name").bufferedReader().use { it.readText() }
    }
}
