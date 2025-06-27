package net.aquamine.server.entity.ai.memory

import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import net.aquamine.annotations.CataloguedBy
import net.aquamine.server.registry.AquaRegistries
import org.kryptonmc.serialization.Codec

@CataloguedBy(MemoryKeys::class)
class MemoryKey<T>(val codec: Codec<T>) : Keyed {

    override fun key(): Key = AquaRegistries.MEMORY_KEY.getKey(this)!!

    override fun toString(): String = "MemoryKey(codec=$codec)"
}
