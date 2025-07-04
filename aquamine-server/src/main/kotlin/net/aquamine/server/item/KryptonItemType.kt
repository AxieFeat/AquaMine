package net.aquamine.server.item

import net.kyori.adventure.key.Key
import net.aquamine.api.block.Block
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.item.ItemRarity
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.tags.TagKey
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.util.Keys
import net.aquamine.server.world.block.KryptonBlock

@Suppress("LeakingThis") // The 'leak' doesn't need any of the data that wouldn't be initialized, it's just used as a key, so it's fine.
open class KryptonItemType(
    override val rarity: ItemRarity,
    override val maximumStackSize: Int,
    override val durability: Int,
    override val isEdible: Boolean,
    override val isFireResistant: Boolean,
    override val eatingSound: SoundEvent,
    override val drinkingSound: SoundEvent
) : ItemType {

    private var descriptionId: String? = null
    private val builtInRegistryHolder = KryptonRegistries.ITEM.createIntrusiveHolder(this)

    override val canBreak: Boolean
        get() = durability > 0

    override fun key(): Key = KryptonRegistries.ITEM.getKey(this)

    override fun translationKey(): String = getOrCreateTranslationKey()

    protected fun getOrCreateTranslationKey(): String {
        if (descriptionId == null) descriptionId = Keys.translation("item", key())
        return descriptionId!!
    }

    override fun asBlock(): Block = KryptonRegistries.BLOCK.get(key())

    @Suppress("UNCHECKED_CAST")
    fun eq(tag: TagKey<ItemType>): Boolean = builtInRegistryHolder.eq(tag as TagKey<KryptonItemType>)

    companion object {

        @JvmField
        val BY_BLOCK: HashMap<KryptonBlock, KryptonItemType> = HashMap()

        @JvmStatic
        fun fromBlock(block: KryptonBlock): KryptonItemType = BY_BLOCK.getOrDefault(block, ItemTypes.AIR.get().downcast())
    }
}
