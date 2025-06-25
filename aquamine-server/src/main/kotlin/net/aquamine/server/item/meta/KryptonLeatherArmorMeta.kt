package net.aquamine.server.item.meta

import net.aquamine.api.item.meta.LeatherArmorMeta
import net.aquamine.api.util.Color
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.IntTag

class KryptonLeatherArmorMeta(data: CompoundTag) : AbstractItemMeta<KryptonLeatherArmorMeta>(data), LeatherArmorMeta {

    override val color: Color? = getDisplay<IntTag, _>(data, COLOR_TAG, IntTag.ID, null) { Color(it.value) }

    override fun copy(data: CompoundTag): KryptonLeatherArmorMeta = KryptonLeatherArmorMeta(data)

    override fun withColor(color: Color?): KryptonLeatherArmorMeta {
        val newData = if (color == null) data.remove(COLOR_TAG) else data.putInt(COLOR_TAG, color.encode())
        return KryptonLeatherArmorMeta(newData)
    }

    override fun toBuilder(): LeatherArmorMeta.Builder = Builder(this)

    class Builder : KryptonItemMetaBuilder<LeatherArmorMeta.Builder, LeatherArmorMeta>, LeatherArmorMeta.Builder {

        private var color: Color? = null

        constructor() : super()

        constructor(meta: KryptonLeatherArmorMeta) : super(meta) {
            color = meta.color
        }

        override fun color(color: Color?): LeatherArmorMeta.Builder = apply { this.color = color }

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            if (color != null) putInt(COLOR_TAG, color!!.encode())
        }

        override fun build(): KryptonLeatherArmorMeta = KryptonLeatherArmorMeta(buildData().build())
    }

    companion object {

        private const val COLOR_TAG = "color"
    }
}
