package net.aquamine.api.entity.attribute

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import java.util.UUID

/**
 * A modifier that can be applied to an [Attribute].
 */
@ImmutableType
interface AttributeModifier {

    /**
     * The unique ID of the modifier.
     */
    val uuid: UUID

    /**
     * The name of the modifier.
     */
    val name: String

    /**
     * The amount to modify attribute values by.
     */
    val amount: Double

    /**
     * The operation to perform on the modifier.
     */
    val operation: ModifierOperation

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(uuid: UUID, name: String, amount: Double, operation: ModifierOperation): AttributeModifier
    }

    companion object {

        /**
         * Creates a new attribute modifier with the given values.
         *
         * @param uuid The unique ID of the modifier.
         * @param name The name of the modifier.
         * @param amount The amount to modify attribute values by.
         * @param operation The operation to perform on the modifier.
         *
         * @return A new attribute modifier.
         */
        @JvmStatic
        @Contract("_, _, _, _ -> new", pure = true)
        fun of(uuid: UUID, name: String, amount: Double, operation: ModifierOperation): AttributeModifier =
            AquaMine.factory<Factory>().of(uuid, name, amount, operation)
    }
}
