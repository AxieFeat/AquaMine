package net.aquamine.server.entity.ai.memory

import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound
import net.aquamine.serialization.nbt.NbtOps

// A static memory is a memory that does not expire.
class StaticMemory<T : Any>(override val value: T?) : Memory<T> {

    override fun tick() {
        // Do nothing - no expiry for static memories
    }

    override fun save(key: MemoryKey<in T>, data: CompoundTag.Builder): CompoundTag.Builder {
        return data.compound(key.key().asString()) {
            if (value != null) put("value", key.codec.encodeStart(value, NbtOps.INSTANCE).result().get())
        }
    }

    override fun toString(): String = "StaticMemory(value=$value)"
}
