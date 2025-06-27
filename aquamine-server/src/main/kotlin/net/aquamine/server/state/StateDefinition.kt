package net.aquamine.server.state

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSortedMap
import net.aquamine.server.state.property.AquaProperty
import org.kryptonmc.serialization.Decoder
import org.kryptonmc.serialization.Encoder
import org.kryptonmc.serialization.MapCodec
import org.kryptonmc.util.Pair
import java.util.Collections
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Stream

class StateDefinition<O, S : AquaState<O, S>>(
    defaultGetter: Function<O, S>,
    val owner: O,
    factory: Factory<O, S>,
    propertiesByName: Map<String, AquaProperty<*>>
) {

    private val propertiesByName = ImmutableSortedMap.copyOf(propertiesByName)
    val states: ImmutableList<S>

    init {
        val defaultSupplier = Supplier { defaultGetter.apply(owner) }
        var propertiesCodec = MapCodec.of(Encoder.empty(), Decoder.unit(defaultSupplier))
        this.propertiesByName.entries.forEach { propertiesCodec = appendPropertyCodec(propertiesCodec, defaultSupplier, it.key, it.value) }

        val statesByProperties = LinkedHashMap<Map<AquaProperty<*>, Comparable<*>>, S>()
        val stateList = ArrayList<S>()

        var properties: Stream<List<Pair<AquaProperty<*>, Comparable<*>>>> = Stream.of(Collections.emptyList())
        propertiesByName.values.forEach { property ->
            properties = properties.flatMap { list ->
                property.values.stream().map { ArrayList(list).apply { add(Pair.of(property, it)) } }
            }
        }

        properties.forEach { list ->
            val values = list.stream().collect(ImmutableMap.toImmutableMap({ it.first() }, { it.second() }))
            val state = factory.create(owner, values, propertiesCodec)
            statesByProperties.put(values, state)
            stateList.add(state)
        }
        stateList.forEach { it.populateNeighbours(statesByProperties) }
        states = ImmutableList.copyOf(stateList)
    }

    fun properties(): Collection<AquaProperty<*>> = propertiesByName.values

    fun any(): S = states.get(0)

    fun getProperty(name: String): AquaProperty<*>? = propertiesByName.get(name)

    override fun toString(): String = "StateDefinition(block=$owner, properties=${propertiesByName.values.map { it.name }})"

    fun interface Factory<O, S> {

        fun create(owner: O, values: ImmutableMap<AquaProperty<*>, Comparable<*>>, propertiesCodec: MapCodec<S>): S
    }

    class Builder<O, S : AquaState<O, S>>(private val owner: O) {

        private val properties = HashMap<String, AquaProperty<*>>()

        fun add(vararg properties: AquaProperty<*>): Builder<O, S> = add(properties.asList())

        fun add(properties: Collection<AquaProperty<*>>): Builder<O, S> = apply {
            properties.forEach {
                validateProperty(it)
                this.properties.put(it.name, it)
            }
        }

        private fun <T : Comparable<T>> validateProperty(property: AquaProperty<T>) {
            val name = property.name
            require(NAME_REGEX.matches(name)) { "$owner has property with invalid name $name!" }
            val values = property.values
            require(values.size > 1) { "$owner attempted to use property $name with less than 2 possible values!" }
            values.forEach {
                val valueName = property.toString(it)
                require(NAME_REGEX.matches(valueName)) { "$owner has property $name with invalid value name $valueName!" }
            }
            require(!properties.containsKey(name)) { "$owner has duplicate property $name!" }
        }

        fun build(defaultGetter: Function<O, S>, factory: Factory<O, S>): StateDefinition<O, S> =
            StateDefinition(defaultGetter, owner, factory, properties)
    }

    companion object {

        private val NAME_REGEX = Regex("^[a-z0-9_]+$")

        @JvmStatic
        private fun <S : AquaState<*, S>, T : Comparable<T>> appendPropertyCodec(codec: MapCodec<S>, supplier: Supplier<S>, key: String,
                                                                                    property: AquaProperty<T>): MapCodec<S> {
            return MapCodec.pair(codec, property.valueCodec.fieldOf(key).orElseGet { property.value(supplier.get()) })
                .xmap({ it.first.setProperty(property, it.second.value) }, { Pair.of(it, property.value(it)) })
        }
    }
}
