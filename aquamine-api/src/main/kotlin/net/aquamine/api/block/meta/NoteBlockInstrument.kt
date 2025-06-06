package net.aquamine.api.block.meta

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.registry.RegistryReference

/**
 * Indicates the instrument that a note block this property is applied to will
 * play when it is attacked or a redstone signal is applied to it.
 *
 * @param sound The corresponding sound event that will be played when note blocks with this instrument are interacted with.
 * @param type The type of instrument this instrument is.
 */
enum class NoteBlockInstrument(
    val sound: RegistryReference<SoundEvent>,
    val type: Type
) {

    HARP(SoundEvents.NOTE_BLOCK_HARP, Type.BLOCK),
    BASEDRUM(SoundEvents.NOTE_BLOCK_BASEDRUM, Type.BLOCK),
    SNARE(SoundEvents.NOTE_BLOCK_SNARE, Type.BLOCK),
    HAT(SoundEvents.NOTE_BLOCK_HAT, Type.BLOCK),
    BASS(SoundEvents.NOTE_BLOCK_BASS, Type.BLOCK),
    FLUTE(SoundEvents.NOTE_BLOCK_FLUTE, Type.BLOCK),
    BELL(SoundEvents.NOTE_BLOCK_BELL, Type.BLOCK),
    GUITAR(SoundEvents.NOTE_BLOCK_GUITAR, Type.BLOCK),
    CHIME(SoundEvents.NOTE_BLOCK_CHIME, Type.BLOCK),
    XYLOPHONE(SoundEvents.NOTE_BLOCK_XYLOPHONE, Type.BLOCK),
    IRON_XYLOPHONE(SoundEvents.NOTE_BLOCK_IRON_XYLOPHONE, Type.BLOCK),
    COW_BELL(SoundEvents.NOTE_BLOCK_COW_BELL, Type.BLOCK),
    DIDGERIDOO(SoundEvents.NOTE_BLOCK_DIDGERIDOO, Type.BLOCK),
    BIT(SoundEvents.NOTE_BLOCK_BIT, Type.BLOCK),
    BANJO(SoundEvents.NOTE_BLOCK_BANJO, Type.BLOCK),
    PLING(SoundEvents.NOTE_BLOCK_PLING, Type.BLOCK),
    ZOMBIE(SoundEvents.NOTE_BLOCK_IMITATE_ZOMBIE, Type.MOB_HEAD),
    SKELETON(SoundEvents.NOTE_BLOCK_IMITATE_SKELETON, Type.MOB_HEAD),
    CREEPER(SoundEvents.NOTE_BLOCK_IMITATE_CREEPER, Type.MOB_HEAD),
    DRAGON(SoundEvents.NOTE_BLOCK_IMITATE_ENDER_DRAGON, Type.MOB_HEAD),
    WITHER_SKELETON(SoundEvents.NOTE_BLOCK_IMITATE_WITHER_SKELETON, Type.MOB_HEAD),
    PIGLIN(SoundEvents.NOTE_BLOCK_IMITATE_PIGLIN, Type.MOB_HEAD),
    CUSTOM_HEAD(SoundEvents.UI_BUTTON_CLICK, Type.CUSTOM);

    /**
     * The type of note block instrument.
     */
    enum class Type {

        BLOCK,
        MOB_HEAD,
        CUSTOM
    }
}
