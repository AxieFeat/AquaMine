package net.aquamine.server.util.crypto

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.security.PublicKey
import java.security.Signature
import java.security.SignatureException

fun interface SignatureValidator {

    fun validate(payload: ByteArray, updater: SignatureUpdater): Boolean

    fun validate(payload: ByteArray, signature: ByteArray): Boolean = validate(signature) { it.update(payload) }

    companion object {

        @JvmField
        val LOGGER: Logger = LogManager.getLogger()
        @JvmField
        val YGGDRASIL: SignatureValidator = from(YggdrasilSessionKey.get().createSignature())

        @JvmStatic
        fun from(publicKey: PublicKey, algorithm: String): SignatureValidator =
            from(Signature.getInstance(algorithm).apply { initVerify(publicKey) })

        private fun from(signature: Signature): SignatureValidator = SignatureValidator { payload, updater ->
            try {
                updater.update { signature.update(it) }
                signature.verify(payload)
            } catch (exception: SignatureException) {
                LOGGER.error("Failed to verify signature!", exception)
                false
            }
        }
    }
}
