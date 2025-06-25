package net.aquamine.server.item.meta

import net.aquamine.api.auth.GameProfile
import net.aquamine.api.item.meta.PlayerHeadMeta
import net.aquamine.server.auth.GameProfileUtil
import xyz.axie.nbt.CompoundTag

class KryptonPlayerHeadMeta(data: CompoundTag) : AbstractItemMeta<KryptonPlayerHeadMeta>(data), PlayerHeadMeta {

    override val owner: GameProfile? = GameProfileUtil.getProfile(data, OWNER_TAG)

    override fun copy(data: CompoundTag): KryptonPlayerHeadMeta = KryptonPlayerHeadMeta(data)

    override fun withOwner(owner: GameProfile?): KryptonPlayerHeadMeta = copy(GameProfileUtil.putProfile(data, OWNER_TAG, owner))

    override fun toBuilder(): PlayerHeadMeta.Builder = Builder(this)

    class Builder : KryptonItemMetaBuilder<PlayerHeadMeta.Builder, PlayerHeadMeta>, PlayerHeadMeta.Builder {

        private var owner: GameProfile? = null

        constructor() : super()

        constructor(meta: KryptonPlayerHeadMeta) : super(meta) {
            owner = meta.owner
        }

        override fun owner(owner: GameProfile?): PlayerHeadMeta.Builder = apply { this.owner = owner }

        override fun buildData(): CompoundTag.Builder = GameProfileUtil.putProfile(super.buildData(), OWNER_TAG, owner)

        override fun build(): KryptonPlayerHeadMeta = KryptonPlayerHeadMeta(buildData().build())
    }

    companion object {

        private const val OWNER_TAG = "SkullOwner"
    }
}
