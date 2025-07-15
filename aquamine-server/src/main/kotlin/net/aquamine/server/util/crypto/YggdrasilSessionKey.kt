package net.aquamine.server.util.crypto

import java.security.PublicKey
import java.security.Signature

@JvmRecord
data class YggdrasilSessionKey(val publicKey: PublicKey) {

    fun createSignature(): Signature = Signature.getInstance("SHA1withRSA").apply { initVerify(publicKey) }

    companion object {

        private val INSTANCE = load()

        @JvmStatic
        fun get(): YggdrasilSessionKey = INSTANCE

        @JvmStatic
        private fun load(): YggdrasilSessionKey {
            try {
                val keyBytes = YggdrasilSessionKey::class.java.getResourceAsStream("/yggdrasil_session_pubkey.der")!!.readAllBytes()
                return YggdrasilSessionKey(Crypto.bytesToRsaPublicKey(keyBytes))
            } catch (exception: Exception) {
                throw IllegalStateException("Cannot load Yggdrasil session public key!", exception)
            }
        }
    }
}
