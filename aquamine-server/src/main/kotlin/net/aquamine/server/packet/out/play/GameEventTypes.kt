package net.aquamine.server.packet.out.play

object GameEventTypes {

    const val NO_RESPAWN_BLOCK_AVAILABLE: Byte = 0
    const val END_RAINING: Byte = 1
    const val BEGIN_RAINING: Byte = 2
    const val CHANGE_GAMEMODE: Byte = 3
    const val WIN_GAME: Byte = 4
    const val DEMO_EVENT: Byte = 5
    const val ARROW_HIT_PLAYER: Byte = 6
    const val RAIN_LEVEL_CHANGE: Byte = 7
    const val THUNDER_LEVEL_CHANGE: Byte = 8
    const val PLAY_PUFFERFISH_STING_SOUND: Byte = 9
    const val PLAY_ELDER_GUARDIAN_MOB_APPEARANCE: Byte = 10
    const val ENABLE_RESPAWN_SCREEN: Byte = 11
}
