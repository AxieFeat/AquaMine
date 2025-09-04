package net.aquamine.api.tags

import net.aquamine.api.block.entity.banner.BannerPatternType
import net.aquamine.api.resource.ResourceKeys
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
object BannerPatternTags {

    // @formatter:off
    @JvmField
    val NO_ITEM_REQUIRED: TagKey<BannerPatternType> = get("no_item_required")

    @JvmField
    val PATTERN_ITEM_FLOWER: TagKey<BannerPatternType> = get("pattern_item/flower")

    @JvmField
    val PATTERN_ITEM_CREEPER: TagKey<BannerPatternType> = get("pattern_item/creeper")

    @JvmField
    val PATTERN_ITEM_SKULL: TagKey<BannerPatternType> = get("pattern_item/skull")

    @JvmField
    val PATTERN_ITEM_MOJANG: TagKey<BannerPatternType> = get("pattern_item/mojang")

    @JvmField
    val PATTERN_ITEM_GLOBE: TagKey<BannerPatternType> = get("pattern_item/globe")

    @JvmField
    val PATTERN_ITEM_PIGLIN: TagKey<BannerPatternType> = get("pattern_item/piglin")

    @JvmField
    val PATTERN_ITEM_FLOW: TagKey<BannerPatternType> = get("pattern_item/flow")

    @JvmField
    val PATTERN_ITEM_GUSTER: TagKey<BannerPatternType> = get("pattern_item/guster")

    @JvmField
    val PATTERN_ITEM_FIELD_MASONED: TagKey<BannerPatternType> =
            get("pattern_item/field_masoned")

    @JvmField
    val PATTERN_ITEM_BORDURE_INDENTED: TagKey<BannerPatternType> =
            get("pattern_item/bordure_indented")


    // @formatter:on
    @JvmStatic
    private fun get(key: String): TagKey<BannerPatternType> = TagKey.of(ResourceKeys.BANNER_PATTERN, Key.key(key))
}
