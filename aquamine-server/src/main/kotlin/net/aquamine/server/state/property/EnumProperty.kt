package net.aquamine.server.state.property

import com.google.common.collect.ImmutableSet
import com.google.common.collect.Sets
import java.util.Arrays
import java.util.EnumSet
import java.util.function.Predicate

open class EnumProperty<E : Enum<E>>(name: String, type: Class<E>, values: Collection<E>) : AquaProperty<E>(name, type) {

    final override val values: Collection<E> = ImmutableSet.copyOf(values)
    private val names = HashMap<String, E>()
    private val idLookupTable: IntArray

    init {
        for (value in values) {
            val constantName = value.name.lowercase()
            require(!names.containsKey(constantName)) { "Multiple values have the same name $constantName!" }
            names.put(constantName, value)
        }

        var id = 0
        idLookupTable = IntArray(type.enumConstants.size)
        Arrays.fill(idLookupTable, -1)
        for (value in values) {
            idLookupTable[value.ordinal] = id++
        }
    }

    override fun fromString(value: String): E? = names.get(value)

    override fun toString(value: E): String = value.name.lowercase()

    override fun idFor(value: E): Int = idLookupTable[value.ordinal]

    @Suppress("MagicNumber") // This is a hash code function
    override fun generateHashCode(): Int {
        var value = super.generateHashCode()
        value = 31 * value + values.hashCode()
        return 31 * value + names.hashCode()
    }

    companion object {

        @JvmStatic
        inline fun <reified E : Enum<E>> create(name: String): EnumProperty<E> = create(name, EnumSet.allOf(E::class.java))

        @JvmStatic
        inline fun <reified E : Enum<E>> create(name: String, predicate: Predicate<E>): EnumProperty<E> =
            create(name, Arrays.stream(enumValues<E>()).filter(predicate).collect(Sets.toImmutableEnumSet()))

        @JvmStatic
        inline fun <reified E : Enum<E>> create(name: String, vararg values: E): EnumProperty<E> = create(name, values.asList())

        @JvmStatic
        inline fun <reified E : Enum<E>> create(name: String, values: Collection<E>): EnumProperty<E> = EnumProperty(name, E::class.java, values)
    }
}
