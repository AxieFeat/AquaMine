package net.aquamine.server.item.meta

import net.aquamine.api.item.meta.CompassMeta
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.World
import net.aquamine.server.util.nbt.getBlockPos
import net.aquamine.server.util.nbt.getNullableCompound
import net.aquamine.server.util.nbt.putBlockPos
import net.aquamine.server.util.serialization.Codecs
import xyz.axie.nbt.CompoundTag
import org.kryptonmc.serialization.nbt.NbtOps

class KryptonCompassMeta(data: CompoundTag) : AbstractItemMeta<KryptonCompassMeta>(data), CompassMeta {

    override val isTrackingLodestone: Boolean = data.getBoolean(TRACKED_TAG)
    override val lodestoneDimension: ResourceKey<World>? = Codecs.DIMENSION.read(data.get(DIMENSION_TAG), NbtOps.INSTANCE).result().orElse(null)
    override val lodestonePosition: Vec3i? = data.getNullableCompound(POS_TAG)?.getBlockPos()

    override fun copy(data: CompoundTag): KryptonCompassMeta = KryptonCompassMeta(data)

    override fun withLodestone(dimension: ResourceKey<World>, position: Vec3i): KryptonCompassMeta =
        copy(data.putBoolean(TRACKED_TAG, true).putString(DIMENSION_TAG, dimension.location.asString()).putBlockPos(POS_TAG, position))

    override fun withoutLodestone(): KryptonCompassMeta = copy(CompoundTag.EMPTY)

    override fun toBuilder(): CompassMeta.Builder = Builder(this)

    class Builder : KryptonItemMetaBuilder<CompassMeta.Builder, CompassMeta>, CompassMeta.Builder {

        private var tracking = false
        private var dimension: ResourceKey<World>? = null
        private var position: Vec3i? = null

        constructor() : super()

        constructor(meta: KryptonCompassMeta) : super(meta) {
            tracking = meta.isTrackingLodestone
            dimension = meta.lodestoneDimension
            position = meta.lodestonePosition
        }

        override fun lodestone(dimension: ResourceKey<World>, position: Vec3i): Builder = apply {
            tracking = true
            this.dimension = dimension
            this.position = position
        }

        override fun buildData(): CompoundTag.Builder = super.buildData().apply {
            putBoolean(TRACKED_TAG, tracking)
            if (dimension != null) putString(DIMENSION_TAG, dimension!!.location.asString())
            if (position != null) putBlockPos(POS_TAG, position!!)
        }

        override fun build(): KryptonCompassMeta = KryptonCompassMeta(buildData().build())
    }

    companion object {

        private const val TRACKED_TAG = "LodestoneTracked"
        private const val DIMENSION_TAG = "LodestoneDimension"
        private const val POS_TAG = "LodestonePos"
    }
}
