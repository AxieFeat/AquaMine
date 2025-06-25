package net.aquamine.server.network.chat

import com.google.common.primitives.Ints
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.ByteBufExtras
import net.aquamine.server.util.crypto.SignatureUpdater
import java.util.BitSet

@JvmRecord
data class LastSeenMessages(val entries: List<MessageSignature>) {

    fun updateSignature(output: SignatureUpdater.Output) {
        output.update(Ints.toByteArray(entries.size))
        entries.forEach { output.update(it.bytes) }
    }

    fun pack(signatureCache: MessageSignatureCache): Packed = Packed(entries.map { it.pack(signatureCache) })

    @JvmRecord
    data class Packed(val entries: List<MessageSignature.Packed>) : Writable {

        constructor(reader: BinaryReader) : this(reader.readCollection(ByteBufExtras.limitValue(::ArrayList, 20), MessageSignature.Packed::read))

        override fun write(writer: BinaryWriter) {
            writer.writeCollection(entries) { it.write(writer) }
        }

        companion object {

            @JvmField
            val EMPTY: Packed = Packed(emptyList())
        }
    }

    @JvmRecord
    data class Update(val offset: Int, val acknowledged: BitSet) : Writable {

        constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readFixedBitSet(LAST_SEEN_MESSAGE_MAX_LENGTH))

        override fun write(writer: BinaryWriter) {
            writer.writeVarInt(offset)
            writer.writeFixedBitSet(acknowledged, LAST_SEEN_MESSAGE_MAX_LENGTH)
        }
    }

    companion object {

        @JvmField
        val EMPTY: LastSeenMessages = LastSeenMessages(emptyList())
        private const val LAST_SEEN_MESSAGE_MAX_LENGTH = 20
    }
}
