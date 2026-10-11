package org.ethioware.echo.pilot

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/** Versioned AES-GCM envelope. File identity is authenticated to prevent cross-file substitution. */
object AuthenticatedEnvelope {
    fun seal(name: String, bytes: ByteArray, key: SecretKey): ByteArray {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        check(cipher.iv.size == 12)
        cipher.updateAAD(name.toByteArray(Charsets.UTF_8))
        return byteArrayOf(1) + cipher.iv + cipher.doFinal(bytes)
    }
    fun open(name: String, bytes: ByteArray, key: SecretKey): ByteArray {
        require(bytes.size >= 29 && bytes[0] == 1.toByte()) { "Invalid private storage format" }
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, bytes.copyOfRange(1, 13)))
        cipher.updateAAD(name.toByteArray(Charsets.UTF_8))
        return cipher.doFinal(bytes, 13, bytes.size - 13)
    }
}
