package net.aquamine.server.item

import net.kyori.adventure.key.Key
import net.kyori.adventure.nbt.api.BinaryTagHolder
import net.kyori.adventure.text.event.HoverEvent
import net.aquamine.api.item.ItemLike
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.item.meta.ItemMeta
import net.aquamine.api.item.meta.ItemMetaBuilder
import net.aquamine.server.item.meta.AbstractItemMeta
import net.aquamine.server.item.meta.AquaItemMeta
import net.aquamine.server.registry.AquaRegistries
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.ImmutableCompoundTag
import java.util.function.UnaryOperator

@JvmRecord
data class AquaItemStack(override val type: AquaItemType, override val amount: Int, override val meta: AbstractItemMeta<*>) : ItemStack {

    constructor(like: ItemLike) : this(like.asItem().downcast(), 1, AquaItemMeta.DEFAULT)

    fun save(tag: CompoundTag.Builder): CompoundTag.Builder = tag.apply {
        putString("id", type.key().asString())
        putInt("Count", amount)
        put("tag", meta.data)
    }

    fun save(): CompoundTag = save(ImmutableCompoundTag.builder()).build()

    fun isEmpty(): Boolean = type === ItemTypes.AIR || amount <= 0

    fun eq(type: ItemType): Boolean = this.type === type

    override fun <I : ItemMeta> meta(type: Class<I>): I? = if (type.isInstance(meta)) type.cast(meta) else null

    override fun withType(type: ItemType): AquaItemStack {
        if (type == this.type) return this
        return AquaItemStack(type.downcast(), amount, meta)
    }

    override fun withAmount(amount: Int): AquaItemStack {
        if (amount == this.amount) return this
        return AquaItemStack(type, amount, meta)
    }

    override fun grow(amount: Int): AquaItemStack = withAmount(this.amount + amount)

    override fun shrink(amount: Int): AquaItemStack = withAmount(this.amount - amount)

    override fun withMeta(meta: ItemMeta): AquaItemStack {
        if (meta == this.meta) return this
        return AquaItemStack(type, amount, meta as AbstractItemMeta<*>)
    }

    //override fun withMeta(builder: ItemMeta.Builder.() -> Unit): AquaItemStack = withMeta(ItemMeta.builder().apply(builder).build())

    override fun toBuilder(): Builder = Builder(this)

    override fun asHoverEvent(op: UnaryOperator<HoverEvent.ShowItem>): HoverEvent<HoverEvent.ShowItem> =
        HoverEvent.showItem(op.apply(HoverEvent.ShowItem.showItem(type.key(), amount, BinaryTagHolder.binaryTagHolder(meta.data.asString()))))

    class Builder() : ItemStack.Builder {

        private var type: AquaItemType = ItemTypes.AIR.get().downcast()
        private var amount = 1
        private var meta: AbstractItemMeta<*> = AquaItemMeta.DEFAULT

        constructor(item: AquaItemStack) : this() {
            type = item.type
            amount = item.amount
            meta = item.meta
        }

        override fun type(type: ItemType): Builder = apply { this.type = type.downcast() }

        override fun amount(amount: Int): Builder = apply {
            require(amount in 1..type.maximumStackSize) { "Item amount must be between 1 and ${type.maximumStackSize}, was $amount!" }
            this.amount = amount
        }

        override fun meta(meta: ItemMeta): Builder = apply { this.meta = meta.downcastBase() }

        inline fun meta(builder: ItemMeta.Builder.() -> Unit): Builder = meta(AquaItemMeta.Builder().apply(builder).build())

        @JvmName("metaGeneric")
        inline fun <B : ItemMetaBuilder<B, P>, reified P> meta(builder: B.() -> Unit): Builder where P : ItemMetaBuilder.Provider<B>, P : ItemMeta =
            meta(ItemFactory.builder(P::class.java).apply(builder).build())

        override fun build(): AquaItemStack {
            if (type == EMPTY.type && amount == EMPTY.amount && meta == EMPTY.meta) return EMPTY
            return AquaItemStack(type, amount, meta)
        }
    }

    object Factory : ItemStack.Factory {

        override fun builder(): Builder = Builder()

        override fun empty(): ItemStack = EMPTY
    }

    companion object {

        @JvmField
        val EMPTY: AquaItemStack = AquaItemStack(ItemTypes.AIR.get())

        @JvmStatic
        fun from(data: CompoundTag): AquaItemStack {
            val type = AquaRegistries.ITEM.get(Key.key(data.getString("id")))
            return AquaItemStack(type, data.getInt("Count"), ItemFactory.create(type, data.getCompound("tag")))
        }
    }
}
