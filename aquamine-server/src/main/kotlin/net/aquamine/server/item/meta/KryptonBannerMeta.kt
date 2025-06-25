package net.aquamine.server.item.meta

import com.google.common.collect.ImmutableList
import net.aquamine.api.block.entity.banner.BannerPattern
import net.aquamine.api.item.meta.BannerMeta
import net.aquamine.server.util.collection.BuilderCollection
import net.aquamine.server.world.block.entity.banner.KryptonBannerPattern
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.list

class KryptonBannerMeta(data: CompoundTag) : AbstractItemMeta<KryptonBannerMeta>(data), BannerMeta {

    override val patterns: ImmutableList<BannerPattern> =
        mapToList(data, PATTERNS_TAG, CompoundTag.ID) { KryptonBannerPattern.load(it as CompoundTag) }

    override fun copy(data: CompoundTag): KryptonBannerMeta = KryptonBannerMeta(data)

    override fun withPatterns(patterns: List<BannerPattern>): BannerMeta = copy(put(data, PATTERNS_TAG, patterns, KryptonBannerPattern::save))

    override fun withPattern(pattern: BannerPattern): BannerMeta =
        copy(data.update(PATTERNS_TAG, CompoundTag.ID) { it.add(KryptonBannerPattern.save(pattern)) })

    override fun withoutPattern(index: Int): BannerMeta = copy(data.update(PATTERNS_TAG, CompoundTag.ID) { it.remove(index) })

    override fun withoutPattern(pattern: BannerPattern): BannerMeta =
        copy(data.update(PATTERNS_TAG, CompoundTag.ID) { it.remove(KryptonBannerPattern.save(pattern)) })

    override fun toBuilder(): BannerMeta.Builder = Builder()

    class Builder : KryptonItemMetaBuilder<BannerMeta.Builder, BannerMeta>, BannerMeta.Builder {

        private var patterns: MutableCollection<BannerPattern>

        constructor() : super() {
            patterns = BuilderCollection()
        }

        constructor(meta: KryptonBannerMeta) : super(meta) {
            patterns = BuilderCollection(meta.patterns)
        }

        override fun patterns(patterns: Collection<BannerPattern>): Builder = apply { this.patterns = BuilderCollection(patterns) }

        override fun addPattern(pattern: BannerPattern): Builder = apply { patterns.add(pattern) }

        override fun build(): KryptonBannerMeta = KryptonBannerMeta(buildData().build())

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            if (patterns.isNotEmpty()) list(PATTERNS_TAG) { patterns.forEach(KryptonBannerPattern::save) }
        }
    }

    companion object {

        private const val PATTERNS_TAG = "Patterns"
    }
}
