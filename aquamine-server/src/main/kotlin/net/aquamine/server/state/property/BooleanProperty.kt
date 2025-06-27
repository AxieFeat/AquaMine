package net.aquamine.server.state.property

import com.google.common.collect.ImmutableSet

class BooleanProperty(name: String) : AquaProperty<Boolean>(name, Boolean::class.javaObjectType) {

    override val values: Collection<Boolean>
        get() = VALUES

    override fun idFor(value: Boolean): Int = if (value) 1 else 0

    override fun fromString(value: String): Boolean? = value.toBooleanStrictOrNull()

    override fun toString(value: Boolean): String = value.toString()

    @Suppress("MagicNumber") // This is a hash code function
    override fun generateHashCode(): Int = 31 * super.generateHashCode() + VALUES.hashCode()

    companion object {

        private val VALUES = ImmutableSet.of(true, false)
    }
}
