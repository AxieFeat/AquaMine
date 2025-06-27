package net.aquamine.server.item.meta

import net.aquamine.api.item.meta.ItemMeta
import net.aquamine.api.item.meta.ItemMetaBuilder
import net.aquamine.server.item.ItemFactory
import xyz.axie.nbt.CompoundTag

class AquaItemMeta(data: CompoundTag) : AbstractItemMeta<AquaItemMeta>(data), ItemMeta {

    override fun copy(data: CompoundTag): AquaItemMeta = AquaItemMeta(data)

    class Builder : AquaItemMetaBuilder<ItemMeta.Builder, ItemMeta>(), ItemMeta.Builder {

        override fun build(): AquaItemMeta = AquaItemMeta(buildData().build())
    }

    object Factory : ItemMeta.Factory {

        override fun builder(): ItemMeta.Builder = Builder()

        override fun <B : ItemMetaBuilder<B, P>, P : ItemMetaBuilder.Provider<B>> builder(type: Class<P>): B = ItemFactory.builder(type)
    }

    companion object {

        @JvmField
        val DEFAULT: AquaItemMeta = Builder().build()
    }
}
