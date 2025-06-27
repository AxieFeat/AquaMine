package net.aquamine.server.effect.sound

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.server.util.downcastApiType

fun SoundEvent.downcast(): AquaSoundEvent = downcastApiType("SoundEvent")
