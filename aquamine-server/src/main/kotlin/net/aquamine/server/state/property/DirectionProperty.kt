package net.aquamine.server.state.property

import com.google.common.collect.Sets
import net.aquamine.api.util.Direction
import java.util.Arrays
import java.util.EnumSet
import java.util.function.Predicate

class DirectionProperty(name: String, values: Collection<Direction>) : EnumProperty<Direction>(name, Direction::class.java, values) {

    companion object {

        private val VALUES = Direction.values()
        private val VALUE_SET = Sets.immutableEnumSet(EnumSet.allOf(Direction::class.java))

        @JvmStatic
        fun create(name: String): DirectionProperty = DirectionProperty(name, VALUE_SET)

        @JvmStatic
        fun create(name: String, predicate: Predicate<Direction>): DirectionProperty =
            DirectionProperty(name, Arrays.stream(VALUES).filter(predicate).collect(Sets.toImmutableEnumSet()))

        @JvmStatic
        fun create(name: String, vararg values: Direction): DirectionProperty = create(name, values.asList())

        @JvmStatic
        private fun create(name: String, values: Collection<Direction>): DirectionProperty = DirectionProperty(name, Sets.immutableEnumSet(values))
    }
}
