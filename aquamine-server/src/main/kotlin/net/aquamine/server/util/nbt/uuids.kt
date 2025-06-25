package net.aquamine.server.util.nbt

import net.aquamine.server.util.uuid.UUIDUtil
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.IntArrayTag
import xyz.axie.nbt.ListTag
import java.util.UUID

fun CompoundTag.hasUUID(name: String): Boolean {
    val tag = get(name)
    return tag != null && tag.id == IntArrayTag.ID && (tag as IntArrayTag).data.size == 4
}

fun CompoundTag.getUUID(name: String): UUID = UUIDUtil.loadUUID(get(name)!!)

fun CompoundTag.Builder.putUUID(name: String, uuid: UUID): CompoundTag.Builder = put(name, UUIDUtil.createUUID(uuid))

fun ListTag.Builder.addUUID(uuid: UUID): ListTag.Builder = add(UUIDUtil.createUUID(uuid))
