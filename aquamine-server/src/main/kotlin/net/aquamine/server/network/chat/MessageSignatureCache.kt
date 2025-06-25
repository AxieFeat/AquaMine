package net.aquamine.server.network.chat

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
import java.util.ArrayDeque
import java.util.Deque

class MessageSignatureCache(entryCount: Int) {

    private val entries = arrayOfNulls<MessageSignature>(entryCount)

    fun pack(signature: MessageSignature): Int = entries.indexOfFirst { it == signature }

    fun push(message: PlayerChatMessage) {
        val lastSeen = message.signedBody.lastSeen.entries
        val queue = ArrayDeque<MessageSignature>(lastSeen.size + 1)
        queue.addAll(lastSeen)
        message.signature?.let { queue.add(it) }
        push(queue)
    }

    private fun push(queue: Deque<MessageSignature>) {
        val signatures = ObjectOpenHashSet<MessageSignature>()
        var i = 0
        while (!queue.isEmpty() && i < entries.size) {
            val entry = entries[i]
            entries[i] = queue.removeLast()
            if (entry != null && !signatures.contains(entry)) queue.addFirst(entry)
            ++i
        }
    }

    companion object {

        private const val DEFAULT_CAPACITY = 128

        @JvmStatic
        fun createDefault(): MessageSignatureCache = MessageSignatureCache(DEFAULT_CAPACITY)
    }
}
