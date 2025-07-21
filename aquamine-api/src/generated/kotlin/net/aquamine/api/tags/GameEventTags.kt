package net.aquamine.api.tags

import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.world.gameevent.GameEvent
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
object GameEventTags {

    // @formatter:off
    @JvmField
    val VIBRATIONS: TagKey<GameEvent> = get("vibrations")

    @JvmField
    val WARDEN_CAN_LISTEN: TagKey<GameEvent> = get("warden_can_listen")

    @JvmField
    val SHRIEKER_CAN_LISTEN: TagKey<GameEvent> = get("shrieker_can_listen")

    @JvmField
    val IGNORE_VIBRATIONS_SNEAKING: TagKey<GameEvent> = get("ignore_vibrations_sneaking")

    @JvmField
    val ALLAY_CAN_LISTEN: TagKey<GameEvent> = get("allay_can_listen")


    // @formatter:on
    @JvmStatic
    private fun get(key: String): TagKey<GameEvent> = TagKey.of(ResourceKeys.GAME_EVENT, Key.key(key))
}
