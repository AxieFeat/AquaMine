package net.aquamine.api.world.gameevent

import net.aquamine.annotations.Catalogue
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
@Catalogue(GameEvent::class)
object GameEvents {

    // @formatter:off
    @JvmField
    val BLOCK_ACTIVATE: RegistryReference<GameEvent> = of("block_activate")
    @JvmField
    val BLOCK_ATTACH: RegistryReference<GameEvent> = of("block_attach")
    @JvmField
    val BLOCK_CHANGE: RegistryReference<GameEvent> = of("block_change")
    @JvmField
    val BLOCK_CLOSE: RegistryReference<GameEvent> = of("block_close")
    @JvmField
    val BLOCK_DEACTIVATE: RegistryReference<GameEvent> = of("block_deactivate")
    @JvmField
    val BLOCK_DESTROY: RegistryReference<GameEvent> = of("block_destroy")
    @JvmField
    val BLOCK_DETACH: RegistryReference<GameEvent> = of("block_detach")
    @JvmField
    val BLOCK_OPEN: RegistryReference<GameEvent> = of("block_open")
    @JvmField
    val BLOCK_PLACE: RegistryReference<GameEvent> = of("block_place")
    @JvmField
    val CONTAINER_CLOSE: RegistryReference<GameEvent> = of("container_close")
    @JvmField
    val CONTAINER_OPEN: RegistryReference<GameEvent> = of("container_open")
    @JvmField
    val DRINK: RegistryReference<GameEvent> = of("drink")
    @JvmField
    val EAT: RegistryReference<GameEvent> = of("eat")
    @JvmField
    val ELYTRA_GLIDE: RegistryReference<GameEvent> = of("elytra_glide")
    @JvmField
    val ENTITY_DAMAGE: RegistryReference<GameEvent> = of("entity_damage")
    @JvmField
    val ENTITY_DIE: RegistryReference<GameEvent> = of("entity_die")
    @JvmField
    val ENTITY_DISMOUNT: RegistryReference<GameEvent> = of("entity_dismount")
    @JvmField
    val ENTITY_INTERACT: RegistryReference<GameEvent> = of("entity_interact")
    @JvmField
    val ENTITY_MOUNT: RegistryReference<GameEvent> = of("entity_mount")
    @JvmField
    val ENTITY_PLACE: RegistryReference<GameEvent> = of("entity_place")
    @JvmField
    val ENTITY_ACTION: RegistryReference<GameEvent> = of("entity_action")
    @JvmField
    val EQUIP: RegistryReference<GameEvent> = of("equip")
    @JvmField
    val EXPLODE: RegistryReference<GameEvent> = of("explode")
    @JvmField
    val FLAP: RegistryReference<GameEvent> = of("flap")
    @JvmField
    val FLUID_PICKUP: RegistryReference<GameEvent> = of("fluid_pickup")
    @JvmField
    val FLUID_PLACE: RegistryReference<GameEvent> = of("fluid_place")
    @JvmField
    val HIT_GROUND: RegistryReference<GameEvent> = of("hit_ground")
    @JvmField
    val INSTRUMENT_PLAY: RegistryReference<GameEvent> = of("instrument_play")
    @JvmField
    val ITEM_INTERACT_FINISH: RegistryReference<GameEvent> = of("item_interact_finish")
    @JvmField
    val ITEM_INTERACT_START: RegistryReference<GameEvent> = of("item_interact_start")
    @JvmField
    val JUKEBOX_PLAY: RegistryReference<GameEvent> = of("jukebox_play")
    @JvmField
    val JUKEBOX_STOP_PLAY: RegistryReference<GameEvent> = of("jukebox_stop_play")
    @JvmField
    val LIGHTNING_STRIKE: RegistryReference<GameEvent> = of("lightning_strike")
    @JvmField
    val NOTE_BLOCK_PLAY: RegistryReference<GameEvent> = of("note_block_play")
    @JvmField
    val PRIME_FUSE: RegistryReference<GameEvent> = of("prime_fuse")
    @JvmField
    val PROJECTILE_LAND: RegistryReference<GameEvent> = of("projectile_land")
    @JvmField
    val PROJECTILE_SHOOT: RegistryReference<GameEvent> = of("projectile_shoot")
    @JvmField
    val SCULK_SENSOR_TENDRILS_CLICKING: RegistryReference<GameEvent> = of("sculk_sensor_tendrils_clicking")
    @JvmField
    val SHEAR: RegistryReference<GameEvent> = of("shear")
    @JvmField
    val SHRIEK: RegistryReference<GameEvent> = of("shriek")
    @JvmField
    val SPLASH: RegistryReference<GameEvent> = of("splash")
    @JvmField
    val STEP: RegistryReference<GameEvent> = of("step")
    @JvmField
    val SWIM: RegistryReference<GameEvent> = of("swim")
    @JvmField
    val TELEPORT: RegistryReference<GameEvent> = of("teleport")
    @JvmField
    val UNEQUIP: RegistryReference<GameEvent> = of("unequip")
    @JvmField
    val RESONATE_1: RegistryReference<GameEvent> = of("resonate_1")
    @JvmField
    val RESONATE_2: RegistryReference<GameEvent> = of("resonate_2")
    @JvmField
    val RESONATE_3: RegistryReference<GameEvent> = of("resonate_3")
    @JvmField
    val RESONATE_4: RegistryReference<GameEvent> = of("resonate_4")
    @JvmField
    val RESONATE_5: RegistryReference<GameEvent> = of("resonate_5")
    @JvmField
    val RESONATE_6: RegistryReference<GameEvent> = of("resonate_6")
    @JvmField
    val RESONATE_7: RegistryReference<GameEvent> = of("resonate_7")
    @JvmField
    val RESONATE_8: RegistryReference<GameEvent> = of("resonate_8")
    @JvmField
    val RESONATE_9: RegistryReference<GameEvent> = of("resonate_9")
    @JvmField
    val RESONATE_10: RegistryReference<GameEvent> = of("resonate_10")
    @JvmField
    val RESONATE_11: RegistryReference<GameEvent> = of("resonate_11")
    @JvmField
    val RESONATE_12: RegistryReference<GameEvent> = of("resonate_12")
    @JvmField
    val RESONATE_13: RegistryReference<GameEvent> = of("resonate_13")
    @JvmField
    val RESONATE_14: RegistryReference<GameEvent> = of("resonate_14")
    @JvmField
    val RESONATE_15: RegistryReference<GameEvent> = of("resonate_15")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<GameEvent> = RegistryReference.of(Registries.GAME_EVENT, Key.key(name))
}
