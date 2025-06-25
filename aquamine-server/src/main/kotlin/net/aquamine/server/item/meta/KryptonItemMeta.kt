package net.aquamine.server.item.meta

import net.aquamine.api.item.meta.ItemMeta
import net.aquamine.api.item.meta.ItemMetaBuilder
import net.aquamine.server.item.ItemFactory
import xyz.axie.nbt.CompoundTag

class KryptonItemMeta(data: CompoundTag) : AbstractItemMeta<KryptonItemMeta>(data), ItemMeta {

    override fun copy(data: CompoundTag): KryptonItemMeta = KryptonItemMeta(data)

    class Builder : KryptonItemMetaBuilder<ItemMeta.Builder, ItemMeta>(), ItemMeta.Builder {

        override fun build(): KryptonItemMeta = KryptonItemMeta(buildData().build())
    }

    object Factory : ItemMeta.Factory {

        override fun builder(): ItemMeta.Builder = Builder()

        override fun <B : ItemMetaBuilder<B, P>, P : ItemMetaBuilder.Provider<B>> builder(type: Class<P>): B = ItemFactory.builder(type)
    }

    companion object {

        @JvmField
        val DEFAULT: KryptonItemMeta = Builder().build()
    }
}
