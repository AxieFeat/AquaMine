package net.aquamine.server.packet

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.packet.`in`.handshake.PacketInHandshake
import net.aquamine.server.packet.`in`.login.PacketInEncryptionResponse
import net.aquamine.server.packet.`in`.login.PacketInLoginStart
import net.aquamine.server.packet.`in`.login.PacketInPluginResponse
import net.aquamine.server.packet.`in`.play.*
import net.aquamine.server.packet.`in`.status.PacketInPingRequest
import net.aquamine.server.packet.`in`.status.PacketInStatusRequest
import net.aquamine.server.packet.out.login.*
import net.aquamine.server.packet.out.play.*
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
        registerInbound(PacketState.PLAY, 0x01) { PacketInQueryBlockTag(it) }
        // 0x02 difficulty packet
        registerInbound(PacketState.PLAY, 0x03) { PacketInChatAck(it) }
        registerInbound(PacketState.PLAY, 0x04) { PacketInChatCommand(it) }
        registerInbound(PacketState.PLAY, 0x05) { PacketInChat(it) }
        registerInbound(PacketState.PLAY, 0x06) { PacketInClientCommand(it) }
        registerInbound(PacketState.PLAY, 0x07) { PacketInClientInformation(it) }
        registerInbound(PacketState.PLAY, 0x08) { PacketInCommandSuggestionsRequest(it) }
        registerInbound(PacketState.PLAY, 0x09) { PacketInClickContainerButton(it) }
        registerInbound(PacketState.PLAY, 0x0A) { PacketInClickContainer(it) }
        registerInbound(PacketState.PLAY, 0x0B) { PacketInCloseContainer(it) }
        registerInbound(PacketState.PLAY, 0x0C) { PacketInPluginMessage(it) }
        registerInbound(PacketState.PLAY, 0x0D) { PacketInEditBook(it) }
        registerInbound(PacketState.PLAY, 0x0E) { PacketInQueryEntityTag(it) }
        registerInbound(PacketState.PLAY, 0x0F) { PacketInInteract(it) }
        registerInbound(PacketState.PLAY, 0x10) { PacketInGenerateStructure(it) }
        registerInbound(PacketState.PLAY, 0x11) { PacketInKeepAlive(it) }
        // 0x12 not used by server
        registerInbound(PacketState.PLAY, 0x13) { PacketInSetPlayerPosition(it) }
        registerInbound(PacketState.PLAY, 0x14) { PacketInSetPlayerPositionAndRotation(it) }
        registerInbound(PacketState.PLAY, 0x15) { PacketInSetPlayerRotation(it) }
        registerInbound(PacketState.PLAY, 0x16) { PacketInSetPlayerOnGround(it) }
        registerInbound(PacketState.PLAY, 0x17) { PacketInVehicleMove(it) }
        registerInbound(PacketState.PLAY, 0x18) { PacketInSteerBoat(it) }
        registerInbound(PacketState.PLAY, 0x19) { PacketInPickItem(it) }
        registerInbound(PacketState.PLAY, 0x1A) { PacketInCraftRecipeRequest(it) }
        registerInbound(PacketState.PLAY, 0x1B) { PacketInAbilities(it) }
        registerInbound(PacketState.PLAY, 0x1C) { PacketInPlayerAction(it) }
        registerInbound(PacketState.PLAY, 0x1D) { PacketInPlayerCommand(it) }
        registerInbound(PacketState.PLAY, 0x1E) { PacketInPlayerInput(it) }
        registerInbound(PacketState.PLAY, 0x1F) { PacketInPingResponse(it) }
        registerInbound(PacketState.PLAY, 0x20) { PacketInChatSessionUpdate(it) }
        registerInbound(PacketState.PLAY, 0x21) { PacketInSetRecipeBookState(it) }
        registerInbound(PacketState.PLAY, 0x22) { PacketInSetDisplayedRecipe(it) }
        registerInbound(PacketState.PLAY, 0x23) { PacketInNameItem(it) }
        registerInbound(PacketState.PLAY, 0x24) { PacketInResourcePack(it) }
        registerInbound(PacketState.PLAY, 0x25) { PacketInAdvancementTab(it) }
        registerInbound(PacketState.PLAY, 0x26) { PacketInSelectTrade(it) }
        registerInbound(PacketState.PLAY, 0x27) { PacketInSetBeaconEffect(it) }
        registerInbound(PacketState.PLAY, 0x28) { PacketInSetHeldItem(it) }
        registerInbound(PacketState.PLAY, 0x29) { PacketInUpdateCommandBlock(it) }
        registerInbound(PacketState.PLAY, 0x2A) { PacketInUpdateCommandBlockMinecart(it) }
        registerInbound(PacketState.PLAY, 0x2B) { PacketInSetCreativeModeSlot(it) }
        registerInbound(PacketState.PLAY, 0x2D) { PacketInUpdateStructureBlock(it) }
        registerInbound(PacketState.PLAY, 0x2E) { PacketInUpdateSign(it) }
        registerInbound(PacketState.PLAY, 0x2F) { PacketInSwingArm(it) }
        registerInbound(PacketState.PLAY, 0x30) { PacketInSpectate(it) }
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
