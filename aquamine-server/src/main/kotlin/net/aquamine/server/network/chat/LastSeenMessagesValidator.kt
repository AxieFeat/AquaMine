package net.aquamine.server.network.chat

import it.unimi.dsi.fastutil.objects.ObjectArrayList

class LastSeenMessagesValidator(private val lastSeenCount: Int) {

    private val trackedMessages = ObjectArrayList<LastSeenTrackedEntry?>()
    private var lastPendingMessage: MessageSignature? = null

    init {
        for (i in 0 until lastSeenCount) {
            trackedMessages.add(null)
        }
    }

    fun addPending(signature: MessageSignature) {
        if (signature == lastPendingMessage) return
        trackedMessages.add(LastSeenTrackedEntry(signature, true))
        lastPendingMessage = signature
    }

    fun trackedMessagesCount(): Int = trackedMessages.size

    fun applyOffset(offset: Int): Boolean {
        val trackedOffset = trackedMessages.size - lastSeenCount
        if (offset >= 0 && offset <= trackedOffset) {
            trackedMessages.removeElements(0, offset)
            return true
        } else {
            return false
        }
    }

    fun applyUpdate(update: LastSeenMessages.Update): LastSeenMessages? {
        if (!applyOffset(update.offset)) return null
        if (update.acknowledged.length() > lastSeenCount) return null

        val entries = ObjectArrayList<MessageSignature>(update.acknowledged.cardinality())
        for (i in 0 until lastSeenCount) {
            val isAcknowledged = update.acknowledged.get(i)
            val entry = trackedMessages[i]
            if (isAcknowledged) {
                if (entry == null) return null
                trackedMessages[i] = entry.acknowledge()
                entries.add(entry.signature)
            } else {
                if (entry != null && !entry.pending) return null
                trackedMessages[i] = null
            }
        }
        return LastSeenMessages(entries)
    }
}
