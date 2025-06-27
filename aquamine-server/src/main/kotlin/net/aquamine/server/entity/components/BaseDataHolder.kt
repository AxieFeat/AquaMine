package net.aquamine.server.entity.components

import net.kyori.adventure.text.Component
import net.aquamine.api.entity.Entity
import net.aquamine.server.entity.Pose
import net.aquamine.server.entity.metadata.MetadataHolder
import net.aquamine.server.entity.metadata.MetadataKey
import net.aquamine.server.entity.metadata.MetadataKeys

/**
 * A delegating extension of entity that implements the data-backed API
 * properties to avoid putting these in AquaEntity, making it less
 * cluttered.
 */
interface BaseDataHolder : Entity {

    val data: MetadataHolder

    override var isOnFire: Boolean
        get() = data.getFlag(MetadataKeys.Entity.FLAGS, FLAG_ON_FIRE)
        set(value) = data.setFlag(MetadataKeys.Entity.FLAGS, FLAG_ON_FIRE, value)
    override var isSneaking: Boolean
        get() = data.getFlag(MetadataKeys.Entity.FLAGS, FLAG_SNEAKING)
        set(value) = data.setFlag(MetadataKeys.Entity.FLAGS, FLAG_SNEAKING, value)
    override var isSprinting: Boolean
        get() = data.getFlag(MetadataKeys.Entity.FLAGS, FLAG_SPRINTING)
        set(value) = data.setFlag(MetadataKeys.Entity.FLAGS, FLAG_SPRINTING, value)
    override var isSwimming: Boolean
        get() = data.getFlag(MetadataKeys.Entity.FLAGS, FLAG_SWIMMING)
        set(value) = data.setFlag(MetadataKeys.Entity.FLAGS, FLAG_SWIMMING, value)
    override var isInvisible: Boolean
        get() = data.getFlag(MetadataKeys.Entity.FLAGS, FLAG_INVISIBLE)
        set(value) = data.setFlag(MetadataKeys.Entity.FLAGS, FLAG_INVISIBLE, value)
    override var isGlowing: Boolean
        get() = data.getFlag(MetadataKeys.Entity.FLAGS, FLAG_GLOWING)
        set(value) = data.setFlag(MetadataKeys.Entity.FLAGS, FLAG_GLOWING, value)
    override var airSupply: Int
        get() = data.get(MetadataKeys.Entity.AIR_TICKS)
        set(value) = data.set(MetadataKeys.Entity.AIR_TICKS, value)
    override var customName: Component?
        get() = data.get(MetadataKeys.Entity.CUSTOM_NAME)
        set(value) = data.set(MetadataKeys.Entity.CUSTOM_NAME, value)
    override var isCustomNameVisible: Boolean
        get() = data.get(MetadataKeys.Entity.CUSTOM_NAME_VISIBILITY)
        set(value) = data.set(MetadataKeys.Entity.CUSTOM_NAME_VISIBILITY, value)
    override var isSilent: Boolean
        get() = data.get(MetadataKeys.Entity.SILENT)
        set(value) = data.set(MetadataKeys.Entity.SILENT, value)
    override var hasGravity: Boolean
        get() = !data.get(MetadataKeys.Entity.NO_GRAVITY)
        set(value) = data.set(MetadataKeys.Entity.NO_GRAVITY, !value)
    var pose: Pose
        get() = data.get(MetadataKeys.Entity.POSE)
        set(value) = data.set(MetadataKeys.Entity.POSE, value)
    override var frozenTicks: Int
        get() = data.get(MetadataKeys.Entity.FROZEN_TICKS)
        set(value) = data.set(MetadataKeys.Entity.FROZEN_TICKS, value)

    fun defineData()

    fun onDataUpdate(key: MetadataKey<*>) {
        // TODO: Update dimensions when key is pose
    }

    companion object {

        private const val FLAG_ON_FIRE = 0
        private const val FLAG_SNEAKING = 1
        private const val FLAG_SPRINTING = 3
        private const val FLAG_SWIMMING = 4
        private const val FLAG_INVISIBLE = 5
        private const val FLAG_GLOWING = 6
    }
}
