package org.ethioware.echo

import org.ethioware.echo.pilot.AuthenticatedEnvelope
import org.junit.Assert.*
import org.junit.Test
import javax.crypto.KeyGenerator

class AuthenticatedEnvelopeTest {
    private val key = KeyGenerator.getInstance("AES").apply { init(256) }.generateKey()
    @Test fun `roundtrip preserves private bytes and uses a fresh nonce`() {
        val original = "private classroom audio".toByteArray()
        val first = AuthenticatedEnvelope.seal("audio.one.0", original, key)
        val second = AuthenticatedEnvelope.seal("audio.one.0", original, key)
        assertFalse(first.contentEquals(second))
        assertArrayEquals(original, AuthenticatedEnvelope.open("audio.one.0", first, key))
        assertFalse(first.toString(Charsets.UTF_8).contains("private classroom audio"))
    }
    @Test fun `ciphertext modification and file substitution fail closed`() {
        val bytes = AuthenticatedEnvelope.seal("audio.one.0", byteArrayOf(1, 2), key)
        assertThrows(Exception::class.java) { AuthenticatedEnvelope.open("audio.two.0", bytes, key) }
        bytes[bytes.lastIndex] = (bytes.last().toInt() xor 1).toByte()
        assertThrows(Exception::class.java) { AuthenticatedEnvelope.open("audio.one.0", bytes, key) }
    }
    @Test fun `truncated or unknown envelopes fail without returning plaintext`() {
        assertThrows(IllegalArgumentException::class.java) { AuthenticatedEnvelope.open("file", ByteArray(20), key) }
        assertThrows(IllegalArgumentException::class.java) { AuthenticatedEnvelope.open("file", ByteArray(40), key) }
    }
}
