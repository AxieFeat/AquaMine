package net.aquamine.server.item.meta

import com.google.common.collect.ImmutableList
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.aquamine.api.item.meta.WritableBookMeta
import net.aquamine.server.util.collection.BuilderCollection
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.StringTag
import xyz.axie.nbt.list

class AquaWritableBookMeta(data: CompoundTag) : AbstractItemMeta<AquaWritableBookMeta>(data), WritableBookMeta {

    override val pages: ImmutableList<Component> =
        mapToList(data, PAGES_TAG, StringTag.ID) { LegacyComponentSerializer.legacySection().deserialize((it as StringTag).value) }

    override fun copy(data: CompoundTag): AquaWritableBookMeta = AquaWritableBookMeta(data)

    override fun withPages(pages: Collection<Component>): AquaWritableBookMeta = copy(put(data, PAGES_TAG, pages, ::toLegacy))

    override fun withPage(page: Component): AquaWritableBookMeta = copy(data.update(PAGES_TAG, StringTag.ID) { it.add(toLegacy(page)) })

    override fun withoutPage(index: Int): AquaWritableBookMeta = copy(data.update(PAGES_TAG, StringTag.ID) { it.remove(index) })

    override fun withoutPage(page: Component): AquaWritableBookMeta = copy(data.update(PAGES_TAG, StringTag.ID) { it.remove(toLegacy(page)) })

    override fun toBuilder(): WritableBookMeta.Builder = Builder(this)

    class Builder : AquaItemMetaBuilder<WritableBookMeta.Builder, WritableBookMeta>, WritableBookMeta.Builder {

        private var pages: MutableCollection<Component>

        constructor() {
            pages = BuilderCollection()
        }

        constructor(meta: AquaWritableBookMeta) {
            pages = BuilderCollection(meta.pages)
        }

        override fun pages(pages: Collection<Component>): Builder = apply { this.pages = BuilderCollection() }

        override fun addPage(page: Component): Builder = apply { pages.add(page) }

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            if (pages.isNotEmpty()) list(PAGES_TAG) { pages.forEach { addString(toJson(it)) } }
        }

        override fun build(): AquaWritableBookMeta = AquaWritableBookMeta(buildData().build())
    }

    companion object {

        private const val PAGES_TAG = "pages"

        @JvmStatic
        private fun toLegacy(input: Component): StringTag = StringTag.of(LegacyComponentSerializer.legacySection().serialize(input))
    }
}
