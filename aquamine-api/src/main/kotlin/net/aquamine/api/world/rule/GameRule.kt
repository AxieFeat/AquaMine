package net.aquamine.api.world.rule

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.translation.Translatable
import org.jetbrains.annotations.ApiStatus
import net.aquamine.api.AquaMine

/**
 * A rule dictating how a specific aspect of the game functions.
 *
 * @param V The type of the value.
 */
@CataloguedBy(GameRules::class)
@ImmutableType
interface GameRule<V> : Translatable {

    /**
     * The name of this rule.
     */
    val name: String

    /**
     * The default value of this rule.
     */
    val defaultValue: V

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun <V> of(name: String): GameRule<V>
    }

    companion object {

        @JvmSynthetic
        internal fun <V> of(name: String): GameRule<V> = AquaMine.factory<Factory>().of(name)
    }
}
