package net.aquamine.server.item.meta

import net.aquamine.api.item.data.FireworkEffect
import net.aquamine.api.item.meta.FireworkStarMeta
import net.aquamine.server.item.data.AquaFireworkEffect
import xyz.axie.nbt.CompoundTag

class AquaFireworkStarMeta(data: CompoundTag) : AbstractItemMeta<AquaFireworkStarMeta>(data), FireworkStarMeta {

    override val effect: FireworkEffect? = getEffect(data)

    override fun copy(data: CompoundTag): AquaFireworkStarMeta = AquaFireworkStarMeta(data)

    override fun withEffect(effect: FireworkEffect?): AquaFireworkStarMeta {
        val newData = if (effect == null) data.remove(EFFECT_TAG) else data.put(EFFECT_TAG, AquaFireworkEffect.save(effect))
        return AquaFireworkStarMeta(newData)
    }

    override fun toBuilder(): FireworkStarMeta.Builder = Builder(this)

    class Builder : AquaItemMetaBuilder<FireworkStarMeta.Builder, FireworkStarMeta>, FireworkStarMeta.Builder {

        private var effect: FireworkEffect? = null

        constructor() : super()

        constructor(meta: AquaFireworkStarMeta) : super(meta) {
            effect = meta.effect
        }

        override fun effect(effect: FireworkEffect?): FireworkStarMeta.Builder = apply { this.effect = effect }

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            if (effect != null) put(EFFECT_TAG, AquaFireworkEffect.save(effect!!))
        }

        override fun build(): AquaFireworkStarMeta = AquaFireworkStarMeta(buildData().build())
    }

    companion object {

        private const val EFFECT_TAG = "Explosion"

        @JvmStatic
        private fun getEffect(data: CompoundTag): AquaFireworkEffect? {
            if (data.contains(EFFECT_TAG, CompoundTag.ID)) return null
            return AquaFireworkEffect.load(data.getCompound(EFFECT_TAG))
        }
    }
}
