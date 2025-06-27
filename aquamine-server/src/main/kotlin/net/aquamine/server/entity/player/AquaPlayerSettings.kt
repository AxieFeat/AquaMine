package net.aquamine.server.entity.player

import net.aquamine.api.entity.MainHand
import net.aquamine.api.entity.player.ChatVisibility
import net.aquamine.api.entity.player.PlayerSettings
import net.aquamine.api.entity.player.SkinParts
import java.util.Locale

@JvmRecord
data class AquaPlayerSettings(
    override val locale: Locale?,
    override val viewDistance: Int,
    override val chatVisibility: ChatVisibility,
    override val hasChatColors: Boolean,
    override val skinParts: SkinParts,
    override val mainHand: MainHand,
    val filterText: Boolean,
    override val allowsServerListing: Boolean
) : PlayerSettings {

    companion object {

        @JvmField
        val DEFAULT: AquaPlayerSettings =
            AquaPlayerSettings(null, 10, ChatVisibility.FULL, true, AquaSkinParts.ALL, MainHand.RIGHT, false, true)
    }
}
