package org.ethioware.echo.pilot

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.AtomicFile
import java.io.File
import java.security.KeyStore
import java.security.MessageDigest
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

/** One Keystore key per private file; no cleartext files, external storage, export or backup. */
class AndroidPrivateFiles(context: Context) : PrivateFiles {
    private val directory = File(context.noBackupFilesDir, "pilot-v1").apply { check(mkdirs() || isDirectory) }
    private val keys = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
    init {
        // AtomicFile .new files cannot be committed; this instance is created once per process.
        directory.listFiles().orEmpty().filter { it.name.endsWith(".new") }.forEach { check(it.delete()) }
    }
    private fun file(name: String): AtomicFile {
        require(name.matches(Regex("[a-zA-Z0-9.\\-]+")))
        return AtomicFile(File(directory, name))
    }
    private fun alias(name: String) = "echo.pilot." + MessageDigest.getInstance("SHA-256")
        .digest(name.toByteArray()).joinToString("") { "%02x".format(it) }
    private fun key(name: String, create: Boolean): SecretKey {
        val alias = alias(name)
        (keys.getKey(alias, null) as? SecretKey)?.let { return it }
        check(create) { "Private storage key is unavailable. No data was reset." }
        return KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore").apply {
            init(KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setRandomizedEncryptionRequired(true).build())
        }.generateKey()
    }
    @Synchronized override fun names() = directory.listFiles().orEmpty()
        .filter { it.isFile && !it.name.endsWith(".new") }.map { it.name.removeSuffix(".bak") }.distinct()
    @Synchronized override fun read(name: String): ByteArray? {
        val target = file(name)
        if (!target.baseFile.exists() && !File(target.baseFile.path + ".bak").exists()) return null
        return AuthenticatedEnvelope.open(name, target.readFully(), key(name, false))
    }
    @Synchronized override fun write(name: String, bytes: ByteArray) {
        val encrypted = AuthenticatedEnvelope.seal(name, bytes, key(name, true))
        val target = file(name)
        val stream = target.startWrite()
        try { stream.write(encrypted); target.finishWrite(stream) }
        catch (error: Exception) { target.failWrite(stream); throw error }
    }
    @Synchronized override fun delete(name: String) {
        // AtomicFile removes .new/.bak too. Failure is surfaced instead of claiming deletion.
        val target = file(name)
        target.delete()
        check(!target.baseFile.exists() && !File(target.baseFile.path + ".bak").exists())
        keys.deleteEntry(alias(name))
    }
}

object PilotRuntime {
    @Volatile private var instance: PilotStore? = null
    @Synchronized fun store(context: Context): PilotStore = instance ?: PilotStore(AndroidPrivateFiles(context.applicationContext))
        .also { it.recover(); instance = it }
    fun scheduleExpiry(context: Context) {
        context.getSystemService(JobScheduler::class.java).schedule(
            JobInfo.Builder(3101, ComponentName(context, ExpiryJob::class.java))
                .setPeriodic(24 * 60 * 60 * 1000L).setPersisted(true).build())
    }
}

class ExpiryJob : JobService() {
    override fun onStartJob(params: JobParameters): Boolean {
        Thread {
            val retry = runCatching {
                val store = PilotRuntime.store(this)
                store.expire()
                WeeklyReminder.maybeNotify(this, store)
            }.isFailure
            jobFinished(params, retry)
        }.start()
        return true
    }
    override fun onStopJob(params: JobParameters) = true
}
