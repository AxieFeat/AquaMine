package net.aquamine.server.item

import net.kyori.adventure.key.Key
import net.aquamine.api.block.Block
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.item.ItemRarity
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.tags.TagKey
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.Keys
import net.aquamine.server.world.block.AquaBlock

open class AquaItemType(
    override val rarity: ItemRarity,
    override val maximumStackSize: Int,
    override val durability: Int,
    override val isEdible: Boolean,
    override val isFireResistant: Boolean,
    override val eatingSound: SoundEvent,
    override val drinkingSound: SoundEvent
) : ItemType {

    private var descriptionId: String? = null
    private val builtInRegistryHolder = AquaRegistries.ITEM.createIntrusiveHolder(this)

    override val canBreak: Boolean
        get() = durability > 0

    override fun key(): Key = AquaRegistries.ITEM.getKey(this)

    override fun translationKey(): String = getOrCreateTranslationKey()

    protected fun getOrCreateTranslationKey(): String {
        if (descriptionId == null) descriptionId = Keys.translation("item", key())
        return descriptionId!!
    }

    override fun asBlock(): Block = AquaRegistries.BLOCK.get(key())

    @Suppress("UNCHECKED_CAST")
    fun eq(tag: TagKey<ItemType>): Boolean = builtInRegistryHolder.eq(tag as TagKey<AquaItemType>)

    companion object {

        @JvmField
        val BY_BLOCK: HashMap<AquaBlock, AquaItemType> = HashMap()

        @JvmStatic
        fun fromBlock(block: AquaBlock): AquaItemType = BY_BLOCK.getOrDefault(block, ItemTypes.AIR.get().downcast())
    }
}
