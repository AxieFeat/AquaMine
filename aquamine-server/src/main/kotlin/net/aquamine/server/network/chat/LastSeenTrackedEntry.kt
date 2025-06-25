package net.aquamine.server.network.chat

@JvmRecord
data class LastSeenTrackedEntry(val signature: MessageSignature, val pending: Boolean) {

    fun acknowledge(): LastSeenTrackedEntry = if (pending) LastSeenTrackedEntry(signature, false) else this
}
