package net.aquamine.api.world.damage.type

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference

/**
 * All the built-in vanilla damage sources.
 */
@Catalogue(DamageType::class)
object DamageTypes {

    @JvmField
    val IN_FIRE: RegistryReference<DamageType> = of("in_fire")
    @JvmField
    val LIGHTNING_BOLT: RegistryReference<DamageType> = of("lightning_bolt")
    @JvmField
    val ON_FIRE: RegistryReference<DamageType> = of("on_fire")
    @JvmField
    val LAVA: RegistryReference<DamageType> = of("lava")
    @JvmField
    val HOT_FLOOR: RegistryReference<DamageType> = of("hot_floor")
    @JvmField
    val SUFFOCATION: RegistryReference<DamageType> = of("suffocation")
    @JvmField
    val CRAMMING: RegistryReference<DamageType> = of("cramming")
    @JvmField
    val DROWNING: RegistryReference<DamageType> = of("drowning")
    @JvmField
    val STARVING: RegistryReference<DamageType> = of("starving")
    @JvmField
    val CACTUS: RegistryReference<DamageType> = of("cactus")
    @JvmField
    val FALL: RegistryReference<DamageType> = of("falling")
    @JvmField
    val FLY_INTO_WALL: RegistryReference<DamageType> = of("fly_into_wall")
    @JvmField
    val VOID: RegistryReference<DamageType> = of("void")
    @JvmField
    val GENERIC: RegistryReference<DamageType> = of("generic")
    @JvmField
    val MAGIC: RegistryReference<DamageType> = of("magic")
    @JvmField
    val WITHER: RegistryReference<DamageType> = of("wither")
    @JvmField
    val ANVIL: RegistryReference<DamageType> = of("anvil")
    @JvmField
    val FALLING_BLOCK: RegistryReference<DamageType> = of("falling_block")
    @JvmField
    val DRAGON_BREATH: RegistryReference<DamageType> = of("dragon_breath")
    @JvmField
    val DRY_OUT: RegistryReference<DamageType> = of("dry_out")
    @JvmField
    val SWEET_BERRY_BUSH: RegistryReference<DamageType> = of("sweet_berry_bush")
    @JvmField
    val FREEZING: RegistryReference<DamageType> = of("freezing")
    @JvmField
    val FALLING_STALACTITE: RegistryReference<DamageType> = of("falling_stalactite")
    @JvmField
    val STALAGMITE: RegistryReference<DamageType> = of("stalagmite")
    @JvmField
    val STING: RegistryReference<DamageType> = of("sting")
    @JvmField
    val GENERIC_MOB_ATTACK: RegistryReference<DamageType> = of("generic_mob_attack")
    @JvmField
    val PASSIVE_MOB_ATTACK: RegistryReference<DamageType> = of("passive_mob_attack")
    @JvmField
    val PROJECTILE_MOB_ATTACK: RegistryReference<DamageType> = of("indirect_mob_attack")
    @JvmField
    val PLAYER_ATTACK: RegistryReference<DamageType> = of("player_attack")
    @JvmField
    val ARROW: RegistryReference<DamageType> = of("arrow")
    @JvmField
    val TRIDENT: RegistryReference<DamageType> = of("trident")
    @JvmField
    val FIREWORKS: RegistryReference<DamageType> = of("fireworks")
    @JvmField
    val FIREBALL: RegistryReference<DamageType> = of("fireball")
    @JvmField
    val FIREBALL_ON_FIRE: RegistryReference<DamageType> = of("fireball_on_fire")
    @JvmField
    val WITHER_SKULL: RegistryReference<DamageType> = of("wither_skull")
    @JvmField
    val THROWN_PROJECTILE: RegistryReference<DamageType> = of("thrown_projectile")
    @JvmField
    val INDIRECT_MAGIC: RegistryReference<DamageType> = of("indirect_magic")
    @JvmField
    val THORNS: RegistryReference<DamageType> = of("thorns")
    @JvmField
    val EXPLOSION: RegistryReference<DamageType> = of("explosion")
    @JvmField
    val PLAYER_EXPLOSION: RegistryReference<DamageType> = of("player_explosion")
    @JvmField
    val SONIC_BOOM: RegistryReference<DamageType> = of("sonic_boom")
    @JvmField
    val BAD_RESPAWN_POINT: RegistryReference<DamageType> = of("bad_respawn_point")

    @JvmStatic
    private fun of(name: String): RegistryReference<DamageType> = RegistryReference.of(Registries.DAMAGE_TYPES, Key.key(name))
}
