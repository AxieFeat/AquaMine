package net.aquamine.server.item.meta

import net.aquamine.api.item.data.FireworkEffect
import net.aquamine.api.item.meta.FireworkStarMeta
import net.aquamine.server.item.data.KryptonFireworkEffect
import xyz.axie.nbt.CompoundTag

class KryptonFireworkStarMeta(data: CompoundTag) : AbstractItemMeta<KryptonFireworkStarMeta>(data), FireworkStarMeta {

    override val effect: FireworkEffect? = getEffect(data)

    override fun copy(data: CompoundTag): KryptonFireworkStarMeta = KryptonFireworkStarMeta(data)

    override fun withEffect(effect: FireworkEffect?): KryptonFireworkStarMeta {
        val newData = if (effect == null) data.remove(EFFECT_TAG) else data.put(EFFECT_TAG, KryptonFireworkEffect.save(effect))
        return KryptonFireworkStarMeta(newData)
    }

    override fun toBuilder(): FireworkStarMeta.Builder = Builder(this)

    class Builder : KryptonItemMetaBuilder<FireworkStarMeta.Builder, FireworkStarMeta>, FireworkStarMeta.Builder {

        private var effect: FireworkEffect? = null

        constructor() : super()

        constructor(meta: KryptonFireworkStarMeta) : super(meta) {
            effect = meta.effect
        }

        override fun effect(effect: FireworkEffect?): FireworkStarMeta.Builder = apply { this.effect = effect }

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            if (effect != null) put(EFFECT_TAG, KryptonFireworkEffect.save(effect!!))
        }

        override fun build(): KryptonFireworkStarMeta = KryptonFireworkStarMeta(buildData().build())
    }

    companion object {

        private const val EFFECT_TAG = "Explosion"

        @JvmStatic
        private fun getEffect(data: CompoundTag): KryptonFireworkEffect? {
            if (data.contains(EFFECT_TAG, CompoundTag.ID)) return null
            return KryptonFireworkEffect.load(data.getCompound(EFFECT_TAG))
        }
    }
}
