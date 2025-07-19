package net.aquamine.server.entity.player

import net.kyori.adventure.identity.Identity
import net.kyori.adventure.key.Key
import net.kyori.adventure.permission.PermissionChecker
import net.kyori.adventure.pointer.Pointers
import net.kyori.adventure.text.Component
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.effect.particle.ParticleEffect
import net.aquamine.api.effect.particle.data.ColorParticleData
import net.aquamine.api.effect.particle.data.DirectionalParticleData
import net.aquamine.api.effect.particle.data.NoteParticleData
import net.aquamine.api.entity.EquipmentSlot
import net.aquamine.api.entity.Hand
import net.aquamine.api.entity.player.ChatVisibility
import net.aquamine.api.entity.player.TabList
import net.aquamine.api.event.player.PlayerChangeGameModeEvent
import net.aquamine.api.inventory.Inventory
import net.aquamine.api.permission.PermissionFunction
import net.aquamine.api.resource.ResourcePack
import net.aquamine.api.scoreboard.Scoreboard
import net.aquamine.api.statistic.CustomStatistics
import net.aquamine.api.tags.FluidTags
import net.aquamine.api.util.Position
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.GameMode
import net.aquamine.server.commands.AquaPermission
import net.aquamine.server.entity.util.EquipmentSlots
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.components.BasePlayer
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.player.PlayerSerializer
import net.aquamine.server.entity.system.PlayerChunkViewingSystem
import net.aquamine.server.entity.system.PlayerGameModeSystem
import net.aquamine.server.entity.system.PlayerHungerSystem
import net.aquamine.server.inventory.AquaPlayerInventory
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.item.handler
import net.aquamine.server.network.NetworkConnection
import net.aquamine.server.packet.out.play.GameEventTypes
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.network.chat.OutgoingChatMessage
import net.aquamine.server.network.chat.RemoteChatSession
import net.aquamine.server.packet.Packet
import net.aquamine.server.packet.out.play.PacketOutDisconnect
import net.aquamine.server.packet.out.play.PacketOutGameEvent
import net.aquamine.server.packet.out.play.PacketOutOpenBook
import net.aquamine.server.packet.out.play.PacketOutParticle
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoRemove
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate
import net.aquamine.server.packet.out.play.PacketOutPluginMessage
import net.aquamine.server.packet.out.play.PacketOutResourcePack
import net.aquamine.server.packet.out.play.PacketOutSetCamera
import net.aquamine.server.packet.out.play.PacketOutSetContainerSlot
import net.aquamine.server.packet.out.play.PacketOutSetHealth
import net.aquamine.server.packet.out.play.PacketOutSpawnPlayer
import net.aquamine.server.packet.out.play.PacketOutSystemChat
import net.aquamine.server.statistic.AquaStatisticsTracker
import net.aquamine.server.util.ImmutableLists
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.scoreboard.AquaScoreboard
import xyz.axie.nbt.CompoundTag
import java.net.SocketAddress
import java.time.Instant
import java.util.UUID
import kotlin.math.roundToInt
import kotlin.math.sqrt

class AquaPlayer(
    override val connection: NetworkConnection,
    override val profile: GameProfile,
    world: AquaWorld
) : AquaLivingEntity(world), BasePlayer {

    override val type: AquaEntityType<AquaPlayer>
        get() = AquaEntityTypes.PLAYER
    override val serializer: EntitySerializer<AquaPlayer>
        get() = PlayerSerializer
    override var permissionFunction: PermissionFunction = DEFAULT_PERMISSION_FUNCTION

    override val name: String
        get() = profile.name
    override var uuid: UUID
        get() = profile.uuid
        set(_) = Unit // Player UUIDs are read only.
    override val address: SocketAddress
        get() = connection.connectAddress()
    override val ping: Int
        get() = connection.latency()

    override val hungerSystem: PlayerHungerSystem = PlayerHungerSystem(this)
    override val gameModeSystem: PlayerGameModeSystem = PlayerGameModeSystem(this)
    private val chunkViewingSystem = PlayerChunkViewingSystem(this)

    override val abilities: Abilities = Abilities()
    override val inventory: AquaPlayerInventory = AquaPlayerInventory(this)
    override var openInventory: Inventory? = null

    override val statisticsTracker: AquaStatisticsTracker = AquaStatisticsTracker(this)
    override val itemCooldownTracker: AquaCooldownTracker = AquaCooldownTracker(this)

    override var scoreboard: AquaScoreboard = world.scoreboard
    override val tabList: TabList = AquaTabList(this)

    override var settings: AquaPlayerSettings = AquaPlayerSettings.DEFAULT
    private var lastActionTime = System.currentTimeMillis()

    val chatTracker: PlayerChatTracker = PlayerChatTracker(this)
    private var chatSession: RemoteChatSession? = null

    private var camera: AquaEntity = this
        set(value) {
            val old = field
            field = value
            if (old != field) {
                connection.send(PacketOutSetCamera(field.id))
                teleport(field.position)
            }
        }

    override var hasJoinedBefore: Boolean = false
    override var firstJoined: Instant = Instant.EPOCH
    override var lastJoined: Instant = Instant.now()

    internal var respawnData: RespawnData? = null

    override var health: Float
        get() = super.health
        set(value) {
            super.health = value
            connection.send(PacketOutSetHealth(health, foodLevel, foodSaturationLevel))
        }
    override var absorption: Float
        get() = data.get(MetadataKeys.Player.ADDITIONAL_HEARTS)
        set(value) = data.set(MetadataKeys.Player.ADDITIONAL_HEARTS, value)

    override fun defineData() {
        super<AquaLivingEntity>.defineData()
        data.define(MetadataKeys.Player.ADDITIONAL_HEARTS, 0F)
        data.define(MetadataKeys.Player.SCORE, 0)
        data.define(MetadataKeys.Player.SKIN_FLAGS, 0)
        data.define(MetadataKeys.Player.MAIN_HAND, 1)
        data.define(MetadataKeys.Player.LEFT_SHOULDER, CompoundTag.EMPTY)
        data.define(MetadataKeys.Player.RIGHT_SHOULDER, CompoundTag.EMPTY)
    }

    override fun updateGameMode(mode: GameMode): PlayerChangeGameModeEvent? {
        val event = gameModeSystem.changeGameMode(mode)
        if (event == null || !event.isAllowed()) return null

        connection.send(PacketOutGameEvent(GameEventTypes.CHANGE_GAMEMODE, mode.ordinal.toFloat()))
        if (mode == GameMode.SPECTATOR) {
            stopRiding()
        } else {
            camera = this
        }
        return event
    }

    override fun getEquipment(slot: EquipmentSlot): AquaItemStack = when {
        slot == EquipmentSlot.MAIN_HAND -> inventory.mainHand
        slot == EquipmentSlot.OFF_HAND -> inventory.offHand
        slot.type == EquipmentSlot.Type.ARMOR -> inventory.armor[EquipmentSlots.index(slot)]
        else -> AquaItemStack.EMPTY
    }

    override fun setEquipment(slot: EquipmentSlot, item: AquaItemStack) {
        when {
            slot == EquipmentSlot.MAIN_HAND -> inventory.setHeldItem(Hand.MAIN, item)
            slot == EquipmentSlot.OFF_HAND -> inventory.setHeldItem(Hand.OFF, item)
            slot.type == EquipmentSlot.Type.ARMOR -> inventory.armor[EquipmentSlots.index(slot)] = item
        }
    }

    override fun tick() {
        super.tick()
        gameModeSystem.tick()
        hungerSystem.tick()
        itemCooldownTracker.tick()
    }

    @Suppress("UnusedPrivateMember") // We will use the position later.
    fun isBlockActionRestricted(position: Vec3i): Boolean {
        if (gameMode != GameMode.ADVENTURE && gameMode != GameMode.SPECTATOR) return false
        if (gameMode == GameMode.SPECTATOR) return true
        if (canBuild) return false
        val mainHand = inventory.mainHand
        return mainHand.isEmpty() // TODO: Check Adventure CanDestroy
    }

    fun hasCorrectTool(state: AquaBlockState): Boolean = !state.requiresCorrectTool ||
            inventory.mainHand.type.handler().isCorrectTool(state)

    fun getDestroySpeed(state: AquaBlockState): Float {
        var speed = inventory.getDestroySpeed(state)
        // TODO: Add enchantment and effect checking here
        // TODO: Check aqua affinity when implemented
        if (waterPhysicsSystem.isUnderFluid(FluidTags.WATER)) speed /= WATER_FLYING_DESTROY_SPEED_FACTOR
        if (!isOnGround) speed /= WATER_FLYING_DESTROY_SPEED_FACTOR
        return speed
    }

    @Suppress("UnusedPrivateMember")
    fun interactOn(entity: AquaEntity, hand: Hand): InteractionResult {
        if (gameMode == GameMode.SPECTATOR) {
            // TODO: Open spectator menu
            return InteractionResult.PASS
        }
        // FIXME
        /*
        val heldItem = getHeldItem(hand)
        val result = entity.interact(this, hand)
        if (result.consumesAction()) {
            val currentHeldItem = getHeldItem(hand)
            if (abilities.canInstantlyBuild && heldItem !== currentHeldItem && currentHeldItem.amount < heldItem.amount) {
                setHeldItem(hand, heldItem.withAmount(currentHeldItem.amount))
            }
            return result
        }
        if (heldItem.isEmpty() || entity !is KryptonLivingEntity) return InteractionResult.PASS
        val interactResult = heldItem.type.handler().interactEntity(heldItem, this, entity, hand)
        if (interactResult.consumesAction()) {
            if (heldItem.isEmpty() && !canInstantlyBuild) setHeldItem(hand, KryptonItemStack.EMPTY)
            return interactResult
        }
        */
        return InteractionResult.PASS
    }

    override fun spawnParticles(effect: ParticleEffect, location: Vec3d) {
        val packet = PacketOutParticle.fromEffect(effect, location)
        when (effect.data) {
            // Send multiple packets based on the quantity
            is DirectionalParticleData, is ColorParticleData, is NoteParticleData -> repeat(effect.quantity) { connection.send(packet) }
            // Send particles to player at location
            else -> connection.send(packet)
        }
    }

    override fun showScoreboard(scoreboard: Scoreboard) {
        if (scoreboard !is AquaScoreboard) return
        this.scoreboard.removeViewer(this, true) // The player is no longer viewing the old scoreboard
        this.scoreboard = scoreboard
        scoreboard.addViewer(this) // The player is now viewing the new scoreboard
    }

    override fun resetScoreboard() {
        showScoreboard(world.scoreboard)
    }

    override fun sendPositionUpdate(packet: Packet, old: Position, new: Position) {
        super.sendPositionUpdate(packet, old, new)
        updateMovementStatistics(new.x - old.x, new.y - old.y, new.z - old.z)
        hungerSystem.updateMovementExhaustion(new.x - old.x, new.y - old.y, new.z - old.z)
    }

    fun sendInitialChunks() {
        chunkViewingSystem.loadInitialChunks()
    }

    fun updateChunks() {
        chunkViewingSystem.updateChunks()
    }

    override fun sendPluginMessage(channel: Key, message: ByteArray) {
        connection.send(PacketOutPluginMessage(channel, message))
    }

    override fun sendResourcePack(pack: ResourcePack) {
        connection.send(PacketOutResourcePack(pack.uri.toString(), pack.hash, pack.isForced, pack.promptMessage))
    }

    override fun openBook(item: AquaItemStack) {
        val slot = inventory.items.size + inventory.heldSlot
        val stateId = inventory.stateId()
        connection.send(PacketOutSetContainerSlot(0, stateId, slot.toShort(), item))
        connection.send(PacketOutOpenBook(hand))
        connection.send(PacketOutSetContainerSlot(0, stateId, slot.toShort(), inventory.mainHand))
    }

    override fun pointers(): Pointers {
        if (cachedPointers == null) {
            cachedPointers = Pointers.builder()
                .withDynamic(Identity.DISPLAY_NAME) { displayName }
                .withDynamic(Identity.NAME) { profile.name }
                .withDynamic(Identity.UUID) { profile.uuid }
                .withStatic(PermissionChecker.POINTER, PermissionChecker { getPermissionValue(it) })
                .withDynamic(Identity.LOCALE) { settings.locale }
                .build()
        }
        return cachedPointers!!
    }

    override fun disconnect(text: Component) {
        connection.send(PacketOutDisconnect(text))
        connection.disconnect(text)
    }

    fun onDisconnect() {
        chatTracker.onDisconnect()
        vehicleSystem.ejectPassengers()
        // TODO: Stop sleeping if sleeping
    }

    fun updateMovementStatistics(deltaX: Double, deltaY: Double, deltaZ: Double) {
        // TODO: Walking underwater, walking on water, climbing
        if (isSwimming) {
            val value = (sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) * 100F).roundToInt()
            if (value > 0) statisticsTracker.incrementStatistic(CustomStatistics.SWIM_ONE_CM.get(), value)
            return
        }
        if (isOnGround) {
            val value = (sqrt(deltaX * deltaX + deltaZ * deltaZ) * 100F).roundToInt()
            if (value > 0) {
                when {
                    isSprinting -> statisticsTracker.incrementStatistic(CustomStatistics.SPRINT_ONE_CM.get(), value)
                    isSneaking -> statisticsTracker.incrementStatistic(CustomStatistics.CROUCH_ONE_CM.get(), value)
                    else -> statisticsTracker.incrementStatistic(CustomStatistics.WALK_ONE_CM.get(), value)
                }
            }
            return
        }
        if (isGliding) {
            val value = (sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) * 100F).roundToInt()
            statisticsTracker.incrementStatistic(CustomStatistics.AVIATE_ONE_CM.get(), value)
            return
        }
        val value = (sqrt(deltaX * deltaX + deltaZ * deltaZ) * 100F).roundToInt()
        if (value > FLYING_ACHIEVEMENT_MINIMUM_SPEED) statisticsTracker.incrementStatistic(CustomStatistics.FLY_ONE_CM.get(), value)
    }

    fun resetLastActionTime() {
        lastActionTime = System.currentTimeMillis()
    }

    override fun sendSystemMessage(message: Component) {
        sendSystemMessage(message, false)
    }

    override fun isPushedByFluid(): Boolean = !isFlying

    override fun canBeSeenByAnyone(): Boolean = gameModeSystem.gameMode() != GameMode.SPECTATOR && super.canBeSeenByAnyone()

    fun canUseGameMasterBlocks(): Boolean = abilities.canInstantlyBuild && hasPermission(AquaPermission.USE_GAME_MASTER_BLOCKS.node)

    fun sendSystemMessage(message: Component, overlay: Boolean) {
        if (!acceptsSystemMessages(overlay)) return
        connection.send(PacketOutSystemChat(message, overlay))
    }

    fun sendChatMessage(message: OutgoingChatMessage, filter: Boolean, type: RichChatType.Bound) {
        if (acceptsChatMessages()) message.sendToPlayer(this, filter, type)
    }

    fun shouldFilterMessageTo(target: AquaPlayer): Boolean {
        if (target === this) return false
        return settings.filterText || target.settings.filterText
    }

    fun acceptsSystemMessages(overlay: Boolean): Boolean = if (settings.chatVisibility == ChatVisibility.HIDDEN) overlay else true

    fun acceptsChatMessages(): Boolean = settings.chatVisibility == ChatVisibility.FULL

    fun chatSession(): RemoteChatSession? = chatSession

    fun setChatSession(session: RemoteChatSession) {
        chatSession = session
    }

    override fun isOnline(): Boolean = server.getPlayer(uuid) === this

    override fun showToViewer(viewer: AquaPlayer) {
        viewer.connection.send(PacketOutPlayerInfoUpdate.createPlayerInitializing(ImmutableLists.of(this)))
        super.showToViewer(viewer)
    }

    override fun hideFromViewer(viewer: AquaPlayer) {
        super.hideFromViewer(viewer)
        viewer.connection.send(PacketOutPlayerInfoRemove(this))
    }

    override fun sendPacketToViewersAndSelf(packet: Packet) {
        connection.send(packet)
        super.sendPacketToViewersAndSelf(packet)
    }

    override fun getSpawnPacket(): Packet = PacketOutSpawnPlayer.create(this)

    companion object {

        private const val FLYING_ACHIEVEMENT_MINIMUM_SPEED = 25
        private const val WATER_FLYING_DESTROY_SPEED_FACTOR = 5F

        @JvmField
        val DEFAULT_PERMISSION_FUNCTION: PermissionFunction = PermissionFunction.ALWAYS_NOT_SET

        private const val DEFAULT_ATTACK_DAMAGE = 1.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.1

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaLivingEntity.attributes()
            .add(AquaAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(AquaAttributeTypes.ATTACK_SPEED)
            .add(AquaAttributeTypes.LUCK)
    }
}
