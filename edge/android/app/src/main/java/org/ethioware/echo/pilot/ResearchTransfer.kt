package org.ethioware.echo.pilot

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import org.ethioware.echo.BuildConfig
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/** Platform certificate validation; no redirects, custom trust store or arbitrary destination. */
class ResearchHttpTransport(private val approvedOrigin: String) : ResearchTransport {
    override fun post(origin: String, path: String, token: String?, payload: JSONObject): TransferReply {
        check(approvedOrigin.isNotBlank() && researchEndpoint(origin) == researchEndpoint(approvedOrigin)) { "No approved research host is configured." }
        require(path in setOf("/api/v1/research/enroll","/api/v1/research/summaries","/api/v1/research/withdraw"))
        val body=payload.toString().toByteArray(Charsets.UTF_8);require(body.size<=4096)
        val connection=URL(origin+path).openConnection() as HttpsURLConnection
        try {
            connection.requestMethod="POST";connection.instanceFollowRedirects=false
            connection.connectTimeout=10_000;connection.readTimeout=10_000;connection.doOutput=true
            connection.setRequestProperty("Content-Type","application/json")
            token?.let { connection.setRequestProperty("Authorization","Bearer $it") }
            connection.setFixedLengthStreamingMode(body.size)
            connection.outputStream.use { it.write(body) }
            val status=connection.responseCode
            val stream=if (status in 200..299) connection.inputStream else connection.errorStream
            val bytes=stream?.use {
                val buffer=java.io.ByteArrayOutputStream()
                val block=ByteArray(512)
                while (buffer.size()<=4096) {
                    val read=it.read(block,0,minOf(block.size,4097-buffer.size()))
                    if (read<0)break
                    buffer.write(block,0,read)
                }
                buffer.toByteArray()
            } ?: ByteArray(0)
            require(bytes.size<=4096)
            return TransferReply(status,if (bytes.isEmpty()) JSONObject() else JSONObject(bytes.toString(Charsets.UTF_8)))
        } finally { body.fill(0);connection.disconnect() }
    }
}

object ResearchTransfer {
    fun transport()=ResearchHttpTransport(BuildConfig.RESEARCH_ORIGIN)
    fun schedule(context: Context) {
        if (BuildConfig.RESEARCH_ORIGIN.isBlank())return
        context.getSystemService(JobScheduler::class.java).schedule(JobInfo.Builder(3102,ComponentName(context,ResearchJob::class.java))
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).setRequiresBatteryNotLow(true)
            .setPeriodic(6*60*60*1000L).setPersisted(true).build())
    }
}
class ResearchJob : JobService() {
    @Volatile private var stopped=false
    override fun onStartJob(params: JobParameters): Boolean {
        stopped=false
        Thread {
            val retry=runCatching {
                if (!stopped && !RecordingMonitor.state.running) ResearchQueue(PilotRuntime.store(this)).send(ResearchTransfer.transport())
            }.isFailure
            if (!stopped) jobFinished(params,retry)
        }.start()
        return true
    }
    override fun onStopJob(params: JobParameters): Boolean {stopped=true;return true}
}
