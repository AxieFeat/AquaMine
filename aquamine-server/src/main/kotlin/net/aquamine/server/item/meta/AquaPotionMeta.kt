package net.aquamine.server.item.meta

import net.aquamine.api.item.meta.PotionMeta
import net.aquamine.api.potion.PotionEffect
import net.aquamine.api.potion.PotionType
import net.aquamine.api.util.Color
import net.aquamine.server.potion.AquaPotionEffect
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.collection.BuilderCollection
import net.kyori.adventure.key.Key
import org.jetbrains.annotations.Unmodifiable
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.IntTag
import xyz.axie.nbt.list

class AquaPotionMeta(data: CompoundTag): AbstractItemMeta<AquaPotionMeta>(data), PotionMeta {

    override val effects: @Unmodifiable List<PotionEffect> =
        mapToList(data, POTION_EFFECTS_TAG, CompoundTag.ID) { AquaPotionEffect.load(it as CompoundTag) }

    override val basePotionType: PotionType? =
        AquaRegistries.POTION_TYPE.get(Key.key(data.getString(POTION_TYPE_TAG)))

    override val color: Color? =
        getDisplay<IntTag, _>(data, POTION_COLOR_TAG, IntTag.ID, null) { Color(it.value) }

    override fun copy(data: CompoundTag): AquaPotionMeta = AquaPotionMeta(data)

    override fun withEffects(effects: List<PotionEffect>): PotionMeta =
        copy(put(data, POTION_EFFECTS_TAG, effects, AquaPotionEffect::save))

    override fun withEffect(effect: PotionEffect): PotionMeta =
        copy(data.update(POTION_EFFECTS_TAG, CompoundTag.ID) { it.add(AquaPotionEffect.save(effect)) })

    override fun withoutEffect(index: Int): PotionMeta =
        copy(data.update(POTION_EFFECTS_TAG, CompoundTag.ID) { it.remove(index) })

    override fun withoutEffect(effect: PotionEffect): PotionMeta =
        copy(data.update(POTION_EFFECTS_TAG, CompoundTag.ID) { it.remove(AquaPotionEffect.save(effect)) })

    override fun withBasePotionType(type: PotionType?): PotionMeta {
        val newData = if (type == null) data.remove(POTION_TYPE_TAG) else data.putString(POTION_TYPE_TAG, type.key().asString())

        return copy(newData)
    }

    override fun withColor(color: Color?): PotionMeta {
        val newData = if (color == null) data.remove(POTION_COLOR_TAG) else data.putInt(POTION_COLOR_TAG, color.encode())

        return copy(newData)
    }

    override fun toBuilder(): PotionMeta.Builder = Builder(this)

    class Builder : AquaItemMetaBuilder<PotionMeta.Builder, PotionMeta>, PotionMeta.Builder {

        private var effects: MutableCollection<PotionEffect>
        private var basePotionType: PotionType? = null
        private var color: Color? = null

        constructor() : super() {
            effects = BuilderCollection()
        }

        constructor(meta: AquaPotionMeta) : super(meta) {
            effects = BuilderCollection(meta.effects)
            basePotionType = meta.basePotionType
            color = meta.color
        }

        override fun effects(effects: Collection<PotionEffect>): PotionMeta.Builder = apply { this.effects = BuilderCollection(effects) }

        override fun addEffect(effect: PotionEffect): PotionMeta.Builder = apply { this.effects.add(effect) }

        override fun basePotionType(basePotionType: PotionType?): PotionMeta.Builder = apply { this.basePotionType = basePotionType }

        override fun color(color: Color?): PotionMeta.Builder = apply { this.color = color }

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            if (effects.isNotEmpty()) list(POTION_EFFECTS_TAG) { effects.forEach { add(AquaPotionEffect.save(it)) } }
            if(basePotionType != null) putString(POTION_TYPE_TAG, basePotionType!!.key().asString())
            if (color != null) putInt(POTION_COLOR_TAG, color!!.encode())
        }

        override fun build(): PotionMeta = AquaPotionMeta(buildData().build())
    }

    companion object {

        private const val POTION_TYPE_TAG = "Potion"
        private const val POTION_EFFECTS_TAG = "CustomPotionEffects"
        private const val POTION_COLOR_TAG = "CustomPotionColor"
    }
}
