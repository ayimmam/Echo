package org.ethioware.echo.pilot

import org.json.JSONObject
import java.net.URI
import java.util.UUID

/** Strict projection, never a serialized LocalLesson or teacher/class identifier. */
object ResearchProjection {
    fun build(rows: List<LocalLesson>, reflection: JSONObject, id: String = UUID.randomUUID().toString()): JSONObject {
        require(rows.isNotEmpty() && rows.all { !it.excluded && it.capturedMs > 0 && it.status != "recording" })
        val profile = rows.first().profile
        require(rows.all { it.profile.id == profile.id && it.profile.grade == profile.grade && it.profile.language == profile.language && it.profile.size == profile.size })
        val earliest = minOf(rows.minOf { it.createdAt }, reflection.optLong("created", rows.minOf { it.createdAt }))
        // Floor to UTC day: coarser than a classroom schedule, and expires conservatively early.
        val collected = earliest / 86_400_000L * 86_400_000L
        val rating = reflection.optInt("rating", 0).takeIf { it in 1..5 }
        val rawTried = reflection.optString("tried", "unknown")
        return JSONObject().put("schema_version",1).put("record_id",id).put("collected_at_ms",collected)
            .put("grade",profile.grade).put("language",profile.language)
            .put("class_size_band",when (profile.size) { in 1..39 -> "1-39"; in 40..70 -> "40-70"; else -> "71-150" })
            .put("lesson_count",rows.size).put("recorded_minutes",maxOf(1,rows.sumOf { it.capturedMs } / 60_000L))
            .put("rating",rating ?: JSONObject.NULL).put("practice",reflection.optString("chosen").takeIf { it in Coaching.cards.map { card -> card.id } } ?: "none")
            .put("tried",when (rawTried) { "yes" -> "yes"; "notyet", "notuseful" -> "no"; else -> "unknown" }).put("content_version",Coaching.VERSION)
    }
}

fun researchEndpoint(value: String): String {
    val uri = URI(value.trim())
    require(uri.scheme == "https" && !uri.host.isNullOrBlank() && uri.userInfo == null && uri.query == null && uri.fragment == null)
    require(uri.path.isNullOrEmpty() || uri.path == "/") { "Enter the HTTPS server origin, without a path." }
    return value.trim().trimEnd('/')
}

data class TransferReply(val code: Int, val body: JSONObject)
fun interface ResearchTransport { fun post(origin: String, path: String, token: String?, payload: JSONObject): TransferReply }

/** Store and transitions are testable without networking or Android. Each item is encrypted by PilotStore. */
class ResearchQueue(private val store: PilotStore) {
    fun config() = store.researchRead("config") ?: JSONObject()
    fun pending() = store.researchNames().filter { it.startsWith("item.") }
    fun enroll(origin: String, code: String, transport: ResearchTransport) {
        require(code.length in 30..100)
        check(!config().has("token")) { "Withdraw the existing enrollment before replacing it." }
        val endpoint=researchEndpoint(origin)
        val reply=transport.post(endpoint,"/api/v1/research/enroll",null,JSONObject().put("enrollment_code",code))
        check(reply.code==200) { "Enrollment failed. Check the private code with the research team." }
        val token=reply.body.getString("token");require(token.length in 30..100)
        val expiry=reply.body.getLong("expires_at_ms");check(expiry>store.now())
        store.researchWrite("config",JSONObject().put("origin",endpoint).put("token",token).put("expires",expiry).put("enabled",true))
    }
    fun enqueue(key: String, payload: JSONObject) = synchronized(store) {
        val current=config()
        check(current.optBoolean("enabled",false) && !current.optBoolean("withdrawal",false) && store.now()<current.getLong("expires"))
        require(key.matches(Regex("[a-zA-Z0-9.\\-]+")))
        // Freeze a summary at opt-in submission. Same class/week is never revised silently after ACK.
        val name="item.$key"
        if (store.researchRead(name)==null) store.researchWrite(name,JSONObject().put("payload",payload).put("attempts",0).put("next",0L).put("sent",false))
    }
    fun withdraw() = synchronized(store) {
        val current=config()
        store.researchNames().filter { it.startsWith("item.") }.forEach(store::researchDelete)
        if (current.has("token")) store.researchWrite("config",current.put("enabled",false).put("withdrawal",true).put("next",0L))
    }
    fun send(transport: ResearchTransport) {
        store.expire()
        val current=config();if (!current.has("token")) return
        val time=store.now()
        if (time>=current.optLong("expires",0)) { store.researchNames().forEach(store::researchDelete);return }
        if (current.optBoolean("withdrawal",false)) {
            if (time<current.optLong("next",0))return
            val reply=transport.post(current.getString("origin"),"/api/v1/research/withdraw",current.getString("token"),JSONObject())
            synchronized(store) {
                val latest=config()
                if (latest.optString("token")==current.getString("token") && latest.optBoolean("withdrawal",false)) {
                    if (reply.code in setOf(200,401,403,410)) store.researchDelete("config")
                    else store.researchWrite("config",latest.put("next",time+60*60*1000L))
                }
            }
            return
        }
        if (!current.optBoolean("enabled",false))return
        pending().filter { name ->
            store.researchRead(name)?.let { !it.optBoolean("sent",false) && time>=it.optLong("next",0) } == true
        }.take(3).forEach { name ->
            val row=store.researchRead(name) ?: return@forEach
            val payload=row.getJSONObject("payload")
            if (time>=payload.getLong("collected_at_ms")+RETENTION_MS) { store.researchDelete(name);return@forEach }
            if (row.optBoolean("sent",false) || time<row.optLong("next",0))return@forEach
            val reply=transport.post(current.getString("origin"),"/api/v1/research/summaries",current.getString("token"),payload)
            synchronized(store) {
            // Do not recreate an item cleared during a bounded in-flight request.
            if (store.researchRead(name)==null || config().optBoolean("withdrawal",false) || !config().optBoolean("enabled",false)) return
            if (reply.code==200 && reply.body.optString("status") in setOf("accepted","duplicate")) {
                store.researchWrite(name,row.put("sent",true))
            } else if (reply.code in setOf(401,403)) { withdraw();return }
            else if (reply.code in setOf(400,409,410,413,415)) {
                store.researchWrite(name,row.put("sent",true).put("rejected",true))
            } else {
                val attempts=minOf(10,row.optInt("attempts",0)+1)
                val delay=minOf(24*60*60*1000L,60_000L*(1L shl attempts))
                store.researchWrite(name,row.put("attempts",attempts).put("next",time+delay))
            }
            }
        }
    }
}
