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
    val DISPENSE_FAIL: RegistryReference<GameEvent> = of("dispense_fail")
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
    val ENTITY_INTERACT: RegistryReference<GameEvent> = of("entity_interact")
    @JvmField
    val ENTITY_PLACE: RegistryReference<GameEvent> = of("entity_place")
    @JvmField
    val ENTITY_ROAR: RegistryReference<GameEvent> = of("entity_roar")
    @JvmField
    val ENTITY_SHAKE: RegistryReference<GameEvent> = of("entity_shake")
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
    val PISTON_CONTRACT: RegistryReference<GameEvent> = of("piston_contract")
    @JvmField
    val PISTON_EXTEND: RegistryReference<GameEvent> = of("piston_extend")
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

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<GameEvent> = RegistryReference.of(Registries.GAME_EVENT, Key.key(name))
}
