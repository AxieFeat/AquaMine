package net.aquamine.server.item.meta

import com.google.common.collect.ImmutableList
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.meta.BundleMeta
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.item.downcast
import net.aquamine.server.util.collection.BuilderCollection
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.list

class KryptonBundleMeta(data: CompoundTag) : AbstractItemMeta<KryptonBundleMeta>(data), BundleMeta {

    override val items: ImmutableList<ItemStack> = mapToList(data, ITEMS_TAG, CompoundTag.ID) { KryptonItemStack.from(it as CompoundTag) }

    override fun copy(data: CompoundTag): KryptonBundleMeta = KryptonBundleMeta(data)

    override fun withItems(items: List<ItemStack>): KryptonBundleMeta = copy(put(data, ITEMS_TAG, items) { it.downcast().save() })

    override fun withItem(item: ItemStack): KryptonBundleMeta = copy(data.update(ITEMS_TAG, CompoundTag.ID) { it.add(item.downcast().save()) })

    override fun withoutItem(index: Int): KryptonBundleMeta = copy(data.update(ITEMS_TAG, CompoundTag.ID) { it.remove(index) })

    override fun withoutItem(item: ItemStack): KryptonBundleMeta = copy(data.update(ITEMS_TAG, CompoundTag.ID) { it.remove(item.downcast().save()) })

    override fun toBuilder(): BundleMeta.Builder = Builder(this)

    class Builder : KryptonItemMetaBuilder<BundleMeta.Builder, BundleMeta>, BundleMeta.Builder {

        private var items: MutableCollection<ItemStack>

        constructor() : super() {
            items = BuilderCollection()
        }

        constructor(meta: KryptonBundleMeta) : super(meta) {
            items = BuilderCollection(meta.items)
        }

        override fun items(items: Collection<ItemStack>): Builder = apply { this.items = BuilderCollection(items) }

        override fun addItem(item: ItemStack): Builder = apply { items.add(item) }

        override fun build(): BundleMeta = KryptonBundleMeta(buildData().build())

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            if (items.isNotEmpty()) list(ITEMS_TAG) { items.forEach { it.downcast().save() } }
        }
    }

    companion object {

        private const val ITEMS_TAG = "Items"
    }
}
