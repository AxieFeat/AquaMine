package net.aquamine.server.entity.ai.memory

import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound
import net.aquamine.serialization.nbt.NbtOps

class ExpirableMemory<T : Any>(override val value: T?, private var ttl: Long) : Memory<T> {

    override fun tick() {
        ttl--
    }

    override fun save(key: MemoryKey<in T>, data: CompoundTag.Builder): CompoundTag.Builder {
        return data.compound(key.key().asString()) {
            if (value != null) put("value", key.codec.encodeStart(value, NbtOps.INSTANCE).result().get())
            putLong("ttl", ttl)
        }
    }

    override fun toString(): String = "ExpirableMemory(value=$value, ttl=$ttl)"
}
