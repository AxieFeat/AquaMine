package net.aquamine.server.state

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSortedMap
import net.aquamine.server.state.property.KryptonProperty
import org.kryptonmc.serialization.Decoder
import org.kryptonmc.serialization.Encoder
import org.kryptonmc.serialization.MapCodec
import org.kryptonmc.util.Pair
import java.util.Collections
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Stream

class StateDefinition<O, S : KryptonState<O, S>>(
    defaultGetter: Function<O, S>,
    val owner: O,
    factory: Factory<O, S>,
    propertiesByName: Map<String, KryptonProperty<*>>
) {

    private val propertiesByName = ImmutableSortedMap.copyOf(propertiesByName)
    val states: ImmutableList<S>

    init {
        val defaultSupplier = Supplier { defaultGetter.apply(owner) }
        var propertiesCodec = MapCodec.of(Encoder.empty(), Decoder.unit(defaultSupplier))
        this.propertiesByName.entries.forEach { propertiesCodec = appendPropertyCodec(propertiesCodec, defaultSupplier, it.key, it.value) }

        val statesByProperties = LinkedHashMap<Map<KryptonProperty<*>, Comparable<*>>, S>()
        val stateList = ArrayList<S>()

        var properties: Stream<List<Pair<KryptonProperty<*>, Comparable<*>>>> = Stream.of(Collections.emptyList())
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

    fun properties(): Collection<KryptonProperty<*>> = propertiesByName.values

    fun any(): S = states.get(0)

    fun getProperty(name: String): KryptonProperty<*>? = propertiesByName.get(name)

    override fun toString(): String = "StateDefinition(block=$owner, properties=${propertiesByName.values.map { it.name }})"

    fun interface Factory<O, S> {

        fun create(owner: O, values: ImmutableMap<KryptonProperty<*>, Comparable<*>>, propertiesCodec: MapCodec<S>): S
    }

    class Builder<O, S : KryptonState<O, S>>(private val owner: O) {

        private val properties = HashMap<String, KryptonProperty<*>>()

        fun add(vararg properties: KryptonProperty<*>): Builder<O, S> = add(properties.asList())

        fun add(properties: Collection<KryptonProperty<*>>): Builder<O, S> = apply {
            properties.forEach {
                validateProperty(it)
                this.properties.put(it.name, it)
            }
        }

        private fun <T : Comparable<T>> validateProperty(property: KryptonProperty<T>) {
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
        private fun <S : KryptonState<*, S>, T : Comparable<T>> appendPropertyCodec(codec: MapCodec<S>, supplier: Supplier<S>, key: String,
                                                                                    property: KryptonProperty<T>): MapCodec<S> {
            return MapCodec.pair(codec, property.valueCodec.fieldOf(key).orElseGet { property.value(supplier.get()) })
                .xmap({ it.first.setProperty(property, it.second.value) }, { Pair.of(it, property.value(it)) })
        }
    }
}
