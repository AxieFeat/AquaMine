package net.aquamine.server.entity.ai.memory

import xyz.axie.nbt.CompoundTag

interface Memory<T : Any> {

    val value: T?

    fun tick()

    fun save(key: MemoryKey<in T>, data: CompoundTag.Builder): CompoundTag.Builder
}
