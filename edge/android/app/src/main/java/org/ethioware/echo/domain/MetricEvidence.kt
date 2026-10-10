package org.ethioware.echo.domain

import org.ethioware.echo.data.LessonRecord
import org.ethioware.echo.data.Measure
import org.ethioware.echo.data.MeasurementOrigin
import org.ethioware.echo.data.AdultConditions

enum class MetricReleaseStage { DRAFT, EXPERIMENTAL, RELEASED, WITHDRAWN }

/** A release is pinned to the complete measurement path and the scope that its evidence supports. */
data class MetricRelease(
    val releaseId: String,
    val stage: MetricReleaseStage,
    val metricId: String,
    val cohort: String,
    val grade: String,
    val subject: String,
    val specVersion: Int,
    val labelSchemaVersion: String,
    val modelArtifactSha256: String,
    val featureConfigHash: String,
    val postprocessHash: String,
    val evidenceManifestHash: String,
)

data class MetricDecision(val available: Boolean, val reason: String? = null, val syntheticPreview: Boolean = false)

/**
 * Fail-closed presentation policy. The checked-in registry intentionally has no released model
 * metrics: G0/G1/G5 evidence and institutional release review have not been supplied.
 */
object MetricEvidencePolicy {
    val releases: List<MetricRelease> = emptyList()

    fun decide(
        record: LessonRecord,
        metricId: String,
        measure: Measure,
        allowSyntheticPreview: Boolean,
    ): MetricDecision {
        if (!measure.hasValidShape()) {
            return MetricDecision(false, "invalid_measurement")
        }
        if (!measure.available) return MetricDecision(false, measure.reason)
        if (!validRange(metricId, measure.value)) return MetricDecision(false, "invalid_measurement")
        if (record.adult != AdultConditions.SINGLE_ADULT) {
            return MetricDecision(false, "adult_context_not_confirmed")
        }
        if (record.grade.isBlank() || record.subject.isBlank() || record.cohortKey() == null) {
            return MetricDecision(false, "unknown_provenance")
        }
        if (record.schemaVersion < 4 || record.measurementProvenanceVersion != 1 ||
            measure.methodVersion != record.specVersion ||
            measure.labelSchemaVersion != record.labelSchemaVersion
        ) {
            return MetricDecision(false, "version_mismatch")
        }

        return when (measure.origin) {
            MeasurementOrigin.SYNTHETIC -> {
                if (allowSyntheticPreview && record.synthetic &&
                    measure.sourceManifest?.let(SYNTHETIC_MANIFESTS::contains) == true
                ) {
                    MetricDecision(true, syntheticPreview = true)
                } else {
                    MetricDecision(false, "synthetic_preview_only")
                }
            }
            MeasurementOrigin.OBSERVER -> MetricDecision(false, "observer_not_teacher_metric")
            MeasurementOrigin.UNKNOWN -> MetricDecision(false, "unknown_provenance")
            MeasurementOrigin.MODEL -> {
                if (record.synthetic) MetricDecision(false, "synthetic_preview_only")
                else modelDecision(record, metricId, measure)
            }
        }
    }

    private fun modelDecision(record: LessonRecord, metricId: String, measure: Measure): MetricDecision {
        val identity = listOf(
            measure.modelArtifactSha256,
            measure.featureConfigHash,
            measure.postprocessHash,
            measure.evidenceManifestHash,
            measure.releaseId,
        )
        if (identity.any { it.isNullOrBlank() }) return MetricDecision(false, "unknown_provenance")
        val hashes = identity.take(4)
        if (hashes.any { !HASH.matches(it!!) }) return MetricDecision(false, "unknown_provenance")

        val release = releases.firstOrNull {
            it.releaseId == measure.releaseId &&
                it.metricId == metricId &&
                it.cohort == record.cohortKey() &&
                it.grade == record.grade &&
                it.subject == record.subject &&
                it.specVersion == record.specVersion &&
                it.labelSchemaVersion == record.labelSchemaVersion &&
                it.modelArtifactSha256 == measure.modelArtifactSha256 &&
                it.featureConfigHash == measure.featureConfigHash &&
                it.postprocessHash == measure.postprocessHash &&
                it.evidenceManifestHash == measure.evidenceManifestHash
        } ?: return MetricDecision(false, "unreleased_metric")

        return when (release.stage) {
            MetricReleaseStage.RELEASED -> MetricDecision(true)
            MetricReleaseStage.WITHDRAWN -> MetricDecision(false, "release_withdrawn")
            MetricReleaseStage.DRAFT, MetricReleaseStage.EXPERIMENTAL -> MetricDecision(false, "unreleased_metric")
        }
    }

    private fun validRange(metricId: String, value: Double?): Boolean {
        if (value == null || !value.isFinite()) return false
        return when (metricId) {
            "I1" -> value in 0.0..1.0
            "I5" -> value >= 0.0
            else -> false
        }
    }

    private val HASH = Regex("[a-fA-F0-9]{64}")
    private val SYNTHETIC_MANIFESTS = setOf(
        "tools/demo/mock-data/meta.json",
        "demo/session-simulator-v1",
    )

    private fun LessonRecord.cohortKey(): String? = when (grade) {
        "STEM" -> "stem_adult"
        "O", "1", "2", "3" -> "early_grade"
        else -> null
    }
}
