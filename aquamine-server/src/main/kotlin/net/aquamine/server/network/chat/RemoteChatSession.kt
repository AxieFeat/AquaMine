package net.aquamine.server.network.chat

import net.aquamine.api.auth.GameProfile
import net.aquamine.server.entity.player.PlayerPublicKey
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.crypto.SignatureValidator
import java.time.Duration
import java.util.UUID

@JvmRecord
data class RemoteChatSession(val sessionId: UUID, val publicKey: PlayerPublicKey) {

    fun createMessageValidator(): SignedMessageValidator = SignedMessageValidator.KeyBased(publicKey.createSignatureValidator())

    fun createMessageDecoder(id: UUID): SignedMessageChain.Decoder = SignedMessageChain(id, sessionId).decoder(publicKey)

    fun asData(): Data = Data(sessionId, publicKey.data)

    @JvmRecord
    data class Data(val sessionId: UUID, val publicKey: PlayerPublicKey.Data) {

        fun validate(profile: GameProfile, validator: SignatureValidator, duration: Duration): RemoteChatSession =
            RemoteChatSession(sessionId, PlayerPublicKey.createValidated(validator, profile.uuid, publicKey, duration))

        companion object {

            @JvmStatic
            fun read(reader: BinaryReader): Data = Data(reader.readUUID(), PlayerPublicKey.Data(reader))

            @JvmStatic
            fun write(writer: BinaryWriter, session: Data) {
                writer.writeUUID(session.sessionId)
                session.publicKey.write(writer)
            }
        }
    }
}
