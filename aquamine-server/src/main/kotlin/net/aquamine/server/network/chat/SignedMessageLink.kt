package net.aquamine.server.network.chat

import com.google.common.primitives.Ints
import net.aquamine.server.util.crypto.SignatureUpdater
import net.aquamine.server.util.uuid.UUIDUtil
import java.util.UUID

@JvmRecord
data class SignedMessageLink(val index: Int, val sender: UUID, val sessionId: UUID) {

    fun updateSignature(output: SignatureUpdater.Output) {
        output.update(UUIDUtil.toByteArray(sender))
        output.update(UUIDUtil.toByteArray(sessionId))
        output.update(Ints.toByteArray(index))
    }

    fun isDescendantOf(link: SignedMessageLink): Boolean = index > link.index && sender == link.sender && sessionId == link.sessionId

    fun advance(): SignedMessageLink? = if (index == Int.MAX_VALUE) null else SignedMessageLink(index + 1, sender, sessionId)

    companion object {

        @JvmStatic
        fun unsigned(sender: UUID): SignedMessageLink = root(sender, UUIDUtil.NIL_UUID)

        @JvmStatic
        fun root(sender: UUID, sessionId: UUID): SignedMessageLink = SignedMessageLink(0, sender, sessionId)
    }
}
