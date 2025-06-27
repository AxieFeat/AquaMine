package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Sheep
import net.aquamine.api.item.data.DyeColor
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.SheepSerializer
import net.aquamine.server.util.enumhelper.DyeColors
import net.aquamine.server.world.AquaWorld

class AquaSheep(world: AquaWorld) : AquaAnimal(world), Sheep {

    override val type: AquaEntityType<AquaSheep>
        get() = AquaEntityTypes.SHEEP
    override val serializer: EntitySerializer<AquaSheep>
        get() = SheepSerializer

    override var isSheared: Boolean
        get() = data.getFlag(MetadataKeys.Sheep.FLAGS, FLAG_SHEARED)
        set(value) = data.setFlag(MetadataKeys.Sheep.FLAGS, FLAG_SHEARED, value)
    override var woolColor: DyeColor
        get() = DyeColors.fromId(data.get(MetadataKeys.Sheep.FLAGS).toInt() and WOOL_COLOR_MASK)
        set(value) {
            val old = data.get(MetadataKeys.Sheep.FLAGS).toInt()
            data.set(MetadataKeys.Sheep.FLAGS, (old and CLEAR_WOOL_COLOR_MASK or (value.ordinal and WOOL_COLOR_MASK)).toByte())
        }

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Sheep.FLAGS, 0)
    }

    companion object {

        private const val FLAG_SHEARED = 4
        private const val WOOL_COLOR_MASK = 0xF
        private const val CLEAR_WOOL_COLOR_MASK = 0xF0

        private const val DEFAULT_MAX_HEALTH = 8.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.23

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}
