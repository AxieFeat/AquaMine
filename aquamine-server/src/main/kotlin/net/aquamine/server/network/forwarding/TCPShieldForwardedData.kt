package net.aquamine.server.network.forwarding

@JvmRecord
data class TCPShieldForwardedData(
    override val forwardedAddress: String,
    override val forwardedPort: Int,
    val timestamp: Int,
    val signature: String
) : ProxyForwardedData {

    companion object {

        @JvmStatic
        fun parse(string: String): TCPShieldForwardedData? {
            val split = string.split("///")
            if (split.size < 4) return null
            val ipData = split.get(1).split(':')
            if (ipData.size != 2) return null

            val ip = ipData.get(0)
            val port = ipData.get(1).toIntOrNull() ?: return null
            val timestamp = split.get(2).toIntOrNull() ?: return null
            val signature = split.get(3)
            return TCPShieldForwardedData(ip, port, timestamp, signature)
        }
    }
}
