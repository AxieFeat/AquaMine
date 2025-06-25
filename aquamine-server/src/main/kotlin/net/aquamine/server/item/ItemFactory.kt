package net.aquamine.server.item

import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.item.meta.BannerMeta
import net.aquamine.api.item.meta.BundleMeta
import net.aquamine.api.item.meta.CompassMeta
import net.aquamine.api.item.meta.CrossbowMeta
import net.aquamine.api.item.meta.FireworkRocketMeta
import net.aquamine.api.item.meta.FireworkStarMeta
import net.aquamine.api.item.meta.ItemMetaBuilder
import net.aquamine.api.item.meta.LeatherArmorMeta
import net.aquamine.api.item.meta.PlayerHeadMeta
import net.aquamine.api.item.meta.WritableBookMeta
import net.aquamine.api.item.meta.WrittenBookMeta
import net.aquamine.server.item.meta.AbstractItemMeta
import net.aquamine.server.item.meta.KryptonBannerMeta
import net.aquamine.server.item.meta.KryptonBundleMeta
import net.aquamine.server.item.meta.KryptonCompassMeta
import net.aquamine.server.item.meta.KryptonCrossbowMeta
import net.aquamine.server.item.meta.KryptonFireworkRocketMeta
import net.aquamine.server.item.meta.KryptonFireworkStarMeta
import net.aquamine.server.item.meta.KryptonItemMeta
import net.aquamine.server.item.meta.KryptonLeatherArmorMeta
import net.aquamine.server.item.meta.KryptonPlayerHeadMeta
import net.aquamine.server.item.meta.KryptonWritableBookMeta
import net.aquamine.server.item.meta.KryptonWrittenBookMeta
import xyz.axie.nbt.CompoundTag
import java.util.function.Function
import java.util.function.Supplier

object ItemFactory {

    private val META_BY_TYPE: Map<ItemType, Function<CompoundTag, AbstractItemMeta<*>>> = mapOf(
        ItemTypes.WRITTEN_BOOK.get() to Function { KryptonWrittenBookMeta(it) },
        ItemTypes.WRITABLE_BOOK.get() to Function { KryptonWritableBookMeta(it) },
        ItemTypes.PLAYER_HEAD.get() to Function { KryptonPlayerHeadMeta(it) },
        ItemTypes.LEATHER_HELMET.get() to Function { KryptonLeatherArmorMeta(it) },
        ItemTypes.LEATHER_CHESTPLATE.get() to Function { KryptonLeatherArmorMeta(it) },
        ItemTypes.LEATHER_LEGGINGS.get() to Function { KryptonLeatherArmorMeta(it) },
        ItemTypes.LEATHER_BOOTS.get() to Function { KryptonLeatherArmorMeta(it) },
        ItemTypes.LEATHER_HORSE_ARMOR.get() to Function { KryptonLeatherArmorMeta(it) },
        ItemTypes.FIREWORK_ROCKET.get() to Function { KryptonFireworkRocketMeta(it) },
        ItemTypes.FIREWORK_STAR.get() to Function { KryptonFireworkStarMeta(it) },
        ItemTypes.CROSSBOW.get() to Function { KryptonCrossbowMeta(it) },
        ItemTypes.COMPASS.get() to Function { KryptonCompassMeta(it) },
        ItemTypes.BUNDLE.get() to Function { KryptonBundleMeta(it) },
        ItemTypes.WHITE_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.ORANGE_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.MAGENTA_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.LIGHT_BLUE_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.YELLOW_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.LIME_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.PINK_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.GRAY_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.LIGHT_GRAY_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.CYAN_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.PURPLE_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.BLUE_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.BROWN_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.GREEN_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.RED_BANNER.get() to Function { KryptonBannerMeta(it) },
        ItemTypes.BLACK_BANNER.get() to Function { KryptonBannerMeta(it) }
    )
    private val BUILDERS_BY_TYPE: Map<Class<out ItemMetaBuilder.Provider<*>>, Supplier<ItemMetaBuilder<*, *>>> = mapOf(
        BannerMeta::class.java to Supplier { KryptonBannerMeta.Builder() },
        BundleMeta::class.java to Supplier { KryptonBundleMeta.Builder() },
        CompassMeta::class.java to Supplier { KryptonCompassMeta.Builder() },
        CrossbowMeta::class.java to Supplier { KryptonCrossbowMeta.Builder() },
        FireworkRocketMeta::class.java to Supplier { KryptonFireworkRocketMeta.Builder() },
        FireworkStarMeta::class.java to Supplier { KryptonFireworkStarMeta.Builder() },
        LeatherArmorMeta::class.java to Supplier { KryptonLeatherArmorMeta.Builder() },
        PlayerHeadMeta::class.java to Supplier { KryptonPlayerHeadMeta.Builder() },
        WritableBookMeta::class.java to Supplier { KryptonWritableBookMeta.Builder() },
        WrittenBookMeta::class.java to Supplier { KryptonWrittenBookMeta.Builder() }
    )

    @JvmStatic
    fun create(type: ItemType, data: CompoundTag): AbstractItemMeta<*> = META_BY_TYPE.get(type)?.apply(data) ?: KryptonItemMeta(data)

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <B : ItemMetaBuilder<B, P>, P : ItemMetaBuilder.Provider<B>> builder(type: Class<P>): B = BUILDERS_BY_TYPE.get(type)?.get() as B
}
