package net.aquamine.server.util.crypto

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PublicKey
import javax.crypto.Cipher

/**
 * A utility for generating the server's key pair and decrypting data with the
 * public key.
 *
 * See [here](https://wiki.vg/Protocol_Encryption#Key_Exchange)
 */
object Encryption {

    const val SYMMETRIC_ALGORITHM: String = "AES"
    private const val ASYMMETRIC_ALGORITHM: String = "RSA"
    private const val ASYMMETRIC_BITS: Int = 1024
    const val SIGNATURE_ALGORITHM: String = "SHA256withRSA"

    private val keyPair = generateKeyPair()
    val publicKey: PublicKey = keyPair.public

    @JvmStatic
    fun decrypt(encryptedData: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(ASYMMETRIC_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, keyPair.private)
        return cipher.doFinal(encryptedData)
    }

    @JvmStatic
    private fun generateKeyPair(): KeyPair {
        val generator = KeyPairGenerator.getInstance(ASYMMETRIC_ALGORITHM)
        generator.initialize(ASYMMETRIC_BITS)
        return generator.generateKeyPair()
    }
}
