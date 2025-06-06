package net.aquamine.api.adventure

import com.mojang.brigadier.Message
import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine

/**
 * A Brigadier [Message] that wraps a [Component].
 *
 * This class is special, as it should be checked for internally by the
 * command manager when a command syntax exception is thrown, so that the
 * wrapped component is correctly serialized in to JSON.
 *
 * You should use this class when you want to send a [Component] error message as
 * a response to a Brigadier command.
 */
@ImmutableType
interface AdventureMessage : Message, ComponentLike {

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(component: Component): AdventureMessage
    }

    companion object {

        /**
         * Creates a new Brigadier message that wraps the given [component].
         *
         * @param component The component message.
         *
         * @return A new adventure message.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun of(component: Component): AdventureMessage = AquaMine.factory<Factory>().of(component)
    }
}
