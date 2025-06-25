package net.aquamine.server.entity.ai.memory

import xyz.axie.nbt.CompoundTag

object EmptyMemory : Memory<Any> {

    override val value: Any?
        get() = null

    override fun tick() {
        // Nothing to do
    }

    override fun save(key: MemoryKey<in Any>, data: CompoundTag.Builder): CompoundTag.Builder {
        throw UnsupportedOperationException("Cannot save empty memory!")
    }
}
