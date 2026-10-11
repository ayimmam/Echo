package org.ethioware.echo.pilot

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context

/** Deferred local processing: no model/network runtime and no work while recording. */
object LocalAnalysis {
    fun schedule(context: Context) {
        context.getSystemService(JobScheduler::class.java).schedule(JobInfo.Builder(3103,ComponentName(context,AnalysisJob::class.java))
            .setRequiresCharging(true).setRequiresBatteryNotLow(true).setRequiresStorageNotLow(true)
            .setPersisted(true).setMinimumLatency(30_000).build())
    }
}
class AnalysisJob : JobService() {
    @Volatile private var stopped=false
    override fun onStartJob(params: JobParameters): Boolean {
        stopped=false
        Thread {
            val retry=runCatching {
                if (!RecordingMonitor.state.running) {
                    val store=PilotRuntime.store(this)
                    store.lessons().filter { it.status!="recording" }.forEach {
                        if (!stopped && !RecordingMonitor.state.running) store.analyzeSignal(it.id)
                    }
                }
            }.isFailure
            if (!stopped) jobFinished(params,retry)
        }.start()
        return true
    }
    override fun onStopJob(params: JobParameters): Boolean {stopped=true;return true}
}
