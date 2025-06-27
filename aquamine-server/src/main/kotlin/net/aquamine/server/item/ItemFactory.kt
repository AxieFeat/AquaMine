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
import net.aquamine.server.item.meta.AquaBannerMeta
import net.aquamine.server.item.meta.AquaBundleMeta
import net.aquamine.server.item.meta.AquaCompassMeta
import net.aquamine.server.item.meta.AquaCrossbowMeta
import net.aquamine.server.item.meta.AquaFireworkRocketMeta
import net.aquamine.server.item.meta.AquaFireworkStarMeta
import net.aquamine.server.item.meta.AquaItemMeta
import net.aquamine.server.item.meta.AquaLeatherArmorMeta
import net.aquamine.server.item.meta.AquaPlayerHeadMeta
import net.aquamine.server.item.meta.AquaWritableBookMeta
import net.aquamine.server.item.meta.AquaWrittenBookMeta
import xyz.axie.nbt.CompoundTag
import java.util.function.Function
import java.util.function.Supplier

object ItemFactory {

    private val META_BY_TYPE: Map<ItemType, Function<CompoundTag, AbstractItemMeta<*>>> = mapOf(
        ItemTypes.WRITTEN_BOOK.get() to Function { AquaWrittenBookMeta(it) },
        ItemTypes.WRITABLE_BOOK.get() to Function { AquaWritableBookMeta(it) },
        ItemTypes.PLAYER_HEAD.get() to Function { AquaPlayerHeadMeta(it) },
        ItemTypes.LEATHER_HELMET.get() to Function { AquaLeatherArmorMeta(it) },
        ItemTypes.LEATHER_CHESTPLATE.get() to Function { AquaLeatherArmorMeta(it) },
        ItemTypes.LEATHER_LEGGINGS.get() to Function { AquaLeatherArmorMeta(it) },
        ItemTypes.LEATHER_BOOTS.get() to Function { AquaLeatherArmorMeta(it) },
        ItemTypes.LEATHER_HORSE_ARMOR.get() to Function { AquaLeatherArmorMeta(it) },
        ItemTypes.FIREWORK_ROCKET.get() to Function { AquaFireworkRocketMeta(it) },
        ItemTypes.FIREWORK_STAR.get() to Function { AquaFireworkStarMeta(it) },
        ItemTypes.CROSSBOW.get() to Function { AquaCrossbowMeta(it) },
        ItemTypes.COMPASS.get() to Function { AquaCompassMeta(it) },
        ItemTypes.BUNDLE.get() to Function { AquaBundleMeta(it) },
        ItemTypes.WHITE_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.ORANGE_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.MAGENTA_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.LIGHT_BLUE_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.YELLOW_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.LIME_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.PINK_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.GRAY_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.LIGHT_GRAY_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.CYAN_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.PURPLE_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.BLUE_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.BROWN_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.GREEN_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.RED_BANNER.get() to Function { AquaBannerMeta(it) },
        ItemTypes.BLACK_BANNER.get() to Function { AquaBannerMeta(it) }
    )
    private val BUILDERS_BY_TYPE: Map<Class<out ItemMetaBuilder.Provider<*>>, Supplier<ItemMetaBuilder<*, *>>> = mapOf(
        BannerMeta::class.java to Supplier { AquaBannerMeta.Builder() },
        BundleMeta::class.java to Supplier { AquaBundleMeta.Builder() },
        CompassMeta::class.java to Supplier { AquaCompassMeta.Builder() },
        CrossbowMeta::class.java to Supplier { AquaCrossbowMeta.Builder() },
        FireworkRocketMeta::class.java to Supplier { AquaFireworkRocketMeta.Builder() },
        FireworkStarMeta::class.java to Supplier { AquaFireworkStarMeta.Builder() },
        LeatherArmorMeta::class.java to Supplier { AquaLeatherArmorMeta.Builder() },
        PlayerHeadMeta::class.java to Supplier { AquaPlayerHeadMeta.Builder() },
        WritableBookMeta::class.java to Supplier { AquaWritableBookMeta.Builder() },
        WrittenBookMeta::class.java to Supplier { AquaWrittenBookMeta.Builder() }
    )

    @JvmStatic
    fun create(type: ItemType, data: CompoundTag): AbstractItemMeta<*> = META_BY_TYPE.get(type)?.apply(data) ?: AquaItemMeta(data)

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <B : ItemMetaBuilder<B, P>, P : ItemMetaBuilder.Provider<B>> builder(type: Class<P>): B = BUILDERS_BY_TYPE.get(type)?.get() as B
}
