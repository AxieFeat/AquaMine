package net.aquamine.api.auth

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import org.jetbrains.annotations.ApiStatus

/**
 * A property of a game profile.
 */
@ImmutableType
interface ProfileProperty {

    /**
     * The name of the property.
     */
    val name: String

    /**
     * The value of the property.
     */
    val value: String

    /**
     * The signature for this property.
     *
     * May be null if this property isn't signed.
     */
    val signature: String?

    /**
     * Creates a new profile property with the given [signature].
     *
     * @param signature The new signature.
     *
     * @return The new profile property.
     */
    @Contract("_ -> new", pure = true)
    fun withSignature(signature: String?): ProfileProperty

    /**
     * Creates a new profile property without a signature.
     *
     * @return The new profile property.
     */
    @Contract("_ -> new", pure = true)
    fun withoutSignature(): ProfileProperty

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(name: String, value: String, signature: String?): ProfileProperty
    }

    companion object {

        /**
         * Creates a new profile property with the given [name], [value], and
         * [signature].
         *
         * @param name The name.
         * @param value The value.
         * @param signature The signature, or null for no signature.
         *
         * @return A new profile property.
         */
        @JvmStatic
        @Contract("_, _, _ -> new", pure = true)
        fun of(name: String, value: String, signature: String?): ProfileProperty = AquaMine.factory<Factory>().of(name, value, signature)

        /**
         * Creates a new profile property with the given [name] and [value].
         *
         * @param name The name.
         * @param value The value.
         *
         * @return A new profile property.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun of(name: String, value: String): ProfileProperty = of(name, value, null)
    }
}
