package net.aquamine.server.packet

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.packet.`in`.handshake.PacketInHandshake
import net.aquamine.server.packet.`in`.login.PacketInEncryptionResponse
import net.aquamine.server.packet.`in`.login.PacketInLoginStart
import net.aquamine.server.packet.`in`.login.PacketInPluginResponse
import net.aquamine.server.packet.`in`.play.PacketInAbilities
import net.aquamine.server.packet.`in`.play.PacketInSwingArm
import net.aquamine.server.packet.`in`.play.PacketInSetHeldItem
import net.aquamine.server.packet.out.play.PacketOutChatSuggestions
import net.aquamine.server.packet.out.play.PacketOutDeleteChat
import net.aquamine.server.packet.out.play.PacketOutDisguisedChat
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoRemove
import net.aquamine.server.packet.`in`.play.PacketInChat
import net.aquamine.server.packet.`in`.play.PacketInClientInformation
import net.aquamine.server.packet.`in`.play.PacketInClientCommand
import net.aquamine.server.packet.`in`.play.PacketInChatCommand
import net.aquamine.server.packet.`in`.play.PacketInChatSessionUpdate
import net.aquamine.server.packet.`in`.play.PacketInSetCreativeModeSlot
import net.aquamine.server.packet.`in`.play.PacketInPlayerCommand
import net.aquamine.server.packet.`in`.play.PacketInQueryEntityTag
import net.aquamine.server.packet.`in`.play.PacketInInteract
import net.aquamine.server.packet.`in`.play.PacketInKeepAlive
import net.aquamine.server.packet.`in`.play.PacketInUseItemOn
import net.aquamine.server.packet.`in`.play.PacketInPlayerAction
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerOnGround
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerPosition
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerPositionAndRotation
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerRotation
import net.aquamine.server.packet.`in`.play.PacketInUseItem
import net.aquamine.server.packet.`in`.play.PacketInPluginMessage
import net.aquamine.server.packet.`in`.play.PacketInResourcePack
import net.aquamine.server.packet.`in`.play.PacketInPlayerInput
import net.aquamine.server.packet.`in`.play.PacketInCommandSuggestionsRequest
import net.aquamine.server.packet.`in`.play.PacketInConfirmTeleportation
import net.aquamine.server.packet.`in`.status.PacketInPingRequest
import net.aquamine.server.packet.`in`.status.PacketInStatusRequest
import net.aquamine.server.packet.out.login.PacketOutEncryptionRequest
import net.aquamine.server.packet.out.login.PacketOutLoginDisconnect
import net.aquamine.server.packet.out.login.PacketOutLoginSuccess
import net.aquamine.server.packet.out.login.PacketOutPluginRequest
import net.aquamine.server.packet.out.login.PacketOutSetCompression
import net.aquamine.server.packet.out.play.PacketOutAbilities
import net.aquamine.server.packet.out.play.PacketOutAcknowledgeBlockChange
import net.aquamine.server.packet.out.play.PacketOutSetActionBarText
import net.aquamine.server.packet.out.play.PacketOutAnimation
import net.aquamine.server.packet.out.play.PacketOutUpdateAttributes
import net.aquamine.server.packet.out.play.PacketOutAwardStatistics
import net.aquamine.server.packet.out.play.PacketOutBlockUpdate
import net.aquamine.server.packet.out.play.PacketOutBossBar
import net.aquamine.server.packet.out.play.PacketOutChangeDifficulty
import net.aquamine.server.packet.out.play.PacketOutChunkDataAndLight
import net.aquamine.server.packet.out.play.PacketOutClearTitles
import net.aquamine.server.packet.out.play.PacketOutCommandSuggestionsResponse
import net.aquamine.server.packet.out.play.PacketOutCommands
import net.aquamine.server.packet.out.play.PacketOutRemoveEntities
import net.aquamine.server.packet.out.play.PacketOutDisconnect
import net.aquamine.server.packet.out.play.PacketOutDisplayObjective
import net.aquamine.server.packet.out.play.PacketOutEntityEvent
import net.aquamine.server.packet.out.play.PacketOutEntitySoundEffect
import net.aquamine.server.packet.out.play.PacketOutGameEvent
import net.aquamine.server.packet.out.play.PacketOutSetHeadRotation
import net.aquamine.server.packet.out.play.PacketOutInitializeWorldBorder
import net.aquamine.server.packet.out.play.PacketOutKeepAlive
import net.aquamine.server.packet.out.play.PacketOutLogin
import net.aquamine.server.packet.out.play.PacketOutSetEntityMetadata
import net.aquamine.server.packet.out.play.PacketOutTagQueryResponse
import net.aquamine.server.packet.out.play.PacketOutUpdateObjectives
import net.aquamine.server.packet.out.play.PacketOutOpenBook
import net.aquamine.server.packet.out.play.PacketOutParticle
import net.aquamine.server.packet.out.play.PacketOutPlayerChat
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate
import net.aquamine.server.packet.out.play.PacketOutSetTabListHeaderAndFooter
import net.aquamine.server.packet.out.play.PacketOutSynchronizePlayerPosition
import net.aquamine.server.packet.out.play.PacketOutPluginMessage
import net.aquamine.server.packet.out.play.PacketOutResourcePack
import net.aquamine.server.packet.out.play.PacketOutSetBlockDestroyStage
import net.aquamine.server.packet.out.play.PacketOutSetCamera
import net.aquamine.server.packet.out.play.PacketOutSetContainerContent
import net.aquamine.server.packet.out.play.PacketOutSetContainerSlot
import net.aquamine.server.packet.out.play.PacketOutSetCooldown
import net.aquamine.server.packet.out.play.PacketOutSetEntityVelocity
import net.aquamine.server.packet.out.play.PacketOutSetHeldItem
import net.aquamine.server.packet.out.play.PacketOutSetPassengers
import net.aquamine.server.packet.out.play.PacketOutSoundEffect
import net.aquamine.server.packet.out.play.PacketOutSpawnEntity
import net.aquamine.server.packet.out.play.PacketOutSpawnExperienceOrb
import net.aquamine.server.packet.out.play.PacketOutSpawnPlayer
import net.aquamine.server.packet.out.play.PacketOutSetDefaultSpawnPosition
import net.aquamine.server.packet.out.play.PacketOutStopSound
import net.aquamine.server.packet.out.play.PacketOutSetSubtitleText
import net.aquamine.server.packet.out.play.PacketOutSystemChat
import net.aquamine.server.packet.out.play.PacketOutUpdateTags
import net.aquamine.server.packet.out.play.PacketOutUpdateTeams
import net.aquamine.server.packet.out.play.PacketOutTeleportEntity
import net.aquamine.server.packet.out.play.PacketOutUpdateTime
import net.aquamine.server.packet.out.play.PacketOutSetTitleText
import net.aquamine.server.packet.out.play.PacketOutSetTitleAnimationTimes
import net.aquamine.server.packet.out.play.PacketOutUnloadChunk
import net.aquamine.server.packet.out.play.PacketOutUpdateRecipeBook
import net.aquamine.server.packet.out.play.PacketOutUpdateEntityPosition
import net.aquamine.server.packet.out.play.PacketOutUpdateEntityPositionAndRotation
import net.aquamine.server.packet.out.play.PacketOutUpdateEntityRotation
import net.aquamine.server.packet.out.play.PacketOutSetHealth
import net.aquamine.server.packet.out.play.PacketOutUpdateLight
import net.aquamine.server.packet.out.play.PacketOutUpdateRecipes
import net.aquamine.server.packet.out.play.PacketOutUpdateScore
import net.aquamine.server.packet.out.play.PacketOutSetCenterChunk
import net.aquamine.server.packet.out.play.PacketOutUpdateEnabledFeatures
import net.aquamine.server.packet.out.play.PacketOutWorldEvent
import net.aquamine.server.packet.out.status.PacketOutPingResponse
import net.aquamine.server.packet.out.status.PacketOutStatusResponse
import java.nio.ByteBuffer

object PacketRegistry {

    private val byEncoded = Int2ObjectOpenHashMap<PacketConstructor>()
    private val toId = Reference2IntOpenHashMap<Class<*>>().apply { defaultReturnValue(-1) }

    fun getOutboundPacketId(clazz: Class<*>): Int = toId.getInt(clazz)

    fun getInboundPacket(state: PacketState, id: Int, buffer: ByteBuffer): InboundPacket<*>? {
        val constructor = byEncoded.get(encodeInboundLookupKey(state, id)) ?: return null
        return constructor.create(BinaryReader(buffer))
    }

    @Suppress("LongMethod", "MagicNumber")
    fun bootstrap() {
        // Handshake
        registerInbound(PacketState.HANDSHAKE, 0x00) { PacketInHandshake(it) }

        // Status
        registerInbound(PacketState.STATUS, 0x00) { PacketInStatusRequest }
        registerInbound(PacketState.STATUS, 0x01) { PacketInPingRequest(it) }

        registerOutbound<PacketOutStatusResponse>(0x00)
        registerOutbound<PacketOutPingResponse>(0x01)

        // Login
        registerInbound(PacketState.LOGIN, 0x00) { PacketInLoginStart(it) }
        registerInbound(PacketState.LOGIN, 0x01) { PacketInEncryptionResponse(it) }
        registerInbound(PacketState.LOGIN, 0x02) { PacketInPluginResponse(it) }

        registerOutbound<PacketOutLoginDisconnect>(0x00)
        registerOutbound<PacketOutEncryptionRequest>(0x01)
        registerOutbound<PacketOutLoginSuccess>(0x02)
        registerOutbound<PacketOutSetCompression>(0x03)
        registerOutbound<PacketOutPluginRequest>(0x04)

        // Play
        registerInbound(PacketState.PLAY, 0x00) { PacketInConfirmTeleportation(it) }
        registerInbound(PacketState.PLAY, 0x04) { PacketInChatCommand(it) }
        registerInbound(PacketState.PLAY, 0x05) { PacketInChat(it) }
        registerInbound(PacketState.PLAY, 0x06) { PacketInClientCommand(it) }
        registerInbound(PacketState.PLAY, 0x07) { PacketInClientInformation(it) }
        registerInbound(PacketState.PLAY, 0x08) { PacketInCommandSuggestionsRequest(it) }
        registerInbound(PacketState.PLAY, 0x0C) { PacketInPluginMessage(it) }
        registerInbound(PacketState.PLAY, 0x0E) { PacketInQueryEntityTag(it) }
        registerInbound(PacketState.PLAY, 0x0F) { PacketInInteract(it) }
        registerInbound(PacketState.PLAY, 0x11) { PacketInKeepAlive(it) }
        registerInbound(PacketState.PLAY, 0x13) { PacketInSetPlayerPosition(it) }
        registerInbound(PacketState.PLAY, 0x14) { PacketInSetPlayerPositionAndRotation(it) }
        registerInbound(PacketState.PLAY, 0x15) { PacketInSetPlayerRotation(it) }
        registerInbound(PacketState.PLAY, 0x16) { PacketInSetPlayerOnGround(it) }
        registerInbound(PacketState.PLAY, 0x1B) { PacketInAbilities(it) }
        registerInbound(PacketState.PLAY, 0x1C) { PacketInPlayerAction(it) }
        registerInbound(PacketState.PLAY, 0x1D) { PacketInPlayerCommand(it) }
        registerInbound(PacketState.PLAY, 0x1E) { PacketInPlayerInput(it) }
        registerInbound(PacketState.PLAY, 0x20) { PacketInChatSessionUpdate(it) }
        registerInbound(PacketState.PLAY, 0x24) { PacketInResourcePack(it) }
        registerInbound(PacketState.PLAY, 0x28) { PacketInSetHeldItem(it) }
        registerInbound(PacketState.PLAY, 0x2B) { PacketInSetCreativeModeSlot(it) }
        registerInbound(PacketState.PLAY, 0x2F) { PacketInSwingArm(it) }
        registerInbound(PacketState.PLAY, 0x31) { PacketInUseItemOn(it) }
        registerInbound(PacketState.PLAY, 0x32) { PacketInUseItem(it) }

        registerOutbound<PacketOutSpawnEntity>(0x00)
        registerOutbound<PacketOutSpawnExperienceOrb>(0x01)
        registerOutbound<PacketOutSpawnPlayer>(0x02)
        registerOutbound<PacketOutAnimation>(0x03)
        registerOutbound<PacketOutAwardStatistics>(0x04)
        registerOutbound<PacketOutAcknowledgeBlockChange>(0x05)
        registerOutbound<PacketOutSetBlockDestroyStage>(0x06)
        registerOutbound<PacketOutBlockUpdate>(0x09)
        registerOutbound<PacketOutBossBar>(0x0A)
        registerOutbound<PacketOutChangeDifficulty>(0x0B)
        registerOutbound<PacketOutClearTitles>(0x0C)
        registerOutbound<PacketOutCommandSuggestionsResponse>(0x0D)
        registerOutbound<PacketOutCommands>(0x0E)
        registerOutbound<PacketOutSetContainerContent>(0x10)
        registerOutbound<PacketOutSetContainerSlot>(0x12)
        registerOutbound<PacketOutSetCooldown>(0x13)
        registerOutbound<PacketOutChatSuggestions>(0x14)
        registerOutbound<PacketOutPluginMessage>(0x15)
        registerOutbound<PacketOutDeleteChat>(0x16)
        registerOutbound<PacketOutDisconnect>(0x17)
        registerOutbound<PacketOutDisguisedChat>(0x18)
        registerOutbound<PacketOutEntityEvent>(0x19)
        registerOutbound<PacketOutUnloadChunk>(0x1B)
        registerOutbound<PacketOutGameEvent>(0x1C)
        registerOutbound<PacketOutInitializeWorldBorder>(0x1E)
        registerOutbound<PacketOutKeepAlive>(0x1F)
        registerOutbound<PacketOutChunkDataAndLight>(0x20)
        registerOutbound<PacketOutWorldEvent>(0x21)
        registerOutbound<PacketOutParticle>(0x22)
        registerOutbound<PacketOutUpdateLight>(0x23)
        registerOutbound<PacketOutLogin>(0x24)
        registerOutbound<PacketOutUpdateEntityPosition>(0x27)
        registerOutbound<PacketOutUpdateEntityPositionAndRotation>(0x28)
        registerOutbound<PacketOutUpdateEntityRotation>(0x29)
        registerOutbound<PacketOutOpenBook>(0x2B)
        registerOutbound<PacketOutAbilities>(0x30)
        registerOutbound<PacketOutPlayerChat>(0x31)
        registerOutbound<PacketOutPlayerInfoRemove>(0x35)
        registerOutbound<PacketOutPlayerInfoUpdate>(0x36)
        registerOutbound<PacketOutSynchronizePlayerPosition>(0x38)
        registerOutbound<PacketOutUpdateRecipeBook>(0x39)
        registerOutbound<PacketOutRemoveEntities>(0x3A)
        registerOutbound<PacketOutResourcePack>(0x3C)
        registerOutbound<PacketOutSetHeadRotation>(0x3E)
        registerOutbound<PacketOutSetActionBarText>(0x42)
        registerOutbound<PacketOutSetCamera>(0x48)
        registerOutbound<PacketOutSetHeldItem>(0x49)
        registerOutbound<PacketOutSetCenterChunk>(0x4A)
        registerOutbound<PacketOutSetDefaultSpawnPosition>(0x4C)
        registerOutbound<PacketOutDisplayObjective>(0x4D)
        registerOutbound<PacketOutSetEntityMetadata>(0x4E)
        registerOutbound<PacketOutSetEntityVelocity>(0x50)
        registerOutbound<PacketOutSetHealth>(0x53)
        registerOutbound<PacketOutUpdateObjectives>(0x54)
        registerOutbound<PacketOutSetPassengers>(0x55)
        registerOutbound<PacketOutUpdateTeams>(0x56)
        registerOutbound<PacketOutUpdateScore>(0x57)
        registerOutbound<PacketOutSetSubtitleText>(0x59)
        registerOutbound<PacketOutUpdateTime>(0x5A)
        registerOutbound<PacketOutSetTitleText>(0x5B)
        registerOutbound<PacketOutSetTitleAnimationTimes>(0x5C)
        registerOutbound<PacketOutEntitySoundEffect>(0x5D)
        registerOutbound<PacketOutSoundEffect>(0x5E)
        registerOutbound<PacketOutStopSound>(0x5F)
        registerOutbound<PacketOutSystemChat>(0x60)
        registerOutbound<PacketOutSetTabListHeaderAndFooter>(0x61)
        registerOutbound<PacketOutTagQueryResponse>(0x62)
        registerOutbound<PacketOutTeleportEntity>(0x64)
        registerOutbound<PacketOutUpdateAttributes>(0x66)
        registerOutbound<PacketOutUpdateEnabledFeatures>(0x67)
        registerOutbound<PacketOutUpdateRecipes>(0x69)
        registerOutbound<PacketOutUpdateTags>(0x6A)
    }

    @JvmStatic
    private fun registerInbound(state: PacketState, id: Int, creator: PacketConstructor) {
        byEncoded.put(encodeInboundLookupKey(state, id), creator)
    }

    @JvmStatic
    private inline fun <reified T> registerOutbound(id: Int) {
        toId.put(T::class.java, id)
    }

    // This encoding places the state in the upper 16 bits, and the ID in the lower 16 bits.
    @JvmStatic
    @Suppress("MagicNumber") // Explained above
    private fun encodeInboundLookupKey(state: PacketState, id: Int): Int = state.ordinal shl 16 or (id and 0xFFFF)

    private fun interface PacketConstructor {

        /**
         * This returns InboundPacket rather than just Packet to try and ensure that all our inbound packets implement this interface.
         */
        fun create(reader: BinaryReader): InboundPacket<*>
    }
}
