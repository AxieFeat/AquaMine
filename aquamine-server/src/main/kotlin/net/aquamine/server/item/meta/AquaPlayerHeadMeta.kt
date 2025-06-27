package net.aquamine.server.item.meta

import net.aquamine.api.auth.GameProfile
import net.aquamine.api.item.meta.PlayerHeadMeta
import net.aquamine.server.auth.GameProfileUtil
import xyz.axie.nbt.CompoundTag

class AquaPlayerHeadMeta(data: CompoundTag) : AbstractItemMeta<AquaPlayerHeadMeta>(data), PlayerHeadMeta {

    override val owner: GameProfile? = GameProfileUtil.getProfile(data, OWNER_TAG)

    override fun copy(data: CompoundTag): AquaPlayerHeadMeta = AquaPlayerHeadMeta(data)

    override fun withOwner(owner: GameProfile?): AquaPlayerHeadMeta = copy(GameProfileUtil.putProfile(data, OWNER_TAG, owner))

    override fun toBuilder(): PlayerHeadMeta.Builder = Builder(this)

    class Builder : AquaItemMetaBuilder<PlayerHeadMeta.Builder, PlayerHeadMeta>, PlayerHeadMeta.Builder {

        private var owner: GameProfile? = null

        constructor() : super()

        constructor(meta: AquaPlayerHeadMeta) : super(meta) {
            owner = meta.owner
        }

        override fun owner(owner: GameProfile?): PlayerHeadMeta.Builder = apply { this.owner = owner }

        override fun buildData(): CompoundTag.Builder = GameProfileUtil.putProfile(super.buildData(), OWNER_TAG, owner)

        override fun build(): AquaPlayerHeadMeta = AquaPlayerHeadMeta(buildData().build())
    }

    companion object {

        private const val OWNER_TAG = "SkullOwner"
    }
}
