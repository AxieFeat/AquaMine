package net.aquamine.api.world.damage

import net.aquamine.annotations.ImmutableType
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.entity.Entity
import net.aquamine.api.world.damage.type.DamageType

/**
 * A damage source that affects an entity.
 */
@ImmutableType
interface EntityDamageSource : DamageSource {

    /**
     * The entity that caused the damage.
     */
    val entity: Entity

    companion object {

        /**
         * Creates a new damage source with the given [type], where the damage
         * originated from the given [entity].
         *
         * @param type The type of damage the source will cause.
         * @param entity The source of the damage.
         *
         * @return A new entity damage source.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun of(type: DamageType, entity: Entity): EntityDamageSource = AquaMine.factory<DamageSource.Factory>().entity(type, entity)
    }
}
