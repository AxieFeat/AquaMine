package net.aquamine.server.item.data

import net.aquamine.api.effect.sound.SoundEvent

@JvmRecord
data class Instrument(val sound: SoundEvent, val useDuration: Int, val range: Float)
