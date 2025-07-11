package net.aquamine.api.block.meta

import net.kyori.adventure.translation.Translatable

/**
 * Indicates the mode that a structure block this property is applied to is in.
 *
 * The mode of a structure block influences its behavior. For example, in load
 * mode, the structure block will load a saved structure. In save mode, it will
 * save a captured structure.
 */
enum class StructureMode : Translatable {

    /**
     * In this mode, a structure block can load a saved structure in to the
     * world.
     */
    LOAD,

    /**
     * In this mode, a structure block can save a captured structure for use
     * later.
     */
    SAVE,

    /**
     * In this mode, a structure block will act as a marker for the opposing
     * corner of a structure block in save mode.
     *
     * This is used to make capturing structures easier, and ensure that no
     * unwanted part of a structure is included in the save.
     */
    CORNER,

    /**
     * In this mode, a structure block will mark the location of a function to
     * run, specified by its metadata input. This only works for specific
     * structures and only during natural generation.
     */
    DATA;

    override fun translationKey(): String = "structure_block.mode_info.${name.lowercase()}"
}
