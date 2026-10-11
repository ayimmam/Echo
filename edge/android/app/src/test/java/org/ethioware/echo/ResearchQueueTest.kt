package org.ethioware.echo

import org.ethioware.echo.pilot.*
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test

class ResearchQueueTest {
    private class Memory : PrivateFiles {
        val data=mutableMapOf<String,ByteArray>()
        override fun names()=data.keys.toList()
        override fun read(name:String)=data[name]?.copyOf()
        override fun write(name:String,bytes:ByteArray) {data[name]=bytes.copyOf()}
        override fun delete(name:String) {data.remove(name)}
    }
    private var time=1_800_000_000_000L
    private val store=PilotStore(Memory()) {time}
    private val queue=ResearchQueue(store)
    private fun enrolled() {
        queue.enroll("https://research.example.et","c".repeat(43),ResearchTransport { _,_,_,_->
            TransferReply(200,JSONObject().put("token","t".repeat(43)).put("expires_at_ms",time+RETENTION_MS))
        })
    }
    private fun lesson():LocalLesson {
        val row=store.create(ClassProfile(name="PRIVATE NAME"))
        store.append(row.id,byteArrayOf(1,2),30_000,100,2,3);store.finish(row.id)
        store.correct(row.id,"Not specified","PRIVATE NOTE",false)
        return store.lessons().first()
    }
    private fun payload()=ResearchProjection.build(listOf(lesson()),JSONObject().put("rating",4))
    @Test fun projectionContainsNoPrivateStringsOrClassIdentifiers() {
        val row=lesson();val result=ResearchProjection.build(listOf(row),JSONObject().put("rating",5))
        assertFalse(result.toString().contains("PRIVATE"));assertFalse(result.toString().contains(row.profile.id))
        assertFalse(result.has("audio"));assertEquals(Coaching.VERSION,result.getString("content_version"))
        assertTrue(result.getLong("collected_at_ms")<=row.createdAt)
    }
    @Test fun noEnrollmentNoQueue() {
        assertThrows(IllegalStateException::class.java) {queue.enqueue("review",payload())}
    }
    @Test fun onlyHttpsOrigins() {
        for (bad in listOf("http://example.et","https://user:pass@example.et","https://example.et/path","https://example.et?token=secret"))
            assertThrows(IllegalArgumentException::class.java) {researchEndpoint(bad)}
    }
    @Test fun retryUsesIdenticalPayloadAndAcknowledgesOnce() {
        enrolled();queue.enqueue("review",payload());var calls=0;var original=""
        val transport=ResearchTransport { _,_,_,value->calls++;if (original.isEmpty())original=value.toString() else assertEquals(original,value.toString());TransferReply(if (calls==1)503 else 200,JSONObject().put("status","duplicate"))}
        queue.send(transport);queue.send(transport);assertEquals(1,calls)
        time+=180_000;queue.send(transport);queue.send(transport);assertEquals(2,calls)
    }
    @Test fun withdrawalPrecedesUploadAndRetriesOffline() {
        enrolled();queue.enqueue("review",payload());queue.withdraw();assertTrue(queue.pending().isEmpty())
        queue.send(ResearchTransport { _,path,_,_->assertEquals("/api/v1/research/withdraw",path);TransferReply(503,JSONObject())})
        assertTrue(queue.config().getBoolean("withdrawal"));time+=3_600_001
        queue.send(ResearchTransport { _,path,_,_->assertEquals("/api/v1/research/withdraw",path);TransferReply(200,JSONObject())})
        assertFalse(queue.config().has("token"))
    }
    @Test fun withdrawalDuringAcknowledgmentCannotResurrectQueue() {
        enrolled();queue.enqueue("review",payload())
        queue.send(ResearchTransport { _,_,_,_->queue.withdraw();TransferReply(200,JSONObject().put("status","accepted"))})
        assertTrue(queue.pending().isEmpty());assertTrue(queue.config().getBoolean("withdrawal"))
    }
    @Test fun existingEnrollmentCannotBeReplacedWithoutWithdrawal() {
        enrolled()
        assertThrows(IllegalStateException::class.java) { enrolled() }
    }
    @Test fun acknowledgedMarkersDoNotStarveLaterQueuedReviews() {
        enrolled()
        for (i in 1..5) queue.enqueue("review$i",payload())
        var calls=0
        val transport=ResearchTransport { _,_,_,_->calls++;TransferReply(200,JSONObject().put("status","accepted")) }
        queue.send(transport);assertEquals(3,calls)
        queue.send(transport);assertEquals(5,calls)
        queue.send(transport);assertEquals(5,calls)
    }
    @Test fun expiryNeverUploads() {
        enrolled();queue.enqueue("review",payload());time+=RETENTION_MS
        queue.send(ResearchTransport { _,_,_,_->throw AssertionError("expired item uploaded") })
        assertTrue(queue.pending().isEmpty());assertFalse(queue.config().has("token"))
    }
    @Test fun exclusionWithdrawsWithoutSendingPrivateCorrection() {
        enrolled();val row=lesson();queue.enqueue("review",ResearchProjection.build(listOf(row),JSONObject()))
        store.correct(row.id,"Other adult or recorded audio","private",true)
        assertTrue(queue.pending().isEmpty());assertTrue(queue.config().getBoolean("withdrawal"))
    }
    @Test fun clearDataKeepsOnlyPendingWithdrawalCredentials() {
        enrolled();queue.enqueue("review",payload());store.clearCollectedData()
        assertTrue(store.lessons().isEmpty());assertTrue(queue.pending().isEmpty())
        assertTrue(queue.config().getBoolean("withdrawal"))
    }
    @Test fun invalidMixedClassesCannotBecomeOneSummary() {
        val a=lesson();val b=a.copy(profile=a.profile.copy(id="another"))
        assertThrows(IllegalArgumentException::class.java) {ResearchProjection.build(listOf(a,b),JSONObject())}
    }
    @Test fun signalHasNoSemanticMetricsAndExpiresWithLesson() {
        val row=lesson();val result=store.analyzeSignal(row.id)!!
        assertFalse(result.getBoolean("pedagogy_available"));assertEquals(0.02,result.getDouble("clipped_fraction"),0.00001)
        time+=RETENTION_MS;assertNull(store.signalAnalysis(row.id));assertNull(store.analyzeSignal(row.id))
    }
    @Test fun expiredSourceIsNeverAnalyzed() {
        val row=lesson();time+=RETENTION_MS;assertNull(store.analyzeSignal(row.id))
    }
    @Test fun noSamplesIsUnavailableNotZero() {
        val row=store.create(ClassProfile());store.finish(row.id)
        val result=store.analyzeSignal(row.id)!!;assertFalse(result.getBoolean("signal_available"));assertTrue(result.isNull("near_zero_fraction"))
    }
}
