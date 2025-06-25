package net.aquamine.server.item.meta

import com.google.common.collect.ImmutableList
import net.aquamine.api.item.data.FireworkEffect
import net.aquamine.api.item.meta.FireworkRocketMeta
import net.aquamine.server.item.data.KryptonFireworkEffect
import net.aquamine.server.util.collection.BuilderCollection
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.ListTag
import xyz.axie.nbt.compound
import xyz.axie.nbt.list
import java.util.function.UnaryOperator

class KryptonFireworkRocketMeta(data: CompoundTag) : AbstractItemMeta<KryptonFireworkRocketMeta>(data), FireworkRocketMeta {

    override val effects: ImmutableList<FireworkEffect> =
        mapToList(data.getCompound(FIREWORKS_TAG), EFFECTS_TAG, CompoundTag.ID) { KryptonFireworkEffect.load(it as CompoundTag) }
    override val flightDuration: Int = data.getCompound(FIREWORKS_TAG).getByte(FLIGHT_TAG).toInt()

    override fun copy(data: CompoundTag): KryptonFireworkRocketMeta = KryptonFireworkRocketMeta(data)

    override fun withEffects(effects: List<FireworkEffect>): KryptonFireworkRocketMeta =
        copy(data.update(FIREWORKS_TAG) { fireworks -> put(fireworks, EFFECTS_TAG, effects, KryptonFireworkEffect::save) })

    override fun withEffect(effect: FireworkEffect): KryptonFireworkRocketMeta =
        copy(modifyEffects(data) { it.add(KryptonFireworkEffect.save(effect)) })

    override fun withoutEffect(index: Int): KryptonFireworkRocketMeta = copy(modifyEffects(data) { it.remove(index) })

    override fun withoutEffect(effect: FireworkEffect): KryptonFireworkRocketMeta =
        copy(modifyEffects(data) { it.remove(KryptonFireworkEffect.save(effect)) })

    override fun withFlightDuration(duration: Int): KryptonFireworkRocketMeta =
        copy(data.update(FIREWORKS_TAG) { it.putByte(FLIGHT_TAG, duration.toByte()) })

    override fun toBuilder(): FireworkRocketMeta.Builder = Builder(this)

    class Builder : KryptonItemMetaBuilder<FireworkRocketMeta.Builder, FireworkRocketMeta>, FireworkRocketMeta.Builder {

        private var effects: MutableCollection<FireworkEffect>
        private var flightDuration = 0

        constructor() : super() {
            effects = BuilderCollection()
        }

        constructor(meta: KryptonFireworkRocketMeta) : super(meta) {
            effects = BuilderCollection(meta.effects)
            flightDuration = meta.flightDuration
        }

        override fun effects(effects: Collection<FireworkEffect>): Builder = apply { this.effects = BuilderCollection(effects) }

        override fun flightDuration(duration: Int): Builder = apply { flightDuration = duration }

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            compound(FIREWORKS_TAG) {
                if (effects.isNotEmpty()) list(EFFECTS_TAG) { effects.forEach { add(KryptonFireworkEffect.save(it)) } }
                putByte(FLIGHT_TAG, flightDuration.toByte())
            }
        }

        override fun build(): KryptonFireworkRocketMeta = KryptonFireworkRocketMeta(buildData().build())
    }

    companion object {

        private const val FIREWORKS_TAG = "Fireworks"
        private const val EFFECTS_TAG = "Explosions"
        private const val FLIGHT_TAG = "Flight"

        @JvmStatic
        private fun modifyEffects(data: CompoundTag, modifier: UnaryOperator<ListTag>): CompoundTag {
            if (!data.contains(FIREWORKS_TAG, CompoundTag.ID)) return data
            return data.put(FIREWORKS_TAG, data.getCompound(FIREWORKS_TAG).update(EFFECTS_TAG, ListTag.ID, modifier))
        }
    }
}
