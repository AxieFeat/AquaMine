package net.aquamine.server.entity

import net.kyori.adventure.identity.Identity
import net.kyori.adventure.permission.PermissionChecker
import net.kyori.adventure.pointer.Pointers
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.util.BoundingBox
import net.aquamine.api.util.Position
import net.aquamine.api.util.Vec3d
import net.aquamine.api.world.damage.type.DamageTypes
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.AquaSender
import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.coordinate.SectionPos
import net.aquamine.server.entity.components.BaseEntity
import net.aquamine.server.entity.components.SerializableEntity
import net.aquamine.server.entity.system.EntityVehicleSystem
import net.aquamine.server.entity.metadata.MetadataHolder
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.entity.serializer.BaseEntitySerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.system.EntityWaterPhysicsSystem
import net.aquamine.server.entity.tracking.EntityTracker
import net.aquamine.server.entity.tracking.EntityTypeTarget
import net.aquamine.server.entity.tracking.EntityViewCallback
import net.aquamine.server.entity.tracking.EntityViewingEngine
import net.aquamine.server.event.player.AquaEntityEnterViewEvent
import net.aquamine.server.event.player.AquaEntityExitViewEvent
import net.aquamine.server.network.PacketGrouping
import net.aquamine.server.packet.Packet
import net.aquamine.server.packet.out.play.PacketOutRemoveEntities
import net.aquamine.server.packet.out.play.PacketOutSetEntityMetadata
import net.aquamine.server.packet.out.play.PacketOutSetEntityVelocity
import net.aquamine.server.packet.out.play.PacketOutSetHeadRotation
import net.aquamine.server.packet.out.play.PacketOutSpawnEntity
import net.aquamine.server.packet.out.play.PacketOutTeleportEntity
import net.aquamine.server.packet.out.play.PacketOutUpdateEntityPosition
import net.aquamine.server.packet.out.play.PacketOutUpdateEntityPositionAndRotation
import net.aquamine.server.packet.out.play.PacketOutUpdateEntityRotation
import net.aquamine.server.util.math.Maths
import net.aquamine.server.scheduling.AquaScheduler
import net.aquamine.server.ticking.Tickable
import net.aquamine.server.util.random.RandomSource
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.damage.AquaDamageSource
import net.aquamine.server.world.rule.GameRuleKeys
import java.util.UUID
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.abs

abstract class AquaEntity(final override var world: AquaWorld) : BaseEntity, SerializableEntity, AquaSender, Tickable {

    override val serializer: EntitySerializer<out AquaEntity>
        get() = BaseEntitySerializer

    val random: RandomSource = RandomSource.create()
    final override val id: Int = NEXT_ENTITY_ID.incrementAndGet()
    override var uuid: UUID = Maths.createInsecureUUID(random)

    final override val data: MetadataHolder = MetadataHolder(this)
    final override val vehicleSystem: EntityVehicleSystem = EntityVehicleSystem(this)
    final override val waterPhysicsSystem: EntityWaterPhysicsSystem = EntityWaterPhysicsSystem(this)

    val viewingEngine: EntityViewingEngine = EntityViewingEngine(this)
    val trackingTarget: EntityTypeTarget<AquaEntity> = if (this is AquaPlayer) EntityTypeTarget.PLAYERS else EntityTypeTarget.ENTITIES
    val trackingViewCallback: EntityViewCallback<AquaEntity> = object : EntityViewCallback<AquaEntity> {
        override fun add(entity: AquaEntity) {
            viewingEngine.handleEnterView(entity)
        }

        override fun remove(entity: AquaEntity) {
            viewingEngine.handleExitView(entity)
        }

        override fun referenceUpdate(position: Position, tracker: EntityTracker) {
            viewingEngine.updateTracker(world, position)
        }
    }

    var eyeHeight: Float = 0F
        private set
    private var removed = false
    final override var position: Position = Position.ZERO
    final override var velocity: Vec3d = Vec3d.ZERO
        set(value) {
            field = value
            velocityNeedsUpdate = true
        }
    private var velocityNeedsUpdate = false
    final override var boundingBox: BoundingBox = BoundingBox.ZERO
    final override var isOnGround: Boolean = true
    final override var ticksExisted: Int = 0
    final override var remainingFireTicks: Int = 0
    final override var isInvulnerable: Boolean = false
    final override var fallDistance: Float = 0F

    protected var cachedPointers: Pointers? = null

    override val scheduler: AquaScheduler = AquaScheduler()

    init {
        defineData()
    }

    fun playSound(event: SoundEvent, volume: Float, pitch: Float) {
        if (isSilent) return
        world.playSound(position.x, position.y, position.z, event, soundSource(), volume, pitch)
    }

    override fun tick(time: Long) {
        // Tick the scheduler here so that we can schedule things for the next tick within the tick.
        scheduler.process()

        // Run the main tick
        tick()

        // Do things that we need to do after the tick
        postTick()
    }

    open fun tick() {
        ticksExisted++
        waterPhysicsSystem.tick()
    }

    // This is for certain things that need to always be sent after the tick is finished, like update packets.
    protected open fun postTick() {
        if (data.isDirty()) sendPacketToViewersAndSelf(PacketOutSetEntityMetadata(id, data.collectDirty()))
        if (velocityNeedsUpdate) {
            sendPacketToViewers(PacketOutSetEntityVelocity.fromEntity(this))
            velocityNeedsUpdate = false
        }
    }

    final override fun teleport(position: Position) {
        val old = this.position
        this.position = position
        updatePosition(old, position)
    }

    private fun updatePosition(old: Position, new: Position) {
        val dx = abs(new.x - old.x)
        val dy = abs(new.y - old.y)
        val dz = abs(new.z - old.z)
        val positionChange = dx != 0.0 || dy != 0.0 || dz != 0.0
        val rotationChange = new.yaw != old.yaw || new.pitch != old.pitch

        if (dx > 8 || dy > 8 || dz > 8) {
            // The update packets can only handle a maximum of 8 blocks in any direction due to the way they
            // are designed, and so, if an entity moves more than 8 blocks, we need to teleport them.
            sendPositionUpdate(PacketOutTeleportEntity.create(this), old, new)
            return
        } else if (positionChange && rotationChange) {
            sendPositionUpdate(PacketOutUpdateEntityPositionAndRotation.create(id, old, new, isOnGround), old, new)
            sendPacketToViewers(PacketOutSetHeadRotation(id, new.yaw))
        } else if (positionChange) {
            sendPositionUpdate(PacketOutUpdateEntityPosition.create(id, old, new, isOnGround), old, new)
        } else if (rotationChange) {
            sendPacketToViewers(PacketOutUpdateEntityRotation(id, new.yaw, new.pitch, isOnGround))
            sendPacketToViewers(PacketOutSetHeadRotation(id, new.yaw))
        }
    }

    protected open fun sendPositionUpdate(packet: Packet, old: Position, new: Position) {
        sendPacketToViewers(packet)
        world.entityTracker.onMove(this, new, trackingTarget, trackingViewCallback)

        val oldChunkX = SectionPos.blockToSection(old.x)
        val oldChunkZ = SectionPos.blockToSection(old.z)
        val newChunkX = SectionPos.blockToSection(new.x)
        val newChunkZ = SectionPos.blockToSection(new.z)
        if (oldChunkX != newChunkX || oldChunkZ != newChunkZ) {
            if (this is AquaPlayer) updateChunks()
            world.chunkManager.updateEntityPosition(this, ChunkPos(newChunkX, newChunkZ))
        }
    }

    // TODO: Separate interaction logic
    //open fun interact(player: KryptonPlayer, hand: Hand): InteractionResult = InteractionResult.PASS

    override fun isInvulnerableTo(source: AquaDamageSource): Boolean {
        return isRemoved() || isInvulnerable && source.type !== DamageTypes.VOID && !source.isCreativePlayer()
    }

    override fun damage(source: AquaDamageSource, damage: Float): Boolean {
        if (isInvulnerableTo(source)) return false
        markDamaged()
        return false
    }

    protected fun markDamaged() {
        velocityNeedsUpdate = true
    }

    override fun remove() {
        if (removed) return
        removed = true
        vehicleSystem.ejectVehicle()
        vehicleSystem.ejectPassengers()
        world.removeEntity(this)
    }

    override fun pointers(): Pointers {
        if (cachedPointers == null) {
            cachedPointers = Pointers.builder()
                .withDynamic(Identity.DISPLAY_NAME) { nameOrDescription() }
                .withDynamic(Identity.UUID) { uuid }
                .withStatic(PermissionChecker.POINTER, PermissionChecker { getPermissionValue(it) })
                .build()
        }
        return cachedPointers!!
    }

    override fun soundSource(): Sound.Source = Sound.Source.NEUTRAL

    override fun sendSystemMessage(message: Component) {
        // Do nothing by default
    }

    override fun acceptsSuccess(): Boolean = world.gameRules().getBoolean(GameRuleKeys.SEND_COMMAND_FEEDBACK)

    override fun acceptsFailure(): Boolean = true

    override fun shouldInformAdmins(): Boolean = true

    final override fun createCommandSourceStack(): CommandSourceStack {
        return CommandSourceStack(this, position, world, name, displayName, server, this)
    }

    open fun headYaw(): Float = 0F

    open fun showToViewer(viewer: AquaPlayer) {
        server.eventNode.fire(AquaEntityEnterViewEvent(viewer, this))

        viewer.connection.send(getSpawnPacket())
        if (velocity.lengthSquared() > 0.001) viewer.connection.send(PacketOutSetEntityVelocity.fromEntity(this))
        viewer.connection.send(PacketOutSetEntityMetadata(id, data.collectAll()))
    }

    open fun hideFromViewer(viewer: AquaPlayer) {
        server.eventNode.fire(AquaEntityExitViewEvent(viewer, this))
        viewer.connection.send(PacketOutRemoveEntities.removeSingle(this))
    }

    fun sendPacketToViewers(packet: Packet) {
        PacketGrouping.sendGroupedPacket(viewingEngine.viewers(), packet)
    }

    protected open fun sendPacketToViewersAndSelf(packet: Packet) {
        sendPacketToViewers(packet)
    }

    protected open fun getSpawnPacket(): Packet = PacketOutSpawnEntity.create(this)

    override fun isRemoved(): Boolean = removed

    companion object {

        private val NEXT_ENTITY_ID = AtomicInteger(0)
        const val BREATHING_DISTANCE_BELOW_EYES: Float = 0.11111111F
    }
}
