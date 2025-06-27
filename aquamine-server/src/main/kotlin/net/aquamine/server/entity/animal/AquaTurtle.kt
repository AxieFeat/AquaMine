package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Turtle
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.TurtleSerializer
import net.aquamine.server.world.AquaWorld

class AquaTurtle(world: AquaWorld) : AquaAnimal(world), Turtle {

    override val type: AquaEntityType<AquaTurtle>
        get() = AquaEntityTypes.TURTLE
    override val serializer: EntitySerializer<AquaTurtle>
        get() = TurtleSerializer

    override var hasEgg: Boolean
        get() = data.get(MetadataKeys.Turtle.HAS_EGG)
        set(value) = data.set(MetadataKeys.Turtle.HAS_EGG, value)
    override var isLayingEgg: Boolean
        get() = data.get(MetadataKeys.Turtle.LAYING_EGG)
        set(value) = data.set(MetadataKeys.Turtle.LAYING_EGG, value)
    override var isGoingHome: Boolean
        get() = data.get(MetadataKeys.Turtle.GOING_HOME)
        set(value) = data.set(MetadataKeys.Turtle.GOING_HOME, value)
    override var isTravelling: Boolean
        get() = data.get(MetadataKeys.Turtle.TRAVELLING)
        set(value) = data.set(MetadataKeys.Turtle.TRAVELLING, value)
    override var home: Vec3i
        get() = data.get(MetadataKeys.Turtle.HOME)
        set(value) = data.set(MetadataKeys.Turtle.HOME, value)
    override var destination: Vec3i
        get() = data.get(MetadataKeys.Turtle.DESTINATION)
        set(value) = data.set(MetadataKeys.Turtle.DESTINATION, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Turtle.HOME, Vec3i.ZERO)
        data.define(MetadataKeys.Turtle.HAS_EGG, false)
        data.define(MetadataKeys.Turtle.LAYING_EGG, false)
        data.define(MetadataKeys.Turtle.DESTINATION, Vec3i.ZERO)
        data.define(MetadataKeys.Turtle.GOING_HOME, false)
        data.define(MetadataKeys.Turtle.TRAVELLING, false)
    }

    override fun canFallInLove(): Boolean = super.canFallInLove() && !hasEgg

    override fun isFood(item: ItemStack): Boolean = item.type === ItemTypes.SEAGRASS

    override fun isPushedByFluid(): Boolean = false

    companion object {

        private const val DEFAULT_MAX_HEALTH = 30.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.25

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}
