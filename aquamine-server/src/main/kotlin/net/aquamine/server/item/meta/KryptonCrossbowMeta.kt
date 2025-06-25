package net.aquamine.server.item.meta

import com.google.common.collect.ImmutableList
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.meta.CrossbowMeta
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.item.downcast
import net.aquamine.server.util.collection.BuilderCollection
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.list

class KryptonCrossbowMeta(data: CompoundTag) : AbstractItemMeta<KryptonCrossbowMeta>(data), CrossbowMeta {

    override val isCharged: Boolean = data.getBoolean(CHARGED_TAG)
    override val projectiles: ImmutableList<ItemStack> =
        mapToList(data, PROJECTILES_TAG, CompoundTag.ID) { KryptonItemStack.from(it as CompoundTag) }

    override fun copy(data: CompoundTag): KryptonCrossbowMeta = KryptonCrossbowMeta(data)

    override fun withCharged(charged: Boolean): KryptonCrossbowMeta = copy(data.putBoolean(CHARGED_TAG, charged))

    override fun withProjectiles(projectiles: List<ItemStack>): KryptonCrossbowMeta =
        copy(put(data, PROJECTILES_TAG, projectiles) { it.downcast().save() })

    override fun withProjectile(projectile: ItemStack): KryptonCrossbowMeta =
        copy(data.update(PROJECTILES_TAG, CompoundTag.ID) { it.add(projectile.downcast().save()) })

    override fun withoutProjectile(index: Int): KryptonCrossbowMeta = copy(data.update(PROJECTILES_TAG, CompoundTag.ID) { it.remove(index) })

    override fun withoutProjectile(projectile: ItemStack): KryptonCrossbowMeta =
        copy(data.update(PROJECTILES_TAG, CompoundTag.ID) { it.remove(projectile.downcast().save()) })

    override fun toBuilder(): CrossbowMeta.Builder = Builder(this)

    class Builder : KryptonItemMetaBuilder<CrossbowMeta.Builder, CrossbowMeta>, CrossbowMeta.Builder {

        private var charged = false
        private var projectiles: MutableCollection<ItemStack>

        constructor() : super() {
            projectiles = BuilderCollection()
        }

        constructor(meta: KryptonCrossbowMeta) : super(meta) {
            charged = meta.isCharged
            projectiles = BuilderCollection(meta.projectiles)
        }

        override fun charged(value: Boolean): Builder = apply { charged = value }

        override fun projectiles(projectiles: Collection<ItemStack>): Builder = apply { this.projectiles = BuilderCollection(projectiles) }

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            putBoolean(CHARGED_TAG, charged)
            if (projectiles.isNotEmpty()) list(PROJECTILES_TAG) { projectiles.forEach { add(it.downcast().save()) } }
        }

        override fun build(): KryptonCrossbowMeta = KryptonCrossbowMeta(buildData().build())
    }

    companion object {

        private const val CHARGED_TAG = "Charged"
        private const val PROJECTILES_TAG = "ChargedProjectiles"
    }
}
