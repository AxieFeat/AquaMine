package net.aquamine.server.world.block

import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.state.KryptonState
import net.aquamine.server.state.property.KryptonProperty
import net.aquamine.server.state.property.downcast
import net.aquamine.server.world.block.state.KryptonBlockState
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.StringTag
import xyz.axie.nbt.compound

object BlockStateSerialization {

    private const val NAME_TAG = "Name"
    private const val PROPERTIES_TAG = "Properties"
    private val LOGGER = LogManager.getLogger()

    @JvmStatic
    fun decode(data: CompoundTag): KryptonBlockState {
        if (!data.contains(NAME_TAG, StringTag.ID)) return KryptonBlocks.AIR.defaultState
        val block = KryptonRegistries.BLOCK.get(Key.key(data.getString(NAME_TAG)))
        var state = block.defaultState
        if (data.contains(PROPERTIES_TAG, CompoundTag.ID)) {
            val properties = data.getCompound(PROPERTIES_TAG)

            properties.keySet().forEach { property ->
                block.stateDefinition.getProperty(property)?.let { state = set(state, it, property, properties, data) }
            }
        }
        return state
    }

    @JvmStatic
    fun encode(state: KryptonBlockState): CompoundTag = compound {
        putString(NAME_TAG, KryptonRegistries.BLOCK.getKey(state.block).asString())
        if (state.properties.isEmpty()) return@compound
        compound(PROPERTIES_TAG) {
            state.properties.forEach {
                val property = it.key.downcast()
                putString(property.name, name(property, it.value))
            }
        }
    }

    @JvmStatic
    fun encode(data: CompoundTag.Builder, name: String, state: KryptonBlockState): CompoundTag.Builder = data.put(name, encode(state))

    @JvmStatic
    private fun <S : KryptonState<*, S>, T : Comparable<T>> set(state: S, property: KryptonProperty<T>, name: String, propertiesTag: CompoundTag,
                                                                blockStateTag: CompoundTag): S {
        val value = property.fromString(propertiesTag.getString(name))
        if (value != null) return state.setProperty(property, value)
        LOGGER.warn("Unable to read property $name with value ${propertiesTag.getString(name)} for block state $blockStateTag!")
        return state
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    private fun <T : Comparable<T>> name(property: KryptonProperty<T>, value: Comparable<*>): String = property.toString(value as T)
}
