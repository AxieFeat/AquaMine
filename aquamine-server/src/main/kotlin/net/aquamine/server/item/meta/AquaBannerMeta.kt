package net.aquamine.server.item.meta

import com.google.common.collect.ImmutableList
import net.aquamine.api.block.entity.banner.BannerPattern
import net.aquamine.api.item.meta.BannerMeta
import net.aquamine.server.util.collection.BuilderCollection
import net.aquamine.server.world.block.entity.banner.AquaBannerPattern
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.list

class AquaBannerMeta(data: CompoundTag) : AbstractItemMeta<AquaBannerMeta>(data), BannerMeta {

    override val patterns: ImmutableList<BannerPattern> =
        mapToList(data, PATTERNS_TAG, CompoundTag.ID) { AquaBannerPattern.load(it as CompoundTag) }

    override fun copy(data: CompoundTag): AquaBannerMeta = AquaBannerMeta(data)

    override fun withPatterns(patterns: List<BannerPattern>): BannerMeta = copy(put(data, PATTERNS_TAG, patterns, AquaBannerPattern::save))

    override fun withPattern(pattern: BannerPattern): BannerMeta =
        copy(data.update(PATTERNS_TAG, CompoundTag.ID) { it.add(AquaBannerPattern.save(pattern)) })

    override fun withoutPattern(index: Int): BannerMeta = copy(data.update(PATTERNS_TAG, CompoundTag.ID) { it.remove(index) })

    override fun withoutPattern(pattern: BannerPattern): BannerMeta =
        copy(data.update(PATTERNS_TAG, CompoundTag.ID) { it.remove(AquaBannerPattern.save(pattern)) })

    override fun toBuilder(): BannerMeta.Builder = Builder()

    class Builder : AquaItemMetaBuilder<BannerMeta.Builder, BannerMeta>, BannerMeta.Builder {

        private var patterns: MutableCollection<BannerPattern>

        constructor() : super() {
            patterns = BuilderCollection()
        }

        constructor(meta: AquaBannerMeta) : super(meta) {
            patterns = BuilderCollection(meta.patterns)
        }

        override fun patterns(patterns: Collection<BannerPattern>): Builder = apply { this.patterns = BuilderCollection(patterns) }

        override fun addPattern(pattern: BannerPattern): Builder = apply { patterns.add(pattern) }

        override fun build(): AquaBannerMeta = AquaBannerMeta(buildData().build())

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            if (patterns.isNotEmpty()) list(PATTERNS_TAG) { patterns.forEach { add(AquaBannerPattern.save(it)) } }
        }
    }

    companion object {

        private const val PATTERNS_TAG = "Patterns"
    }
}
