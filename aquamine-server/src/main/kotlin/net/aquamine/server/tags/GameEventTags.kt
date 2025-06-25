package net.aquamine.server.tags

import net.kyori.adventure.key.Key
import net.aquamine.api.tags.TagKey
import net.aquamine.server.resource.KryptonResourceKeys
import net.aquamine.server.world.gameevent.GameEvent

object GameEventTags {

    @JvmField
    val VIBRATIONS: TagKey<GameEvent> = create("vibrations")
    @JvmField
    val IGNORE_VIBRATIONS_SNEAKING: TagKey<GameEvent> = create("ignore_vibrations_sneaking")

    @JvmStatic
    private fun create(name: String): TagKey<GameEvent> = KryptonTagKey.of(KryptonResourceKeys.GAME_EVENT, Key.key(name))
}
