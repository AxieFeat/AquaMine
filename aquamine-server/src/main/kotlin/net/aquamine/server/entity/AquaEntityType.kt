package net.aquamine.server.entity

import com.google.common.collect.ImmutableSet
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.aquamine.api.block.BlockState
import net.aquamine.api.entity.EntityCategory
import net.aquamine.api.entity.EntityType
import net.aquamine.api.tags.TagKey
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.Keys
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.isBurning
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.block.state.downcast
import javax.annotation.concurrent.Immutable

@Immutable
class AquaEntityType<out T : AquaEntity>(
    override val category: EntityCategory,
    val isSerializable: Boolean,
    override val isSummonable: Boolean,
    override val isImmuneToFire: Boolean,
    val canSpawnFarFromPlayer: Boolean,
    override val immuneTo: ImmutableSet<AquaBlock>,
    override val width: Float,
    override val height: Float,
    override val clientTrackingRange: Int,
    override val updateInterval: Int
) : EntityType<T> {

    private var descriptionId: String? = null
    private var description: Component? = null
    private var cachedLootTable: Key? = null

    private val builtInRegistryHolder = AquaRegistries.ENTITY_TYPE.createIntrusiveHolder(this)
    override val lootTable: Key
        get() {
            if (cachedLootTable == null) {
                val key = key()
                cachedLootTable = Key.key(key.namespace(), "entities/${key.value()}")
            }
            return cachedLootTable!!
        }

    override fun isImmuneTo(block: BlockState): Boolean = isImmuneTo(block.downcast())

    fun isImmuneTo(block: AquaBlockState): Boolean {
        // This is vanilla logic
        if (immuneTo.contains(block.block)) return true
        if (!isImmuneToFire && block.isBurning()) return false
        return block.eq(AquaBlocks.WITHER_ROSE) || block.eq(AquaBlocks.SWEET_BERRY_BUSH) || block.eq(AquaBlocks.CACTUS) ||
                block.eq(AquaBlocks.POWDER_SNOW)
    }

    override fun key(): Key = AquaRegistries.ENTITY_TYPE.getKey(this)

    override fun translationKey(): String {
        if (descriptionId == null) descriptionId = Keys.translation("entity", key())
        return descriptionId!!
    }

    fun description(): Component {
        if (description == null) description = Component.translatable(translationKey())
        return description!!
    }

    @Suppress("UNCHECKED_CAST")
    fun eq(tag: TagKey<EntityType<*>>): Boolean = builtInRegistryHolder.eq(tag as TagKey<AquaEntityType<*>>)

    class Builder<out T : AquaEntity>(private val category: EntityCategory) {

        private var immuneTo = ImmutableSet.of<AquaBlock>()
        private var serializable = true
        private var summonable = true
        private var fireImmune = false
        private var canSpawnFarFromPlayer = false
        private var clientTrackingRange = 5
        private var updateInterval = 3
        private var width = 0.6F
        private var height = 1.8F

        fun size(width: Float, height: Float): Builder<T> = apply {
            this.width = width
            this.height = height
        }

        fun notSummonable(): Builder<T> = apply { summonable = false }

        fun notSerializable(): Builder<T> = apply { serializable = false }

        fun fireImmune(): Builder<T> = apply { fireImmune = true }

        fun immuneTo(vararg blocks: AquaBlock): Builder<T> = apply { immuneTo = ImmutableSet.copyOf(blocks) }

        fun canSpawnFarFromPlayer(): Builder<T> = apply { canSpawnFarFromPlayer = true }

        fun clientTrackingRange(range: Int): Builder<T> = apply { clientTrackingRange = range }

        fun updateInterval(interval: Int): Builder<T> = apply { updateInterval = interval }

        fun build(): AquaEntityType<T> {
            return AquaEntityType(category, serializable, summonable, fireImmune, canSpawnFarFromPlayer, immuneTo, width, height,
                clientTrackingRange, updateInterval)
        }
    }
}
