package net.aquamine.server.world.block.entity.banner

import net.kyori.adventure.key.Key
import net.aquamine.api.block.entity.banner.BannerPatternType

class AquaBannerPatternType(private val key: Key, override val code: String) : BannerPatternType {

    override fun key(): Key = key

    override fun toString(): String = "BannerPatternType(code=$code)"
}
