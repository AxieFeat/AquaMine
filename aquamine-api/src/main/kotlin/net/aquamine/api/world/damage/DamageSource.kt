package net.aquamine.api.world.damage

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.entity.Entity
import net.aquamine.api.world.damage.type.DamageType

/**
 * A source of damage to something.
 */
@ImmutableType
interface DamageSource {

    /**
     * The type of damage that has been caused by this source.
     */
    val type: DamageType

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(type: DamageType): DamageSource

        fun entity(type: DamageType, entity: Entity): EntityDamageSource

        fun indirectEntity(type: DamageType, entity: Entity, indirectEntity: Entity): IndirectEntityDamageSource
    }

    companion object {

        /**
         * Creates a new damage source with the given [type].
         *
         * @param type The type.
         *
         * @return A new damage source.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun of(type: DamageType): DamageSource = AquaMine.factory<Factory>().of(type)
    }
}
