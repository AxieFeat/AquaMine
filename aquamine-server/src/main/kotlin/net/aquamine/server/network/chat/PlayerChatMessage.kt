package net.aquamine.server.network.chat

import com.google.common.primitives.Ints
import net.kyori.adventure.text.Component
import net.aquamine.server.util.crypto.SignatureUpdater
import net.aquamine.server.util.crypto.SignatureValidator
import net.aquamine.server.util.uuid.UUIDUtil
import java.time.Duration
import java.time.Instant
import java.util.UUID

@JvmRecord
data class PlayerChatMessage(
    val link: SignedMessageLink,
    val signature: MessageSignature?,
    val signedBody: SignedMessageBody,
    val unsignedContent: Component?,
    val filterMask: FilterMask
) {

    fun withUnsignedContent(unsigned: Component): PlayerChatMessage {
        val newContent = if (unsigned != Component.text(signedContent())) unsigned else null
        return PlayerChatMessage(link, signature, signedBody, newContent, filterMask)
    }

    fun removeUnsignedContent(): PlayerChatMessage {
        if (unsignedContent == null) return this
        return PlayerChatMessage(link, signature, signedBody, null, filterMask)
    }

    fun filter(mask: FilterMask): PlayerChatMessage {
        if (filterMask == mask) return this
        return PlayerChatMessage(link, signature, signedBody, unsignedContent, mask)
    }

    fun filter(state: Boolean): PlayerChatMessage = filter(if (state) filterMask else FilterMask.PASS_THROUGH)

    fun verify(validator: SignatureValidator): Boolean = signature != null && signature.verify(validator) { updateSignature(it, link, signedBody) }

    private fun signedContent(): String = signedBody.content

    fun decoratedContent(): Component = unsignedContent ?: Component.text(signedContent())

    fun timestamp(): Instant = signedBody.timestamp

    fun salt(): Long = signedBody.salt

    fun hasExpired(time: Instant): Boolean = time.isAfter(timestamp().plus(MESSAGE_EXPIRES_AFTER))

    fun sender(): UUID = link.sender

    fun isSystem(): Boolean = sender() == SYSTEM_SENDER

    fun hasSignature(): Boolean = signature != null

    fun hasSignatureFrom(id: UUID): Boolean = hasSignature() && link.sender == id

    fun isFullyFiltered(): Boolean = filterMask.isFullyFiltered()

    companion object {

        private val SYSTEM_SENDER = UUIDUtil.NIL_UUID
        private val MESSAGE_EXPIRES_AFTER = Duration.ofMinutes(5L)

        @JvmStatic
        fun system(content: String): PlayerChatMessage = unsigned(SYSTEM_SENDER, content)

        @JvmStatic
        fun unsigned(sender: UUID, content: String): PlayerChatMessage =
            PlayerChatMessage(SignedMessageLink.unsigned(sender), null, SignedMessageBody.unsigned(content), null, FilterMask.PASS_THROUGH)

        @JvmStatic
        fun updateSignature(output: SignatureUpdater.Output, link: SignedMessageLink, body: SignedMessageBody) {
            output.update(Ints.toByteArray(1))
            link.updateSignature(output)
            body.updateSignature(output)
        }
    }
}
