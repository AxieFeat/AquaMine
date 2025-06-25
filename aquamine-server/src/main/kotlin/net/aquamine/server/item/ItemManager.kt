package net.aquamine.server.item

import net.aquamine.server.item.handler.ItemHandler
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.item.handler.DebugStickHandler
import net.aquamine.server.item.handler.FoodHandler
import net.aquamine.server.item.handler.SwordHandler
import net.aquamine.server.item.handler.TridentHandler

object ItemManager {

    private val handlers = HashMap<String, ItemHandler>()

    @JvmStatic
    fun bootstrap() {
        register(ItemTypes.WOODEN_SWORD.get(), SwordHandler)
        register(ItemTypes.STONE_SWORD.get(), SwordHandler)
        register(ItemTypes.GOLDEN_SWORD.get(), SwordHandler)
        register(ItemTypes.IRON_SWORD.get(), SwordHandler)
        register(ItemTypes.DIAMOND_SWORD.get(), SwordHandler)
        register(ItemTypes.NETHERITE_SWORD.get(), SwordHandler)
        register(ItemTypes.TRIDENT.get(), TridentHandler)
        register(ItemTypes.DEBUG_STICK.get(), DebugStickHandler)
        register(ItemTypes.COOKED_BEEF.get(), FoodHandler)
    }

    @JvmStatic
    fun handler(type: ItemType): ItemHandler? = handlers.get(type.key().asString())

    @JvmStatic
    fun register(type: ItemType, handler: ItemHandler) {
        handlers.put(type.key().asString(), handler)
    }
}
