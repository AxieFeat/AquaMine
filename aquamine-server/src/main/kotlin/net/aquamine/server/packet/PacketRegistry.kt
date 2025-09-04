package net.aquamine.server.packet

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.packet.`in`.handshake.PacketInHandshake
import net.aquamine.server.packet.`in`.login.PacketInEncryptionResponse
import net.aquamine.server.packet.`in`.login.PacketInLoginStart
import net.aquamine.server.packet.`in`.login.PacketInPluginResponse
import net.aquamine.server.packet.`in`.play.PacketInAbilities
import net.aquamine.server.packet.`in`.play.PacketInAdvancementTab
import net.aquamine.server.packet.`in`.play.PacketInChat
import net.aquamine.server.packet.`in`.play.PacketInChatAck
import net.aquamine.server.packet.`in`.play.PacketInChatCommand
import net.aquamine.server.packet.`in`.play.PacketInChatSessionUpdate
import net.aquamine.server.packet.`in`.play.PacketInClickContainer
import net.aquamine.server.packet.`in`.play.PacketInClickContainerButton
import net.aquamine.server.packet.`in`.play.PacketInClientCommand
import net.aquamine.server.packet.`in`.play.PacketInClientInformation
import net.aquamine.server.packet.`in`.play.PacketInCloseContainer
import net.aquamine.server.packet.`in`.play.PacketInCommandSuggestionsRequest
import net.aquamine.server.packet.`in`.play.PacketInConfirmTeleportation
import net.aquamine.server.packet.`in`.play.PacketInCraftRecipeRequest
import net.aquamine.server.packet.`in`.play.PacketInEditBook
import net.aquamine.server.packet.`in`.play.PacketInGenerateStructure
import net.aquamine.server.packet.`in`.play.PacketInInteract
import net.aquamine.server.packet.`in`.play.PacketInKeepAlive
import net.aquamine.server.packet.`in`.play.PacketInNameItem
import net.aquamine.server.packet.`in`.play.PacketInPickItem
import net.aquamine.server.packet.`in`.play.PacketInPingResponse
import net.aquamine.server.packet.`in`.play.PacketInPlayerAction
import net.aquamine.server.packet.`in`.play.PacketInPlayerCommand
import net.aquamine.server.packet.`in`.play.PacketInPlayerInput
import net.aquamine.server.packet.`in`.play.PacketInPluginMessage
import net.aquamine.server.packet.`in`.play.PacketInQueryBlockTag
import net.aquamine.server.packet.`in`.play.PacketInQueryEntityTag
import net.aquamine.server.packet.`in`.play.PacketInResourcePack
import net.aquamine.server.packet.`in`.play.PacketInSelectTrade
import net.aquamine.server.packet.`in`.play.PacketInSetBeaconEffect
import net.aquamine.server.packet.`in`.play.PacketInSetCreativeModeSlot
import net.aquamine.server.packet.`in`.play.PacketInSetDisplayedRecipe
import net.aquamine.server.packet.`in`.play.PacketInSetHeldItem
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerOnGround
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerPosition
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerPositionAndRotation
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerRotation
import net.aquamine.server.packet.`in`.play.PacketInSetRecipeBookState
import net.aquamine.server.packet.`in`.play.PacketInSpectate
import net.aquamine.server.packet.`in`.play.PacketInSteerBoat
import net.aquamine.server.packet.`in`.play.PacketInSwingArm
import net.aquamine.server.packet.`in`.play.PacketInUpdateCommandBlock
import net.aquamine.server.packet.`in`.play.PacketInUpdateCommandBlockMinecart
import net.aquamine.server.packet.`in`.play.PacketInUpdateSign
import net.aquamine.server.packet.`in`.play.PacketInUpdateStructureBlock
import net.aquamine.server.packet.`in`.play.PacketInUseItem
import net.aquamine.server.packet.`in`.play.PacketInUseItemOn
import net.aquamine.server.packet.`in`.play.PacketInVehicleMove
import net.aquamine.server.packet.`in`.status.PacketInPingRequest
import net.aquamine.server.packet.`in`.status.PacketInStatusRequest
import net.aquamine.server.packet.out.login.PacketOutEncryptionRequest
import net.aquamine.server.packet.out.login.PacketOutLoginDisconnect
import net.aquamine.server.packet.out.login.PacketOutLoginSuccess
import net.aquamine.server.packet.out.login.PacketOutPluginRequest
import net.aquamine.server.packet.out.login.PacketOutSetCompression
import net.aquamine.server.packet.out.play.PacketOutAbilities
import net.aquamine.server.packet.out.play.PacketOutAcknowledgeBlockChange
import net.aquamine.server.packet.out.play.PacketOutAnimation
import net.aquamine.server.packet.out.play.PacketOutAwardStatistics
import net.aquamine.server.packet.out.play.PacketOutBlockUpdate
import net.aquamine.server.packet.out.play.PacketOutBossBar
import net.aquamine.server.packet.out.play.PacketOutChangeDifficulty
import net.aquamine.server.packet.out.play.PacketOutChatSuggestions
import net.aquamine.server.packet.out.play.PacketOutChunkDataAndLight
import net.aquamine.server.packet.out.play.PacketOutClearTitles
import net.aquamine.server.packet.out.play.PacketOutCommandSuggestionsResponse
import net.aquamine.server.packet.out.play.PacketOutCommands
import net.aquamine.server.packet.out.play.PacketOutDeleteChat
import net.aquamine.server.packet.out.play.PacketOutDisconnect
import net.aquamine.server.packet.out.play.PacketOutDisguisedChat
import net.aquamine.server.packet.out.play.PacketOutDisplayObjective
import net.aquamine.server.packet.out.play.PacketOutEntityEvent
import net.aquamine.server.packet.out.play.PacketOutEntityPotionEffect
import net.aquamine.server.packet.out.play.PacketOutEntityRemovePotionEffect
import net.aquamine.server.packet.out.play.PacketOutEntitySoundEffect
import net.aquamine.server.packet.out.play.PacketOutGameEvent
import net.aquamine.server.packet.out.play.PacketOutInitializeWorldBorder
import net.aquamine.server.packet.out.play.PacketOutKeepAlive
import net.aquamine.server.packet.out.play.PacketOutLogin
import net.aquamine.server.packet.out.play.PacketOutOpenBook
import net.aquamine.server.packet.out.play.PacketOutParticle
import net.aquamine.server.packet.out.play.PacketOutPlayerChat
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoRemove
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate
import net.aquamine.server.packet.out.play.PacketOutPluginMessage
import net.aquamine.server.packet.out.play.PacketOutRemoveEntities
import net.aquamine.server.packet.out.play.PacketOutResourcePack
import net.aquamine.server.packet.out.play.PacketOutSetActionBarText
import net.aquamine.server.packet.out.play.PacketOutSetBlockDestroyStage
import net.aquamine.server.packet.out.play.PacketOutSetCamera
import net.aquamine.server.packet.out.play.PacketOutSetCenterChunk
import net.aquamine.server.packet.out.play.PacketOutSetContainerContent
import net.aquamine.server.packet.out.play.PacketOutSetContainerSlot
import net.aquamine.server.packet.out.play.PacketOutSetCooldown
import net.aquamine.server.packet.out.play.PacketOutSetDefaultSpawnPosition
import net.aquamine.server.packet.out.play.PacketOutSetEntityMetadata
import net.aquamine.server.packet.out.play.PacketOutSetEntityVelocity
import net.aquamine.server.packet.out.play.PacketOutSetHeadRotation
import net.aquamine.server.packet.out.play.PacketOutSetHealth
import net.aquamine.server.packet.out.play.PacketOutSetHeldItem
import net.aquamine.server.packet.out.play.PacketOutSetPassengers
import net.aquamine.server.packet.out.play.PacketOutSetSubtitleText
import net.aquamine.server.packet.out.play.PacketOutSetTabListHeaderAndFooter
import net.aquamine.server.packet.out.play.PacketOutSetTitleAnimationTimes
import net.aquamine.server.packet.out.play.PacketOutSetTitleText
import net.aquamine.server.packet.out.play.PacketOutSoundEffect
import net.aquamine.server.packet.out.play.PacketOutSpawnEntity
import net.aquamine.server.packet.out.play.PacketOutSpawnExperienceOrb
import net.aquamine.server.packet.out.play.PacketOutSpawnPlayer
import net.aquamine.server.packet.out.play.PacketOutStopSound
import net.aquamine.server.packet.out.play.PacketOutSynchronizePlayerPosition
import net.aquamine.server.packet.out.play.PacketOutSystemChat
import net.aquamine.server.packet.out.play.PacketOutTagQueryResponse
import net.aquamine.server.packet.out.play.PacketOutTeleportEntity
import net.aquamine.server.packet.out.play.PacketOutUnloadChunk
import net.aquamine.server.packet.out.play.PacketOutUpdateAttributes
import net.aquamine.server.packet.out.play.PacketOutUpdateEnabledFeatures
import net.aquamine.server.packet.out.play.PacketOutUpdateEntityPosition
import net.aquamine.server.packet.out.play.PacketOutUpdateEntityPositionAndRotation
import net.aquamine.server.packet.out.play.PacketOutUpdateEntityRotation
import net.aquamine.server.packet.out.play.PacketOutUpdateLight
import net.aquamine.server.packet.out.play.PacketOutUpdateObjectives
import net.aquamine.server.packet.out.play.PacketOutUpdateRecipeBook
import net.aquamine.server.packet.out.play.PacketOutUpdateRecipes
import net.aquamine.server.packet.out.play.PacketOutUpdateScore
import net.aquamine.server.packet.out.play.PacketOutUpdateTags
import net.aquamine.server.packet.out.play.PacketOutUpdateTeams
import net.aquamine.server.packet.out.play.PacketOutUpdateTime
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
        // 0x05 Cookie Request (login)

        // Configuration



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

        // 0x00 Bundle delimiter
        registerOutbound<PacketOutSpawnEntity>(0x01)
        registerOutbound<PacketOutAnimation>(0x02)
        registerOutbound<PacketOutAwardStatistics>(0x03)
        registerOutbound<PacketOutAcknowledgeBlockChange>(0x04)
        registerOutbound<PacketOutSetBlockDestroyStage>(0x05)
        // 0x06 Block entity data
        // 0x07 Block action
        registerOutbound<PacketOutBlockUpdate>(0x08)
        registerOutbound<PacketOutBossBar>(0x09)
        registerOutbound<PacketOutChangeDifficulty>(0x0A)
        // 0x0B Chunk batch finished
        // 0x0C Chunk batch start
        // 0x0D Chunk biomes
        registerOutbound<PacketOutClearTitles>(0x0E)
        registerOutbound<PacketOutCommandSuggestionsResponse>(0x0F)
        registerOutbound<PacketOutCommands>(0x10)
        // 0x11 Close container
        registerOutbound<PacketOutSetContainerContent>(0x12)
        // 0x13 Set container property
        registerOutbound<PacketOutSetContainerSlot>(0x14)
        // 0x15 Cookie request (play)
        registerOutbound<PacketOutSetCooldown>(0x16)
        registerOutbound<PacketOutChatSuggestions>(0x17)
        registerOutbound<PacketOutPluginMessage>(0x18)
        // 0x19 Damage event
        // 0x1A Debug Sample
        registerOutbound<PacketOutDeleteChat>(0x1B)
        registerOutbound<PacketOutDisconnect>(0x1C)
        registerOutbound<PacketOutDisguisedChat>(0x1D)
        registerOutbound<PacketOutEntityEvent>(0x1E)
        registerOutbound<PacketOutTeleportEntity>(0x1F)
        // 0x20 Explosion
        registerOutbound<PacketOutUnloadChunk>(0x21)
        registerOutbound<PacketOutGameEvent>(0x22)
        // 0x23 Open horse screen
        // 0x24 Hurt animation
        registerOutbound<PacketOutInitializeWorldBorder>(0x25)
        registerOutbound<PacketOutKeepAlive>(0x26)
        registerOutbound<PacketOutChunkDataAndLight>(0x27)
        registerOutbound<PacketOutWorldEvent>(0x28)
        registerOutbound<PacketOutParticle>(0x29)
        registerOutbound<PacketOutUpdateLight>(0x2A)
        registerOutbound<PacketOutLogin>(0x2B)
        // 0x2C Map data
        // 0x2D Merchant Offers
        registerOutbound<PacketOutUpdateEntityPosition>(0x2E)
        registerOutbound<PacketOutUpdateEntityPositionAndRotation>(0x2F)
        // 0x30 Move Minecart Along Track
        registerOutbound<PacketOutUpdateEntityRotation>(0x31)
        // 0x32 Move vehicle
        registerOutbound<PacketOutOpenBook>(0x33)
        // 0x34 Open screen
        // 0x35 Open sign editor
        // 0x36 Ping
        // 0x37 Ping response
        // 0x38 Place ghost Recipe
        registerOutbound<PacketOutAbilities>(0x38)
        registerOutbound<PacketOutPlayerChat>(0x3A)
        // 0x3B End combat
        // 0x3C Enter combat
        // 0x3D Combat death
        registerOutbound<PacketOutPlayerInfoRemove>(0x3E)
        registerOutbound<PacketOutPlayerInfoUpdate>(0x3F)
        // 0x40 Look at
        registerOutbound<PacketOutSynchronizePlayerPosition>(0x41)
        // 0x43 Recipe book add
        // 0x44 Recipe book remove
        // 0x45 Recipe book settings
        registerOutbound<PacketOutRemoveEntities>(0x46)
        registerOutbound<PacketOutEntityRemovePotionEffect>(0x47)
        // 0x48 Reset score
        // 0x49 Remove resource pack
        registerOutbound<PacketOutResourcePack>(0x4A)
        // 0x4B Respawn
        registerOutbound<PacketOutSetHeadRotation>(0x4C)
        // 0x4D Update section blocks
        // 0x4E Select advancement tab
        // 0x4F Server data
        registerOutbound<PacketOutSetActionBarText>(0x50)
        // 0x51 Set border center
        // 0x52 Set border lerp size
        // 0x53 Set border size
        // 0x54 Set border warning delay
        // 0x55 Set border warning distance
        registerOutbound<PacketOutSetCamera>(0x56)
        registerOutbound<PacketOutSetCenterChunk>(0x57)
        // 0x58 Set render distance
        // 0x59 Set cursor item
        registerOutbound<PacketOutSetDefaultSpawnPosition>(0x5A)
        registerOutbound<PacketOutDisplayObjective>(0x5B)
        registerOutbound<PacketOutSetEntityMetadata>(0x5C)
        // 0x5D Link entities
        registerOutbound<PacketOutSetEntityVelocity>(0x5E)
        // 0x5F Set equipment
        // 0x60 Set experience
        registerOutbound<PacketOutSetHealth>(0x61)
        registerOutbound<PacketOutSetHeldItem>(0x61)
        registerOutbound<PacketOutUpdateObjectives>(0x63)
        registerOutbound<PacketOutSetPassengers>(0x64)
        // 0x65 Set player inventory slot
        registerOutbound<PacketOutUpdateTeams>(0x66)
        registerOutbound<PacketOutUpdateScore>(0x67)
        // 0x68 Set simulation distance
        registerOutbound<PacketOutSetSubtitleText>(0x69)
        registerOutbound<PacketOutUpdateTime>(0x6A)
        registerOutbound<PacketOutSetTitleText>(0x6B)
        registerOutbound<PacketOutSetTitleAnimationTimes>(0x6C)
        registerOutbound<PacketOutEntitySoundEffect>(0x6D)
        registerOutbound<PacketOutSoundEffect>(0x6E)
        // 0x6F Start configuration
        registerOutbound<PacketOutStopSound>(0x70)
        // 0x71 Store cookie
        registerOutbound<PacketOutSystemChat>(0x72)
        registerOutbound<PacketOutSetTabListHeaderAndFooter>(0x73)
        registerOutbound<PacketOutTagQueryResponse>(0x74)
        // 0x75 Pickup item
        // 0x76 Synchronize vehicle position
        // 0x77 Test instance block status
        // 0x78 Set ticking state
        // 0x79 Step tick
        // 0x7A Transfer
        // 0x7B Update advancements
        registerOutbound<PacketOutUpdateAttributes>(0x7C)
        registerOutbound<PacketOutEntityPotionEffect>(0x7D)
        registerOutbound<PacketOutUpdateRecipes>(0x7E)
        registerOutbound<PacketOutUpdateTags>(0x7F)
        // 0x80 Projectile power
        // 0x81 Custom report details
        // 0x82 Server links
        // 0x83 Waypoint
        // 0x84 Clear dialog
        // 0x85 Show dialog
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
