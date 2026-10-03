package org.ethioware.echo.data

import java.time.LocalDate
import java.time.LocalTime
import org.json.JSONArray
import org.json.JSONObject

/** Parsing and serialisation for the shared mock-data files and the saved app state. */
object MockJson {

    fun parseLessons(text: String): List<LessonRecord> =
        JSONObject(text).getJSONArray("records").objects().map(::lessonFromJson)

    fun lessonFromJson(o: JSONObject): LessonRecord {
        val capture = o.getJSONObject("capture")
        val context = o.getJSONObject("context")
        val indicators = o.getJSONObject("indicators")
        return LessonRecord(
            id = o.getString("record_id"),
            startedOn = LocalDate.parse(o.getString("started_on")),
            startTime = LocalTime.parse(o.getString("start_time")),
            durationS = o.getInt("duration_s"),
            grade = o.getString("grade"),
            subject = o.getString("subject"),
            specVersion = o.getInt("indicator_spec_version"),
            capture = Capture(
                observedS = capture.getDouble("observed_s"),
                missingS = capture.getDouble("missing_s"),
                coverage = capture.getDouble("coverage"),
                mic = Mic.fromKey(capture.optString("mic")),
                classSize = capture.optInt("class_size_estimate", 0),
            ),
            activity = Activity.fromKey(context.optString("activity")),
            adult = AdultConditions.fromKey(context.optString("adult_conditions")),
            i1 = measureFromJson(indicators.getJSONObject("I1_adult_talk_ratio")),
            i5 = measureFromJson(indicators.getJSONObject("I5_turns_per_minute")),
        )
    }

    fun lessonToJson(r: LessonRecord): JSONObject = JSONObject()
        .put("record_id", r.id)
        .put("indicator_spec_version", r.specVersion)
        .put("started_on", r.startedOn.toString())
        .put("start_time", r.startTime.withSecond(0).withNano(0).toString())
        .put("duration_s", r.durationS)
        .put("grade", r.grade)
        .put("subject", r.subject)
        .put(
            "capture",
            JSONObject()
                .put("observed_s", r.capture.observedS)
                .put("missing_s", r.capture.missingS)
                .put("coverage", r.capture.coverage)
                .put("mic", r.capture.mic.key)
                .put("class_size_estimate", r.capture.classSize),
        )
        .put("context", JSONObject().put("activity", r.activity.key).put("adult_conditions", r.adult.key))
        .put(
            "indicators",
            JSONObject()
                .put("I1_adult_talk_ratio", measureToJson(r.i1))
                .put("I5_turns_per_minute", measureToJson(r.i5)),
        )

    private fun measureFromJson(o: JSONObject): Measure =
        if (o.optBoolean("available")) Measure.of(o.getDouble("value"))
        else Measure.unavailable(o.optString("reason", REASON_INSUFFICIENT_SPEECH))

    private fun measureToJson(m: Measure): JSONObject =
        if (m.available) JSONObject().put("available", true).put("value", m.value)
        else JSONObject().put("available", false).put("reason", m.reason)

    fun parseProfile(text: String): TeacherProfile {
        val o = JSONObject(text)
        return TeacherProfile(
            pseudoId = o.getString("teacher_pseudo_id"),
            displayName = o.getString("display_name"),
            schoolLabel = o.getString("school_label"),
            grade = o.getString("grade"),
            subject = o.getString("subject"),
            classSize = o.getInt("class_size"),
            preferredMic = Mic.fromKey(o.optString("preferred_mic")),
            referenceDate = LocalDate.parse(o.getString("reference_date")),
        )
    }

    fun parseCards(text: String): List<PracticeCard> =
        JSONObject(text).getJSONArray("cards").objects().map { c ->
            PracticeCard(
                id = c.getString("id"),
                title = localized(c.getJSONObject("title")),
                action = localized(c.getJSONObject("action")),
                example = localized(c.getJSONObject("example")),
                discussionPrompt = localized(c.getJSONObject("discussion_prompt")),
            )
        }

    private fun localized(o: JSONObject) = Localized(
        en = o.getString("en"),
        am = o.optString("am").takeIf { !o.isNull("am") && it.isNotBlank() },
        sid = o.optString("sid").takeIf { !o.isNull("sid") && it.isNotBlank() },
    )

    fun parseLocalState(text: String): LocalState = localStateFromJson(JSONObject(text))

    fun localStateFromJson(o: JSONObject): LocalState = LocalState(
        excludedIds = o.optJSONArray("excluded_record_ids").strings().toSet(),
        chosen = o.optJSONObject("chosen_card")?.let {
            ChosenCard(it.getString("card_id"), LocalDate.parse(it.getString("chosen_on")))
        },
        followUps = o.optJSONArray("follow_ups").objects().map {
            FollowUp(
                cardId = it.getString("card_id"),
                answeredOn = LocalDate.parse(it.getString("answered_on")),
                answer = FollowUpAnswer.valueOf(it.getString("answer")),
            )
        },
        dismissedCardIds = o.optJSONArray("dismissed_card_ids").strings().toSet(),
    )

    fun localStateToJson(s: LocalState): JSONObject = JSONObject()
        .put("excluded_record_ids", JSONArray(s.excludedIds.sorted()))
        .put(
            "chosen_card",
            s.chosen?.let { JSONObject().put("card_id", it.cardId).put("chosen_on", it.chosenOn.toString()) }
                ?: JSONObject.NULL,
        )
        .put(
            "follow_ups",
            JSONArray(
                s.followUps.map {
                    JSONObject()
                        .put("card_id", it.cardId)
                        .put("answered_on", it.answeredOn.toString())
                        .put("answer", it.answer.name)
                },
            ),
        )
        .put("dismissed_card_ids", JSONArray(s.dismissedCardIds.sorted()))
}

internal fun JSONArray?.objects(): List<JSONObject> =
    if (this == null) emptyList() else (0 until length()).map { getJSONObject(it) }

internal fun JSONArray?.strings(): List<String> =
    if (this == null) emptyList() else (0 until length()).map { getString(it) }
