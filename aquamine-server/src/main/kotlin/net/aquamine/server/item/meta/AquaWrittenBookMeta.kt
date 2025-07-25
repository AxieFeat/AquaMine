package net.aquamine.server.item.meta

import com.google.common.collect.ImmutableList
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.aquamine.api.item.data.WrittenBookGeneration
import net.aquamine.api.item.meta.WrittenBookMeta
import net.aquamine.server.util.collection.BuilderCollection
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.StringTag
import xyz.axie.nbt.list

class AquaWrittenBookMeta(data: CompoundTag) : AbstractItemMeta<AquaWrittenBookMeta>(data), WrittenBookMeta {

    override val title: Component = LegacyComponentSerializer.legacySection().deserialize(data.getString(TITLE_TAG))
    override val author: Component = LegacyComponentSerializer.legacySection().deserialize(data.getString(AUTHOR_TAG))
    override val pages: ImmutableList<Component> =
        mapToList(data, PAGES_TAG, StringTag.ID) { GsonComponentSerializer.gson().deserialize((it as StringTag).value) }
    override val generation: WrittenBookGeneration = GENERATIONS.getOrNull(data.getInt(GENERATION_TAG)) ?: WrittenBookGeneration.ORIGINAL

    override fun copy(data: CompoundTag): AquaWrittenBookMeta = AquaWrittenBookMeta(data)

    override fun withTitle(title: Component): AquaWrittenBookMeta =
        copy(data.putString(TITLE_TAG, LegacyComponentSerializer.legacySection().serialize(title)))

    override fun withAuthor(author: Component): AquaWrittenBookMeta =
        copy(data.putString(AUTHOR_TAG, LegacyComponentSerializer.legacySection().serialize(author)))

    override fun withPages(pages: Collection<Component>): AquaWrittenBookMeta =
        copy(put(data, PAGES_TAG, pages) { StringTag.of(LegacyComponentSerializer.legacySection().serialize(it)) })

    override fun withGeneration(generation: WrittenBookGeneration): AquaWrittenBookMeta = copy(data.putInt(GENERATION_TAG, generation.ordinal))

    override fun withPage(page: Component): AquaWrittenBookMeta =
        copy(data.update(PAGES_TAG, StringTag.ID) { it.add(StringTag.of(toJson(page))) })

    override fun withoutPage(index: Int): AquaWrittenBookMeta = copy(data.update(PAGES_TAG, StringTag.ID) { it.remove(index) })

    override fun withoutPage(page: Component): AquaWrittenBookMeta =
        copy(data.update(PAGES_TAG, StringTag.ID) { it.remove(StringTag.of(toJson(page))) })

    override fun toBuilder(): WrittenBookMeta.Builder = Builder(this)

    class Builder : AquaItemMetaBuilder<WrittenBookMeta.Builder, WrittenBookMeta>, WrittenBookMeta.Builder {

        private var title: Component = Component.empty()
        private var author: Component = Component.empty()
        private var pages: MutableCollection<Component>
        private var generation: WrittenBookGeneration = WrittenBookGeneration.ORIGINAL

        constructor() : super() {
            pages = BuilderCollection()
        }

        constructor(meta: AquaWrittenBookMeta) : super(meta) {
            title = meta.title
            author = meta.author
            pages = BuilderCollection(meta.pages)
            generation = meta.generation
        }

        override fun title(title: Component): WrittenBookMeta.Builder = apply { this.title = title }

        override fun author(author: Component): WrittenBookMeta.Builder = apply { this.author = author }

        override fun pages(pages: Collection<Component>): WrittenBookMeta.Builder = apply { this.pages = BuilderCollection(pages) }

        override fun addPage(page: Component): WrittenBookMeta.Builder = apply { pages.add(page) }

        override fun generation(generation: WrittenBookGeneration): WrittenBookMeta.Builder = apply { this.generation = generation }

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            putString(TITLE_TAG, LegacyComponentSerializer.legacySection().serialize(title))
            putString(AUTHOR_TAG, LegacyComponentSerializer.legacySection().serialize(author))
            list(PAGES_TAG) { pages.forEach { addString(toJson(it)) } }
            putInt(GENERATION_TAG, generation.ordinal)
        }

        override fun build(): AquaWrittenBookMeta = AquaWrittenBookMeta(buildData().build())
    }

    companion object {

        private const val TITLE_TAG = "title"
        private const val AUTHOR_TAG = "author"
        private const val PAGES_TAG = "pages"
        private const val GENERATION_TAG = "generation"
        private val GENERATIONS = WrittenBookGeneration.entries.toTypedArray()
    }
}
