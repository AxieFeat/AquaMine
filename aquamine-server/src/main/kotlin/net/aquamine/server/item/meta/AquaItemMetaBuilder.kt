package net.aquamine.server.item.meta

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.aquamine.api.block.Block
import net.aquamine.api.item.ItemAttributeModifier
import net.aquamine.api.item.data.ItemFlag
import net.aquamine.api.item.meta.ItemMeta
import net.aquamine.api.item.meta.ItemMetaBuilder
import net.aquamine.server.item.data.AquaItemAttributeModifier
import net.aquamine.server.item.mask
import net.aquamine.server.util.collection.BuilderCollection
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.buildCompound
import xyz.axie.nbt.compound
import xyz.axie.nbt.list

@Suppress("UNCHECKED_CAST")
abstract class AquaItemMetaBuilder<B : ItemMetaBuilder<B, I>, I : ItemMeta> : ItemMetaBuilder<B, I> {

    private var damage = 0
    private var unbreakable = false
    private var customModelData = 0
    private var name: Component? = null
    private var lore: MutableCollection<Component>
    private var hideFlags = 0
    private var canDestroy: MutableCollection<Block>
    private var canPlaceOn: MutableCollection<Block>
    private var modifiers: MutableCollection<ItemAttributeModifier>

    protected constructor() {
        lore = BuilderCollection()
        canDestroy = BuilderCollection()
        canPlaceOn = BuilderCollection()
        modifiers = BuilderCollection()
    }

    protected constructor(meta: AbstractItemMeta<*>) {
        damage = meta.damage
        unbreakable = meta.isUnbreakable
        customModelData = meta.customModelData
        name = meta.name
        lore = BuilderCollection(meta.lore)
        hideFlags = meta.hideFlags
        canDestroy = BuilderCollection(meta.canDestroy)
        canPlaceOn = BuilderCollection(meta.canPlaceOn)
        modifiers = BuilderCollection(meta.attributeModifiers)
    }

    final override fun damage(damage: Int): B = apply { this.damage = damage } as B

    final override fun unbreakable(value: Boolean): B = apply { unbreakable = value } as B

    final override fun customModelData(data: Int): B = apply { customModelData = data } as B

    final override fun name(name: Component?): B = apply { this.name = name } as B

    final override fun lore(lore: Collection<Component>): B = apply { this.lore = BuilderCollection(lore) } as B

    final override fun addLore(lore: Component): B = apply { this.lore.add(lore) } as B

    final override fun hideFlags(flags: Int): B = apply { hideFlags = flags } as B

    final override fun addFlag(flag: ItemFlag): B = apply { hideFlags = hideFlags or flag.mask() } as B

    final override fun canDestroy(blocks: Collection<Block>): B = apply { canDestroy = BuilderCollection(blocks) } as B

    final override fun addCanDestroy(block: Block): B = apply { canDestroy.add(block) } as B

    final override fun canPlaceOn(blocks: Collection<Block>): B = apply { canPlaceOn = BuilderCollection(blocks) } as B

    final override fun addCanPlaceOn(block: Block): B = apply { canPlaceOn.add(block) } as B

    final override fun attributeModifiers(modifiers: Collection<ItemAttributeModifier>): B =
        apply { this.modifiers = BuilderCollection(modifiers) } as B

    final override fun addAttributeModifier(modifier: ItemAttributeModifier): B = apply { modifiers.add(modifier) } as B

    protected open fun buildData(): CompoundTag.Builder = buildCompound {
        putInt("Damage", damage)
        putBoolean("Unbreakable", unbreakable)
        putInt("CustomModelData", customModelData)
        compound("display") {
            if (name != null) putString("Name", GsonComponentSerializer.gson().serialize(name!!))
            if (lore.isNotEmpty()) list("Lore") { lore.forEach { addString(GsonComponentSerializer.gson().serialize(it)) } }
        }
        putInt("HideFlags", hideFlags)
        if (canDestroy.isNotEmpty()) list("CanDestroy") { canDestroy.forEach { addString(it.key().asString()) } }
        if (canPlaceOn.isNotEmpty()) list("CanPlaceOn") { canPlaceOn.forEach { addString(it.key().asString()) } }
        if (modifiers.isNotEmpty()) list("AttributeModifiers") { modifiers.forEach { add(AquaItemAttributeModifier.save(it)) } }
    }
}
