package net.aquamine.server.util

import java.net.InetAddress
import java.net.SocketAddress

object AddressUtil {

    @JvmStatic
    fun asString(address: SocketAddress): String = formatAddressString(address.toString())

    @JvmStatic
    fun asString(address: InetAddress): String = formatAddressString(address.toString())

    @JvmStatic
    private fun formatAddressString(input: String): String {
        var temp = input
        if (temp.contains('/')) temp = temp.substring(temp.indexOf('/') + 1)
        if (temp.contains(':')) temp = temp.substring(0, temp.indexOf(':'))
        return temp
    }
}
