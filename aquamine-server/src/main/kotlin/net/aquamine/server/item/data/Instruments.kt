package net.aquamine.server.item.data

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.registry.RegistryReference
import net.aquamine.server.registry.KryptonRegistries

object Instruments {

    @JvmField
    val PONDER_GOAT_HORN: Instrument = register("ponder_goat_horn", SoundEvents.GOAT_HORN_0)
    @JvmField
    val SING_GOAT_HORN: Instrument = register("sing_goat_horn", SoundEvents.GOAT_HORN_1)
    @JvmField
    val SEEK_GOAT_HORN: Instrument = register("seek_goat_horn", SoundEvents.GOAT_HORN_2)
    @JvmField
    val FEEL_GOAT_HORN: Instrument = register("feel_goat_horn", SoundEvents.GOAT_HORN_3)
    @JvmField
    val ADMIRE_GOAT_HORN: Instrument = register("admire_goat_horn", SoundEvents.GOAT_HORN_4)
    @JvmField
    val CALL_GOAT_HORN: Instrument = register("call_goat_horn", SoundEvents.GOAT_HORN_5)
    @JvmField
    val YEARN_GOAT_HORN: Instrument = register("yearn_goat_horn", SoundEvents.GOAT_HORN_6)
    @JvmField
    val DREAM_GOAT_HORN: Instrument = register("dream_goat_horn", SoundEvents.GOAT_HORN_7)

    @JvmStatic
    @Suppress("MagicNumber")
    private fun register(name: String, sound: RegistryReference<SoundEvent>): Instrument =
        KryptonRegistries.register(KryptonRegistries.INSTRUMENT, Key.key(name), Instrument(sound.get(), 140, 256F))
}
