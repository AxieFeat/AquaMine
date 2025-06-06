package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.MetaDsl
import net.kyori.adventure.text.Component
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable
import net.aquamine.api.AquaMine
import net.aquamine.api.block.Block
import net.aquamine.api.item.ItemAttributeModifier
import net.aquamine.api.item.data.ItemFlag
import net.aquamine.api.world.GameMode

/**
 * Holder for various item metadata values for an item stack.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
interface ItemMeta {

    /**
     * The current damage on the item.
     */
    val damage: Int

    /**
     * If the item cannot be broken.
     */
    val isUnbreakable: Boolean

    /**
     * The custom model data for the item.
     *
     * The meaning of this may vary, but it is usually used by resource packs
     * to determine what variant of an item should be displayed, in the case
     * of adding custom items to the game only using a resource pack.
     */
    val customModelData: Int

    /**
     * The display name of the item.
     *
     * If null, the default display name of the item will be used.
     */
    val name: Component?

    /**
     * The lore of the item.
     */
    val lore: @Unmodifiable List<Component>

    /**
     * The flags that determine what is hidden for the item.
     *
     * To check whether the item has a specific flag, use [hasFlag].
     */
    @get:JvmName("hideFlags")
    val hideFlags: Int

    /**
     * All the blocks that the item can destroy.
     *
     * In vanilla, this is only applicable when the holder of the item is in
     * [adventure mode][GameMode.ADVENTURE].
     *
     * If this is empty, there are no restrictions on what blocks the item can
     * destroy.
     */
    @get:JvmName("canDestroy")
    val canDestroy: @Unmodifiable Set<Block>

    /**
     * All the blocks that the item can be placed on.
     *
     * In vanilla, this is only applicable when the holder of the item is in
     * [adventure mode][GameMode.ADVENTURE].
     *
     * If this is empty, there are no restrictions on what blocks the item can
     * be placed on.
     */
    @get:JvmName("canPlaceOn")
    val canPlaceOn: @Unmodifiable Set<Block>

    /**
     * All the attribute modifiers that will be applied to entities holding
     * items with this metadata.
     */
    val attributeModifiers: @Unmodifiable List<ItemAttributeModifier>

    /**
     * Checks whether the item has the given item [flag].
     *
     * @param flag The flag.
     *
     * @return `true` if the item has the flag, `false` otherwise.
     */
    fun hasFlag(flag: ItemFlag): Boolean

    /**
     * Creates new item metadata with the given [damage].
     *
     * @param damage The new damage.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withDamage(damage: Int): ItemMeta

    /**
     * Creates new item metadata with the given [unbreakable] setting.
     *
     * @param unbreakable The new unbreakable setting.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withUnbreakable(unbreakable: Boolean): ItemMeta

    /**
     * Creates new item metadata with the given custom model [data].
     *
     * @param data The new custom model data.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withCustomModelData(data: Int): ItemMeta

    /**
     * Creates new item metadata with the given [name].
     *
     * @param name The new name.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withName(name: Component?): ItemMeta

    /**
     * Creates new item metadata with the given [lore].
     *
     * @param lore The new lore.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withLore(lore: List<Component>): ItemMeta

    /**
     * Creates new item metadata with the given [lore] line added to the bottom
     * of the lore text.
     *
     * @param lore The lore line to add.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withLore(lore: Component): ItemMeta

    /**
     * Creates new item metadata with the lore line at the given [index]
     * removed from the lore.
     *
     * @param index The index of the lore line to remove.
     *
     * @return New item metadata.
     *
     * @throws IllegalArgumentException if the index results in an out of
     * bounds exception, i.e., when it is too small or too big.
     */
    @Contract("_ -> new", pure = true)
    fun withoutLore(index: Int): ItemMeta

    /**
     * Creates new item metadata with the given [lore] line removed from the
     * lore.
     *
     * @param lore The lore line to remove.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutLore(lore: Component): ItemMeta

    /**
     * Creates new item metadata with the given hide [flags].
     *
     * @param flags The new hide flags.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withHideFlags(flags: Int): ItemMeta

    /**
     * Creates new item metadata with the given hide [flag] set.
     *
     * @param flag The flag to set.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withHideFlag(flag: ItemFlag): ItemMeta

    /**
     * Creates new item metadata without the given hide [flag] set.
     *
     * @param flag The flag to remove.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutHideFlag(flag: ItemFlag): ItemMeta

    /**
     * Creates new item metadata with the given can destroy [blocks].
     *
     * @param blocks The new blocks the item can destroy.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withCanDestroy(blocks: Collection<Block>): ItemMeta

    /**
     * Creates new item metadata with the given can destroy [blocks].
     *
     * @param blocks The new blocks the item can destroy.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withCanPlaceOn(blocks: Collection<Block>): ItemMeta

    /**
     * Creates new item metadata with the given attribute [modifiers].
     *
     * @param modifiers The new attribute modifiers.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withAttributeModifiers(modifiers: Collection<ItemAttributeModifier>): ItemMeta

    /**
     * Creates new item metadata without any modifiers.
     *
     * @return New item metadata.
     */
    @Contract("-> new", pure = true)
    fun withoutAttributeModifiers(): ItemMeta

    /**
     * Creates new item metadata with the given [modifier] added to the list of
     * modifiers for this item.
     *
     * @param modifier The modifier to add.
     *
     * @return New item metadata.
     */
    @Contract("_, _, _ -> new", pure = true)
    fun withAttributeModifier(modifier: ItemAttributeModifier): ItemMeta

    /**
     * Creates new item metadata with the given [modifier] removed from the
     * list of modifiers for this item.
     *
     * @param modifier The modifier to remove.
     *
     * @return New item metadata.
     */
    @Contract("_, _, _ -> new", pure = true)
    fun withoutAttributeModifier(modifier: ItemAttributeModifier): ItemMeta

    /**
     * A builder for building item metadata.
     */
    @MetaDsl
    interface Builder : ItemMetaBuilder<Builder, ItemMeta>

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(): Builder

        fun <B : ItemMetaBuilder<B, P>, P : ItemMetaBuilder.Provider<B>> builder(type: Class<P>): B
    }

    companion object {

        /**
         * Creates a new builder for building item metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = AquaMine.factory<Factory>().builder()

        @JvmSynthetic
        @PublishedApi
        internal fun <B : ItemMetaBuilder<B, P>, P : ItemMetaBuilder.Provider<B>> builder(type: Class<P>): B =
            AquaMine.factory<Factory>().builder(type)
    }
}
