package net.aquamine.server.entity

import net.aquamine.api.entity.ArmorStand
import net.aquamine.api.entity.EquipmentSlot
import net.aquamine.api.util.Rotation
import net.aquamine.server.entity.components.AquaEquipable
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.ArmorStandSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.util.EquipmentSlots
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.util.collection.FixedList
import net.aquamine.server.world.AquaWorld

class AquaArmorStand(world: AquaWorld) : AquaLivingEntity(world), ArmorStand, AquaEquipable {

    override val type: AquaEntityType<AquaArmorStand>
        get() = AquaEntityTypes.ARMOR_STAND
    override val serializer: EntitySerializer<AquaArmorStand>
        get() = ArmorStandSerializer

    internal val armorItems = FixedList(4, AquaItemStack.EMPTY)
    internal val handItems = FixedList(2, AquaItemStack.EMPTY)
    internal var disabledSlots = 0

    override var isSmall: Boolean
        get() = data.getFlag(MetadataKeys.ArmorStand.FLAGS, FLAG_SMALL)
        set(value) = data.setFlag(MetadataKeys.ArmorStand.FLAGS, FLAG_SMALL, value)
    override var hasArms: Boolean
        get() = data.getFlag(MetadataKeys.ArmorStand.FLAGS, FLAG_ARMS)
        set(value) = data.setFlag(MetadataKeys.ArmorStand.FLAGS, FLAG_ARMS, value)
    override var hasBasePlate: Boolean
        get() = data.getFlag(MetadataKeys.ArmorStand.FLAGS, FLAG_BASE_PLATE)
        set(value) = data.setFlag(MetadataKeys.ArmorStand.FLAGS, FLAG_BASE_PLATE, value)
    override var isMarker: Boolean
        get() = data.getFlag(MetadataKeys.ArmorStand.FLAGS, FLAG_MARKER)
        set(value) = data.setFlag(MetadataKeys.ArmorStand.FLAGS, FLAG_MARKER, value)
    override var headPose: Rotation
        get() = data.get(MetadataKeys.ArmorStand.HEAD_ROTATION)
        set(value) = data.set(MetadataKeys.ArmorStand.HEAD_ROTATION, value)
    override var bodyPose: Rotation
        get() = data.get(MetadataKeys.ArmorStand.BODY_ROTATION)
        set(value) = data.set(MetadataKeys.ArmorStand.BODY_ROTATION, value)
    override var leftArmPose: Rotation
        get() = data.get(MetadataKeys.ArmorStand.LEFT_ARM_ROTATION)
        set(value) = data.set(MetadataKeys.ArmorStand.LEFT_ARM_ROTATION, value)
    override var rightArmPose: Rotation
        get() = data.get(MetadataKeys.ArmorStand.RIGHT_ARM_ROTATION)
        set(value) = data.set(MetadataKeys.ArmorStand.RIGHT_ARM_ROTATION, value)
    override var leftLegPose: Rotation
        get() = data.get(MetadataKeys.ArmorStand.LEFT_LEG_ROTATION)
        set(value) = data.set(MetadataKeys.ArmorStand.LEFT_LEG_ROTATION, value)
    override var rightLegPose: Rotation
        get() = data.get(MetadataKeys.ArmorStand.RIGHT_LEG_ROTATION)
        set(value) = data.set(MetadataKeys.ArmorStand.RIGHT_LEG_ROTATION, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.ArmorStand.FLAGS, 0)
        data.define(MetadataKeys.ArmorStand.HEAD_ROTATION, ArmorStandSerializer.DEFAULT_HEAD_ROTATION)
        data.define(MetadataKeys.ArmorStand.BODY_ROTATION, ArmorStandSerializer.DEFAULT_BODY_ROTATION)
        data.define(MetadataKeys.ArmorStand.LEFT_ARM_ROTATION, ArmorStandSerializer.DEFAULT_LEFT_ARM_ROTATION)
        data.define(MetadataKeys.ArmorStand.RIGHT_ARM_ROTATION, ArmorStandSerializer.DEFAULT_RIGHT_ARM_ROTATION)
        data.define(MetadataKeys.ArmorStand.LEFT_LEG_ROTATION, ArmorStandSerializer.DEFAULT_LEFT_LEG_ROTATION)
        data.define(MetadataKeys.ArmorStand.RIGHT_LEG_ROTATION, ArmorStandSerializer.DEFAULT_RIGHT_LEG_ROTATION)
    }

    override fun getEquipment(slot: EquipmentSlot): AquaItemStack = when (slot.type) {
        EquipmentSlot.Type.HAND -> handItems.get(EquipmentSlots.index(slot))
        EquipmentSlot.Type.ARMOR -> armorItems.get(EquipmentSlots.index(slot))
    }

    override fun setEquipment(slot: EquipmentSlot, item: AquaItemStack) {
        when (slot.type) {
            EquipmentSlot.Type.HAND -> handItems.set(EquipmentSlots.index(slot), item)
            EquipmentSlot.Type.ARMOR -> armorItems.set(EquipmentSlots.index(slot), item)
        }
    }

    companion object {

        private const val FLAG_SMALL = 0
        private const val FLAG_ARMS = 2
        private const val FLAG_BASE_PLATE = 3
        private const val FLAG_MARKER = 4
    }
}
