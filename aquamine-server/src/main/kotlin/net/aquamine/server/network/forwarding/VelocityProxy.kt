package net.aquamine.server.network.forwarding

import com.google.common.net.InetAddresses
import net.aquamine.server.network.buffer.BinaryReader
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object VelocityProxy {

    private const val SIGNATURE_ALGORITHM = "HmacSHA256"
    private const val SIGNATURE_BYTES = 32
    private const val MODERN_FORWARDING_WITH_KEY = 2
    const val MAX_SUPPORTED_FORWARDING_VERSION: Int = MODERN_FORWARDING_WITH_KEY

    @JvmStatic
    fun verifyIntegrity(reader: BinaryReader, secret: ByteArray): Boolean {
        val signature = reader.readBytes(SIGNATURE_BYTES)
        val data = reader.readAllBytes()

        val mac = Mac.getInstance(SIGNATURE_ALGORITHM)
        mac.init(SecretKeySpec(secret, SIGNATURE_ALGORITHM))
        val mySignature = mac.doFinal(data)
        return MessageDigest.isEqual(signature, mySignature)
    }

    @JvmStatic
    fun readData(reader: BinaryReader): VelocityForwardedData {
        val address = InetAddresses.forString(reader.readString())
        val uuid = reader.readUUID()
        val name = reader.readString()
        require(name.length <= 16) { "Name too long! Max: 16" }
        return VelocityForwardedData(address, uuid, name, reader.readProfileProperties())
    }
}
