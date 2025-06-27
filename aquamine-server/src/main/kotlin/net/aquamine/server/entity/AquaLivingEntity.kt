package net.aquamine.server.entity

import net.aquamine.api.entity.ArmorSlot
import net.aquamine.api.entity.EquipmentSlot
import net.aquamine.api.entity.Hand
import net.aquamine.api.entity.LivingEntity
import net.aquamine.api.entity.attribute.Attribute
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.api.fluid.Fluid
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.Difficulty
import net.aquamine.server.entity.attribute.AttributeMap
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.DefaultAttributes
import net.aquamine.server.entity.attribute.AquaAttribute
import net.aquamine.server.entity.attribute.AquaAttributeType
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.attribute.downcast
import net.aquamine.server.entity.ai.memory.Brain
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.LivingEntitySerializer
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.entity.components.AquaEquipable
import net.aquamine.server.entity.util.EquipmentSlots
import net.aquamine.server.packet.out.play.PacketOutUpdateAttributes
import net.aquamine.server.world.AquaWorld

@Suppress("LeakingThis")
abstract class AquaLivingEntity(world: AquaWorld) : AquaEntity(world), LivingEntity, AquaEquipable {

    abstract override val type: AquaEntityType<AquaLivingEntity>
    override val serializer: EntitySerializer<out AquaLivingEntity>
        get() = LivingEntitySerializer

    final override val maxHealth: Float
        get() = attributes.getValue(AquaAttributeTypes.MAX_HEALTH).toFloat()
    override var absorption: Float = 0F
    final override var isDead: Boolean = false
    final override var deathTime: Int = 0
    final override var hurtTime: Int = 0
    final override var lastHurtTimestamp: Int = 0
    private var tickCount = 0
    val attributes: AttributeMap = AttributeMap(DefaultAttributes.get(type))
    open val brain: Brain = Brain()
    private var headYaw = position.yaw

    @Suppress("MemberVisibilityCanBePrivate")
    var lastHurtByMob: AquaLivingEntity? = null
        set(value) {
            field = value
            lastHurtByMobTime = tickCount
        }
    private var lastHurtByMobTime = 0
    @Suppress("MemberVisibilityCanBePrivate")
    var lastHurtByPlayer: AquaPlayer? = null
        set(value) {
            field = value
            lastHurtByPlayerTime = tickCount
        }
    private var lastHurtByPlayerTime = 0

    open val isBaby: Boolean
        get() = false

    final override var isGliding: Boolean
        get() = data.getFlag(MetadataKeys.Entity.FLAGS, FLAG_ENTITY_GLIDING)
        set(value) = data.setFlag(MetadataKeys.Entity.FLAGS, FLAG_ENTITY_GLIDING, value)
    final override var isUsingItem: Boolean
        get() = data.getFlag(MetadataKeys.LivingEntity.FLAGS, FLAG_USING_ITEM)
        set(value) = data.setFlag(MetadataKeys.LivingEntity.FLAGS, FLAG_USING_ITEM, value)
    final override var hand: Hand
        get() = if (data.getFlag(MetadataKeys.LivingEntity.FLAGS, FLAG_OFFHAND)) Hand.OFF else Hand.MAIN
        set(value) = data.setFlag(MetadataKeys.LivingEntity.FLAGS, FLAG_OFFHAND, value == Hand.OFF)
    final override var isInRiptideSpinAttack: Boolean
        get() = data.getFlag(MetadataKeys.LivingEntity.FLAGS, FLAG_IN_RIPTIDE_SPIN_ATTACK)
        set(value) = data.setFlag(MetadataKeys.LivingEntity.FLAGS, FLAG_IN_RIPTIDE_SPIN_ATTACK, value)
    override var health: Float
        get() = data.get(MetadataKeys.LivingEntity.HEALTH)
        set(value) = data.set(MetadataKeys.LivingEntity.HEALTH, value)
    var arrowCount: Int
        get() = data.get(MetadataKeys.LivingEntity.ARROWS)
        set(value) = data.set(MetadataKeys.LivingEntity.ARROWS, value)
    var stingerCount: Int
        get() = data.get(MetadataKeys.LivingEntity.STINGERS)
        set(value) = data.set(MetadataKeys.LivingEntity.STINGERS, value)
    final override var sleepingPosition: Vec3i?
        get() = data.get(MetadataKeys.LivingEntity.BED_LOCATION)
        set(value) = data.set(MetadataKeys.LivingEntity.BED_LOCATION, value)

    init {
        data.set(MetadataKeys.LivingEntity.HEALTH, maxHealth)
    }

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.LivingEntity.FLAGS, 0)
        data.define(MetadataKeys.LivingEntity.HEALTH, 1F)
        data.define(MetadataKeys.LivingEntity.POTION_EFFECT_COLOR, 0)
        data.define(MetadataKeys.LivingEntity.POTION_EFFECT_AMBIENCE, false)
        data.define(MetadataKeys.LivingEntity.ARROWS, 0)
        data.define(MetadataKeys.LivingEntity.STINGERS, 0)
        data.define(MetadataKeys.LivingEntity.BED_LOCATION, null)
    }

    override fun postTick() {
        super.postTick()
        if (attributes.isDirty()) sendPacketToViewersAndSelf(PacketOutUpdateAttributes.create(id, attributes.dirty()))
    }

    abstract override fun getEquipment(slot: EquipmentSlot): AquaItemStack

    override fun getHeldItem(hand: Hand): AquaItemStack {
        if (hand == Hand.MAIN) return getEquipment(EquipmentSlot.MAIN_HAND)
        if (hand == Hand.OFF) return getEquipment(EquipmentSlot.OFF_HAND)
        error("Tried to get held item for hand $hand that should not exist! Please sure that no plugins are injecting entries in to enums!")
    }

    override fun setHeldItem(hand: Hand, item: AquaItemStack) {
        when (hand) {
            Hand.MAIN -> setEquipment(EquipmentSlot.MAIN_HAND, item)
            Hand.OFF -> setEquipment(EquipmentSlot.OFF_HAND, item)
            else -> error("Tried to set held item for hand $hand that should not exist! Please sure that no plugins are injecting " +
                    "entries in to enums!")
        }
    }

    override fun getArmor(slot: ArmorSlot): AquaItemStack = getEquipment(EquipmentSlots.fromArmorSlot(slot))

    override fun setArmor(slot: ArmorSlot, item: AquaItemStack) {
        setEquipment(EquipmentSlots.fromArmorSlot(slot), item)
    }

    fun canAttack(target: AquaLivingEntity): Boolean {
        if (target is AquaPlayer && world.difficulty === Difficulty.PEACEFUL) return false
        return target.canBeSeenAsEnemy()
    }

    @Suppress("FunctionOnlyReturningConstant", "UnusedPrivateMember") // This will have logic in the future
    fun canStandOnFluid(fluid: Fluid): Boolean = false

    protected fun getAttribute(type: AquaAttributeType): AquaAttribute? = attributes.getAttribute(type)

    override fun getAttribute(type: AttributeType): Attribute? = getAttribute(type.downcast())

    final override fun isAlive(): Boolean = super.isAlive() && health > 0F

    open fun canBeSeenAsEnemy(): Boolean = !isInvulnerable && canBeSeenByAnyone()

    open fun canBeSeenByAnyone(): Boolean = isAlive()

    open fun soundVolume(): Float = 1F

    open fun voicePitch(): Float {
        val babyFactor = if (isBaby) 1.5F else 1F
        return (random.nextFloat() - random.nextFloat()) * 0.2F + babyFactor
    }

    fun killCredit(): AquaLivingEntity? {
        // TODO: Check combat tracker here
        if (lastHurtByPlayer != null) return lastHurtByPlayer
        if (lastHurtByMob != null) return lastHurtByMob
        return null
    }

    override fun headYaw(): Float = headYaw

    override fun showToViewer(viewer: AquaPlayer) {
        super.showToViewer(viewer)
        viewer.connection.send(PacketOutUpdateAttributes.create(id, attributes.syncable()))
    }

    companion object {

        private const val FLAG_ENTITY_GLIDING = 7
        private const val FLAG_USING_ITEM = 0
        private const val FLAG_OFFHAND = 1
        private const val FLAG_IN_RIPTIDE_SPIN_ATTACK = 2

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AttributeSupplier.builder()
            .add(AquaAttributeTypes.MAX_HEALTH)
            .add(AquaAttributeTypes.KNOCKBACK_RESISTANCE)
            .add(AquaAttributeTypes.MOVEMENT_SPEED)
            .add(AquaAttributeTypes.ARMOR)
            .add(AquaAttributeTypes.ARMOR_TOUGHNESS)
    }
}
