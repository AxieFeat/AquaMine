package net.aquamine.server.world.block.data

import net.aquamine.api.block.BlockSoundGroup
import net.aquamine.api.effect.sound.SoundEvent

@JvmRecord
data class KryptonBlockSoundGroup(
    override val volume: Float,
    override val pitch: Float,
    override val breakSound: SoundEvent,
    override val stepSound: SoundEvent,
    override val placeSound: SoundEvent,
    override val hitSound: SoundEvent,
    override val fallSound: SoundEvent
) : BlockSoundGroup
